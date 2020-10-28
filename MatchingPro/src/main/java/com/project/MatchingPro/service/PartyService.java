package com.project.MatchingPro.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.MatchingPro.domain.party.Party;
import com.project.MatchingPro.domain.party.PartyRepository;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PartyService {
	
	private final PartyRepository partyRepository;
	private final TeamRepository teamRepository;
	private final UserRepository userRepository;
	
	
	// user는 토큰으로 만든 완벽한 객체, id는 팀 아이디만, party는 빈 객체
	public ResponseEntity<?> solosave(User user, Party party, int teamid){
		try {
			party.setRoleNumber(0);	// 개인 -> 팀 요청
			party.setUser(user);
			party.setTeam(teamRepository.findById(teamid).orElseThrow(()-> new IllegalArgumentException(teamid+"는 존재하지 않습니다.")));
			partyRepository.save(party);
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("no",HttpStatus.EXPECTATION_FAILED);
		}
	}
		public ResponseEntity<?> teamsave(User user, int userid){
			try {
				Party party = new Party();
//				Party party = new Party().builder()
//						.roleNumber(1)
//						.user(user)
//						.team(teamRepository.findById(userid).orElseThrow(()-> new IllegalArgumentException(userid+"는 존재하지 않습니다.")))
//						.build();
				party.setRoleNumber(1);	// 팀 -> 개인 요청
				party.setUser(user);
				party.setTeam(teamRepository.findById(userid).orElseThrow(()-> new IllegalArgumentException(userid+"는 존재하지 않습니다.")));
				System.out.println("변수");
				partyRepository.save(party);
				return new ResponseEntity<String>("ok",HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<String>("no",HttpStatus.EXPECTATION_FAILED);
			}
	
		
		
	}
}
