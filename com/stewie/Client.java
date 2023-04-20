package com.stewie;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;
import javax.xml.validation.Validator;

public class Client {
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            input = new DataInputStream(System.in);
            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
            return;
        } catch (IOException i) {
            System.out.println(i);
            return;
        }

        String line = "";

        while (!line.equals("Over")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
                if(line.equalsIgnoreCase("roll the dice")){
                    var scanner = new Scanner(System.in);
                    String choice = input.readLine();
                    System.out.print("Roll the dice? (y/n): ");
                    while(choice.equalsIgnoreCase("y")){
                        System.out.print("Roll again? (y/n): ");
                    }
                }
            } catch (IOException i) {
                System.out.println(i);
            }
        }
        try {
            input.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        var client = new Client("127.0.0.1", 5000);
    }
}


