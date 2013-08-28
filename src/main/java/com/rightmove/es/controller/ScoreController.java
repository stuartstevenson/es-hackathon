package com.rightmove.es.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rightmove.es.dto.GameTopScoresDto;
import com.rightmove.es.service.ScoreService;

@Controller
@RequestMapping("/scores")
public class ScoreController {

	@Autowired
	private ScoreService	scoreService;

	@RequestMapping(value = "/top-scores/{gameId}", method = RequestMethod.GET)
	public @ResponseBody GameTopScoresDto getTopScores(
			@PathVariable("gameId") long gameId, 
			@RequestParam(value = "maxResults", defaultValue = "10") int maxResults) {
		// TODO property for maxResults (or at least constant)
		return scoreService.listTopScores(gameId, maxResults);
	}
}
