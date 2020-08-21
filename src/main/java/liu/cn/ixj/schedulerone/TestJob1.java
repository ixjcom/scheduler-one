package liu.cn.ixj.schedulerone;

import org.quartz.*;

import java.util.Date;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class TestJob1 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("one1>>>>" + new Date());
    }
}
