package com.isw.machineapp;

import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ApplicationType;
import org.opcfoundation.ua.transport.security.SecurityMode;
import utils.MachineAppUtils;
import utils.RetryStrategy;

import java.util.Locale;

/**
 * Created by Timur on 28.11.2015.
 * Copyright Timur Tasci
 */
public class OPCUAConnector {

    private static OPCUAConnector connector;
    private static boolean isConnected;
    private UaClient client;

    private OPCUAConnector() {
    }

    public static OPCUAConnector getInstance() {
        if (connector == null) {
            connector = new OPCUAConnector();
        }
        return connector;
    }

    public void connectToServer(String serverName) throws Exception {

        client = new UaClient(serverName);
        RetryStrategy retry = new RetryStrategy();
        client.setSecurityMode(SecurityMode.NONE);
        init(client);
        while (retry.shouldRetry() && !client.isConnected()){
            try {
                client.connect();
            } catch (Exception e) {
                retry.errorOccured();
            }
        }
        setConnectionStatus(client.isConnected());
    }

    public void disconnect(){
        if(client != null){
            client.disconnect();
        }
    }

    protected static void setConnectionStatus(boolean status){
        OPCUAConnector.isConnected = status;
    }

    public boolean isConnected(){
        return isConnected;
    }

    protected static void init(UaClient client) {
        ApplicationDescription appDescription = new ApplicationDescription();
        appDescription.setApplicationName(new LocalizedText(MachineAppUtils.APP_NAME, Locale.ENGLISH));
        // 'localhost' (all lower case) in the URI is converted to the actual
        // host name of the computer in which the application is run
        appDescription.setApplicationUri("urn:localhost:UA:" + MachineAppUtils.APP_NAME);
        appDescription.setProductUri("urn:prosysopc.com:UA:" + MachineAppUtils.APP_NAME);
        appDescription.setApplicationType(ApplicationType.Client);

        final ApplicationIdentity identity = new ApplicationIdentity();
        identity.setApplicationDescription(appDescription);
        client.setApplicationIdentity(identity);
    }

}
