package protocol;


import java.util.Properties;

abstract public class ConnectionProtocol {

    protected Properties properties;

    public void setConfig(Properties properties) {
        this.properties = properties;
    }

    abstract public void downloadLogs();
}
