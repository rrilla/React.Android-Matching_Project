package com.project.MatchingPro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.party.Party;
import com.project.MatchingPro.domain.party.PartyRepository;
import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.service.PartyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PartyController {

	private final PartyRepository partyRepository;
	private final HttpSession session;
	private final PartyService partyService;

	// 개인 -> 팀 에 가입 신청
	@PostMapping("/user/apply1/{teamid}")
	public ResponseEntity<?> soloApply(@PathVariable int teamid) {
		User user = (User) session.getAttribute("principal");
		return partyService.solosave(user, teamid);
	}
	
	//팀 -> 개인 에 가입요청
	@PostMapping("/user/apply2/{userid}")
	public ResponseEntity<?>  ownerApply(@PathVariable int userid){
		User user = (User) session.getAttribute("principal");
		return partyService.teamsave(user, userid);
	}
	
	//팀 리스트
	@GetMapping("/party")
	public List<Party> party(){
		return partyRepository.findAll();
	}
}
