package com.agh.main;

import com.agh.jobs.JobsScheduler;
import com.agh.properties.PropertiesUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    private static List<Properties> propertiesList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        initializeProperties(args);
        JobsScheduler jobsScheduler = new JobsScheduler(propertiesList);
        jobsScheduler.scheduleJobs();
    }

    private static void initializeProperties(String[] args) throws Exception {
        Files.walk(Paths.get(args[0]))
            .filter(Files::isRegularFile)
            .map(Path::toFile)
            .forEach(file -> {
                Properties property = new Properties();
                try {
                    property.load(new FileInputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (PropertiesUtils.validateProperties(property)) {
                    propertiesList.add(property);
                }
            });
    }
}


