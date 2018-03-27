package main;


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    private static List<Properties> propertiesList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        initializeProperties();
        startScheduler();
    }

    private static void initializeProperties() throws Exception {
        List<File> filesInFolder =
                Files.walk(Paths.get("config"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList()
                );

        for (File file : filesInFolder) {
            Properties property = new Properties();
            property.load(new FileInputStream(file));
            propertiesList.add(property);
        }
    }
    
    private static void startScheduler() {
        for (Properties properties : propertiesList) {
            
        }
    }
}


