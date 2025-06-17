package com.keyin.rest.team;

import com.keyin.rest.division.Division;
import com.keyin.rest.division.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DivisionService divisionService;

    public List<Team> getAllTeams() {
        return (List<Team>) teamRepository.findAll();
    }

    public Team getTeamById(long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public List<Team> getTeamsByPlayerLastName(String playerLastName) {
        return teamRepository.findByPlayers_LastNameIgnoreCase(playerLastName);
    }

    public List<Team> getTeamsByDivisionName(String divisionName) {
        return teamRepository.findByDivision_NameIgnoreCase(divisionName);
    }

    public void deleteTeamById(long id) {
        teamRepository.deleteById(id);
    }

    public Team createTeam(Team newTeam) {
        Division division = getOrCreateDivision(newTeam.getDivision());
        newTeam.setDivision(division);
        return teamRepository.save(newTeam);
    }

    public Team updateTeam(long id, Team updatedTeam) {
        Optional<Team> teamToUpdateOptional = teamRepository.findById(id);

        if (teamToUpdateOptional.isPresent()) {
            Team teamToUpdate = teamToUpdateOptional.get();

            teamToUpdate.setName(updatedTeam.getName());
            teamToUpdate.setDivision(getOrCreateDivision(updatedTeam.getDivision()));
            teamToUpdate.setPlayers(updatedTeam.getPlayers());

            return teamRepository.save(teamToUpdate);
        }

        return null;
    }

    // Helper method to get or create a division
    private Division getOrCreateDivision(Division divisionInput) {
        if (divisionInput == null || divisionInput.getName() == null) return null;

        Division division = divisionService.findByName(divisionInput.getName());
        if (division == null) {
            division = divisionService.createDivision(divisionInput);
        }
        return division;
    }
}
