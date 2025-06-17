package com.keyin.rest.team;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    // Find all teams that have a player with a given last name
    List<Team> findByPlayers_LastNameIgnoreCase(String lastName);

    // Find all teams that belong to a division with a given name
    List<Team> findByDivision_NameIgnoreCase(String divisionName);
}
