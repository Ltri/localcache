# localcache本地缓存
- 通过ConcurrentHashMap + Timer实现TTL
## 可优化
- LRU算法可通过LinkHashMap+ReadWriteLock实现
- 持久化可通过io读取本地文件
