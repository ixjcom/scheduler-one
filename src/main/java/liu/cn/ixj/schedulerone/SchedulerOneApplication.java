package liu.cn.ixj.schedulerone;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;
import java.text.ParseException;

@SpringBootApplication
public class SchedulerOneApplication {

    public static void main(String[] args) throws ParseException, SchedulerException {
        ConfigurableApplicationContext run = SpringApplication.run(SchedulerOneApplication.class, args);
       /* run.getBean(InitJobs.class).createJob(TestJob.class,"TestJob","0/10 * * * * ?");
        run.getBean(InitJobs.class).createJob(TestJob1.class,"TestJob1","0/10 * * * * ?");
        run.getBean(InitJobs.class).createJob(TestJob2.class,"TestJob2","0/10 * * * * ?");*/
    }
}
