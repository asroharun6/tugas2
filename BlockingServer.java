import java.io.*;
import java.net.*;

public class BlockingServer {
    public static void main(String[] args) throws IOException {
        int port = 1234; // Contoh port
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept(); // Menunggu koneksi dari klien
            System.out.println("Accepted connection from " + clientSocket);
            new ClientHandler(clientSocket).start();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client: " + inputLine);
                out.println("Echo: " + inputLine); // Mengirim balik pesan ke klien
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
