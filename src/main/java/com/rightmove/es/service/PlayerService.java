package com.rightmove.es.service;

import java.util.List;

import com.rightmove.es.domain.Player;

public interface PlayerService {

	/**
	 * Lists all Players with paging options.
	 * 
	 * @param totalPerPage total to be displayed per page
	 * @param pageNumber the page number offset
	 * @return list of Players
	 */
	List<Player> listAll(int totalPerPage, int pageNumber);

	Player findByEmail(String email);

	Player createPlayer(String name, String email);

	Player createPlayerAndScore(String playerName, String email, Long gameId, Long scoreValue);

}
