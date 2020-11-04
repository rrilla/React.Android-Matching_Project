package com.project.MatchingPro.controller;
	

import java.util.List;

import javax.servlet.http.HttpSession;	

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.battle.Battle;
import com.project.MatchingPro.domain.battle.BattleRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.service.BattleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BattleController {
	
	private final BattleService battleService;
	private final HttpSession session;
	private final BattleRepository battleRepository;
	
	
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
	
	//매칭 리스트
	@GetMapping("/battleList")
	public List<Battle> list(){
		return battleRepository.findAll();
	}
	
	//로그인한 유저의 팀의 매칭리스트
	@GetMapping("/user/loginBattleList")
	public List<Battle> loginBattleList (){
		User user = (User) session.getAttribute("principal");
		System.out.println("//////////////////////");
		
		System.out.println(user.getTeams().getId());
		System.out.println(battleRepository.mfindAll(user.getTeams().getId()));
		return battleRepository.mfindAll(user.getTeams().getId());
	}
}
