import service.Server;

public class Main {
    public static void main(String[] args) {
        try {
            Server http = new Server(8080);
            http.waitConnection();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
