package protocol;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HTTP extends ConnectionProtocol {

    @Override
    public void downloadLogs() {
        String url = properties.getProperty("url");
        System.out.println("Downloading file from " + url);
        try {
            try (InputStream in = URI.create(url).toURL().openStream()) {
                Files.copy(in, Paths.get(properties.getProperty("saveTo")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
