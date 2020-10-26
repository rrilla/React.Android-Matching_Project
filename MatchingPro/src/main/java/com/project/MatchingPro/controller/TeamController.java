package com.project.MatchingPro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TeamController {

	private final TeamRepository teamRepository;
	private final TeamService teamService;
	
	
	//팀생성
	@PostMapping("/post/join")
	public ResponseEntity<?> join(@RequestBody Team team){
		
		
		
		return teamService.join(team) ;
	}
}
