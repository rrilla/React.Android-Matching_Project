package com.project.MatchingPro.domain.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByLoginidAndPassword(String loginid, String password);
	
	int countByLoginid(String loginid);	//count = 결과 레코드수 반환

	int countByNickname(String nickname);
	
	@Query(value = "select * from user where teams_id is null", nativeQuery = true)
	List<User>  mfindAll();

}
