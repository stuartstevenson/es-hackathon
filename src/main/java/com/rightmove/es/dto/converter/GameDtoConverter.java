package com.rightmove.es.dto.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rightmove.es.domain.Game;
import com.rightmove.es.dto.GameDto;

@Component
public class GameDtoConverter {

	public GameDto convert(Game game) {
		GameDto gameDto = new GameDto();
		gameDto.setId(game.getId());
		gameDto.setName(game.getName());
		return gameDto;
	}
	
	public Collection<GameDto> convert(Collection<Game> games) {
		List<GameDto> dtos = new ArrayList<GameDto>();
		if(games == null) {
			return dtos;
		}
		for (Game game : games) {
			dtos.add(convert(game));
		}
		return dtos;
	}
	
}
