/* Roll Dice over TCP

   Author: Stephen Stuart

   Assignment: Program 1

   A program that roll a set amount of dice over a TCP connection. Written in Java-JDK 19
 */


package com.stewie;

import java.io.*;
import java.net.*;

public class Client {
    private DataInputStream userInput;
    private BufferedReader serverInput;
    private DataOutputStream clientOut;
    private Socket clientSocket;

    public static void main (String[]args) throws IOException {
            var client = new Client();
            client.startConnection("127.0.0.1", 5000);
            client.sendMessage();
            client.stopConnection();
    }

    private void startConnection(String address, int port) throws IOException {
        clientSocket = new Socket(address, port);
        System.out.println("Connected");

        userInput = new DataInputStream(System.in);
        clientOut = new DataOutputStream(clientSocket.getOutputStream());
        serverInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendMessage() throws IOException {

        String line = "";

        while (!line.equalsIgnoreCase("Over")) {
            line = userInput.readLine();
            clientOut.writeUTF(line);

            String result = serverInput.readLine();
            System.out.println("Roll: " + result);
        }
    }

    private void stopConnection() throws IOException {
        userInput.close();
        clientOut.close();
        clientSocket.close();
    }

}