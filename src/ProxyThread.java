import java.net.*;
import java.io.*;



public class ProxyThread extends Thread {
    Socket socket;

    ProxyThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader((socket.getInputStream())));
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            // 1. Read request

            HTTPRequest request = new HTTPRequest(reader);

            if (!"GET".equals(request.method)) {
                socket.close();
                return;
            } else {
                System.out.println("* " + request);
            }

            // 2. Analyze request's host

            // TODO

            // 3. Sent request to the real host and get response

            HTTPResponse response = request.getResponse();

            // 4. Analyze response

            // TODO

            // 5. Send back response to the client
            response.send(outputStream);

            // 6. Close connection
            reader.close();
            outputStream.close();

        } catch (Exception e) {
            System.err.println("* " + e);
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
