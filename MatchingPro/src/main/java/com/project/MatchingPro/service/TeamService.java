package com.project.MatchingPro.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamService {

	private final TeamRepository teamRepository;
	
	// 팀 생성
	public ResponseEntity<?> save(Team team, User user) {
		team.setOwner(user);
		teamRepository.save(team);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	

	//팀이름 체크
	public ResponseEntity<?> nameCheck(String name){
		int n = teamRepository.countByName(name);
		if(n == 0) {
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("no", HttpStatus.OK);
		}
	}
  
}
