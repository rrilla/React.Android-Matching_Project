package com.project.MatchingPro.domain.teamInfo;

import java.util.List;	

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.MatchingPro.domain.battle.Battle;
import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TeamInfo {
	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	
	@OneToOne
	private Team team;
	@OneToOne
	private User user1;
	@OneToOne
	private User user2;
	@OneToOne
	private User user3;
	@OneToOne
	private User user4;
	@OneToOne
	private User user5;
	@OneToOne
	private User user6;
	@OneToOne
	private User user7;
	@OneToOne
	private User user8;
	@OneToOne
	private User user9;
	@OneToOne
	private User user10;
	@OneToOne
	private User user11;
	
//	@JsonIgnoreProperties({"teaminfo"})
//	@OneToMany(mappedBy = "teaminfo", fetch = FetchType.LAZY)//LAZY
//	private List<User> users;
	
	/////////////////////////////////
	@JsonIgnoreProperties({"teamInfo1"})
	@OneToMany(mappedBy = "teamInfo1", fetch = FetchType.LAZY)//LAZY
	private List<Battle> teamInfo1; 
	 
	@JsonIgnoreProperties({"teamInfo2"}) 
	@OneToMany(mappedBy = "teamInfo2", fetch = FetchType.LAZY)//LAZY
	private List<Battle> teamInfo2;
}
