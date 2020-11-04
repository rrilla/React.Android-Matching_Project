package com.project.MatchingPro.domain.battle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BattleRepository extends JpaRepository<Battle, Integer> {
	
	@Query(value = "select * from battle where team1 = :id or team2= :id", nativeQuery = true)
	List<Battle> mfindAll(int id);//
}
