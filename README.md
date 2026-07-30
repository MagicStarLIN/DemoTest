# DemoTest

这是一个 Java 学习示例仓库，包含算法、数据结构、并发、网络、设计模式和常用工具等相互独立的 demo。代码以教学和实验为目的，并非生产应用。

## 环境要求

- JDK 21（构建会拒绝其他 Java 主版本）
- Apache Maven 3.9 或更高版本

检查本地版本：

```bash
java -version
mvn -version
```

## 构建与检查

```bash
# 编译、测试并打包
mvn clean package

# 清理后运行全部测试
mvn clean test

# 分析已声明、未使用及未声明的依赖
mvn dependency:analyze

# 运行 SpotBugs 静态分析
mvn -DskipTests spotbugs:check
```

SpotBugs 可能报告刻意保留的教学示例问题；请逐条判断，不要通过全局降低规则或置信度来掩盖报告。

## 代码目录

| 类别 | 主要包 | 内容 |
| --- | --- | --- |
| 算法 | `com.lcl.arithmetic`、`com.lcl.leetcode` | 排序、搜索、字符串、链表及 LeetCode 练习 |
| 数据结构 | `com.lcl.DataStructure` | 堆、队列、树等基础实现 |
| 并发 | `com.lcl.thread`、`com.lcl.juc`、`com.lcl.disruptor`、`com.lcl.designmodel.producerAndConsumer` | 线程、锁、线程池、生产者/消费者与 Disruptor |
| 网络 | `com.lcl.Socket`、`com.lcl.nettyTest`、`com.lcl.Crawler` | BIO/NIO、Netty、HTTP 客户端与爬虫 |
| 设计模式 | `com.lcl.designmodel` | 工厂、代理、观察者、策略、适配器等模式 |
| 工具 | `com.lcl.utils`、`com.lcl.io`、`com.lcl.guava` | HTTP、日期、文件、图片与 I/O 示例 |

## 爬虫配置

`com.lcl.Crawler.LoginSimulate` 从环境变量读取登录信息。按所选登录方式设置以下变量；不要把值写入源码、README、提交记录或终端截图：

- `DOUBAN_LOGIN_NAME`
- `DOUBAN_LOGIN_PASSWORD`
- `DOUBAN_COOKIE`

爬虫会访问外部站点。运行前请确认目标站点的服务条款、robots 规则、账号权限及当地法律，并控制请求频率。仓库旧历史中曾出现过爬虫凭据；即使当前源码已经清理，相关凭据也必须轮换。

## 危险及长时间运行示例

以下类不属于自动测试，运行时需单独评估并准备终止方式：

- `com.lcl.thread.DeadLockDemo` 会故意制造死锁。
- `com.lcl.testdemos.oom` 会持续分配内存并可能触发 `OutOfMemoryError`、系统换页或进程被操作系统终止。
- `com.lcl.Socket` 与 `com.lcl.nettyTest` 下的 socket 客户端/服务器会监听端口、连接网络或持续等待连接；请确认端口、网络边界并手动停止服务器。
- `com.lcl.Crawler` 下的示例会发起真实外部请求，并可能使用账号或 Cookie。
- `mail.Mail` 会连接真实 SMTP 服务器并尝试发送邮件；示例中的占位信息不可直接使用，不要提交真实密码或授权码。
- 包含无限循环或长期阻塞行为的教学示例（例如自旋锁、服务器事件循环）可能持续占用 CPU、线程或端口。只应在隔离环境中运行，并设置资源限制和超时。

## 兼容性边界

`com.lcl.jerryMouse` 是一个不完整的学习型 Servlet 容器，本次 Java 21 现代化明确排除了该包，未修改其源码。它不是可用于生产环境的 Web 容器。

为保持这个冻结示例可编译，项目保留两项受控的旧版兼容依赖：

- `jakarta.servlet-api` 固定为 `6.0.0`，因为冻结的 `jerryMouse` 尚未实现 Servlet 6.1 新增的方法。
- Apache HttpClient 4 的 `4.5.14` 仅供冻结的 `jerryMouse` 使用其 `DateUtils`；所有非 `jerryMouse` HTTP 代码均使用 HttpClient 5。

`com.lcl.leetcode.Generate` 的公开契约仍然是返回完整的杨辉三角，而不是只返回最后一行。

Disruptor 4 已移除 3.x 的 `WorkerPool`/`WorkHandler` 工作共享 API。普通的多个
`EventHandler` 会各自收到每个事件，并不是两个工作线程之间的任务分配。因此
`TestDisruptorDemo` 使用单个事件处理器，以明确保持每个发布事件只处理一次；如需
并行工作共享，应选用具备明确队列所有权和关闭协议的独立示例，而不是把广播处理器
误当成工作池。
