package com.project.MatchingPro.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TeamService {
	private final TeamRepository teamRepository;
	private final UserRepository userRepository;
	
	public ResponseEntity<?>join(Team team){
		
		User userEntity = userRepository.findById(1).get();
		
		team.setRole("Reader");
		team.setUser(userEntity);
		teamRepository.save(team);
		return new ResponseEntity<String>("ok",HttpStatus.CREATED);
	}
	
}
