package com.project.MatchingPro.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByLoginidAndPassword(String loginid, String password);
	
	int countByLoginid(String loginid);	//count = 결과 레코드수 반환
	
	int countByNickname(String nickname);
}
