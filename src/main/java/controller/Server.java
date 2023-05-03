package controller;

import model.File;
import model.Seat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Server {
    private static final ArrayList<Seat> dataServer = ReserveController.initialState();
    public static ServerSocket start(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("[SERVER " + getDateTime() + "] Online in port " + port + "!");
            System.out.println("[SERVER " + getDateTime() + "] http://127.0.0.1" + "\n");
            return server;
        } catch (Exception error) {
            System.out.println("[SERVER " + getDateTime() +"] Error:" + error);
            System.out.println("[SERVER " + getDateTime() +"] Offline!");
            return null;
        }
    }

    public static void listenConnection(ServerSocket server) {
        try {
            Socket socket = server.accept();
            System.out.println("[SERVER " + getDateTime() +"] New connection!");

            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            byte[] buffer = new byte[1024];
            int nBytes = input.read(buffer);
            String[] request = (new String(buffer, 0, nBytes)).split("\n");

            String route = request[0].split(" ")[1].split("\\?")[0];

            switch (route) {
                case "/":
                    String index = File.render("index", dataServer);
                    serveFile(index, output);
                    break;
                case "/reserve":
                    String slug = ReserveController.getSlug(request);
                    if (slug != null) {
                        String reserve = File.render("reserve", dataServer, slug);
                        serveFile(reserve, output);
                    }
                    break;
                case "/make-reserve":
                    boolean reserve = ReserveController.makeReserve(request, dataServer);
                    if (reserve) {
                        System.out.println("[SERVER " + getDateTime() + "] Successfully reserved!");
                        String success = File.render("success", dataServer);
                        serveFile(success, output);
                    } else {
                        String failed = File.render("failed", dataServer);
                        serveFile(failed, output);
                    }
                    break;
                default:
                    String notFound = File.render("notFound", dataServer);
                    serveFile(notFound, output);
            }

            output.flush();
            socket.close();
        } catch (Exception error) {
            System.out.println("[SERVER " + getDateTime() + "] Error:" + error.getMessage());
            System.out.println("[SERVER " + getDateTime() + "] Server not started!");
        }
    }

    public static void serveFile(String html, OutputStream output) {
        try {
            output.write(html.getBytes(StandardCharsets.UTF_8));
        } catch (Exception error) {
            System.out.println("[SERVER " + getDateTime() +"] Error:" + error.getMessage());
        }
    }

    public static String getDateTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM HH:mm:ss");
        String dateTime = dateTimeFormatter.format(LocalDateTime.now());
        return dateTime;
    }
}
