package jobs;


import protocol.*;

import java.util.List;
import java.util.Properties;

import static properties.PropertiesUtils.PROTOCOL;

public class JobsScheduler {

    private final List<Properties> propertiesList;

    public JobsScheduler(List<Properties> propertiesList) {
        this.propertiesList = propertiesList;
    }

    public void scheduleJobs() {
        propertiesList.forEach(this::scheduleDownload);
    }

    private void scheduleDownload(Properties properties) {
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
        connectionProtocol.downloadLogs();
    }
}
