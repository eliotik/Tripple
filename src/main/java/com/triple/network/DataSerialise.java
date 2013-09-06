package com.triple.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.triple.game.elements.Element;
import com.triple.game.grid.Grid;
import com.triple.game.player.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataSerialise {
    private Element element;
    private List dataList = new ArrayList();
    private Gson gson = new Gson();

//    public byte[] getSerialiseData(Player player, Cell cell, Element newElement) {
//        byte[] data = new byte[1024];
//        int x = (int) cell.getX();
//        int y = (int) cell.getY();
//        element = player.getHand().getElement();
//        dataList.add(player);
//        dataList.add(x);
//        dataList.add(y);
//        dataList.add(element);
//        dataList.add(newElement);
//        String jsonData = gson.toJson(dataList);
//        data = jsonData.getBytes();
//
//        return data;
//    }

    public byte[] getSerialisePlayer(Player player) {
        byte[] data = new byte[1024];
        String jsonData = gson.toJson(player);
        data = jsonData.getBytes();

        return data;
    }

    public byte[] getSerialiseGrid(Grid grid) {
        byte[] data = new byte[10000];
        String jsonData = gson.toJson(grid);
        data = jsonData.getBytes();

        return data;
    }

    public Player getUnSerialisePlayer(String serialisedData){
        Type playerType = new TypeToken<Player>(){}.getType();
        Player player = gson.fromJson(serialisedData, playerType);

    return player;
    }

    public Grid getUnSerialiseGrid(String serialisedData){
        Type gridType = new TypeToken<Grid>(){}.getType();
        Grid grid = gson.fromJson(serialisedData, gridType);

        return grid;
    }


//    public List getUnSerialiseData(String serialisedData){
//        Type listType = new TypeToken<List>(){}.getType();
//        List list = gson.fromJson(serialisedData, listType);
//
//        return list;
//    }

}
