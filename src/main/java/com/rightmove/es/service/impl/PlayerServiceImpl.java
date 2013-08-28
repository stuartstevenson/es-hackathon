package com.rightmove.es.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rightmove.es.dao.PlayerDao;
import com.rightmove.es.domain.Game;
import com.rightmove.es.domain.Player;
import com.rightmove.es.exception.GameScoreBusinessException;
import com.rightmove.es.service.GameService;
import com.rightmove.es.service.PlayerService;
import com.rightmove.es.service.ScoreService;

@Component
@Transactional
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerDao		playerDao;

	@Autowired
	private ScoreService	scoreService;

	@Autowired
	private GameService		gameService;

	@Transactional(readOnly = true)
	@Override
	public List<Player> listAll(int totalPerPage, int pageNumber) {
		if (totalPerPage < 1) {
			throw new GameScoreBusinessException("totalPerPage must be greater than 0, received " + totalPerPage);
		}
		if (pageNumber < 1) {
			throw new GameScoreBusinessException("pageNumber must be greater than 0, received " + pageNumber);
		}
		return playerDao.listAll(totalPerPage, pageNumber);
	}

	@Transactional(readOnly = true)
	@Override
	public Player findByEmail(String email) {
		return playerDao.findByEmail(email);
	}

	@Override
	public Player createPlayer(String name, String email) {
		if (findByEmail(email) != null) {
			throw new GameScoreBusinessException("email " + email + " is already in use");
		}
		Player player = new Player();
		player.setName(name);
		player.setEmail(email);
		return playerDao.createPlayer(player);
	}

	@Override
	public Player createPlayerAndScore(String playerName, String email, Long gameId, Long scoreValue) {
		Player player = this.createPlayer(playerName, email);
		Game game = gameService.findByPk(gameId);
		if (game == null) {
			throw new GameScoreBusinessException("Game doesn't exist");
		}
		scoreService.createScore(game, player, scoreValue);
		return player;
	}
}
