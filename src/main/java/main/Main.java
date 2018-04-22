package main;

import jobs.JobsScheduler;
import protocol.Protocol;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Properties;

public class Main {

    private static List<Properties> propertiesList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        initializeProperties();
        JobsScheduler jobsScheduler = new JobsScheduler(propertiesList);
        jobsScheduler.scheduleJobs();
    }

    private static void initializeProperties() throws Exception {
        EnumSet<Protocol> availableProtocols = EnumSet.allOf(Protocol.class);
        Files.walk(Paths.get("src\\main\\resources\\configs"))
        .filter(Files::isRegularFile)
        .map(Path::toFile)
        .forEach(file -> {
            Properties property = new Properties();
            try {
                property.load(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(validateProtocol(availableProtocols, property)) {
                propertiesList.add(property);
            }

        });
    }

    private static boolean validateProtocol(EnumSet<Protocol> availableProtocols,
                                         Properties properties) {
        String protocolProperty = properties.getProperty("protocol");
        return availableProtocols.stream().anyMatch(protocol -> protocol.toString().equals(protocolProperty));
    }
}


