package com.project.MatchingPro.domain.score;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.project.MatchingPro.domain.team.Team;

import lombok.Data;

@Data
@Entity
public class Score {
	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	private int win;
	private int draw;
	private int lose;
	
	@OneToMany
	private List<Team> score; 
}
