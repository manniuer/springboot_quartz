package sbq.scheduler.tasks;

import lombok.Setter;
import org.quartz.*;


@Setter
public class QuartzTestTask implements Job {

    String jobSays;
    float myFloatValue;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getMergedJobDataMap();

        System.err.println("Instance " + jobKey + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
    }
}
