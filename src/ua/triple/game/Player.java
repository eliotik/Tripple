package ua.triple.game;

import ua.triple.game.elements.Element;

public class Player {

	private String name = "";
	private Element hand;
	private int score;
	
	public Player(String name, Element hand) {
		this.name = name;
		this.hand = hand;
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Element getHand() {
		return hand;
	}

	public void setHand(Element hand) {
		this.hand = hand;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
