package com.agh.protocol;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HTTP extends ConnectionProtocol {

    private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    public void downloadLogs() {
        String url = properties.getProperty("url");
        System.out.println("Downloading file from " + url);
        try {
            try (InputStream in = URI.create(url).toURL().openStream()) {
                String date = df.format(new Date());
                String filename = properties.getProperty("saveTo") + date;
                System.out.println("Saving to file: " + filename);
                Files.copy(in, Paths.get(filename));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
