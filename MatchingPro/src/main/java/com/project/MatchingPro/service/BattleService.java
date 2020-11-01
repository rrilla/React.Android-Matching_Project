package com.project.MatchingPro.service;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.MatchingPro.domain.maching.Battle;
import com.project.MatchingPro.domain.maching.BattleRepository;
import com.project.MatchingPro.domain.teamInfo.TeamInfo;
import com.project.MatchingPro.domain.teamInfo.TeamInfoRepository;
import com.project.MatchingPro.domain.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BattleService {
	private final BattleRepository battleRepository;
	private final TeamInfoRepository teamInfoRepository;
	
	//teamInfo 아이디를 받아와서 베틀(매칭) 테이블에 저장
	public ResponseEntity<?> machingRegister(Battle battle,int infoid){
		TeamInfo teamInfoEntity = teamInfoRepository.findById(infoid).orElseThrow(()-> new IllegalArgumentException(infoid+"는 존재하지 않습니다."));
		battle.setTeamInfo1(teamInfoEntity);	
		battleRepository.save(battle);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
	//상대방이 수락시 팀인포에 팀이랑 나갈인원넣고 베틀테이블에 추가
	@Transactional
	public void registerResult(int battleid,int teamInfo2){
		Battle battleEntity = battleRepository.findById(battleid).get();
		battleEntity.setTeamInfo2(teamInfoRepository.findById(teamInfo2).orElseThrow(()-> new IllegalArgumentException(teamInfo2+"는 존재하지 않습니다.")));
	}
	
	public ResponseEntity<?> TeamInfo2Register(TeamInfo teaminfo2) {
		teamInfoRepository.save(teaminfo2);
		return new ResponseEntity<String>("ok11",HttpStatus.OK);
	}
}
