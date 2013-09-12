package com.triple.network;

import com.triple.game.Game;
import com.triple.game.elements.ElementTypesCollection;
import com.triple.game.player.Player;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

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
            ArrayList<HashMap<String, Object>> networkCollection = dataSerialise.getUnSerialisedList(message.trim());
            Player player = dataSerialise.getPlayer(message.trim());
            ElementTypesCollection elementTypesCollection = dataSerialise.getElementTypesCollection(message.trim());
            System.out.println(player);
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
