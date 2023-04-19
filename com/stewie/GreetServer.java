package com.stewie;

import java.io.*;
import java.net.*;

public class GreetServer {
    private ServerSocket server;
    private Socket socket;
    private DataInputStream in;

    public GreetServer(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server Started");
            System.out.println("Waiting for client ... ");
            socket = server.accept();
            System.out.println("Client accept");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";

            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                }
                catch (IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
            socket.close();
            in.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }



    }

    public static void main(String[] args) throws IOException {
        var server = new ServerSocket(5000);
    }
//    public void start(int port) throws IOException {
//        serverSocket = new ServerSocket(port);
//        clientSocket = serverSocket.accept();
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        String greeting = in.readLine();
//        if("hello server".equals(greeting)){
//            out.println("Hello Client");
//        } else {
//            out.println("Unrecognized greeting.");
//        }
//    }
//
//    public void stop() throws IOException {
//        in.close();
//        out.close();
//        clientSocket.close();
//        serverSocket.close();
//    }
//
//    public static void main(String[] args) throws IOException {
//        GreetServer server = new GreetServer();
//        server.start(6666);
//
//    }
}
