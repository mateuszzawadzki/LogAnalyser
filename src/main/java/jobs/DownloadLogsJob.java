package jobs;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import protocol.ConnectionProtocol;

public class DownloadLogsJob implements Job{

    private final ConnectionProtocol connectionProtocol;

    public DownloadLogsJob(ConnectionProtocol connectionProtocol) {
        this.connectionProtocol = connectionProtocol;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
