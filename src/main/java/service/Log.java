package service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Log {
    public static void write(String message) {
        try {
            Path path = Paths.get("src/main/java/service/logs/serverLog.txt");
            String log = "[" + Server.getDateTime() + "] " + message + "\n";
            Files.write(path, log.getBytes(), CREATE, APPEND);
            System.out.print(log);
        }  catch (Exception error) {
            error.printStackTrace();
        }
    }
}
