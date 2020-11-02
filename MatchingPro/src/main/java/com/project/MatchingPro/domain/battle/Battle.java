package com.project.MatchingPro.domain.battle;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.teamInfo.TeamInfo;

import lombok.Data;

@Data
@Entity
public class Battle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	
	@JoinColumn(name = "users")
	@ManyToOne
	private TeamInfo teamInfo1;
	
	@JoinColumn(name = "users2")
	@ManyToOne
	private TeamInfo teamInfo2;
	
	private String location;

	private String matchDate;
	
	private String info; 
	private String role; //0 일때 신청 1일때 수락 2일때 거절
	
	//Fk키            //ref : team테이블
	@JoinColumn(name = "team1")
	 @ManyToOne
	private Team team1;
	 
	//Fk키            //ref : team테이블
	@JoinColumn(name = "team2")
	 @ManyToOne
	 private Team team2;

}
