package protocol;


import java.util.Properties;

abstract public class ConnectionProtocol {

    protected Properties properties;

    void setConfig(Properties properties) {
        this.properties = properties;
    }

    abstract void downloadLogs() throws Exception;
}
