package protocol;


import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HTTP extends BaseConnection implements ConnectionProtocol {

    private static final String FILENAME = "temp";


    @Override
    public void downloadLogs() throws Exception {
        String url = properties.getProperty("url");
        try (InputStream in = URI.create(url).toURL().openStream()) {
            Files.copy(in, Paths.get(FILENAME));
        }
    }
}
