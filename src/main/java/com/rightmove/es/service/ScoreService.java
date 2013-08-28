package com.rightmove.es.service;

import java.util.List;

import com.rightmove.es.domain.Game;
import com.rightmove.es.domain.Player;
import com.rightmove.es.domain.Score;
import com.rightmove.es.dto.GameTopScoresDto;

public interface ScoreService {

//	Score findBestScore(Game game, Date dateBegin, Date dateEnd);
//
//	Score findBestScore(Game game, Player player);
//
//	Score findBestScore(Game game, Player player, Date date);
//
//	Score findBestScore(Game game, Player player, Date dateBegin, Date dateEnd);

	Score createScore(Game game, Player player, Long scoreValue);

	List<Score> list(Game game, Player player);

	GameTopScoresDto listTopScores(long gameId, int maxResults);

}
