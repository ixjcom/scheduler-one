package liu.cn.ixj.schedulerone;

import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;

@Component
public class InitJobs {

    @Resource
    private Scheduler scheduler;

    private String job_group="quartzJobTaskGroup";


    public void createJob(Class clazz, String jobName, String cron) throws SchedulerException, ParseException {
        if (scheduler.getJobDetail(JobKey.jobKey(jobName, job_group)) == null) {
            JobDetail jebDetail = JobBuilder.newJob(clazz).withIdentity(JobKey.jobKey(jobName, job_group)).build();
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            CronTrigger build = TriggerBuilder.newTrigger().withIdentity(jobName, job_group).withSchedule(scheduleBuilder.withMisfireHandlingInstructionDoNothing()).startNow().build();
            scheduler.scheduleJob(jebDetail, build);
        }
    }
}
