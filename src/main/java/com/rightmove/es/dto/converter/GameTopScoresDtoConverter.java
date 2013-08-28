package com.rightmove.es.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rightmove.es.domain.Game;
import com.rightmove.es.domain.Score;
import com.rightmove.es.dto.GameTopScoresDto;
import com.rightmove.es.dto.ScoreDto;
import com.rightmove.es.exception.GameScoreBusinessException;

@Component
public class GameTopScoresDtoConverter {

	public GameTopScoresDto convert(Game game, List<Score> topScores) {
		if(game == null) {
			throw new GameScoreBusinessException("game is null");
		}
		if(topScores == null) {
			throw new GameScoreBusinessException("topScores is null");
		}
		
		List<ScoreDto> topScoreDtos = new ArrayList<ScoreDto>();

		GameTopScoresDto gameTopScoresDto = new GameTopScoresDto();
		gameTopScoresDto.setGameId(game.getId());
		gameTopScoresDto.setGameName(game.getName());

		for (Score score : topScores) {
			ScoreDto scoreDto = new ScoreDto();
			scoreDto.setPlayerName(score.getPlayer().getName());
			scoreDto.setScoreId(score.getId());
			scoreDto.setScoreValue(score.getScoreValue());
			topScoreDtos.add(scoreDto);
		}

		gameTopScoresDto.setScoresSorted(topScoreDtos);
		return gameTopScoresDto;
	}

}
