package com.stewie;

import java.net.*;
import java.io.*;
import java.util.Random;

public class Server {
    private DataInputStream clientIn;
    private PrintWriter serverOut;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public static void main(String[] args) throws IOException {
        var server = new Server();
        server.serverStart(5000);
        server.serverStop();
    }

    public void serverStart(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started");
        System.out.println("Waiting for a client... ");
        clientSocket = serverSocket.accept();
        System.out.println("Client accepted");

        clientIn = new DataInputStream(
                new BufferedInputStream(clientSocket.getInputStream()));

        serverOut = new PrintWriter(clientSocket.getOutputStream(), true);


        String line = "";

        while (!line.equalsIgnoreCase("Over")) {
            line = clientIn.readUTF();
            System.out.println(line);

            if (isInteger(line)) {
                int total = diceRoll(Integer.parseInt(line));
                System.out.println("Roll: " + total);
                serverOut.println(total);
            }
        }}

    private static int diceRoll(int numberOfDice) {
        var random = new Random();
        int total = 0;
        for (int i = 0; i < numberOfDice; i++) {
            int roll = random.nextInt(6) + 1;
            total += roll;
        }
        return total;
    }

    private boolean isInteger(String string) {
        boolean isInt = true;
        try{
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            isInt = false;
        }
        return isInt;
    }

    private void serverStop() throws IOException {
        System.out.println("Closing connection");
        clientSocket.close();
        clientIn.close();
    }

}