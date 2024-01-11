package com.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {
            String command = (String) input.readObject();
            String result = CommandRunner.runCommand(command);
            output.writeObject(result);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
