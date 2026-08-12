# 墨云·IP模糊派生灵活生产平台 (Moyu Cloud Platform)

> **平台定位**：面向 IP 衍生品定制行业的智能特征识别、柔性匹配与打样协同平台（代号：Moyu Cloud / 墨云）。平台支持 2D/3D 矢量设计稿解析与模糊派生匹配，通过 AI 算法将 IP 元素映射至毛绒玩具、陶瓷马克杯、亚克力立牌等多种衍生产品，并自动撮合最优柔性生产工厂，实现打样与供应链节点全流程实时追踪。

---

## 核心工程架构与 Git 分支模型

### Git 分支规范与标签计划 (根据版本管理时间表)
- **`main`**: 生产受保护分支，包含版本基线 Tag `v0.1.0-init` 与正式发布 Tag `v1.0.0-release`。
- **`develop`**: 开发受保护集成分支，每日进行 SonarQube 全量基线扫描与 JUnit 测试（标签 `v0.1.1` ~ `v0.5.0`，`dev-freeze-20260620`）。
- **`release/v1.0`**: 发布候选分支，仅接收缺陷修复（标签 `v1.0.0-rc1`, `v1.0.0-rc2`, `v1.0.0-rc3`）。
- **`feature/*`**: 各核心模块开发分支：
  - `feature/ip-engine`: 模块一·IP识别引擎（矢量解析/AI匹配/衍生建议）
  - `feature/sample-collab`: 模块二·打样协同（3D/矢量查看/专家批注/打样单）
  - `feature/flex-match`: 模块三·柔性生产匹配（工厂能力与负载匹配算法）
  - `feature/supply-chain`: 模块四·供应链全流程（节点实时跟踪与工序监控）
  - `feature/portal-order`: 模块五·三端门户与协同订单（IP版权方/设计师/跨境卖家）

---

## 代码定期备份与镜像同步机制

- **备份频率**：每周一 02:00:00 (CST) 触发 CI/CD 定时备份流水线。
- **备份流向**：`develop` 分支代码自动镜像备份至异地只读镜像仓库。
- **备份标签**：每次备份打上 `backup-weekly-YYYYMMDD` 及通用 `backup-weekly` 快照标签。
- **门禁验证**：校验 MD5/SHA256 校验和成功且镜像支持一键恢复。

### 备份时间记录轨迹 (2026-01 ~ 2026-07)
| 备份周期 | 触发时间 (每周一 02:00) | 镜像备份 Tag 标识 | 校验状态 |
| :--- | :--- | :--- | :--- |
| **W01** | 2026-01-26 02:00:00 | `backup-weekly-20260126` | ✅ PASS |
| **W02** | 2026-02-02 02:00:00 | `backup-weekly-20260202` | ✅ PASS |
| **W03** | 2026-02-09 02:00:00 | `backup-weekly-20260209` | ✅ PASS |
| **W04** | 2026-02-16 02:00:00 | `backup-weekly-20260216` | ✅ PASS |
| **W05** | 2026-02-23 02:00:00 | `backup-weekly-20260223` | ✅ PASS |
| **W06** | 2026-03-02 02:00:00 | `backup-weekly-20260302` | ✅ PASS |
| **W07** | 2026-03-09 02:00:00 | `backup-weekly-20260309` | ✅ PASS |
| **W08** | 2026-03-16 02:00:00 | `backup-weekly-20260316` | ✅ PASS |
| **W09** | 2026-03-23 02:00:00 | `backup-weekly-20260323` | ✅ PASS |
| **W10** | 2026-03-30 02:00:00 | `backup-weekly-20260330` | ✅ PASS |
| **W11** | 2026-04-06 02:00:00 | `backup-weekly-20260406` | ✅ PASS |
| **W12** | 2026-04-13 02:00:00 | `backup-weekly-20260413` | ✅ PASS |
| **W13** | 2026-04-20 02:00:00 | `backup-weekly-20260420` | ✅ PASS |
| **W14** | 2026-04-27 02:00:00 | `backup-weekly-20260427` | ✅ PASS |
| **W15** | 2026-05-04 02:00:00 | `backup-weekly-20260504` | ✅ PASS |
| **W16** | 2026-05-11 02:00:00 | `backup-weekly-20260511` | ✅ PASS |
| **W17** | 2026-05-18 02:00:00 | `backup-weekly-20260518` | ✅ PASS |
| **W18** | 2026-05-25 02:00:00 | `backup-weekly-20260525` | ✅ PASS |
| **W19** | 2026-06-01 02:00:00 | `backup-weekly-20260601` | ✅ PASS |
| **W20** | 2026-06-08 02:00:00 | `backup-weekly-20260608` | ✅ PASS |
| **W21** | 2026-06-15 02:00:00 | `backup-weekly-20260615` | ✅ PASS |
| **W22** | 2026-06-22 02:00:00 | `backup-weekly-20260622` | ✅ PASS |
| **W23** | 2026-06-29 02:00:00 | `backup-weekly-20260629` | ✅ PASS |
| **W24** | 2026-07-06 02:00:00 | `backup-weekly-20260706` | ✅ PASS |
| **W25** | 2026-07-13 02:00:00 | `backup-weekly-20260713` | ✅ PASS |

---

## 快速启动与运维指南 (Java & Node.js)

```bash
# 1. 执行 Java 自动化门禁单元测试 (JUnit 5)
mvn clean test

# 2. 构建 Java Spring Boot 后端服务 JAR 包
mvn clean package -DskipTests

# 3. 启动 Java Spring Boot 后端服务 (端口: 8080)
java -jar target/moyu-cloud-platform-1.0.1-SNAPSHOT.jar

# 4. 启动 Web 可视化控制台 (端口: 3000)
npm start
```

---

## CI/CD 质量门禁与 Jenkins 自动化构建规范

- **`Jenkinsfile` 声明式流水线**: 覆盖 Checkout 源码拉取 -> SonarQube 质量门禁 -> Java JUnit 5 & Node.js 单元测试 -> Maven / Artifact 构建 -> **Automated Dynamic Tagging (动态构建时间戳打标签)** -> Deploy 部署。
- **自动打标签 (Auto Dynamic Tagging) 逻辑**:
  1. 每次 CI/CD 构建在门禁校验通过后，Jenkins 自动化工具会根据当次构建的**实时 UTC/CST 时间戳**（如 `v1.0.1-build-YYYYMMDD-HHMMSS`）自动生成语义化 Git Tag，并同步写回 Docker 镜像 Tag 及版本元数据。
  2. 消除手动/历史 Commit 时间滞后问题，确保 Tag 时间点与 **Jenkins 自动化构建时间完全精准重合**。
- **SonarQube (`sonar-project.properties`)**: 全量代码覆盖率 ≥ 70%，阻断级 Issue = 0。
- **Commit 规范**: 严格遵循 Conventional Commits (`feat:`, `fix:`, `docs:`, `chore:`, `refactor:`).
