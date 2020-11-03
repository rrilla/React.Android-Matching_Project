package com.project.MatchingPro.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.MatchingPro.domain.battle.Battle;
import com.project.MatchingPro.domain.battle.BattleRepository;
import com.project.MatchingPro.domain.score.ScoreRepository;
import com.project.MatchingPro.domain.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScoreService {

	private final ScoreRepository scoreRepository;
	private final BattleRepository battleRepository;
	
	@Transactional
	public ResponseEntity<?> scoreRegister(User user, int battleid){
		Battle battleEntity=battleRepository.findById(battleid).get();
		battleEntity.getTeam1().getScore().setWin(user.getTeams().getScore().getWin()+1);
		battleEntity.getTeam2().getScore().setLose(battleEntity.getTeam2().getScore().getLose()+1);
		//승무패 변경하고 배틀테이블 삭제
		battleRepository.deleteById(battleid);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<?> scoreDraw(User user, int battleid){
		Battle battleEntity=battleRepository.findById(battleid).get();
		battleEntity.getTeam1().getScore().setDraw(user.getTeams().getScore().getDraw()+1);
		battleEntity.getTeam2().getScore().setDraw(battleEntity.getTeam2().getScore().getDraw()+1);
		//승무패 변경하고 배틀테이블 삭제
		battleRepository.deleteById(battleid);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<?> scoreLose(User user, int battleid){
		Battle battleEntity=battleRepository.findById(battleid).get();
		battleEntity.getTeam1().getScore().setLose(user.getTeams().getScore().getLose()+1);
		battleEntity.getTeam2().getScore().setWin(battleEntity.getTeam2().getScore().getWin()+1);
		//승무패 변경하고 배틀테이블 삭제
		battleRepository.deleteById(battleid);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	

}
