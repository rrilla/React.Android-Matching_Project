package com.project.MatchingPro.domain.score;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
	
	//<11/05>
	@Query(value = "select * from score order by total desc", nativeQuery = true)//nativeQuery: 이쿼리를 사용하겠다.
	List<Score> rank();
}
