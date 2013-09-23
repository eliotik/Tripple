package com.triple.game.player;

import com.triple.game.elements.Element;

import java.net.InetAddress;

public class Player {

	private int id;
	private String name = "";
	private PlayerHand hand = new PlayerHand();
	private PlayerScore score = new PlayerScore();
	private InetAddress inetAddress;

	public Player(String name, Element element) {
		this.id = 0;
		this.name = name;
		hand.setElement(element);
	}

	public Player(int id, String name, Element element) {
		this.id = id;
		this.name = name;
		hand.setElement(element);
	}	
	
	public Player(String name) {
		this.id = 0;
		this.name = name;
		hand.setElement(null);
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

	public PlayerScore getScore() {
		return score;
	}

	public void setScore(PlayerScore score) {
		this.score = score;
	}

	public Player setId(int id) {
		this.id = id;
		return this;
	}

    public int getId() {

		return id;
	}

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }
	
}
