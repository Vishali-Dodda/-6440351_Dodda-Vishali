import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started, waiting for client...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;

            // Start a thread to read from client and print to server console
            Thread readThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = inFromClient.readLine()) != null) {
                        System.out.println("Client: " + msg);
                    }
                } catch (IOException e) {
                    System.out.println("Client disconnected.");
                }
            });
            readThread.start();

            // Main thread sends messages typed by server user to client
            while ((serverMessage = userInput.readLine()) != null) {
                outToClient.println(serverMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
