package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        String host = "netology.homework";
        String ENDMESSAGE = "The end of programm";
        int port = 8089;
        Scanner scanner = new Scanner(System.in);
        String input;

        try {
            Socket clientSocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (true) {
                try {
                    String answerFromServer = in.readLine();
                    System.out.println(answerFromServer);
                    input = scanner.nextLine();
                    if (input.equals("0")) {
                        out.println(input);
                        System.out.println(ENDMESSAGE);
                        break;
                    } else {
                        out.println(input);
                    }
                } catch (SocketException exc) {
                    System.out.println(ENDMESSAGE);
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
