package utils;

/**
 * Created by Timur on 04.12.2015.
 */

import com.google.code.jconfig.reader.hierarchical.IHierarchicalReader;
import com.google.code.jconfig.reader.plugins.IConfigurationPlugin;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager implements IConfigurationPlugin<List<ServerConfig>>{


    public static void init(){

    }


    public List<ServerConfig> readConfiguration(IHierarchicalReader reader) {

        IHierarchicalReader serversNode = reader.getChildren().get(0);
        List<ServerConfig> servers = new ArrayList<ServerConfig>();
        for (IHierarchicalReader child : serversNode.getChildren()) {
            servers.add(new ServerConfig(child.getAttributeValue("name"), Integer.parseInt(child.getAttributeValue("port"))));
        }

        return servers;
    }
}
