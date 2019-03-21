package sbq.scheduler.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sbq.scheduler.tasks.TestTask1;
import sbq.scheduler.tasks.TestTask2;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail testTask1() {
        return JobBuilder.newJob(TestTask1.class).withIdentity("testTask1_id").storeDurably().build();
    }

    @Bean
    public Trigger testTaskTrigger1() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(testTask1())
                .withIdentity("testTask1_id")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail testTask2() {
        return JobBuilder.newJob(TestTask2.class).withIdentity("testTask2_id").storeDurably().build();
    }

    @Bean
    public Trigger testTaskTrigger2() {
        return TriggerBuilder.newTrigger().forJob(testTask2())
                .withIdentity("testTasks2_id")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                .build();
    }
}
