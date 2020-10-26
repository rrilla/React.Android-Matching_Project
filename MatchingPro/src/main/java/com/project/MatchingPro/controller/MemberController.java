package com.project.MatchingPro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.Member.Member;
import com.project.MatchingPro.domain.Member.MemberRepository;
import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {
	
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	private final MemberRepository memberRepository;
	
	@PostMapping("/post/team/join")
	public ResponseEntity<?> join(@RequestBody Member member){
		User userEntity = userRepository.findById(1).get();
		Team teamEntity = teamRepository.findById(1).get();
		member.setRole("USER");
		member.setTeam(teamEntity);
		member.setUser(userEntity);
		memberRepository.save(member);
		return new ResponseEntity<String>("ok",HttpStatus.CREATED);
	}
}
