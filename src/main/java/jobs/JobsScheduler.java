package jobs;


import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import protocol.*;

import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static properties.PropertiesUtils.INTERVAL;
import static properties.PropertiesUtils.PROTOCOL;

public class JobsScheduler {

    private final List<Properties> propertiesList;

    public JobsScheduler(List<Properties> propertiesList) {
        this.propertiesList = propertiesList;
    }

    public void scheduleJobs() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            propertiesList.forEach(properties -> scheduleDownload(properties, scheduler));
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void scheduleDownload(Properties properties, Scheduler scheduler) {
        String protocolProperty = properties.getProperty(PROTOCOL).toUpperCase();
        Protocol protocol = Protocol.valueOf(protocolProperty);
        ConnectionProtocol connectionProtocol;
        switch (protocol) {
            case FTP:
                connectionProtocol = new FTP();
                break;
            case HTTP:
                connectionProtocol = new HTTP();
                break;
            case SFTP:
                connectionProtocol = new SFTP();
                break;
            default:
                connectionProtocol = null;
                break;
        }
        connectionProtocol.setConfig(properties);
        JobDetail job = newJob(DownloadLogsJob.class)
                .build();
        job.getJobDataMap().put("connectionProtocol", connectionProtocol);
        Integer interval = Integer.parseInt(properties.getProperty(INTERVAL));
        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(interval)
                        .repeatForever())
                .build();
        System.out.println("Started job " + protocolProperty + " with interval " + interval);
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
