package com.triple.game.elements;

public class ElementsFactory {
	public static Element getElement(ElementType type) {
		if (type == null) return null;
		
		Element element = null;
		
		try {
			
			String elementClass = "com.triple.game.elements.subspecies.Element"+type.getSubspecies().substring(0,1).toUpperCase()+type.getSubspecies().substring(1);
			Class<?> cls = Class.forName(elementClass);
			element = (Element) cls.newInstance();
			element.setType(type);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return element;
	}
}
