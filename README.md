# Netty

## IO共存

Java目前有三种IO共存分别是：BIO（同步阻塞）、NIO（同步非阻塞）、AIO（异步非阻塞）

BIO: 发起请求→ 阻塞等待→处理完成

NIO: selector主动轮询channel→处理请求→处理完成

AIO:发起请求→通知回调

### AIO案例代码