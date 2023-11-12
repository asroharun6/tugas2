import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.net.*;

public class NonBlockingClient {
    public static void main(String[] args) throws IOException {
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 1234);
        SocketChannel client = SocketChannel.open(hostAddress);

        System.out.println("Client started");

        ByteBuffer buffer = ByteBuffer.allocate(256);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = reader.readLine()) != null) {
            buffer.put(line.getBytes());
            buffer.flip();
            client.write(buffer);
            buffer.clear();
        }

        client.close();
    }
}
// Java Document