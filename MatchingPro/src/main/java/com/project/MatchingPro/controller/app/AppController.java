package com.project.MatchingPro.controller.app;


import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.dto.app.MainDataDto;
import com.project.MatchingPro.service.app.AppService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//@RequestMapping("app/")
public class AppController {
	
	private final AppService appService;
	private final HttpSession session;

	@GetMapping("user/appmain")
	public ResponseEntity<?> mainData(){
		System.out.println("asdfasdf");
		User user = (User)session.getAttribute("principal");
		return new ResponseEntity<MainDataDto>(appService.mainData(user.getId()),HttpStatus.OK);
	}
}
