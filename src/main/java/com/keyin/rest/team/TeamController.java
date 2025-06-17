package com.keyin.rest.team;

// Removed unnecessary import of Team from the same package
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // GET all teams
    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    // SEARCH by player last name or division name
    @GetMapping("/search")
    public List<Team> getTeamsByFilters(
            @RequestParam(value = "player_last_name", required = false) String playerLastName,
            @RequestParam(value = "division_name", required = false) String divisionName
    ) {
        if (playerLastName != null) {
            return teamService.getTeamsByPlayerLastName(playerLastName);
        } else if (divisionName != null) {
            return teamService.getTeamsByDivisionName(divisionName);
        }
        return List.of(); // Return empty list if no filters are applied
    }

    // GET team by ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable long id) {
        Team team = teamService.getTeamById(id);
        return team != null ? ResponseEntity.ok(team) : ResponseEntity.notFound().build();
    }

    // CREATE new team
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = teamService.createTeam(team);
        return ResponseEntity.status(201).body(createdTeam);
    }

    // UPDATE existing team
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable long id, @RequestBody Team team) {
        Team updatedTeam = teamService.updateTeam(id, team);
        return updatedTeam != null ? ResponseEntity.ok(updatedTeam) : ResponseEntity.notFound().build();
    }

    // DELETE team by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamById(@PathVariable long id) {
        teamService.deleteTeamById(id);
        return ResponseEntity.noContent().build();
    }
}
