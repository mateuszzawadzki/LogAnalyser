package com.agh.jobs;


import org.quartz.*;
import com.agh.protocol.ConnectionProtocol;

public class DownloadLogsJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ConnectionProtocol connectionProtocol = (ConnectionProtocol) jobExecutionContext.getMergedJobDataMap().get("connectionProtocol");
        connectionProtocol.downloadLogs();
    }
}
