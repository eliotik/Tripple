package com.triple.network;

import com.triple.game.Game;
import com.triple.game.elements.Element;
import com.triple.game.player.Player;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

public class Server extends Thread{
    private int port = 1444;
    private DatagramSocket socket;
    private Network network = new Network();
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
            handler(packet);
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

    private void handler(DatagramPacket packet) {
        String message = new String(packet.getData());
        DataSerialise dataSerialise = new DataSerialise();
        HashMap<String, Element> gridElements = dataSerialise.getGridElements(message.trim());
        Player player = dataSerialise.getPlayer(message.trim());
        player.setInetAddress(packet.getAddress());
        network.addPlayer(Game.getPlayerPanel().getPlayer(0));
        network.addPlayer(player);
        network.setGrid(gridElements);
        System.out.println(network.getPlayers().count());
//      sendData(message.getBytes(), player.getInetAddress(), 1444);
    }
}
