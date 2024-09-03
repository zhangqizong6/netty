# Netty
## IO共存

Java目前有三种IO共存分别是：BIO（同步阻塞）、NIO（同步非阻塞）、AIO（异步非阻塞）

BIO: 发起请求→ 阻塞等待→处理完成

NIO: selector主动轮询channel→处理请求→处理完成

AIO:发起请求→通知回调

record:

- 2024-09-01 更新aio bio案例，运用适配器模式对客户端和服务端处理【adapter适配器，handler处理器】
- 2024-09-02 新增nio案例代码,同样是基于以实现aio bio的设计模式
- 2024-09-03 1.新增netty案例代码 修改pom文件 2.用netty编写一个能接收数据的socketServer服务端
