package utils;

/**
 * Created by Timur on 04.12.2015.
 */
public class ServerConfig {
    private String serverName;
    private int serverPort;

    public ServerConfig(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    public String getServerName() {
        return serverName;
    }

    public int getServerPort() {
        return serverPort;
    }

}
