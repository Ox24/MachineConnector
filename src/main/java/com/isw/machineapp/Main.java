package com.isw.machineapp;

import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Timur on 28.11.2015.
 * Copyright Timur Tasci
 */
public class Main {

    // Action codes for readAction, etc.
    protected static final int ACTION_ALL = -4;
    protected static final int ACTION_BACK = -2;
    protected static final int ACTION_RETURN = -1;
    protected static final int ACTION_ROOT = -3;
    protected static final int ACTION_TRANSLATE = -6;
    protected static final int ACTION_UP = -5;

    protected static final String LF4J_PROP = "\\src\\main\\java\\utils\\log.properties";
    protected final static List<String> cmdSequence = new ArrayList<String>();
    OPCUAConnector connector = OPCUAConnector.getInstance();

    public static void main(String args[]){
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString();
        PropertyConfigurator.configure(path.concat(LF4J_PROP));
        Main menu = new Main();
        menu.mainMenu();
    }

    private void printMenu() {
        System.out.println("--------------------------------------");
        System.out.println("-----------------Menu-----------------");
        System.out.println("[0] connect to OPCServer");
        System.out.println("[1] disconnect from OPCServer");
        System.out.println("[2] create subscription");
        System.out.println("[3] publish to MQM");
        System.out.println("[x] quit");
        System.out.println("--------------------------------------");
        System.out.println("--------------------------------------");
    }

    private void mainMenu() {
        do {
            printMenu();
            try {
                switch (readAction()) {
                    case ACTION_RETURN:
                        return;
                    case 0:
                        connector.connectToServer("opc.tcp://Timur:48010");
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:

                    case 'x':
                        connector.disconnect();
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }

    private static int readAction() {
        return parseAction(readInput(true).toLowerCase());
    }

    protected static String readInput(boolean useCmdSequence) {
        return readInput(useCmdSequence, null);
    }

    protected static String readInput(boolean useCmdSequence, String defaultValue) {
        // You can provide "commands" already from the command line, in which
        // case they will be kept in cmdSequence
        if (useCmdSequence && !cmdSequence.isEmpty()) {
            String cmd = cmdSequence.remove(0);
            try {
                // Negative int values are used to pause for n seconds
                int i = Integer.parseInt(cmd);
                if (i < 0) {
                    try {
                        TimeUnit.SECONDS.sleep(-i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return readInput(useCmdSequence, defaultValue);
                }
            } catch (NumberFormatException e) {
                // never mind
            }
            return cmd;
        }
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        do
            try {
                s = stdin.readLine();
                if ((s == null) || (s.length() == 0))
                    s = defaultValue;
            } catch (IOException e) {
                e.printStackTrace();
            }
        while ((s == null) || (s.length() == 0));
        return s;
    }

    protected static int parseAction(String s) {
        if (s.equals("x"))
            return ACTION_RETURN;
        if (s.equals("b"))
            return ACTION_BACK;
        if (s.equals("r"))
            return ACTION_ROOT;
        if (s.equals("a"))
            return ACTION_ALL;
        if (s.equals("u"))
            return ACTION_UP;
        if (s.equals("t"))
            return ACTION_TRANSLATE;
        return Integer.parseInt(s);
    }
}
