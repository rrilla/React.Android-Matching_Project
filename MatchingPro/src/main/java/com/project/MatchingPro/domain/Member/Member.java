package com.project.MatchingPro.domain.Member;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Member {
	@JoinColumn(name="userId")	// fk로 넣을 이름을 지정할 수 있다
	@ManyToOne	// EAGER : default / LAZY : 늦게, 지연 / 효율을 위한 전략
	private User user;
	 
	@JoinColumn(name="TeamId")	// fk로 넣을 이름을 지정할 수 있다
	@ManyToOne	// EAGER : default / LAZY : 늦게, 지연 / 효율을 위한 전략
	private Team team;
	
	@Id				// pk. pk없으면 안 만들어준다
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 해당 db 번호증가 전략을 따라감 (oracle:시퀀스, mysql:auto increment)
	private int id;
	private String role;
}
