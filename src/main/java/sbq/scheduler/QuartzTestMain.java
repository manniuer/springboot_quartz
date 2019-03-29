package sbq.scheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import sbq.scheduler.tasks.QuartzTestTask;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class QuartzTestMain {

    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.start();

            JobDetail job = newJob(QuartzTestTask.class)
                    .withIdentity("testTask1", "group1")
                    .usingJobData("jobSays", "Hello, World!")
                    .usingJobData("myFloatValue", 3.14f)
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("testTrigger", "group1")
                    .usingJobData("myStateData", "fucking data")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(5)
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(job, trigger);

            Thread.sleep(60000);

            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException se) {
            se.printStackTrace();
        }
    }
}
