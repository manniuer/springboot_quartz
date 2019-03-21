package sbq.scheduler.tasks;


import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzTestTask implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        String jobSays = dataMap.getString("jobSays");
        float myFloatValue = dataMap.getFloatValue("myFloatValue");
        System.err.println("Instance " + jobKey + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("QuartzTestTask for Quartz---" + sdf.format(new Date()));
    }
}
