package com.triple.game.elements;

import com.triple.game.configs.Config;
import com.triple.game.configs.XmlReader;

import org.hamcrest.Matchers;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;

import static ch.lambdaj.Lambda.*;

public class ElementTypesCollection {
	
	private static ArrayList<ElementType> elementMap = new ArrayList<ElementType>();

    private HashMap<String, String> chanceContainer = new HashMap<String, String>();
    private ElementType element;
    private String elementTypeCount = "0";
    private int allHandElementCount = 0;
    private double counter;
    private double quotient;
    private double chance;

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
                elementType.setJoinable((elemj.getAttribute("joinable").equals("1")) ? true : false);
                elementType.setPlayable((elemj.getAttribute("playable").equals("1")) ? true : false);
                elementType.setContainer((elemj.getAttribute("container").equals("1")) ? true : false);
                elementType.setJoinResult(elemj.getAttribute("join_result").toString());
                elementType.setSubspecies(elemj.getAttribute("subspecies").toString());
                elementType.setScore(Integer.parseInt(elemj.getAttribute("score").toString()));
                elementType.setJoinScoreMultiplier(Double.parseDouble(elemj.getAttribute("join_score_multiplier").toString()));
                
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

    public Element getRandomForHand() {
        List<ElementType> eTs = filter(
                having(on(ElementType.class).getJoinable(), Matchers.equalTo(true))
                        .and(having(on(ElementType.class).getPlayable(), Matchers.equalTo(true)))
                        .and(having(on(ElementType.class).getType(), Matchers.isIn(Arrays.asList("base", "extended")))),
                elementMap);
        Random generator = new Random();
        Object[] values = eTs.toArray();
        allHandElementCount++;
        ElementType elementType = (ElementType) values[generator.nextInt(values.length)];

        elementTypeCount = chanceContainer.get( elementType.getName() );
        if (elementTypeCount == null){
            elementTypeCount = "0";
        }
        counter = Double.parseDouble( elementTypeCount );
        quotient =  counter / allHandElementCount;
        chance = Double.parseDouble( elementType.getChance() );

        if (quotient < chance){
            counter++;
            elementTypeCount = Double.toString( counter );
            chanceContainer.put( elementType.getName(), elementTypeCount );

            element = elementType;
        }
        else{
            allHandElementCount--;
            getRandomForHand();
        }

        return new Element(element);

    }


	public static ElementType getTypeById(String id) {
		List<ElementType> eTs = filter(having(on(ElementType.class).getId(), Matchers.equalTo(id)), elementMap);		
		if (eTs == null || eTs.size() == 0) return new ElementType();
		return eTs.get(0);		
	}	
	
}
