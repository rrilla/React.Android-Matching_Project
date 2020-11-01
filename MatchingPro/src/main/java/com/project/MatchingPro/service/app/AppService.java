package com.project.MatchingPro.service.app;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;
import com.project.MatchingPro.dto.app.NavDataDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppService {

	private final UserRepository userRepository;
	
	public NavDataDto navData(int userid) {
		
		User user = userRepository.findById(userid).orElseThrow(()-> new IllegalArgumentException(userid+"는 존재하지 않습니다."));
		
		if(user.getTeams() == null) {
			return NavDataDto.builder().
					username(user.getUsername()).
					nickname(user.getNickname()).
					phone(user.getPhone()).
					image(user.getImage()).build();
		}else {
			return NavDataDto.builder().
					username(user.getUsername()).
					nickname(user.getNickname()).
					phone(user.getPhone()).
					image(user.getImage()).
					t_name(user.getTeams().getName()).
					t_image(user.getTeams().getImage()).
					t_explaintation(user.getTeams().getExplaintation()).build();
		}
    
	}

	public String imgUpload(MultipartFile multipartFile) {
		String fileRoot = "C:/Users/admin/Desktop/test/";	//학원,저장될 외부 파일 경로
		//String fileRoot = "C:/Users/user/Desktop/test/";	//집,저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		File targetFile = new File(fileRoot + savedFileName);	
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			e.printStackTrace();
		}
		System.out.println(savedFileName);
		return savedFileName;
	}
  
}
