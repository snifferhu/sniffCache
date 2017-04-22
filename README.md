# sniffCache
a cache fremawork for redis
#背景
- spring缺乏对于redis sharedpool的支持
- 在项目应用中，确实需要应用sharedpool
- 同时从企业业务稳定角度考虑，老板不让升级redis到更高版本

#目的：
- 简化缓存操作
- 将针对于缓存的map,queue,list，stack等数据结构操作做柯里化封装
- 利用缓存实现分布式互斥锁、公平锁等封装
- 将存储数据内容的json格式自动拆箱、装箱封装
