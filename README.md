# sniffCache
a cache fremawork for redis

#目的：
- 简化缓存操作
- 将针对于缓存的map,queue,list，stack等数据结构操作做柯里化封装
- 利用缓存实现分布式互斥锁、公平锁等封装
- 将存储数据内容的json格式自动拆箱、装箱封装
