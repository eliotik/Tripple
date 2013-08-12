package com.triple.network;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

class Client {
    static Socket socket;
    static ObjectInputStream inputStream;
    private List data;

    public Client(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public List getData() throws IOException, ClassNotFoundException {
        data = (List) inputStream.readObject();
        return data;
    }
}
