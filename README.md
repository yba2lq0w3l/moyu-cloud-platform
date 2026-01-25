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

## 快速启动与运维指南

```bash
# 1. 启动 Web 平台可视化控制台 (端口: 3000)
npm start

# 2. 执行自动化门禁单元测试
npm test
```

---

## CI/CD 质量门禁与测试规范

- **`Jenkinsfile`**: 支持 Checkout, SonarScan, Node Test, Build, Deploy 完整构建流水线。
- **SonarQube (`sonar-project.properties`)**: 全量代码覆盖率 ≥ 70%，阻断级 Issue = 0。
- **Commit 规范**: 严格遵循 Conventional Commits (`feat:`, `fix:`, `docs:`, `chore:`, `refactor:`).
