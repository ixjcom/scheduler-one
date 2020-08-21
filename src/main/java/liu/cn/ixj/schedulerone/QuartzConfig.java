package liu.cn.ixj.schedulerone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class QuartzConfig {

    @Bean(name = "quartzScheduler")
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource,JobFactory jobFactory){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);
        Properties properties = new Properties();
        /*
                可以是任何字符串，但对于所有调度程序来说，必须是唯一的，就像它们在集群中是相同的“逻辑”调度程序一样。
                如果希望为您生成Id，则可以使用值“AUTO”作为instanceId。
                或者如果你想要这个值来自系统属性“org.quartz.scheduler.instanceId”的值“SYS_PROP”
                一般设置为AUTO就行
         */
        properties.put("org.quartz.scheduler.instanceId","AUTO");

        /**
                可以是任何字符串，用去区分多个实例的标识
                如果是集群下的同一个实例，该值必须相同
         */
        properties.put("org.quartz.scheduler.instanceName","quartzScheduler");

        /**
                跳过更新。不理解为什么jar还要更新
         */
        properties.put("org.quartz.scheduler.skipUpdateCheck","true");

        /**
                标识使用哪个线程池
                SimpleThreadPool是quartz自带的
         */
        properties.put("org.quartz.threadPool.class","org.quartz.simpl.SimpleThreadPool");

        /**
            线程池的大小
         */
        properties.put("org.quartz.threadPool.threadCount","50");

        /*
            权重（取值范围是1-10，默认为5）
         */
        properties.put("org.quartz.threadPool.threadPriority","9");

        /*
            是不是守护线程
         */
        properties.put("org.quartz.threadPool.makeThreadsDaemons","false");

        /*

         */
        properties.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread","true");

        /*
            在线程范围内初始化线程
         */
        properties.put("org.quartz.scheduler.threadsInheritContextClassLoaderOfInitializer","true");

        /*
             标识数据存储到数据库
         */
        properties.put("org.quartz.jobStore.class","org.quartz.impl.jdbcjobstore.JobStoreTX");

        /*
            标识存到什么数据库
            org.quartz.impl.jdbcjobstore.StdJDBCDelegate（用于完全符合JDBC的驱动程序）
            org.quartz.impl.jdbcjobstore.MSSQLDelegate（对于Microsoft SQL Server和Sybase）
            org.quartz.impl.jdbcjobstore.PostgreSQLDelegate
            org.quartz.impl.jdbcjobstore.WebLogicDelegate（对于WebLogic驱动程序）
            org.quartz.impl.jdbcjobstore.oracle.OracleDelegate
            org.quartz.impl.jdbcjobstore.oracle.WebLogicOracleDelegate（对于Weblogic中使用的Oracle驱动程序）
            org.quartz.impl.jdbcjobstore.oracle.weblogic.WebLogicOracleDelegate（对于在Weblogic中使用的Oracle驱动程序）
            org.quartz.impl.jdbcjobstore.CloudscapeDelegate
            org.quartz.impl.jdbcjobstore.DB2v6Delegate
            org.quartz.impl.jdbcjobstore.DB2v7Delegate
            org.quartz.impl.jdbcjobstore.DB2v8Delegate
            org.quartz.impl.jdbcjobstore.HSQLDBDelegate
            org.quartz.impl.jdbcjobstore.PointbaseDelegate
            org.quartz.impl.jdbcjobstore.SybaseDelegate
         */
        properties.put("org.quartz.jobStore.driverDelegateClass","org.quartz.impl.jdbcjobstore.StdJDBCDelegate");

        /*
            “使用属性”标志指示JDBCJobStore，JobDataMaps中的所有值都将是“字符串”，因此可以将其存储为名称 - 值对，而不是以BLOB列的序列化形式存储更复杂的对象。
            这可以方便，因为您避免了将非String类序列化为BLOB时可能产生的类版本控制问题。
         */
        properties.put("org.quartz.jobStore.useProperties","false");

        /*
            数据库表名前缀
         */
        properties.put("org.quartz.jobStore.tablePrefix","qrtz_");

        /*
            是否是集群
         */
        properties.put("org.quartz.jobStore.isClustered","true");

        /*
            检测时间
         */
        properties.put("clusterCheckinInterval","1000");

        /*
            本次任务执行时，如果上个任务没执行完，且时间在这个时间内，本次执行跳过
         */
        properties.put("org.quartz.jobStore.misfireThreshold","60000");

        /*
            获取虚拟机的终止事件，并停掉定时器
         */
        properties.put("org.quartz.plugin.shutdownhook.class","org.quartz.plugins.management.ShutdownHookPlugin");
        properties.put("org.quartz.plugin.shutdownhook.cleanShutdown","true");

        /*
            当检测到失去与数据库的连接时，重连的等待时间
         */
        properties.put("org.quartz.scheduler.dbFailureRetryInterval","1000");

        /*
            旧版需要设置，新版一般设置为false
         */
        properties.put("org.quartz.jobStore.acquireTriggersWithinLock","false");
        schedulerFactoryBean.setQuartzProperties(properties);
        schedulerFactoryBean.setJobFactory(jobFactory);
        return schedulerFactoryBean;
    }
}
