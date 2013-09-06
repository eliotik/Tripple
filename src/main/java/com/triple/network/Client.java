package com.triple.network;

import com.triple.game.Game;

import java.io.IOException;
import java.net.*;

public class Client extends Thread {
    private int port = 1444;
    private InetAddress inetAddress;
    private DatagramSocket socket;
    private Game game;

    public Client(Game game, String inetAddress) {
        this.game = game;
        try {
            this.socket = new DatagramSocket();
            this.inetAddress = InetAddress.getByName(inetAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[2048];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("test from client");
        }
    }

    public void sendData(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
