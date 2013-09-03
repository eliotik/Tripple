package com.triple.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.triple.game.elements.Element;
import com.triple.game.grid.Cell;
import com.triple.game.player.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataSerialise {
    private Element element;
    private List dataList = new ArrayList();
    private Gson gson = new Gson();

    public byte[] getSerialiseData(Player player, Cell cell, Element newElement) {
        byte[] data = new byte[1024];
        int x = (int) cell.getX();
        int y = (int) cell.getY();
        element = player.getHand().getElement();
        dataList.add(player);
        dataList.add(x);
        dataList.add(y);
        dataList.add(element);
        dataList.add(newElement);
        String jsonData = gson.toJson(dataList);
        data = jsonData.getBytes();

        return data;
    }

    public List getUnSerialiseData(String serialisedData){
        Type listType = new TypeToken<List>(){}.getType();
        List list = gson.fromJson(serialisedData, listType);

        return list;
    }

}
