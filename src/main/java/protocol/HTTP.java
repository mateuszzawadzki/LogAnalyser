package protocol;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HTTP extends ConnectionProtocol {

    private static final String FILENAME = "temp";

    @Override
    public void downloadLogs() {
        String url = properties.getProperty("url");
        try {
            try (InputStream in = URI.create(url).toURL().openStream()) {
                Files.copy(in, Paths.get(FILENAME));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
