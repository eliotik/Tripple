package ua.triple.game.elements;

import java.util.HashMap;
import java.util.Random;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ua.triple.game.configs.Config;
import ua.triple.game.configs.XmlReader;

public class ElementTypesCollection {
	
	private static HashMap<String, HashMap<String, ElementType>> elementMap = new HashMap<String, HashMap<String, ElementType>>();
	
	public static ElementType getRandomByType(String type) {
		HashMap<String, ElementType> hm = elementMap.get(type);
		if (hm == null) return new ElementType();
		Random generator = new Random();
		Object[] values = hm.values().toArray();
		return (ElementType) values[generator.nextInt(values.length)];		
		//return (ElementType) values[0];		
	}

    public static void loadElements() {
        XmlReader.getStreamFromFile(Config.elementsFile);
        NodeList listOfElements = XmlReader.read();
        
        for( int j=0; j < listOfElements.getLength(); ++j ) {
            Node firstNode=listOfElements.item(j);
            if( firstNode.getNodeType() == Node.ELEMENT_NODE ) {
                org.w3c.dom.Element elemj = (org.w3c.dom.Element) firstNode;
                
                ElementType elementType = new ElementType();
                
                elementType.setId(elemj.getAttribute("id").toString());
                elementType.setType(elemj.getAttribute("type").toString());
                elementType.setTile(Integer.parseInt(elemj.getAttribute("tile_x").toString()), Integer.parseInt(elemj.getAttribute("tile_y").toString()));
                elementType.setBackground(elemj.getAttribute("background").toString());
                elementType.setJoinable((elemj.getAttribute("joinable").equals("1")) ? true : false);
                
                HashMap<String, ElementType> hm = elementMap.get(elementType.getType());
                if (hm == null)
                {
                	hm = new HashMap<String, ElementType>();
                }
                hm.put(elementType.getId(), elementType);
                elementMap.put(elementType.getType(), hm);
            }
        }
    }

	public static ElementType getTypeById(String type, String id) {
		HashMap<String, ElementType> hmByType = elementMap.get(type);
		if (hmByType == null) return new ElementType();
		ElementType elT = hmByType.get(id);
		if (elT == null) return new ElementType();
		return elT;
	}	
	
}
