package com.rightmove.es.dao;

import java.util.Collection;
import java.util.List;

import com.rightmove.es.domain.Game;

public interface GameDao {

	void addGame(Game game);

	List<Game> listAll();

	Game findByPk(Long pk);

	Collection<Game> findByName(String name);

}
