import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.function.BiConsumer;

class HTTPRequest {
    int port = 80;
    String method = "GET";
    String path = "/";
    String version = "HTTP/1.0";

    HashMap<String, String> headers = new HashMap<>();

    // Used for getResponse()
    Socket socket;
    DataOutputStream outputStream;
    BufferedReader reader;

    HTTPRequest (BufferedReader reader) throws Exception {
        String reqLine = reader.readLine();

        if (reqLine == null) {
            throw new Exception("Invalid request!");
        }

        String[] split = reqLine.split(" ");
        method = split[0];
        path = split[1];
        version = split[2];

        // PROXY: path may contain host too (Example: path=http://foo.com/bar)
        try {
            URL url = new URL(path);
            path = url.getPath();
            headers.put("Host", url.getHost());

        } catch (Exception e) {
            // Do nothing
        }

        // TODO: Read more lines using reader.readLine() and parse request headers

        // Rewrite accept-encoding to identity (Why?)
        // https://tools.ietf.org/html/rfc2616#section-14.3
        headers.put("Accept-Encoding", "identity");

        // Rewrite Connection header to close (Why?)
        headers.put("Connection", "close");
    }

    HTTPResponse getResponse() throws Exception {
        // 1. Get host address using DNS and connect

        InetAddress address = InetAddress.getByName((headers.get("Host")));

        System.out.println("REQ: Connecting to " + address);

        socket = new Socket(address, port);
        outputStream = new DataOutputStream(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // 2. Send request

        write(method + " " + path + " " + version);

        for(String key : headers.keySet()) {
            write(key + ": " + headers.get(key));
        }

        write("");

        // 3. Read response

        HTTPResponse response = new HTTPResponse(reader);

        return response;
    }

    private void write (String line) throws Exception {
        System.out.println("REQ > " + line);
        outputStream.writeBytes(line + "\r\n");
        outputStream.flush();
    }

    @Override
    public String toString() {
        return "[REQUEST] " + "Method: " + method + " " + "Path: " + path + " " + "Version: " + version;
    }
}