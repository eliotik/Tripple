package com.triple.network;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    static ServerSocket serverSocket;
    static Socket socket;
    //static DataOutputStream out;
    static ObjectOutputStream out;

    public Server(int port) throws IOException {
        System.out.println("Starting server...");
        serverSocket = new ServerSocket(port);
        System.out.println("Server started...");
        socket = serverSocket.accept();
        //out = new DataOutputStream(socket.getOutputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Server started...");
    }

    public void setData(List data) throws IOException {
        out.writeObject(data);
    }

}
