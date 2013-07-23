package ua.triple.game.elements;

public class Element {

    private ElementType type;
    

    public Element(ElementType type) {
        this.setType(type);
    }

	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
		this.type = type;
	}

}