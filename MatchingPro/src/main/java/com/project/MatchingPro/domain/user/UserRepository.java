package com.project.MatchingPro.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByLoginidAndPassword(String loginid, String password);
	
	int countByLoginid(String loginid);	//count = 결과 레코드수 반환

	int countByNickname(String nickname);
	
	// 유저 테이블의 팀 칼럼 수정하는 쿼리 만들어서
}
