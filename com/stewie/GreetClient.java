package com.stewie;

import java.net.*;
import java.io.*;

public class GreetClient {
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;

    public GreetClient(String address, int port) {
        try {
            clientSocket = new Socket(address, port);
            System.out.println("Connected");
            in = new DataInputStream(System.in);
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException i) {
            System.out.println(i);
            return;
        }
        String line = "";
        while (!line.equals("Over")) {
            try {
                line = in.readLine();
                out.writeUTF(line);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        var client = new GreetClient("127.0.0.1", 5000);
    }

//    public void startConnection(String ip, int port) throws IOException{
//        clientSocket = new Socket(ip, port);
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//    }
//
//    public String sendMessage(String msg) throws IOException {
//        out.println(msg);
//        String resp = in.readLine();
//        return resp;
//    }
//
//    public void stopConnection() throws IOException{
//        in.close();
//        out.close();
//        clientSocket.close();
//    }
}