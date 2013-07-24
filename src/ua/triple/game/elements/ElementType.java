package ua.triple.game.elements;

public class ElementType {

	private String type;
	private String background;
	private Boolean joinable = false;
	private String id;
	private int tile_x = -1;
	private int tile_y = -1;
	
	public String getId() {
		return id;
	}

	public String getBackground() {
		return background;
	}

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

	public void setId(String id) {
		this.id = id;
	}

	public void setTile(int tile_x, int tile_y) {
		setTile_x(tile_x);
		setTile_y(tile_y);
	}

	public int getTile_x() {
		return tile_x;
	}

	public void setTile_x(int x) {
		tile_x = x;
	}

	public int getTile_y() {
		return tile_y;
	}

	public void setTile_y(int y) {
		tile_y = y;
	}
}
