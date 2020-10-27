package com.project.MatchingPro.domain.team;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.MatchingPro.domain.member.Member;
import com.project.MatchingPro.domain.user.User;

import lombok.Data;

@Entity
@Data
public class Team {

	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) //해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	private String content;	//팀설명
	
	@ManyToOne
	private User owner;
	
	@JsonIgnoreProperties({"team","member"})
	@OneToMany(mappedBy = "team")
	private List<Member> members;
}
