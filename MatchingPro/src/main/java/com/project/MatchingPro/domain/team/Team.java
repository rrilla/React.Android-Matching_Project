package com.project.MatchingPro.domain.team;

import java.util.List;	

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.project.MatchingPro.domain.maching.Battle;
import com.project.MatchingPro.domain.party.Party;
import com.project.MatchingPro.domain.teamInfo.TeamInfo;
import com.project.MatchingPro.domain.user.User;

import lombok.Data;

@Entity
@Data
public class Team {

	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터베이스 번호증가 전략을 따라가기
	private int id;

	@Column(unique = true, nullable = false)
	private String name;


	@Column(nullable = false)
	private String explaintation; // 팀설명
	
	@Column(length = 1000000000)
	private String image;
	
	@JsonIgnoreProperties({"teams"})
	@OneToOne
	private User owner;

	@JsonIgnoreProperties({"teams","partys"})
	@OneToMany(mappedBy = "teams", fetch = FetchType.LAZY)//LAZY
	private List<User> users;
	
	@JsonIgnoreProperties({"team","user"})
	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)//LAZY
	private List<Party> partys;
	
//	////////////////////////////////////////////////////
	
//	@JsonIgnoreProperties({"team1"})
//	@OneToMany(mappedBy = "team1", fetch = FetchType.LAZY)//LAZY
//	private List<Battle> battle1;
//	
//	
//	 @JsonIgnoreProperties({"team2"})
//	 @OneToMany(mappedBy = "team2", fetch = FetchType.LAZY)//LAZY private
//	  List<Battle> battle2;
	 
}