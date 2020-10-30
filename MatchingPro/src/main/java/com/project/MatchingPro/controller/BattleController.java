package com.project.MatchingPro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.maching.Battle;
import com.project.MatchingPro.domain.party.PartyRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.service.BattleService;
import com.project.MatchingPro.service.PartyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BattleController {
	
	private final BattleService battleService;
	private final HttpSession session;
	
	@PostMapping("/user/battle/{teamid}")
	public ResponseEntity<?> Register(@RequestBody Battle battle,@PathVariable int teamid){
		User user = (User) session.getAttribute("principal");
		
		return battleService.register(battle,user.getTeams(), teamid);
	}
	
}
