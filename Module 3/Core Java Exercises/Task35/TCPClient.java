import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to server.");

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage, clientMessage;

            // Thread to read messages from server and print to client console
            Thread readThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = inFromServer.readLine()) != null) {
                        System.out.println("Server: " + msg);
                    }
                } catch (IOException e) {
                    System.out.println("Server disconnected.");
                }
            });
            readThread.start();

            // Main thread reads from user input and sends to server
            while ((clientMessage = userInput.readLine()) != null) {
                outToServer.println(clientMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
