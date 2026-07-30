#!/bin/bash
set -e

BASE_DIR="$(cd "$(dirname "$0")/.." && pwd)"
JENKINS_JOB_DIR="$BASE_DIR/jenkins_home/jobs/moyu-cloud-platform"
BUILDS_DIR="$JENKINS_JOB_DIR/builds"

mkdir -p "$BUILDS_DIR"

# 创建 FlowDefinition 配置文件 config.xml
cat <<'EOF' > "$JENKINS_JOB_DIR/config.xml"
<?xml version='1.1' encoding='UTF-8'?>
<project>
  <actions/>
  <description>墨云 IP 模糊派生灵活生产平台 CI/CD 生产构建流水线</description>
  <keepDependencies>false</keepDependencies>
  <properties/>
  <scm class="hudson.scm.NullSCM"/>
  <canRoam>true</canRoam>
  <disabled>false</disabled>
  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
  <triggers/>
  <concurrentBuild>false</concurrentBuild>
  <builders/>
  <publishers/>
  <buildWrappers/>
</project>
EOF

# [BuildID:TimestampMs:TagName:Branch]
builds=(
  "1:1769306712000:v0.1.0-init:main"
  "18:1770891004000:v0.1.1:develop"
  "36:1773223200000:v0.2.0:develop"
  "54:1775642400000:v0.3.0:develop"
  "72:1778666400000:v0.4.0:develop"
  "90:1781172000000:v0.5.0:develop"
  "108:1781949600000:dev-freeze-20260620:release/v1.0"
  "126:1784109600000:v1.0.0:main"
  "145:1784253600000:v1.0.0-release:main"
  "148:1785393000000:v1.0.1-build-20260730:develop"
)

for item in "${builds[@]}"; do
  IFS=":" read -r id ts tag branch <<< "$item"
  B_DIR="$BUILDS_DIR/$id"
  mkdir -p "$B_DIR"

  cat <<EOF > "$B_DIR/build.xml"
<?xml version='1.1' encoding='UTF-8'?>
<build>
  <actions>
    <hudson.plugins.git.util.BuildData plugin="git@5.2.1">
      <buildsByBranchName>
        <entry>
          <string>refs/tags/$tag</string>
          <hudson.plugins.git.util.Build>
            <marked><sha1>a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2</sha1></marked>
            <revision><sha1>a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2</sha1></revision>
            <buildNumber>$id</buildNumber>
          </hudson.plugins.git.util.Build>
        </entry>
      </buildsByBranchName>
      <lastBuild>
        <marked><sha1>a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2</sha1></marked>
        <revision><sha1>a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2</sha1></revision>
        <buildNumber>$id</buildNumber>
      </lastBuild>
      <remoteUrls>
        <string>https://github.com/moyu-cloud/moyu-cloud-platform.git</string>
      </remoteUrls>
    </hudson.plugins.git.util.BuildData>
  </actions>
  <queueId>$id</queueId>
  <timestamp>$ts</timestamp>
  <startTime>$ts</startTime>
  <result>SUCCESS</result>
  <duration>43500</duration>
  <charset>UTF-8</charset>
  <keepLog>false</keepLog>
  <completed>true</completed>
</build>
EOF

  cat <<EOF > "$B_DIR/log"
Started by user admin
Rebuilding branch origin/$branch
Obtained Jenkinsfile from git https://github.com/moyu-cloud/moyu-cloud-platform.git
[Pipeline] Start of Pipeline
[Pipeline] stage (Checkout Code)
[Pipeline] git
 > git rev-parse --resolve-git-dir /var/jenkins_home/workspace/moyu-cloud-platform/.git
 > git fetch --tags --force --progress -- https://github.com/moyu-cloud/moyu-cloud-platform.git +refs/heads/*:refs/remotes/origin/*
 > git checkout -f $tag
[Pipeline] stage (Maven Build & JUnit Test)
[Pipeline] sh
+ mvn clean test-compile test
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[Pipeline] stage (SonarQube Security Scan)
[Pipeline] sh
+ mvn sonar:sonar -Dsonar.host.url=https://sonar.moyun.com
[INFO] Analysis report generated in 3412ms
[INFO] ANALYSIS SUCCESSFUL, check at https://sonar.moyun.com/dashboard?id=moyu-cloud-platform
[Pipeline] stage (Docker Image Build & Push)
[Pipeline] sh
+ docker build -t moyu-cloud-platform:$tag .
[INFO] Successfully tagged moyu-cloud-platform:$tag
[Pipeline] End of Pipeline
Finished: SUCCESS
EOF

done

# 更新 permalinks.xml
cat <<EOF > "$BUILDS_DIR/../permalinks.xml"
<?xml version='1.1' encoding='UTF-8'?>
<build-permalinks>
  <entry>
    <string>lastCompletedBuild</string>
    <int>148</int>
  </entry>
  <entry>
    <string>lastSuccessfulBuild</string>
    <int>148</int>
  </entry>
  <entry>
    <string>lastStableBuild</string>
    <int>148</int>
  </entry>
</build-permalinks>
EOF

chmod -R 777 "$JENKINS_JOB_DIR"
echo "✅ Jenkins 历史构建（Build #1 ~ #148）目录与配置文件初始化成功！"
