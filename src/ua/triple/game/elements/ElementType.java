package ua.triple.game.elements;

public class ElementType {

	private String type;
	private String background;
	
	public String getBackground() {
		return background;
	}

	private Boolean joinable;

    public Boolean getJoinable() {
		return joinable;
	}

	public ElementType() {

    }

	public void setType(String type) {
		this.type = type;
	}

	public void setBackground(String bg) {
		background = bg;
	}

	public void setJoinable(Boolean joinable) {
		this.joinable = joinable;
	}

	public String getType() {
		return type;
	}
}
