package com.rightmove.es.dto;

public class ScoreDto {

	private Long	scoreId;
	private String	playerName;
	private Long	scoreValue;

	public Long getScoreId() {
		return scoreId;
	}

	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Long getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(Long scoreValue) {
		this.scoreValue = scoreValue;
	}

}
