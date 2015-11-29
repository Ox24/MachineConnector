package subs;

import com.prosysopc.ua.client.MonitoredDataItem;
import com.prosysopc.ua.client.MonitoredEventItem;
import com.prosysopc.ua.client.Subscription;
import com.prosysopc.ua.client.SubscriptionNotificationListener;
import org.opcfoundation.ua.builtintypes.*;
import org.opcfoundation.ua.core.NotificationData;

/**
 * Created by Timur on 29.11.2015.
 * Copyright Timur Tasci
 */
public class SubsNotificationListener implements SubscriptionNotificationListener {
    public void onBufferOverflow(Subscription subscription, UnsignedInteger unsignedInteger, ExtensionObject[] extensionObjects) {
        System.out.println("*** SUBCRIPTION BUFFER OVERFLOW ***");
    }

    public void onDataChange(Subscription subscription, MonitoredDataItem monitoredDataItem, DataValue dataValue) {
        // Called for each data change notification
    }

    public void onError(Subscription subscription, Object o, Exception e) {
        // Called if the parsing of the notification data fails,
        // notification is either a MonitoredItemNotification or
        // an EventList
        e.printStackTrace();
    }

    public void onEvent(Subscription subscription, MonitoredEventItem monitoredEventItem, Variant[] variants) {
        // Called for each event notification
    }

    public long onMissingData(UnsignedInteger lastSequenceNumber, long sequenceNumber, long newSequenceNumber,
                              StatusCode serviceResult) {
        System.out.println("Data missed: lastSequenceNumber=" + lastSequenceNumber + " newSequenceNumber=" + newSequenceNumber);
        return 0;
    }

    public void onNotificationData(Subscription subscription, NotificationData notificationData) {

    }

    public void onStatusChange(Subscription subscription, StatusCode statusCode, StatusCode statusCode1, DiagnosticInfo diagnosticInfo) {

    }
}
