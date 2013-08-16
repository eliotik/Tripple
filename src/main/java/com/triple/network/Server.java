package com.triple.network;

import com.triple.game.Game;

import java.io.IOException;
import java.net.*;

public class Server extends Thread{
    private int port = 1444;
    private DatagramSocket socket;
    private Game game;

    public static boolean isRunning = false;

    public Server(Game game) {
        this.game = game;
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        if (isRunning) return;
        isRunning = true;
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String message = new String(packet.getData());
            if (message.trim().equals("tetete")){
                sendData("test".getBytes(), packet.getAddress(), packet.getPort());
                //System.out.println("hjhj");
            }
        }
    }

    public void sendData(byte[] data, InetAddress inetAddress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
