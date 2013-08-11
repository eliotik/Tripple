package com.triple.game.player;

import static ch.lambdaj.Lambda.filter;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;

public class Players {
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public void add(Player player) {
		players.add(player.setId(players.size()));
	}
	
	public Player get(int id) {
		List<Player> player = filter(having(on(Player.class).getId(), Matchers.equalTo(id)),
				players);
		return (player.size() > 0) ? player.get(0) : null;
	}
	
	public Player get(String name) {
		List<Player> player = filter(having(on(Player.class).getName(), Matchers.equalTo(name)),
				players);
		return (player.size() > 0) ? player.get(0) : null;
	}
	
	public int count() {
		return players.size();
	}
	
	public void clear() {
		players.clear();
	}
}
