import controller.Server;

import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        ServerSocket server = Server.start(80);
        while(server != null){
            Server.listenConnection(server);
        }
    }
}