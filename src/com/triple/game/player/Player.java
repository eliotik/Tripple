package com.triple.game.player;

import com.triple.game.elements.Element;

public class Player {

	private String name = "";
	private PlayerHand hand = new PlayerHand();
	private int score;
	
	public Player(String name, Element element) {
		this.name = name;
		hand.setElement(element);
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

	public PlayerHand getHand() {
		return hand;
	}

	public void setHand(PlayerHand hand) {
		this.hand = hand;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}