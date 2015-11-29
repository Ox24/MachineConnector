package com.isw.machineapp;

import com.digitalpetri.opcua.sdk.client.api.UaClient;

/**
 * Created by Timur on 28.11.2015.
 */
public class OPCUAConnector {

    private static OPCUAConnector connector;

    private OPCUAConnector(){}

    public static OPCUAConnector getInstance(){
        if(OPCUAConnector.connector == null){
            OPCUAConnector.connector = new OPCUAConnector();
        }
        return OPCUAConnector.connector;
    }

    public boolean connectToServer(String serverName){

        UaClient client;

        return true;
    }

}
