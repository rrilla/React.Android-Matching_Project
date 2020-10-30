package com.project.MatchingPro.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.MatchingPro.domain.maching.Battle;
import com.project.MatchingPro.domain.maching.BattleRepository;
import com.project.MatchingPro.domain.party.PartyRepository;
import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BattleService {
	private final BattleRepository battleRepository;
	private final TeamRepository teamRepository;
	
	public ResponseEntity<?> register(Battle battle,Team team1, int teamid){
		battle.setTeam1(team1);
		Team team2 =teamRepository.findById(teamid).orElseThrow(()-> new IllegalArgumentException(teamid+"는 존재하지 않습니다."));
		battle.setTeam2(team2);
		battleRepository.save(battle);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
}
