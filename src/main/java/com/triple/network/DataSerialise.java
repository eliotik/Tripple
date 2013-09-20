package com.triple.network;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.triple.game.elements.Element;
import com.triple.game.elements.ElementType;
import com.triple.game.elements.ElementTypesCollection;
import com.triple.game.elements.ElementsFactory;
import com.triple.game.player.Player;
import com.triple.game.player.PlayerHand;
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

    private ArrayList<HashMap<String, String>> getUnSerialisedList(String serialisedData) {
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
            ElementType elementType = new ElementType();
            elementType.setName(elementsInfo.get(key).split(", ")[0]);
            elementType.setSubspecies(elementsInfo.get(key).split(", ")[1]);
            Element element = ElementsFactory.getElement(elementType);
            gridElements.put(key, element);
        }
        return gridElements;
    }

//    public Player getPlayer(String serialisedData) {
//        HashMap<String, Object> playerInfo = playerInfo(serialisedData);
//        LinkedTreeMap playerFromData = (LinkedTreeMap) playerInfo.get("player");
//        LinkedTreeMap playerHandFromData = (LinkedTreeMap) playerFromData.get("hand");
//        LinkedTreeMap playerHandElement = (LinkedTreeMap) playerHandFromData.get("element");
//        ElementType elementTypeHand = getElementType((LinkedTreeMap)playerHandElement.get("type"));
//        Element elementInHand = ElementsFactory.getElement(elementTypeHand);
//        LinkedTreeMap playerScoreFromData = (LinkedTreeMap) playerFromData.get("score");
//
//        Player player = new Player((String) playerFromData.get("name"));
//        PlayerScore score = new PlayerScore();
//        score.setScore((int) ((double) playerScoreFromData.get("score")));
//        score.setMultiplier((double) playerScoreFromData.get("multiplier"));
//        player.setScore(score);
//
//        PlayerHand playerHand = new PlayerHand();
//        playerHand.setElement(elementInHand);
//        player.setHand(playerHand);
//
//        return player;
//    }
//
//    public List<Element> getElementList(String serialisedData) {
//        List<ElementType> elementTypesCollection = getElementTypesCollection(serialisedData);
//        List<Element> elementList = new ArrayList<Element>();
//        for (ElementType elementType : elementTypesCollection) {
//            Element element = ElementsFactory.getElement(elementType);
//            elementList.add(element);
//        }
//        return elementList;
//    }
//
//    public List<ElementType> getElementTypesCollection(String serialisedData){
//        HashMap<String, Object> elementTypes = elementsInfo(serialisedData);
//        List<ElementType> elementTypesCollection = new ArrayList<ElementType>();
//        for (String key : elementTypes.keySet()) {
//            LinkedTreeMap elementTypeFromData = (LinkedTreeMap)elementTypes.get(key);
//            ElementType elementType = getElementType(elementTypeFromData);
//            elementTypesCollection.add(elementType);
//        }
//        return elementTypesCollection;
//    }
//
//    private ElementType getElementType(LinkedTreeMap elementTypeFromData) {
//        ElementType elementType = new ElementType();
//        elementType.setName((String)elementTypeFromData.get("name"));
//        elementType.setType((String) elementTypeFromData.get("type"));
//        elementType.setPlayable((Boolean) elementTypeFromData.get("playable"));
//        elementType.setContainer((Boolean) elementTypeFromData.get("container"));
//        elementType.setId((String) elementTypeFromData.get("id"));
//        elementType.setChance((int) ((double) elementTypeFromData.get("chance")));
//        elementType.setTile_x((int) ((double) elementTypeFromData.get("tile_x")));
//        elementType.setTile_y((int) ((double)elementTypeFromData.get("tile_y")));
//        elementType.setJoinResult((String) elementTypeFromData.get("joinResult"));
//        elementType.setSubspecies((String) elementTypeFromData.get("subspecies"));
//        elementType.setScore((int) ((double)elementTypeFromData.get("score")));
//        elementType.setJoinScoreMultiplier((double) elementTypeFromData.get("joinScoreMultiplier"));
//        elementType.setShowBackground((Boolean) elementTypeFromData.get("show_background"));
//        elementType.setPenalty((int) ((double)elementTypeFromData.get("penalty")));
//
//        return elementType;
//    }



}
