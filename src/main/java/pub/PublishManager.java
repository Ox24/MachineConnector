package pub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by Timur on 04.12.2015.
 */
public class PublishManager {
    private static final String EXCHANGE_NAME = "PUB_ID_8981";
    private static PublishManager manager;
    private static ConnectionFactory factory;

    private PublishManager(){}

    public static PublishManager getInstance(){
        if(manager == null)
            manager = new PublishManager();
        return manager;
    }

    public void init(String host){
        if(factory == null)
            factory = new ConnectionFactory();
        factory.setHost(host);
    }

    public void publishTopics(List<String> topics) throws IOException, TimeoutException {
        for (String topic : topics){
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(topic, "fanout");
        }
    }
}
