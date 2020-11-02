package com.project.MatchingPro.controller;
	

import javax.servlet.http.HttpSession;	

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.battle.Battle;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.service.BattleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BattleController {
	
	private final BattleService battleService;
	private final HttpSession session;
	
	
	//매칭신청을 눌렀을 때
	//Teaminfo 아이디를 받아와서 베틀(매칭) 테이블에 등록하는 컨트롤러.
	@PostMapping("/user/matchApply/{teamid}") 
	public ResponseEntity<?> matchApply(@RequestBody Battle battle,@PathVariable int teamid){
		User user = (User) session.getAttribute("principal");	
		return battleService.matchApply(battle,teamid,user);
	}
	
	//신청받은 팀이 자신이 등록은 팀인포를 등록하고 수락하는 컨트롤러.
	@PutMapping("/user/matchAccept/{battleid}")
	public ResponseEntity<?> matchAccept(@PathVariable int battleid){
		User user = (User) session.getAttribute("principal");
		return battleService.matchAccept(user,battleid);
	}
}
