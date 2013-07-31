package com.triple.game.elements;

import com.triple.game.configs.Config;
import com.triple.game.configs.XmlReader;

import org.hamcrest.Matchers;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static ch.lambdaj.Lambda.*;

public class ElementTypesCollection {
	
	private static ArrayList<ElementType> elementMap = new ArrayList<ElementType>();
	
	public static ElementType getRandomByType(String type) {
		List<ElementType> eTs = filter(having(on(ElementType.class).getJoinable(), Matchers.equalTo(true))
								.and(having(on(ElementType.class).getType(), Matchers.equalTo(type))),
								elementMap);
		Random generator = new Random();
		Object[] values = eTs.toArray();
		return (ElementType) values[generator.nextInt(values.length)];
	}

    public static void loadElements() {
        XmlReader.getStreamFromFile(Config.elementsFile);
        NodeList listOfElements = XmlReader.read();
        
        for( int j=0; j < listOfElements.getLength(); ++j ) {
            Node firstNode=listOfElements.item(j);
            if( firstNode.getNodeType() == Node.ELEMENT_NODE ) {
                org.w3c.dom.Element elemj = (org.w3c.dom.Element) firstNode;
                
                ElementType elementType = new ElementType();
                
                elementType.setName(elemj.getAttribute("name").toString());
                elementType.setId(elemj.getAttribute("id").toString());
                elementType.setType(elemj.getAttribute("type").toString());
                elementType.setChance(elemj.getAttribute("chance").toString());
                elementType.setTile(Integer.parseInt(elemj.getAttribute("tile_x").toString()), Integer.parseInt(elemj.getAttribute("tile_y").toString()));
                elementType.setBackground(elemj.getAttribute("background").toString());
                elementType.setJoinable((elemj.getAttribute("joinable").equals("1")) ? true : false);
                elementType.setPlayable((elemj.getAttribute("playable").equals("1")) ? true : false);
                elementType.setContainer((elemj.getAttribute("container").equals("1")) ? true : false);
                elementType.setJoinResult(elemj.getAttribute("join_result").toString());
                elementType.setSubspecies(elemj.getAttribute("subspecies").toString());
                
                elementMap.add(elementType);
            }
        }
    }

	public static ElementType getTypeById(String type, String id) {
		List<ElementType> eTs = filter(having(on(ElementType.class).getId(), Matchers.equalTo(id))
				.and(having(on(ElementType.class).getType(), Matchers.equalTo(type))),
				elementMap);		
		if (eTs == null || eTs.size() == 0) return new ElementType();
		return eTs.get(0);
	}

	public static Element getRandom() {
		Random generator = new Random();
		List<ElementType> eTs = filter(
				having(on(ElementType.class).getJoinable(), Matchers.equalTo(true))
				.and(having(on(ElementType.class).getPlayable(), Matchers.equalTo(true)))
				.and(having(on(ElementType.class).getType(), Matchers.isIn(Arrays.asList( "base", "extended" )))),
				elementMap);		
		Object[] types = eTs.toArray();
		ElementType randomType = (ElementType) types[generator.nextInt(types.length)];
		return new Element(randomType);
	}

	public static ElementType getTypeById(String id) {
		List<ElementType> eTs = filter(having(on(ElementType.class).getId(), Matchers.equalTo(id)), elementMap);		
		if (eTs == null || eTs.size() == 0) return new ElementType();
		return eTs.get(0);		
	}	
	
}
