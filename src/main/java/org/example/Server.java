package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class Server {

    public static void main(String[] args) throws IOException {

        int inputPort = 8089;
        String NAMEMESSAGE = "Write your name";
        String KIDSMESSAGE = "Are you child? (yes/no)";
        String GTREETINGCHILDMESSAGE = "Welcome to the kids area, %username%! Let's play!";
        String GREETINGADULTMESSAGE = "Welcome to the adult zone, %username%! Have a good rest, or a good working day!";
        String DEFAULTMESSAGE = "(yes/no?)";

        try (ServerSocket serverSocket = new ServerSocket(inputPort)) {

            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.println("New connection accepted");
                    boolean signal;
                    out.println("Hi!" + " " + NAMEMESSAGE);
                    String var1 = in.readLine();
                    out.println("Hi " + var1 + " " + KIDSMESSAGE);

                    do {
                        String var2 = in.readLine();
                        if (var2.equals("yes")) {
                            out.println(GTREETINGCHILDMESSAGE);
                            signal = true;
                        } else if (var2.equals("no")) {
                            out.println(GREETINGADULTMESSAGE);
                            signal = true;
                        } else if (var2.equals("0")) {
                            break;
                        } else {
                            out.println(DEFAULTMESSAGE);
                            signal = false;
                        }
                    } while (!signal);
                } catch (SocketException exc) {
                    System.out.println(exc.getMessage());
                }
            }
        }
    }
}
