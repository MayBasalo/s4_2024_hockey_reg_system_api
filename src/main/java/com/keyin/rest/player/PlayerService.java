package com.keyin.rest.player;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return StreamSupport.stream(playerRepository.findAll().spliterator(), false)
                .toList();
    }

    public Player getPlayerById(long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public List<Player> getPlayersByLastName(String lastName) {
        return playerRepository.findByLastNameIgnoreCase(lastName);
    }

    public void deletePlayerById(long id) {
        playerRepository.deleteById(id);
    }

    public Player createPlayer(Player newPlayer) {
        return playerRepository.save(newPlayer);
    }

    public Player updatePlayer(long id, Player updatedPlayer) {
        Optional<Player> playerToUpdateOptional = playerRepository.findById(id);
        if (playerToUpdateOptional.isPresent()) {
            Player playerToUpdate = playerToUpdateOptional.get();
            playerToUpdate.setFirstName(updatedPlayer.getFirstName());
            playerToUpdate.setLastName(updatedPlayer.getLastName());
            playerToUpdate.setBirthday(updatedPlayer.getBirthday());
            return playerRepository.save(playerToUpdate);
        }
        return null;
    }
}
