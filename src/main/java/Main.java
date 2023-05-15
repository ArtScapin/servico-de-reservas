import service.Server;

public class Main {
    public static void main(String[] args) {
        try {
            Server http = new Server(80);
            http.waitConnection();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
