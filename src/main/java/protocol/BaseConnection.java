package protocol;


import java.util.Properties;

abstract public class BaseConnection {

    protected Properties properties;

    void setConfig(Properties properties) {
        this.properties = properties;
    }
}
