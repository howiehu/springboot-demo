# SpringBoot Demo

本 Demo 用于演示 SpringBoot 框架的使用。

本 Demo 的测试部分基于测试金字塔理论，目的是探求 SpringBoot 框架下对测试金字塔思想的表达，以及一些优秀工具的演示：

- [Practical Test Pyramid](https://martinfowler.com/articles/practical-test-pyramid.html)

## 运行环境

- JDK 8

## 运行程序

使用 `Docker Compose` 启动已经过配置的 `MySQL` 容器（默认端口号为 3306）：

```bash
docker-compose up -d
```

运行以下 `Gradle` 任务启动程序：

```bash
./gradlew bootrun
```

然后用浏览器访问：

- http://localhost:8080

## 运行测试

使用以下命令运行 API 的端到端测试：

```bash
./gradlew test --tests name.huhao.springbootdemo.e2e.*
```

使用以下命令运行 Controller 的集成测试：

```bash
./gradlew test --tests name.huhao.springbootdemo.integration.*
```

使用以下命令运行 Controller 的单元测试：

```bash
./gradlew test --tests name.huhao.springbootdemo.unit.*
```

## 备忘

### 端到端测试中的数据库重置

本 Demo 中的端到端测试使用了 [flyway-test-extensions](https://github.com/flyway/flyway-test-extensions) 进行每个测试用例运行前的数据库重置。

### 可以利用本 Demo 进行的演示

#### 演示符合测试金字塔的测试运行速度差异

1. 在 Intellij IDEA 中运行全部测试。
2. 对比 UserController 的全部测试，会发运行速度有符合测试金字塔的明显差异。

#### 演示测试驱动开发的过程

1. 先写一个新的 Controller 的单元测试，Mock 掉 Repository，会发现驱动出不依赖 Spring 框架的 Controller 代码。
2. 再写一个针对上述 Controller 的集成测试，Mock 掉 Repository，会驱动引入 Spring 针对 Controller 的必要注解。
3. 再写一个针对上述 Controller 所依赖的 Repository 的集成测试，会驱动 Repository 继承 JpaRepository。
4. 再写一个针对上述 Controller 的端到端测试，弥补了之前两种集成测试的测试间隙，实现最终完成。