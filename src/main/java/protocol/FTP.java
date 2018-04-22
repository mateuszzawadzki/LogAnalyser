package protocol;


import org.apache.commons.net.ftp.FTPClient;

import java.io.FileOutputStream;
import java.io.IOException;


public class FTP extends ConnectionProtocol {
    @Override
    public void downloadLogs() {
        String file = properties.getProperty("filename");
        String host = properties.getProperty("hostName");
        String user = properties.getProperty("username");
        String password = properties.getProperty("password");
        String savePath = properties.getProperty("saveTo");
        FTPClient client = new FTPClient();

        FileOutputStream fos = null;
        try {
            client.connect(host);
            client.login(user, password);
            fos = new FileOutputStream(savePath);
            client.retrieveFile(file, fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
