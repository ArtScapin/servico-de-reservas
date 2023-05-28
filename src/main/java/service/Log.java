package service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Log implements Runnable {
    private ArrayList<String> logBuffer = new ArrayList<>();;

    public void add(String message) {
        try {
            this.logBuffer.add(message);
        }  catch (Exception error) {
            error.printStackTrace();
        }
    }

    private void write(String message) {
        try {
            Path path = Paths.get("src/main/java/service/serverLog.txt");
            String log = "[" + Server.getDateTime() + "] " + message + "\n";
            Files.write(path, log.getBytes(), CREATE, APPEND);
            System.out.print(log);
        }  catch (Exception error) {
            error.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (logBuffer){
                    if(!logBuffer.isEmpty()){
                        write(logBuffer.get(0));
                        logBuffer.remove(0);
                    }
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
