#什么是RPC？

[RPC（Remote Procedure Call Protocol）](https://en.wikipedia.org/wiki/Remote_procedure_call)

RPC（Remote Procedure Call Protocol）——远程过程调用协议

#为什么要有RPC?

一台单独机器的计算，存储能力不能够满足日益增长的IT业务需求，所以需要把计算，存储能力扩大到多台机器上。

但各种服务之间如何通信？

分布式调用大体上就分为两类，RPC式的，REST式的：
1. RPC是面向动作的（方法调用）
2. REST是面向资源的（URL表示资源，HTTP动词表示动作)


#RPC框架
https://www.zybuluo.com/phper/note/76641
##那什么是远程调用？

通常我们调用一个php中的方法，比如这样一个函数方法: localAdd(10, 20)，localAdd方法的具体实现要么是用户自己定义的，要么是php库函数中自带的，也就说在localAdd方法的代码实现在本地，它是一个本地调用！

远程调用意思就是：被调用方法的具体实现不在程序运行本地，而是在别的某个远程地方。

##远程调用原理

比如 A (client) 调用 B (server) 提供的remoteAdd方法：

>1.首先A与B之间建立一个TCP连接；

>2.然后A把需要调用的方法名（这里是remoteAdd）以及方法参数（10， 20）序列化成字节流发送出去；

>3.B接受A发送过来的字节流，然后反序列化得到目标方法名，方法参数，接着执行相应的方法调用（可能是localAdd）并把结果30返回；

>4.A接受远程调用结果,输出30。

RPC框架就是把我刚才说的这几点些细节给封装起来，给用户暴露简单友好的API使用。

##远程调用的好处

解耦：当server需要对方法内实现修改时，client完全感知不到，不用做任何变更；这种方式在跨部门，跨公司合作的时候经常用到，并且方法的提供者我们通常称为：服务的暴露。

#其它
1. thrift
2. restful web service
3. GRPC (protobuf)
4. Avro
5. 其它
Dubbo是阿里集团开源的一个极为成员的RPC框架，在很多互联网公司和企业应用中广泛使用。协议和序列化框架都可以插拔是及其鲜明的特色。同样的远程接口是基于Java Interface，并且依托于spring框架方便开发。可以方便的打包成单一文件，独立进程运行，和现在的微服务概念一致。
(阿里的dubbo小组已经解散了，现在内部主要使用hsf)

http://tech.meituan.com/serialization_vs_deserialization.html

JSON（Javascript Object Notation）
典型应用场景和非应用场景
JSON在很多应用场景中可以替代XML，更简洁并且解析速度更快。典型应用场景包括：

    1、公司之间传输数据量相对小，实时性要求相对低（例如秒级别）的服务。
    2、基于Web browser的Ajax请求。
    3、由于JSON具有非常强的前后兼容性，对于接口经常发生变化，并对可调式性要求高的场景，例如Mobile app与服务端的通讯。
    4、由于JSON的典型应用场景是JSON＋HTTP，适合跨防火墙访问。

总的来说，采用JSON进行序列化的额外空间开销比较大，对于大数据量服务或持久化，这意味着巨大的内存和磁盘开销，这种场景不适合。没有统一可用的IDL降低了对参与方的约束，实际操作中往往只能采用文档方式来进行约定，这可能会给调试带来一些不便，延长开发周期。 由于JSON在一些语言中的序列化和反序列化需要采用反射机制，所以在性能要求为ms级别，不建议使用。

选型建议
以上描述的五种序列化和反序列化协议都各自具有相应的特点，适用于不同的场景：

    1、对于公司间的系统调用，如果性能要求在100ms以上的服务，基于XML的SOAP协议是一个值得考虑的方案。
    2、基于Web browser的Ajax，以及Mobile app与服务端之间的通讯，JSON协议是首选。对于性能要求不太高，或者以动态类型语言为主，或者传输数据载荷很小的的运用场景，JSON也是非常不错的选择。
    3、对于调试环境比较恶劣的场景，采用JSON或XML能够极大的提高调试效率，降低系统开发成本。
    4、当对性能和简洁性有极高要求的场景，Protobuf，Thrift，Avro之间具有一定的竞争关系。
    5、对于T级别的数据的持久化应用场景，Protobuf和Avro是首要选择。如果持久化后的数据存储在Hadoop子项目里，Avro会是更好的选择。
    6、由于Avro的设计理念偏向于动态类型语言，对于动态语言为主的应用场景，Avro是更好的选择。
    7、对于持久层非Hadoop项目，以静态类型语言为主的应用场景，Protobuf会更符合静态类型语言工程师的开发习惯。
    8、如果需要提供一个完整的RPC解决方案，Thrift是一个好的选择。
    9、如果序列化之后需要支持不同的传输层协议，或者需要跨防火墙访问的高性能场景，Protobuf可以优先考虑。

资料：
http://www.searchsoa.com.cn/showcontent_75305.htm
https://jersey.java.net/
https://jax-rs-spec.java.net/
http://www.zhihu.com/question/28570307
https://developer.github.com/v3/
http://apistore.baidu.com/
https://thrift.apache.org/
http://www.searchtb.com/2010/11/protocol-buffers%E7%9A%84%E5%BA%94%E7%94%A8%E4%B8%8E%E5%88%86%E6%9E%90.html
http://tech.meituan.com/serialization_vs_deserialization.html
http://www.infoq.com/cn/news/2015/03/grpc-google-http2-protobuf
http://maoyidao.iteye.com/blog/1236916
http://www.useopen.net/blog/2015/rpc-performance.html
http://dubbo.io/User+Guide-zh.htm#UserGuide-zh-%E5%90%AF%E5%8A%A8%E6%97%B6%E6%A3%80%E6%9F%A5
http://blog.qq.com/qzone/1664205/1320112192.htm
http://m.blog.csdn.net/blog/ITer_ZC/39341983
https://github.com/duggan/homebrew-protobuf3/tree/master
http://solicomo.com/network-dev/protobuf-proto3-vs-proto2.html
http://blog.mimvp.com/2015/03/comparing-protobuf-and-thrift/
https://www.zybuluo.com/phper/note/76641
http://www.infoq.com/cn/articles/serialization-and-deserialization
