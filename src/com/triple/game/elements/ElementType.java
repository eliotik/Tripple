package com.triple.game.elements;

public class ElementType {

	private String name;
	private String type;
	private Boolean joinable = false;
	private Boolean playable = false;
    private Boolean container = false;
	private String id = "";
    private String chance;
	private int tile_x = -1;
	private int tile_y = -1;
	private String joinResult;
	private String subspecies;
	private int score;
	private double joinScoreMultiplier;
	
	public String getId() {
		return id;
	}

    public Boolean getJoinable() {
		return joinable;
	}

	public ElementType() {

    }

	public void setType(String type) {
		this.type = type;
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

    public void setChance(String chance){
        this.chance = chance;
    }

    public String getChance(){
        return chance;
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

	public Boolean getPlayable() {
		return playable;
	}

	public void setPlayable(Boolean playable) {
		this.playable = playable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Boolean getContainer() {
        return container;
    }

    public void setContainer(Boolean container) {
        this.container = container;
    }

    public void setJoinResult(String joinResult) {
    	this.joinResult = joinResult;
    }
    
	public String getJoinResult() {
		return joinResult;
	}
	
	public String getSubspecies() {
		return this.subspecies;
	}

	public void setSubspecies(String subspecies) {
		this.subspecies = subspecies;
	}

	public double getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getJoinScoreMultiplier() {
		return joinScoreMultiplier;
	}

	public void setJoinScoreMultiplier(double joinScoreMultiplier) {
		this.joinScoreMultiplier = joinScoreMultiplier;
	}

}
