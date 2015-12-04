package pub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by Timur on 04.12.2015.
 */
public class PublishManager {
    private static final String EXCHANGE_NAME = "PUB_ID_8981";
    private static PublishManager manager;
    private static ConnectionFactory factory;
    private static Connection connection;
    public static HashMap<String, Channel> channels ;

    private PublishManager(){
        channels = new HashMap<String, Channel>();
    }

    public void addChannels(String topic, Channel channel){
        channels.put(topic, channel);
    }

    public HashMap<String, Channel> getChannelsMap(){
        return channels;
    }

    public static PublishManager getInstance(){
        if(manager == null)
            manager = new PublishManager();
        return manager;
    }

    public void init(String host) throws IOException, TimeoutException {
        if(factory == null)
            factory = new ConnectionFactory();
        factory.setHost(host);
        connection = factory.newConnection();
    }

    public void shutdown() throws IOException, TimeoutException {
        Iterator<Channel> iterator = channels.values().iterator();
        while (iterator.hasNext()){
            iterator.next().close();
        }
    }

    public void publishTopics(List<String> topics) throws IOException, TimeoutException {
        for (String topic : topics){
            Channel channel = connection.createChannel();
            addChannels(topic, channel);
            channel.exchangeDeclare(topic, "fanout");
        }
    }

    public void pubStringToChannel(Channel channel, String topic, String message) throws IOException {
        channel.basicPublish(topic,"", null, message.getBytes());
    }
}
