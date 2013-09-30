package com.triple.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.triple.game.elements.Element;
import com.triple.game.elements.ElementType;
import com.triple.game.elements.ElementTypesCollection;
import com.triple.game.elements.ElementsFactory;
import com.triple.game.player.Player;
import com.triple.game.player.PlayerScore;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataSerialise {
    private Gson gson = new Gson();

    public byte[] getSerialisedList(List data) {
        byte[] byteData = new byte[2048];
        String jsonData = gson.toJson(data);
        byteData = jsonData.getBytes();
        return byteData;
    }

    private HashMap<String, String> elementsInfo(String serialisedData) {
        ArrayList<HashMap<String, String>> result = getUnSerialisedList(serialisedData);
        HashMap<String, String> elementTypes = result.get(0);

        return elementTypes;
    }

    private HashMap<String, String> playerInfo(String serialisedData) {
        ArrayList<HashMap<String, String>> result = getUnSerialisedList(serialisedData);
        HashMap<String, String> playerInfo = result.get(1);

        return playerInfo;
    }

    private HashMap<String, String> cellInfo(String serialisedData) {
        ArrayList<HashMap<String, String>> result = getUnSerialisedList(serialisedData);
        HashMap<String, String> clickedCell = result.get(2);

        return clickedCell;
    }

    private ArrayList<HashMap<String, String>> getUnSerialisedList(String serialisedData) {
//        System.out.println(serialisedData);
        Type type = new TypeToken<ArrayList<HashMap<String, String>>>() {}.getType();
        ArrayList<HashMap<String, String>> data = gson.fromJson(serialisedData, type);
        return data;
    }

    public Player getPlayer(String serialisedData) {
        HashMap<String, String> playerInfo = playerInfo(serialisedData);
        ElementType elementType = new ElementType();
        elementType.setName(playerInfo.get("hand"));
        Player player = new Player(playerInfo.get("name"), ElementsFactory.getElement(elementType));
        PlayerScore playerScore = new PlayerScore();
        playerScore.setScore(Integer.parseInt(playerInfo.get("score")));
        playerScore.setMultiplier(Double.parseDouble(playerInfo.get("multiplier")));
        player.setScore(playerScore);
        return player;
    }

    public HashMap<String, Element> getGridElements(String serialisedData) {

        HashMap<String, String> elementsInfo = elementsInfo(serialisedData);
        HashMap<String, Element> gridElements = new HashMap<String, Element>();
        for (String key : elementsInfo.keySet()) {
            if (elementsInfo.get(key) == "") {
                gridElements.put(key, null);
                continue;
            }
            ElementType elementType = ElementTypesCollection.getTypeById(elementsInfo.get(key));
            Element element = ElementsFactory.getElement(elementType);
            gridElements.put(key, element);
        }
        return gridElements;
    }

    public String getClickedCellCoordinates(String serialisedData) {
        String coordinates = "";
        HashMap<String, String> cellInfo = cellInfo(serialisedData);
        for (String key : cellInfo.keySet()) {
            coordinates = cellInfo.get(key);
        }
        return coordinates;
    }

}
