package com.project.MatchingPro.domain.user;

import java.sql.Timestamp;	
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.MatchingPro.domain.Member.Member;
import com.project.MatchingPro.domain.position.Position;
import com.project.MatchingPro.domain.team.Team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class User {
	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) //해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	@Column(unique = true, nullable = false)
	private String loginid;
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(unique = true, nullable = false)
	private String nickname;
	private String email;
	private String phone;
	private String location;
	private String role; // 권한
	@CreationTimestamp	//default 현재시간 자동 적용
	private Timestamp joindate;
	
//	@JoinColumn(name="teamid")
//	@ManyToOne(fetch = FetchType.EAGER)
//	private Team team;
	
	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy = "user")
	private List<Team> teams;

//	@JsonIgnoreProperties({"user"})
//	@OneToMany(mappedBy = "user")	// Post obj의 user 변수 (post에서 fk로 적어놓은 변수명) 
//	private List<Member> members;
	
//	@JsonIgnoreProperties({"user"})
//	@OneToMany(mappedBy = "user")
//	private List<Position> positions;
	
	public String getDate() {
		Timestamp time = this.getJoindate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(time);
	}
}
