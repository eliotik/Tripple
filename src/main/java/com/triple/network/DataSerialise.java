package com.triple.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.triple.game.elements.Element;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataSerialise {
    private Element element;
    private List dataList = new ArrayList();
    private Gson gson = new Gson();

//    public byte[] getSerialisedData(HashMap<String, HashMap<String, Object>> data) {
//        byte[] byteData = new byte[20048];
//        String jsonData = gson.toJson(data);
//        byteData = jsonData.getBytes();
//
//        return byteData;
//    }

    public byte[] getSerialisedNetworkCollection(NetworkCollection networkCollection) {
        byte[] byteData = new byte[20048];
        String jsonData = gson.toJson(networkCollection);
        byteData = jsonData.getBytes();
        return byteData;
    }

//    public byte[] getSerialisedPlayer(Player player) {
//        byte[] data = new byte[1024];
//        String jsonData = gson.toJson(player);
//        data = jsonData.getBytes();
//
//        return data;
//    }
//
//    public byte[] getSerialisedGrid(HashMap<String, Element> grid) {
//        byte[] data = new byte[1024];
//        String jsonData = gson.toJson(grid);
//        data = jsonData.getBytes();
//
//        return data;
//    }
//
//    public Player getUnSerialisedPlayer(String serialisedData){
//        Type playerType = new TypeToken<Player>(){}.getType();
//        Player player = gson.fromJson(serialisedData, playerType);
//
//    return player;
//    }

//    public HashMap<String, Element> getUnSerialisedGrid(String serialisedData){
//        Type gridType = new TypeToken<HashMap<String, Element>>(){}.getType();
//        HashMap grid = (HashMap)gson.fromJson(serialisedData, gridType);
//
//        return grid;
//    }
//

//
//    public <T> HashMap<String, HashMap<String, T>> getUnSerialisedData(String serialisedData){
//        Type listType = new TypeToken<HashMap<String, HashMap<String, T>>>(){}.getType();
//        HashMap<String, HashMap<String, T>> data = gson.fromJson(serialisedData, listType);
//
//        return data;
//    }

    public NetworkCollection getUnSerialisedNetworkCollection(String serialisedData){
        Type type = new TypeToken<NetworkCollection>() {}.getType();
        NetworkCollection data = gson.fromJson(serialisedData, type);

        return data;
    }

}
