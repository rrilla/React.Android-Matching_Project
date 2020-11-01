package com.project.MatchingPro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.teamInfo.TeamInfo;

import com.project.MatchingPro.service.TeamInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TeamInfoController {

	private final TeamInfoService teamInfoService;

	// 유저아이디를 teamInfo에 저장하는 컨트롤러
	@PostMapping("/user/teamInfo")
	public ResponseEntity<?> register(@RequestBody TeamInfo teamInfo) {
		return teamInfoService.register(teamInfo);
	}
}
