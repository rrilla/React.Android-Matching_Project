package com.project.MatchingPro.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	//회원가입
	public ResponseEntity<?> join(User user){
		user.setRole("USER");
		userRepository.save(user);
		return new ResponseEntity<String>("ok",HttpStatus.CREATED);
	}
	
	//아이디 체크
	public ResponseEntity<?> idCheck(String loginid){
		int n = userRepository.countByLoginid(loginid);
		if(n == 0) {
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("no", HttpStatus.OK);
		}
	}
	
	//닉네임 체크
	public ResponseEntity<?> nicknameCheck(String nickname){
		int n = userRepository.countByNickname(nickname);
		if(n == 0) {
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("no", HttpStatus.OK);
		}
	}
	
}
