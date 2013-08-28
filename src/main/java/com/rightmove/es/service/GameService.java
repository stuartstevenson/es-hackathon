package com.rightmove.es.service;

import java.util.Collection;

import com.rightmove.es.domain.Game;

public interface GameService {

	Collection<Game> listAll();

	Collection<Game> findByName(String name);

	Game createGame(String name);

	Game findByPk(Long pk);

	// TODO remove
	void createRandomGame();

}
