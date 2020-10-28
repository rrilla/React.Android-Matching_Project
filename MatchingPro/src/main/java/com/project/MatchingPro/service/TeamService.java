package com.project.MatchingPro.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamService {

	private final TeamRepository teamRepository;
	private final UserRepository userRepository;
	
	// 팀 생성
	public ResponseEntity<?> save(Team team, User user) {
		try {
			team.setOwner(user);
			teamRepository.save(team);
			System.out.println("팀 생성 성공");
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("회원가입 실패");
			return new ResponseEntity<String>("no",HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	//팀 가입하자이 (이거를 팀장이 승인하면 이걸 실행하고)
	@Transactional
	public ResponseEntity<?> teamJoin(User user, int teamid){
		
		User userEntity =userRepository.findById(user.getId()).orElseThrow(()-> new IllegalArgumentException(teamid+"는 존재하지 않습니다."));
		userEntity.setTeams(teamRepository.findById(teamid).orElseThrow(()-> new IllegalArgumentException(teamid+"는 존재하지 않습니다.")));
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	//팀상세보기
	public ResponseEntity<?> detail(int id){
		teamRepository.findById(id);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
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
