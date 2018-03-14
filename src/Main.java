import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);

        System.out.println("Server listening on port 8080!");

        while (true) {
            Socket socket = server.accept();
            new ProxyThread(socket).start();
        }
    }
}
