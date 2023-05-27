package service;

import controller.SeatController;
import model.Seat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Server {
    private ServerSocket server;
    private ArrayList<Seat> dataServer;
    private Log logService;

    public Server(int port) {
        try {
            this.server = new ServerSocket(port);
            this.dataServer = SeatController.initialState();
            this.logService = new Log();
            new Thread(this.logService).start();
            logService.add("Server Online in port " + port + "!");
            logService.add("Access: http://127.0.0.1:" + port);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void waitConnection() {
        try {
            while (this.server != null) {
                Socket socket = this.server.accept();
                new Thread(connection(socket)).start();
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public Runnable connection(Socket socket) {
        try {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            byte[] buffer = new byte[1024];
            int nBytes = input.read(buffer);
            String[] request = (new String(buffer, 0, nBytes)).split("\n");

            String route = request[0].split(" ")[1].split("\\?")[0];

            String page;
            switch (route) {
                case "/":
                    logService.add("New requisition in route 'index'!");
                    page = PageRender.index(this.dataServer);
                    break;
                case "/make-reserve":
                    logService.add("New requisition in route 'make-reserve'!");
                    boolean reserve = SeatController.reserveSeat(request, socket.getInetAddress().toString(), dataServer, logService);
                    if (reserve) {
                        page = PageRender.success();
                    } else {
                        page = PageRender.fail();
                    }
                    break;
                case "/reserve":
                    logService.add("New requisition in route 'reserve'!");
                    String slug = SeatController.getSlug(request);
                    if (slug != null) {
                        page = PageRender.reserve(slug);
                        break;
                    }
                default:
                    page = PageRender.notFound();
            }

            output.write(page.getBytes(StandardCharsets.UTF_8));
            output.flush();
            socket.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    public static String getDateTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM HH:mm:ss");
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
