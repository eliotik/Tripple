package com.triple.game.elements;

import org.junit.Assert;
import org.junit.Test;

public class TestElementsFactory {

	@Test
	public void testGetElement() {

		ElementType type = new ElementType();
		type.setSubspecies("bush");
		Element element = ElementsFactory.getElement(type);

		Assert.assertNotNull(element);
	}
	
	@Test
	public void testGetElementNull() {

		ElementType type = new ElementType();
		type.setSubspecies("bush-bush");
		Element element = ElementsFactory.getElement(type);

		Assert.assertNull(element);
	}
}
