package com.stewie;

import java.net.*;
import java.io.*;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public Server(int port) {
        try{
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client... ");
            socket = server.accept();
            System.out.println("Client accepted");
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";
            while(!line.equals("Over")){
                try {
                    line = in.readUTF();
                    System.out.println(line);
                    if(line.equalsIgnoreCase("y")){
                        int dice1 = 0;
                        int dice2 = 0;
                        int total = 0;
                        for (int i = 1; i <= 4; i++) {
                            dice1 = (int)(Math.random() * 6) + 1;
                            dice2 = (int)(Math.random() * 6) + 1;
                            total = dice1 + dice2;
                        }
                       System.out.println("Roll: " + "\n" + total);
                    }
                }
                catch(IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
            socket.close();
            in.close();

        } catch(IOException i) {
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        var server = new Server(5000);
    }
}
