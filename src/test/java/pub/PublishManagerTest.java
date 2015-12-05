package pub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

/**
 * Created by Timur on 04.12.2015.
 */
public class PublishManagerTest {
    private PublishManager manager = PublishManager.getInstance();

    @org.junit.BeforeClass
    public static void setUp(){
        PublishManager manager = PublishManager.getInstance();
        try {
            manager.init("localhost");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @org.junit.AfterClass
    public static void tearDown() throws IOException, TimeoutException {
        try {
            PublishManager manager = PublishManager.getInstance();
            manager.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testAddChannels() throws Exception {

    }

    @org.junit.Test
    public void testGetChannelsMap() throws Exception {

    }

    /**
     * Should return the same Instance
     * @throws Exception
     */
    @org.junit.Test
    public void testGetInstance() throws Exception {
        assertEquals(manager, PublishManager.getInstance());
    }

    @org.junit.Test
    public void testPublishTopics() throws Exception {
        List<String > topics = new ArrayList<String>();
        topics.add("Topic1");
        topics.add("Topic2");
        manager.publishTopics(topics);
    }

    @org.junit.Test
    public void testPubStringToChannel() throws Exception {

    }
}