package com.stewie;

import java.net.*;
import java.io.*;

public class Server {

    public Server(int port) throws IOException {
        var server = new ServerSocket(port);
        var socket = server.accept();
        System.out.println("Server started");
        System.out.println("Waiting for a client... ");

        var in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        var output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        System.out.println("Client accepted");

        String line = "";

        while (!line.equalsIgnoreCase("Over")) {
            line = in.readUTF();
            System.out.println(line);

            if (line.equals("y")) {
                int dice1;
                int dice2;
                int total;
                String totalStr = "";
                for (int i = 1; i <= 4; i++) {
                    dice1 = (int) (Math.random() * 6) + 1;
                    dice2 = (int) (Math.random() * 6) + 1;
                    total = (dice1 + dice2);
                    totalStr = Integer.toString(total);
                }
                output.writeUTF(totalStr);
                
            }
        }
            System.out.println("Closing connection");
            socket.close();
            in.close();
    }

    public static void main(String[] args) throws IOException {
        var server = new Server(5000);
    }
}
