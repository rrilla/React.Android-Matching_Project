package com.project.MatchingPro.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.MatchingPro.domain.party.Party;
import com.project.MatchingPro.domain.party.PartyRepository;
import com.project.MatchingPro.domain.team.Team;
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
	public ResponseEntity<?> solosave(User user, int teamid){
		try {
			Party party = new Party();
			party.setRoleNumber(0);	// 개인 -> 팀 요청
			party.setUser(user);
			party.setTeam(teamRepository.findById(teamid).orElseThrow(()-> new IllegalArgumentException(teamid+"는 존재하지 않습니다.")));
			partyRepository.save(party);
			
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("no",HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	//팀 -> 개인 초대
	public ResponseEntity<?> teamsave(User user, int userid){
		System.out.println("durldha1");
		try {
			System.out.println(user.getTeams().getId());
			Team team = teamRepository.findById(user.getTeams().getId()).orElseThrow(()-> new IllegalArgumentException(userid+"는 존재하지 않습니다."));
			User userEntity = userRepository.findById(userid).orElseThrow(()-> new IllegalArgumentException(userid+"는 존재하지 않습니다."));
			System.out.println("zz");
			Party party = new Party();
			party.setRoleNumber(1);	// 팀 -> 개인 요청
			party.setUser(userEntity);
			party.setTeam(team);
			partyRepository.save(party);
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}catch(Exception e) {
			System.out.println("durldha2");
			return new ResponseEntity<String>("no",HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	public ResponseEntity<?> partyInfo(int partyid){
		Party partyEntity = partyRepository.findById(partyid).orElseThrow(()-> new IllegalArgumentException(partyid+"는 존재하지 않습니다."));
		return new ResponseEntity<Party>(partyEntity,HttpStatus.OK);
	}
}
