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
- 2024-09-04 新增netty接入netty解码器

由于 TCP 是面向流的协议，它不会为每个消息提供明确的边界，因此多个消息可能会被合并成一个数据包发送，或者一个消息可能被分成多个数据包发送

### 粘包问题

粘包是指发送方发送的两个或多个数据包粘连在一起，接收方一次读取多个数据包，导致无法区分各个独立的数据包。

### 半包问题

半包是指由于一次读取操作只读取到一个数据包的一部分，导致一次读取操作无法获得一个完整的数据包。

- 2024-09-04 通过writeAndFlush发送ByteBuf字节码向客户端传输信息