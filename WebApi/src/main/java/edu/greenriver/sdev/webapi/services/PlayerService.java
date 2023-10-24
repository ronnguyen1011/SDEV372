package edu.greenriver.sdev.webapi.services;

import edu.greenriver.sdev.webapi.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private List<Player> players = new ArrayList<>();
    private int nextId = 1;

    public Player createPlayer(String username, int score, int level) {
        Player player = new Player(username, score, level);
        player.setId(nextId++);
        players.add(player);
        return player;
    }

    public List<Player> getAllPlayers() {
        return players;
    }

    public Optional<Player> getPlayerById(int id) {
        return players.stream().filter(player -> player.getId() == id).findFirst();
    }

    public boolean updatePlayer(Player updatedPlayer) {
        Optional<Player> existingPlayer = getPlayerById(updatedPlayer.getId());
        if (existingPlayer.isPresent()) {
            int index = players.indexOf(existingPlayer.get());
            players.set(index, updatedPlayer);
            return true;
        }
        return false;
    }

    public boolean deletePlayer(int id) {
        Optional<Player> player = getPlayerById(id);
        if (player.isPresent()) {
            players.remove(player.get());
            return true;
        }
        return false;
    }
}
