package com.rightmove.es.service.impl;

import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rightmove.es.dao.GameDao;
import com.rightmove.es.domain.Game;
import com.rightmove.es.domain.Player;
import com.rightmove.es.exception.GameScoreBusinessException;
import com.rightmove.es.service.GameService;
import com.rightmove.es.service.PlayerService;
import com.rightmove.es.service.ScoreService;

@Component
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	private ScoreService	scoreService;
	@Autowired
	private PlayerService	playerService;
	@Autowired
	private GameDao			gameDao;

	@Transactional(readOnly = true)
	@Override
	public Collection<Game> listAll() {
		return gameDao.listAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<Game> findByName(String name) {
		return gameDao.findByName(name);
	}

	@Override
	public Game createGame(String name) {
		if (!findByName(name).isEmpty()) {
			throw new GameScoreBusinessException("Game '" + name + "' already exists");
		}
		Game game = new Game();
		game.setName(name);
		gameDao.addGame(game);
		return game;
	}

	@Transactional(readOnly = true)
	@Override
	public Game findByPk(Long pk) {
		return gameDao.findByPk(pk);
	}

	// TODO test method
	@Scheduled(initialDelay = 0, fixedDelay = 10000)
	@Override
	public void createRandomGame() {
		Game game = createGame("game " + new Random().nextInt(1000000));
		System.out.println("Creating random game #" + game.getId());
		Player player1 = playerService.createPlayer("player name " + new Random().nextInt(1000000), "test" + new Random().nextInt(1000000)
				+ "@test.com");
		Player player2 = playerService.createPlayer("player name " + new Random().nextInt(1000000), "test" + new Random().nextInt(1000000)
				+ "@test.com");
		scoreService.createScore(game, player1, Math.abs(new Random().nextLong()));
		scoreService.createScore(game, player1, Math.abs(new Random().nextLong()));
		scoreService.createScore(game, player1, Math.abs(new Random().nextLong()));
		scoreService.createScore(game, player1, Math.abs(new Random().nextLong()));
		scoreService.createScore(game, player2, Math.abs(new Random().nextLong()));
		scoreService.createScore(game, player2, Math.abs(new Random().nextLong()));
		scoreService.createScore(game, player2, Math.abs(new Random().nextLong()));
		scoreService.createScore(game, player2, Math.abs(new Random().nextLong()));
	}
}
