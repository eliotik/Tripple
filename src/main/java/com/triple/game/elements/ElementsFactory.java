package com.triple.game.elements;

import org.apache.log4j.Logger;


public class ElementsFactory {
	private static final Logger log = Logger.getLogger(ElementsFactory.class); 
	
	public static Element getElement(ElementType type) {
		if (type == null) {
			return null;
		}

		try {
			String elementClass = "com.triple.game.elements.subspecies.Element"
					+ type.getSubspecies().substring(0, 1).toUpperCase()
					+ type.getSubspecies().substring(1);
			Class<?> cls = Class.forName(elementClass);
			Element element = (Element) cls.newInstance();
			element.setType(type);
			return element;
		} catch (Exception e) {
			if (log.isDebugEnabled()){
				log.debug("Could not initialize element: " + type.getSubspecies());
			}
		}

		return null;
	}
}
