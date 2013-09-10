package com.triple.network;

import com.triple.game.Game;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

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
            byte[] data = new byte[20048];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String message = new String(packet.getData());
            DataSerialise dataSerialise = new DataSerialise();
//            HashMap<String, HashMap<String, Object>> dataList = dataSerialise.getUnSerialisedData(message.trim());
//            Player player = (Player) dataList.get("player").get("player");
//            System.out.println(dataList.get("player").toString());
//            System.out.println(dataList.get("player").get("player").getClass());
            NetworkCollection networkCollection = dataSerialise.getUnSerialisedNetworkCollection(message.trim());
//            System.out.println(networkCollection.toString());
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
