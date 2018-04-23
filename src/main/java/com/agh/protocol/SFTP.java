package com.agh.protocol;


import com.jcraft.jsch.*;

public class SFTP extends ConnectionProtocol {
    @Override
    public void downloadLogs() {
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(properties.getProperty("username"), properties.getProperty("host"), 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(properties.getProperty("password"));
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.get(properties.getProperty("filename"), properties.getProperty("saveTo"));
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }
}
