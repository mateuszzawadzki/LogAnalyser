package com.agh.properties;

import com.agh.format.LogFormat;
import com.agh.protocol.Protocol;

import java.util.EnumSet;
import java.util.Properties;


public class PropertiesUtils {

    public static final String INTERVAL = "interval";
    public static final String PROTOCOL = "protocol";
    public static final String FORMAT = "format";

    public static boolean validateProperties(Properties properties) {
        return validateProtocol(properties) && validateInterval(properties)
                && validateFormat(properties);
    }

    private static boolean validateInterval(Properties properties) {
        return properties.getProperty(INTERVAL).matches("\\d+");
    }

    private static boolean validateProtocol(Properties properties) {
        EnumSet<Protocol> availableProtocols = EnumSet.allOf(Protocol.class);
        String protocolProperty = properties.getProperty(PROTOCOL).toUpperCase();
        return availableProtocols.stream().anyMatch(protocol -> protocol.toString().equals(protocolProperty));
    }

    private static boolean validateFormat(Properties properties) {
        EnumSet<LogFormat> availableProtocols = EnumSet.allOf(LogFormat.class);
        String protocolProperty = properties.getProperty(FORMAT).toUpperCase();
        return availableProtocols.stream().anyMatch(protocol -> protocol.toString().equals(protocolProperty));
    }
}
