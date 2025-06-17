package com.keyin.rest.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // Get all players
    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    // Search players by last name (returns list)
    @GetMapping("/search")
    public List<Player> getPlayersByLastName(@RequestParam("last_name") String lastName) {
        return playerService.getPlayersByLastName(lastName);
    }

    // Get player by ID
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable long id) {
        return playerService.getPlayerById(id);
    }

    // Create new player
    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    // Update existing player
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable long id, @RequestBody Player player) {
        return ResponseEntity.ok(playerService.updatePlayer(id, player));
    }

    // Delete player by ID
    @DeleteMapping("/{id}")
    public void deletePlayerById(@PathVariable long id) {
        playerService.deletePlayerById(id);
    }
}
