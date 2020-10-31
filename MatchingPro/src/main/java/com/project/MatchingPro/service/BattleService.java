package com.project.MatchingPro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.MatchingPro.domain.maching.Battle;
import com.project.MatchingPro.domain.maching.BattleRepository;
import com.project.MatchingPro.domain.party.PartyRepository;
import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.teamInfo.TeamInfo;
import com.project.MatchingPro.domain.teamInfo.TeamInfoRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BattleService {
	private final BattleRepository battleRepository;
	private final TeamRepository teamRepository;
	private final UserRepository userRepositoty;
	private final TeamInfoRepository teamInfoRepository;
	
	public ResponseEntity<?> register(int infoid){
		
		Battle battle = new Battle();
		TeamInfo teamInfoEntity = teamInfoRepository.findById(infoid).orElseThrow(()-> new IllegalArgumentException(infoid+"는 존재하지 않습니다."));
		battle.setTeamInfo1(teamInfoEntity);	
		battleRepository.save(battle);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
}
