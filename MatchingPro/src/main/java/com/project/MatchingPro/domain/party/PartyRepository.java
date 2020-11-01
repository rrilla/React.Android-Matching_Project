package com.project.MatchingPro.domain.party;


import org.springframework.data.jpa.repository.JpaRepository;


public interface PartyRepository extends JpaRepository<Party, Integer>{
//	@Query(value = "select * from party where team_id = :id", nativeQuery = true)
//	Party mFindByTeamid(int id);
//	//Board mFindById(int id);
}
