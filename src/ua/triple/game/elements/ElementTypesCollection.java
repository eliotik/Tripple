package ua.triple.game.elements;

import java.util.HashMap;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ua.triple.game.configs.Config;
import ua.triple.game.configs.XmlReader;

public class ElementTypesCollection {
	
	private static HashMap<String, Element> elementMap = new HashMap<String, Element>();
	
	public static ElementType getType(String string) {
		return new ElementType();
	}

    public static void loadElements() {
        XmlReader.getStreamFromFile(Config.elementsFile);
        NodeList listOfElements = XmlReader.read();
        
        Boolean joinable = false;
        
        for( int j=0; j < listOfElements.getLength(); ++j ) {
            Node firstNode=listOfElements.item(j);
            if( firstNode.getNodeType() == Node.ELEMENT_NODE ) {
                org.w3c.dom.Element elemj = (org.w3c.dom.Element) firstNode;
                
                joinable = false;
                if (elemj.getAttribute("joinable").equals("1")) joinable = true;
                
                ElementType elementType = new ElementType();
                
                elementType.setType(elemj.getAttribute("type").toString());
                elementType.setBackground(elemj.getAttribute("background").toString());
                elementType.setJoinable(joinable);
                
                elementMap.put(elementType.getType(), new Element( elementType ));
            }
        }
    }	
	
}
