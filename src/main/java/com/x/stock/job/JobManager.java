package com.x.stock.job;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Title: QuartzManager
 * ProjectName ccetl
 * Description:
 * author wukaijun
 * date 2020/1/8 18:11
 */
public class JobManager {

    public static final Logger logger = LoggerFactory.getLogger(JobManager.class);

    private static JobManager instance = new JobManager();

    private JobManager() {
    }

    public static JobManager getInstance() {
        return instance;
    }

    private SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    private Map<String, Set<String>> flowJobSet = new ConcurrentHashMap();


    /**
     * @param jobName          任务名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass         任务
     * @param cron             时间设置，参考quartz说明文档
     * @Description: 添加一个定时任务
     */
    public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String cron, Map<String, Long> param) throws Exception {
        Scheduler sched = schedulerFactory.getScheduler();
        // 任务名，任务组，任务执行类
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
        // 设置参数
        jobDetail.getJobDataMap().putAll(param);
        // 触发器
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        // 触发器名,触发器组
        triggerBuilder.withIdentity(triggerName, triggerGroupName);
        triggerBuilder.startNow();
        // 触发器时间设定
        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
        // 创建Trigger对象
        CronTrigger trigger = (CronTrigger) triggerBuilder.build();
        // 调度容器设置JobDetail和Trigger
        sched.scheduleJob(jobDetail, trigger);
        // 启动
        if (!sched.isShutdown()) {
            sched.start();
        }
    }

    /**
     * 移除任务
     * param jobName
     * param jobGroupName
     * param triggerName
     * param triggerGroupName
     */
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) throws Exception {
        Scheduler sched = schedulerFactory.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        sched.pauseTrigger(triggerKey);// 停止触发器
        sched.unscheduleJob(triggerKey);// 移除触发器
        sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
    }

    /**
     * 关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
