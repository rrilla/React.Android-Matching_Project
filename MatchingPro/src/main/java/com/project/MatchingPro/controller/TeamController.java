package com.project.MatchingPro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TeamController {

	private final TeamService teamService;
	private final TeamRepository teamRepository;
	private final HttpSession session;
	
	@PostMapping("/user/create")
	public ResponseEntity<?> create(@RequestBody Team team){
		User user = (User)session.getAttribute("principal");
		return teamService.save(team, user);
	}
	
	@GetMapping("/teamList")
	public List<Team> teamList(){
		return teamRepository.findAll();
	}
}
