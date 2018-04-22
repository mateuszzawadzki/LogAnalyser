package jobs;


import protocol.Protocol;

import java.util.EnumSet;
import java.util.List;
import java.util.Properties;

public class JobsScheduler {

    private final List<Properties> propertiesList;

    public JobsScheduler(List<Properties> propertiesList) {
        this.propertiesList = propertiesList;
    }

    public void scheduleJobs() {
        propertiesList.forEach(this::scheduleDownload);
    }

    private void scheduleDownload(Properties properties) {
        String protocolProperty = properties.getProperty("protocol").toUpperCase();
        Protocol protocol = Protocol.valueOf(protocolProperty);
        switch (protocol) {
            case FTP:

        }
    }
}
