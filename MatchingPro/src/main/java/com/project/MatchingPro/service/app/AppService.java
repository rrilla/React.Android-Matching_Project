package com.project.MatchingPro.service.app;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;
import com.project.MatchingPro.dto.app.MainDataDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppService {

	private final UserRepository userRepository;
	
	public MainDataDto mainData(int userid) {
		
		User user = userRepository.findById(userid).orElseThrow(()-> new IllegalArgumentException(userid+"는 존재하지 않습니다."));
		
		if(user.getTeams() == null) {
			return MainDataDto.builder().
					username(user.getUsername()).
					nickname(user.getNickname()).
					phone(user.getPhone()).
					image(user.getImage()).build();
		}else {
			return MainDataDto.builder().
					username(user.getUsername()).
					nickname(user.getNickname()).
					phone(user.getPhone()).
					image(user.getImage()).
					t_name(user.getTeams().getName()).
					t_image(user.getTeams().getImage()).
					t_explaintation(user.getTeams().getExplaintation()).build();
		}
    
	}
  
}
