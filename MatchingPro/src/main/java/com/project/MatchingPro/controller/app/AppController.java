package com.project.MatchingPro.controller.app;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;
import com.project.MatchingPro.dto.app.NavDataDto;
import com.project.MatchingPro.service.app.AppService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//@RequestMapping("app/")
public class AppController {
	
	private final AppService appService;
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	private final HttpSession session;

	@PostMapping("user/navData")
	public ResponseEntity<?> navData(){
		User user = (User)session.getAttribute("principal");
		return new ResponseEntity<NavDataDto>(appService.navData(user.getId()),HttpStatus.OK);
	}
	
	@PostMapping("user/myPage")
	public ResponseEntity<?> myPage(){
		User user = (User)session.getAttribute("principal");
		return new ResponseEntity<User>(
				userRepository.findById(user.getId()).orElseThrow(()-> new IllegalArgumentException("db select결과없음.")),
				HttpStatus.OK);
	}
	
	@PostMapping("app/imgUpload")
	public ResponseEntity<?> imgUpload(@RequestParam("uploadedfile") MultipartFile multipartFile) {
		return new ResponseEntity<String>(
				appService.imgUpload(multipartFile),
				HttpStatus.OK);
	}
	
	//팀 리스트
	@PostMapping("app/teamList")
	public List<Team> teamList(){
		return teamRepository.findAll();
	}
	
	//유저 리스트
		@PostMapping("app/userList")
		public List<User> userList(){
			return userRepository.findAll();
		}
}
