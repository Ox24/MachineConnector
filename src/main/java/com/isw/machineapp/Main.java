package com.isw.machineapp;

/**
 * Created by Timur on 28.11.2015.
 * Copyright Timur Tasci
 */
public class Main {
    public static void main(String args[]) throws Exception{
        OPCUAConnector connector = OPCUAConnector.getInstance();
        connector.connectToServer("opc.tcp://Timur-PC:48010");
        if(connector.isConnected()){
            System.out.println("We are in!");
        }else {
            System.out.print("Fuck!");
        }
    }
}
