import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.net.*;

public class NonBlockingClient {
    public static void main(String[] args) throws IOException {
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 1234);
        SocketChannel client = SocketChannel.open(hostAddress);
        client.configureBlocking(false);

        System.out.println("Client started");

        ByteBuffer buffer = ByteBuffer.allocate(256);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = reader.readLine()) != null) {
            buffer.clear();
            buffer.put(line.getBytes());
            buffer.flip();
            while(buffer.hasRemaining()) {
                client.write(buffer);
            }
        }

        client.close();
    }
}
