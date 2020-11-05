package com.project.MatchingPro.domain.score;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

	@Query(value = "select * from score order by total desc", nativeQuery = true)
	List<Score> rank();
}
