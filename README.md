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
   粘包问题
     粘包是指发送方发送的两个或多个数据包粘连在一起，接收方一次读取多个数据包，导致无法区分各个独立的数据包。
   半包问题
     半包是指由于一次读取操作只读取到一个数据包的一部分，导致一次读取操作无法获得一个完整的数据包。

- 2024-09-04 通过writeAndFlush发送ByteBuf字节码向客户端传输信息
- 2024-09-05 新增字符串的编码
  创建两个NioEventLoopGroup的原因通常如下：
  父线程组：负责接收客户端的连接请求，并创建新的Channel来处理这些连接。
  子线程组：负责处理已经被接受的连接上的所有I/O操作，如读取、解码、编码和写入数据。
- 2024-09-05 新增netty群发消息案例
- 2024-09-11 socket模拟器链接我们的NettyServer
- 2024-09-13 NettyClient半包粘包处理、编码解码处理、收发数据方式
- 2024-09-13_2 自定义编码解码器，来处理字节码传输，并控制半包、粘包以及安全问题,通过自定义MyDecoder和MyEncoder
- 2024-10-17 使用ChannelOutboundHandlerAdapter与ChannelInboundHandlerAdapter。用于在消息管道中不同时机下处理处理消息。
- 2024-10-17_2 netty的UDP通信方式
- 2024-10-18 Netty搭建Http服务
  在后端开发中，目前大多数都是基于servlet容器实现的http服务，往往有一些核心的子系统对性能的要求非常高，这时候就需要选择nio的网络模型来实现http服务，
  由此来提高性能和吞吐量，netty除了开发网络应用方便之外，还内置了HTTP相关的编解码器，让用户可以很方便的开发出高性能的HTTP协议的服务，
  同时主流的Spring Webflux默认是使用的Netty

- 2024-10-20 Netty与SpringBoot整合
- 2024-10-21 Netty使用Protobuf传输数据test
- 2024-10-22 Netty使用Protobuf传输数据补充
- 2024-10-27 Netty传输Java对象 etty在实际应用级开发中，有时候某些特定场景下会需要使用Java对象类型进行传输，但是如果使用Java本身序列化进行传输，
  那么对性能的损耗比较大。为此我们需要借助protostuff-core的工具包将对象以二进制形式传输并做编码解码处理。 
  与直接使用protobuf二进制传输方式不同，这里不需要定义proto文件，而是需要实现对象类型编码解码器，用以传输自定义Java对象。
- 2024-10-28 Netty传输文件、分片发送、断点续传
- 2024-10-29 基于Netty搭建WebSocket
