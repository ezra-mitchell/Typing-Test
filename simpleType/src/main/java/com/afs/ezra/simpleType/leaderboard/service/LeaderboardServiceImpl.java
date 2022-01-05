package com.afs.ezra.simpleType.leaderboard.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.afs.ezra.simpleType.leaderboard.dao.LeaderboardPlaceDao;
import com.afs.ezra.simpleType.leaderboard.dto.LeaderboardPlaceDTO;
import com.afs.ezra.simpleType.leaderboard.dto.TypedCharacterDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService{

	private final ObjectMapper objectMapper;
	private final LeaderboardPlaceDao leaderboard;

	@Override
	public List<LeaderboardPlaceDTO> postLeaderboardScore(String textJSON, String errorsJSON, String name, int age) throws JsonParseException, JsonMappingException, IOException {
		TypedCharacterDTO[] text = objectMapper.readValue(textJSON, TypedCharacterDTO[].class);
		TypedCharacterDTO[] errors = objectMapper.readValue(errorsJSON, TypedCharacterDTO[].class);
		
		//TODO (extra features) validate text and errors
		//calculating speed, by convention a "word" is any five characters
		
		double testLengthMinutes = text[text.length - 1].getTimeTyped() / 60;
		
		int errorsWithCorrection = (int) Arrays.stream(text).filter((typedChar) -> typedChar.getCharacter() != typedChar.getTyped()).count();

		double netWPM = ((text.length / 5) - errorsWithCorrection) / testLengthMinutes;
		
		double accuracy = (1- (double)errors.length / (double)text.length ) * 100;
		
		//TODO (for task 2) do real placements with database
		List<LeaderboardPlaceDTO> placement = new ArrayList<LeaderboardPlaceDTO>();
		placement.add(new LeaderboardPlaceDTO(1, name, netWPM, accuracy));
		return placement;
	}

	@Override
	public List<LeaderboardPlaceDTO> getTopLeaderboard(int length) {
		//TODO get real placements from database
		List<LeaderboardPlaceDTO> placement = new ArrayList<LeaderboardPlaceDTO>();
		placement.add(new LeaderboardPlaceDTO(1, "Example name 1", 120.4, 95.3));
		placement.add(new LeaderboardPlaceDTO(2, "Example name 2", 119.2, 95.3));
		return placement;
	}

}