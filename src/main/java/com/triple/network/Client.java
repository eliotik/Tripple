package com.triple.network;

import com.triple.game.Game;
import java.io.IOException;
import java.net.*;


public class Client extends Thread {
    private int port = 1444;
    private InetAddress inetAddress;
    private DatagramSocket socket;
    private Game game;
    public static boolean isRunning = false;

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
    public synchronized void start() {
        if (isRunning) return;
        isRunning = true;
        new Thread(this).start();
    }


    public void run() {
        while (isRunning) {
            byte[] data = new byte[2048];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            reformer(packet);
//            JOptionPane.showMessageDialog(null, "test from client");

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
