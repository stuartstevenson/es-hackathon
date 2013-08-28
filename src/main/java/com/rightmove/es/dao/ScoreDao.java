package com.rightmove.es.dao;

import java.util.List;

import com.rightmove.es.domain.Game;
import com.rightmove.es.domain.Player;
import com.rightmove.es.domain.Score;

public interface ScoreDao {
	
	List<Score> findTopScores(int maxResults, Game game);
	
	Score createScore(Score score);

	List<Score> listAll(Game game, Player player);

	List<Score> listAll(Game game);

}
