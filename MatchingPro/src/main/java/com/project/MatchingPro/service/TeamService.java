package com.project.MatchingPro.service;
	
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.MatchingPro.domain.party.Party;
import com.project.MatchingPro.domain.party.PartyRepository;
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
	private final PartyRepository partyRepository;
	
	// 팀 생성
		public ResponseEntity<?> save(User user, Team team) {
			try {
				team.setOwner(user);
				teamRepository.save(team);
				// 여기서 그 쿼리 써서 팀에 teamId를 넣으면
				//  유저의 팀에 team id가 들어가니까 

				System.out.println("팀 생성 성공");
				return new ResponseEntity<String>("ok", HttpStatus.OK);
			} catch (Exception e) {
				System.out.println("팀생성 실패");
				return new ResponseEntity<String>("no",HttpStatus.OK);
			}
		}
	
	@Transactional
	public void TeamsRegister(User user, int teamId) {
		User realUser = userRepository.findById(user.getId()).orElseThrow(()-> new IllegalArgumentException(teamId+"는 존재하지 않습니다."));
		realUser.setTeams(teamRepository.findById(teamId).get());
	}
		
	
	//팀 가입 요청시 수락
	@Transactional
	public ResponseEntity<?> teamJoin(int partyid){
	
		Party partyEntity = partyRepository.findById(partyid).orElseThrow(()-> new IllegalArgumentException(partyid+"는 존재하지 않습니다."));
			
		User userEntity = partyEntity.getUser();
		userEntity.setTeams(partyEntity.getTeam());
		//수락을 했으면 파티테이블에 있는 해당 데이터 삭제
		partyRepository.deleteById(partyid);
			return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	
	//팀상세보기
	public ResponseEntity<?> detail(int id){
		Team team = teamRepository.findById(id).get();
		
		return new ResponseEntity<Team>(team ,HttpStatus.OK);
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
	
	// 로그인한 유저의 팀 아이디값 리턴기능
	public ResponseEntity<?> myTeam(User user){
		
		return new ResponseEntity<Integer>(user.getTeams().getId(),HttpStatus.OK);
		
	}
  
}