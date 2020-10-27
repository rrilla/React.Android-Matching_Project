package com.project.MatchingPro.domain.member;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.user.User;

import lombok.Data;

@Entity
@Data
public class Member {
	
	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) //해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	
	@ManyToOne
	private User member;
	
	@ManyToOne
	private Team team;
}
