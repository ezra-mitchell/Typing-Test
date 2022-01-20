package com.afs.ezra.simpleType.leaderboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LeaderboardPlaceDTO {

	private int place;
	private String name;
	private Double speed;
	private Double accuracy;

}