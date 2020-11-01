package com.project.MatchingPro.controller;
	

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.maching.Battle;
import com.project.MatchingPro.domain.teamInfo.TeamInfo;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.service.BattleService;
import com.project.MatchingPro.service.TeamInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BattleController {
	
	private final BattleService battleService;
	private final TeamInfoService teamInfoService;
	private final HttpSession session;
	
	//Teaminfo 아이디를 받아와서 베틀(매칭) 테이블에 등록하는 컨트롤러.
	@PostMapping("/user/maching/{infoid}") 
	public ResponseEntity<?> register(@RequestBody Battle battle,@PathVariable int infoid){
		//User user = (User) session.getAttribute("principal");
		
		return battleService.machingRegister(battle,infoid);
	}
	
	
	//상대방이 수락시 팀인포에 팀이랑 나갈인원넣고 베틀테이블에 추가
	@PutMapping("/user/maching/{battleid}")
	public ResponseEntity<?> registerResult(@PathVariable int battleid, @RequestBody TeamInfo teamInfo2){
		//User user = (User) session.getAttribute("principal");
		System.out.println("adfasfasdfas");
		battleService.TeamInfo2Register(teamInfo2);//팀2인포 등록
		battleService.registerResult(battleid,teamInfo2.getId());
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
}
