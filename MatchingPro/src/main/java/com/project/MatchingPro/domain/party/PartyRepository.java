package com.project.MatchingPro.domain.party;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.MatchingPro.domain.team.Team;

public interface PartyRepository extends JpaRepository<Party, Integer>{
	@Query(value = "select * from party where team_id = :id", nativeQuery = true)
	Party mFindByTeamid(int id);
	//Board mFindById(int id);
}
