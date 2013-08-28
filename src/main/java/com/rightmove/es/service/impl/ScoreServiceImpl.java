package com.rightmove.es.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rightmove.es.dao.ScoreDao;
import com.rightmove.es.domain.Game;
import com.rightmove.es.domain.Player;
import com.rightmove.es.domain.Score;
import com.rightmove.es.dto.GameTopScoresDto;
import com.rightmove.es.dto.converter.GameTopScoresDtoConverter;
import com.rightmove.es.exception.GameScoreBusinessException;
import com.rightmove.es.service.GameService;
import com.rightmove.es.service.ScoreService;

@Component
@Transactional
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	private GameService	gameService;
	@Autowired
	private ScoreDao	scoreDao;
	@Autowired
	private GameTopScoresDtoConverter gameTopScoresDtoConverter;

	@Transactional(readOnly = true)
	@Override
	public Score createScore(Game game, Player player, Long scoreValue) {
		if (scoreValue < 0) {
			throw new GameScoreBusinessException("Score value can't be negative");
		}

		Score score = new Score();
		score.setDate(new Date());
		score.setGame(game);
		score.setPlayer(player);
		score.setScoreValue(scoreValue);

		scoreDao.createScore(score);

		return score;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Score> list(Game game, Player player) {
		return scoreDao.listAll(game, player);
	}

	@Override
	public GameTopScoresDto listTopScores(long gameId, int maxResults) {
		Game game = gameService.findByPk(gameId);
		if (game == null) {
			throw new GameScoreBusinessException("Game #" + gameId + " doesn't exist");
		}
		List<Score> topScores = scoreDao.findTopScores(maxResults, game);
		return gameTopScoresDtoConverter.convert(game, topScores);
	}
}
