package com.project.MatchingPro.domain.user;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.MatchingPro.domain.member.Member;
import com.project.MatchingPro.domain.position.Position;
import com.project.MatchingPro.domain.team.Team;

import lombok.Data;

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
	
	@JsonIgnoreProperties({"owner"})
	@OneToMany(mappedBy = "owner")
	private List<Team> teams;
	
	@JsonIgnoreProperties({"member"})
	@OneToMany(mappedBy = "member")
	private List<Member> member;
	
	//private User owner;
	
//	private List<Team> teams;
//	
//	private List<Member> member;
	
	
//	//joindate 출력양식
//	public String getJoindate() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		//SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");	//hh12,HH24
//		//String time[] = {sdf.format(createDate), sdf2.format(createDate)};
//		
//		//return createDate.toString().substring(0,10);
//		return sdf.format(joindate);
//	}
	
	
	 public String getDate() {
		Timestamp time = this.getJoindate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(time);
	}
	 
}
