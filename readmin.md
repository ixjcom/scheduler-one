quartz集群环境下
    org.quartz.jobStore.isClustered必定设置为true
    org.quartz.scheduler.instanceName实例名必定相同，但是可以设置多个实例
    class package必定相同,包不同会错
    instancename或者ClassName不一样  可能会报:ClusterManager: detected 1 failed or restarted instances
    其他的配置应该都一样
JobFactory
   JOB内注入失败的解决方案
       