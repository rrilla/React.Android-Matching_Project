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
	
	public ResponseEntity<?> save(Team team, User user) {
		team.setOwner(user);
		teamRepository.save(team);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
}
