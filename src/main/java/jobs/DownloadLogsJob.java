package jobs;


import org.quartz.*;
import protocol.ConnectionProtocol;

public class DownloadLogsJob implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = jobExecutionContext.getScheduler().getContext();
        } catch (SchedulerException e1) {
            e1.printStackTrace();
        }
        ConnectionProtocol connectionProtocol =
                (ConnectionProtocol) schedulerContext.get("ConnectionProtocol");
    }
}
