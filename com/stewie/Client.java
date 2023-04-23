package com.stewie;

import java.io.*;
import java.net.*;

public class Client {
    public Client(String address, int port) throws IOException {
        var socket = new Socket(address, port);
        System.out.println("Connected");
        var input = new DataInputStream(System.in);
        var out = new DataOutputStream(socket.getOutputStream());

        String line = "";

        while (!line.equalsIgnoreCase("Over")) {
            line = input.readLine();
            out.writeUTF(line);
            if (line.equals("roll")) {
                System.out.print("Roll the dice? (y/n): ");
            }
            String result = input.readUTF();
            System.out.println(result);

        }
        input.close();
        out.close();
        socket.close();
    }

    public static void main (String[]args) throws IOException {
            var client = new Client("127.0.0.1", 5000);
    }
}



