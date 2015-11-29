package subs;

import com.isw.machineapp.Main;
import com.prosysopc.ua.client.Subscription;
import com.prosysopc.ua.client.SubscriptionAliveListener;

import java.util.Calendar;

/**
 * Created by Timur on 29.11.2015.
 * Copyright Timur Tasci
 */
public class SubsAliveListener  implements SubscriptionAliveListener{
    public void onAfterCreate(Subscription s) {
        System.out.println(String.format("%tc Subscription created: ID=%d lastAlive=%tc",
                Calendar.getInstance(), s.getSubscriptionId().getValue(), s.getLastAlive()));
    }

    public void onAlive(Subscription s) {
        System.out.println(String.format("%tc Subscription alive: ID=%d lastAlive=%tc", Calendar.getInstance(),
                s.getSubscriptionId().getValue(), s.getLastAlive()));
    }

    public void onTimeout(Subscription s) {
        System.out.println(String.format("%tc Subscription timeout: ID=%d lastAlive=%tc",
                Calendar.getInstance(), s.getSubscriptionId().getValue(), s.getLastAlive()));
    }
}
