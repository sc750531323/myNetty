## EventLoopGroup
EventLoopGroup是个接口，继承自EventExecutorGroup
```java
public interface EventLoopGroup extends EventExecutorGroup
```
EventExecutorGroup也是个接口
```java
/**
 * The {@link EventExecutorGroup} is responsible for providing the {@link EventExecutor}'s to use
 * via its {@link #next()} method. Besides this, it is also responsible for handling their
 * life-cycle and allows shutting them down in a global fashion.
 */
public interface EventExecutorGroup extends ScheduledExecutorService, Iterable<EventExecutor>
```
EventLoopGroup类似于线程池，可以从里面获取新的线程，以及 管理线程的生命周期。


## NioEventLoopGroup
```java
/**
 * {@link MultithreadEventLoopGroup} implementations which is used for NIO {@link Selector} based {@link Channel}s.
 */
public class NioEventLoopGroup extends MultithreadEventLoopGroup
```
线程数量如果不设置，那么就会使用机器cpu个数×2.
```java
protected MultithreadEventLoopGroup(int nThreads, Executor executor, Object... args) {
        super(nThreads == 0 ? DEFAULT_EVENT_LOOP_THREADS : nThreads, executor, args);
    }
```
```java
DEFAULT_EVENT_LOOP_THREADS = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
```
有多少个线程就会对应EventExecutor数组。每个线程就是NioEventLoop，每个线程都会负责一部分的客户端连接的
sockechannel，对这些SocketChannel都会去注册在线程自己的selector中，每个线程通过自己的selector去轮询
它负责的客户端网络请求事件

