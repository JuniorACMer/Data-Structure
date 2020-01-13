HashMap原理讲解

简单来说，HashMap是一个Entry对象的数组，数组中的每一个Entry元素又是一个链表的头节点，HashMap不是线程安全的。

问题：put方法的原理？
答：HashMap数组的每一个元素初始值为Null，插入位置的确定:index = hash("key)

问题：get方法的原理？
答：1、把输入的key进行hash应色号得到对应的index。
    2、顺着链表的头节点，一个个的向下来查找。

问题：再完美的hash函数当Entry越来越多的时候也会出现index冲突的情况。
答：使用链表解决。每一个Entry对象通过next指针指向它的下一个Entry节点，当新来的Entry映射到冲突的位置
时，只需要插入到对应的链表即可。

问题：为什么插入链表的时候使用的是头插法？
答：因为HashMap的发明者认为，后插入的Entry被查找的可能性更大。

问题：HashMap的初始长度？
答：默认初始长度是16，并且每次扩展是手动初始时，长度必须时2的幂。之所以选择16，是为了服务于从key映射到index的hash算法。

问题：如何实现一个尽量均用分布的hash函数呢？
答：我们通过利用key的hashcode值来做某种运算。index=HashCode(Key)&(Length-1)，
    长度是16或者2的幂的时候，Length-1的值正好全部都是由1组成的，这时候取位与操作，相当于取HashCode的最后几位，
    只要HashCode是均用的，那么index就是均用的。

高并发下的HashMap

问题：什么是ReHash？
答：ReHash是再HashMap扩容的时候的一个步骤。HashMap的容量是有限的，当经过多次元素插入，使得HashMap达到一定饱和度时，Key
    映射位置发生冲突的几率就会逐渐提高。这时HashMap就需要扩展他的长度，进行Resize。

问题：影响Resize发生的因素？
答：1、Capacity：HashMap的当前长度。
    2、LoadFactor：HashMap的负载因子，默认值是0.75f。
    衡量HashMap是否进行Resize的条件如下：
    HashMap.size >= Capacity * LoadFactor

问题：HashMap的Resize方法，具体做了什么事情？
答：1、扩容：创建一个新的Entry空数组，长度是原来的2倍；
    2、ReHash：遍历原Entry数组，把所有的Entry重新Hash到新数组。因为长度（Length -1 ）改变后，Hash的规则随之改变了。

问题：多线程环境中，HashMap的Rehash操作可能带来的问题？
答:假设此时HashMap达到了Resize的临界点，此时A和B线程同时进行Put操作，两个线程各自Resize扩容，会出现环形链表，形成死循环。
https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653192000&idx=1&sn=118cee6d1c67e7b8e4f762af3e61643e&chksm=8c990d9abbee848c739aeaf25893ae4382eca90642f65fc9b8eb76d58d6e7adebe65da03f80d&scene=21#wechat_redirect

ConcurrentHashMap（基于JDK1.7）
解决HashMap的线程安全问题有很多办法，比如改用HashTable或者Collections.synchronizedMap。但是这两者共同的问题：性能。无论是读操作
还是写操作都会给整个集合加锁，导致同一时间的其他操作为之阻塞。为了兼顾线程安全和运行效率，ConcurrentHashMap应用而生。

问题：什么是Segment？
答：Segment本身就相当于一个HashMap对象。同HashMap一样，Segment包含一个HashEntry数组，数组中的每个HashEntry既是一个键值对，
也是一个链表的头节点。ConcurrentHashMap集合中有2的n次方个Segment对象，共同保存在一个名为segments数组当中。可以说，ConcurrentHashMap是一个
二级哈希表，在一个总的哈希表下面，由若干个子哈希表。和数据库的水平拆分有些相似。Segment的写入是需要上锁的，因此对同一Segment的
并发写入会被阻塞。由此可见，ConcurrentHashMap当中每个Segment各自持有一把锁，在保证线程安全的同时降低了锁的粒度，让并发操作效率
更高。

问题：ConcurrentHashMap的优势？
答：采用了锁分段技术，每一个Segment就好比一个自治区，读写操作高度自治，Segment之间互不影响。不同Segment之间的写入是可以并发执行的。


问题：ConcurrentHashMap读写的详细过程？
答：Get方法：
    1、为输入的Key做hash运算，得到hash值。
    2、通过hash值定位到对应的Segment对象。
    3、再次通过hash值定位到Segment当中数组的具体位置。
    put方法：
    1、为输入的Key做Hash运算，得到hash值。
    2、通过hash值，定位到对应的Segment对象。
    3、获取可重入锁。
    4、再次通过hash值定位到Segment当中数组的具体位置。
    5、插入或覆盖HashEntry对象。
    6、释放锁。

问题：ConcurrentHashMap的size方法，一致性问题的说明？
答：1、遍历所有的Segment。
    2、把Segment的元素数量累加起来。
    3、把Segment的修改次数累加起来。
    4、判断Segment的总修改次数是否大于上一次的总修改次数。如果大于，说明统计过程中有修改，重新统计，尝试次数+1；
       如果不是，说明没有修改，统计结束。
    5、如果尝试次数超过阈值，则对每一个Segment加锁，在重新统计。
    6、再次判断Segment的总修改次数是否大于上一次的总修改次数。由于已经加锁，次数一定和上次相等。
    7、释放锁，统计结束。

问题：为什么这样设计？
答：这种思想和乐观锁悲观锁的思想如出一辙。为了尽量不锁柱所有Segment，首先乐观的假设size过程中不会有修改。当尝试一定次数，
    才无奈转换为悲观锁，所著Segment办证强一致性。




    

