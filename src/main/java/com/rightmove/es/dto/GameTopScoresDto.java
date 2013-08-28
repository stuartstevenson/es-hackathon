package com.rightmove.es.dto;

import java.util.List;

public class GameTopScoresDto {

	private Long			gameId;
	private String			gameName;
	private List<ScoreDto>	scoresSorted;

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public List<ScoreDto> getScoresSorted() {
		return scoresSorted;
	}

	public void setScoresSorted(List<ScoreDto> scoresSorted) {
		this.scoresSorted = scoresSorted;
	}

}
