package com.project.MatchingPro.domain.maching;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.project.MatchingPro.domain.team.Team;

import lombok.Data;

@Data
@Entity
public class Battle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터베이스 번호증가 전략을 따라가기
	private int id;

	@JoinColumn(name = "team1")
	 @ManyToOne
	 private Team team1;
	 	
	@JoinColumn(name = "team2")
	 @ManyToOne
	  private Team team2;
	 
	private String location;

	private String matchDate;
	private String info;
}
