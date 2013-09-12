package com.triple.network;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.triple.game.elements.ElementType;
import com.triple.game.elements.ElementTypesCollection;
import com.triple.game.player.Player;
import com.triple.game.player.PlayerScore;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataSerialise {
    private Gson gson = new Gson();

    public byte[] getSerialisedList(List data) {
        byte[] byteData = new byte[20048];
        String jsonData = gson.toJson(data);
        byteData = jsonData.getBytes();

        return byteData;
    }

    public HashMap<String, Object> elementsInfo(String serialisedData) {
        ArrayList<HashMap<String, Object>> result = getUnSerialisedList(serialisedData);
        HashMap<String, Object> elementTypes = result.get(0);

        return elementTypes;
    }
    public HashMap<String, Object> playerInfo(String serialisedData) {
        ArrayList<HashMap<String, Object>> result = getUnSerialisedList(serialisedData);
        HashMap<String, Object> elementTypes = result.get(1);

        return elementTypes;
    }

    public Player getPlayer(String serialisedData) {
        HashMap<String, Object> playerInfo = playerInfo(serialisedData);
        LinkedTreeMap playerFromData = (LinkedTreeMap) playerInfo.get("player");
        LinkedTreeMap playerScoreFromData = (LinkedTreeMap) playerFromData.get("score");
        Player player = new Player((String) playerFromData.get("name"));
        PlayerScore score = new PlayerScore();
        score.setScore((int) ((double) playerScoreFromData.get("score")));
        score.setMultiplier((double) playerScoreFromData.get("multiplier"));
        player.setScore(score);

        return player;
    }

    public ElementTypesCollection getElementTypesCollection(String serialisedData){
        HashMap<String, Object> elementTypes = elementsInfo(serialisedData);
        ElementTypesCollection elementTypesCollection = new ElementTypesCollection();
        for (String key : elementTypes.keySet()) {
            ElementType elementType = new ElementType();
            LinkedTreeMap elementTypeFromData = (LinkedTreeMap)elementTypes.get(key);
            elementType.setName((String)elementTypeFromData.get("name"));
            elementType.setType((String) elementTypeFromData.get("type"));
            elementType.setPlayable((Boolean) elementTypeFromData.get("playable"));
            elementType.setContainer((Boolean) elementTypeFromData.get("container"));
            elementType.setId((String) elementTypeFromData.get("id"));
            elementType.setChance((int) ((double) elementTypeFromData.get("chance")));
            elementType.setTile_x((int) ((double) elementTypeFromData.get("tile_x")));
            elementType.setTile_y((int) ((double)elementTypeFromData.get("tile_y")));
            elementType.setJoinResult((String) elementTypeFromData.get("joinResult"));
            elementType.setSubspecies((String) elementTypeFromData.get("subspecies"));
            elementType.setScore((int) ((double)elementTypeFromData.get("score")));
            elementType.setJoinScoreMultiplier((double) elementTypeFromData.get("joinScoreMultiplier"));
            elementType.setShowBackground((Boolean) elementTypeFromData.get("show_background"));
            elementType.setPenalty((int) ((double)elementTypeFromData.get("penalty")));

            elementTypesCollection.add(elementType);
        }
        return elementTypesCollection;
    }

    public ArrayList<HashMap<String, Object>> getUnSerialisedList(String serialisedData) {
        Type type = new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType();
        ArrayList<HashMap<String, Object>> data = gson.fromJson(serialisedData, type);
        return data;
    }

}
