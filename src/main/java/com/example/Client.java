package com.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8888;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())){
            // here we can change folder name:
            // the folder will be created in the resources directory
            output.writeObject("mkdir Kasbino3");
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}
