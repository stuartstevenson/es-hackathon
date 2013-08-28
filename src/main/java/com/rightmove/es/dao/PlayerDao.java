package com.rightmove.es.dao;

import java.util.List;

import com.rightmove.es.domain.Player;

public interface PlayerDao {

	List<Player> listAll(int totalPerPage, int pageNumber);

	Player findByEmail(String email);

	Player createPlayer(Player player);

}
