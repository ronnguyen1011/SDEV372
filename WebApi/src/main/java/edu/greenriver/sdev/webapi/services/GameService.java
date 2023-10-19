package edu.greenriver.sdev.webapi.services;

import edu.greenriver.sdev.webapi.model.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final List<Game> games = new ArrayList<>();
    private final int gameId = 1;
    public void createGame(Game game){
        games.add(game);
    }
    public Optional<Game> getGameById(int id) {
        return games.stream().filter(plant -> plant.getId() == id).findFirst();
    }
    public List<Game> readGame(){
        return games;
    }

    public void updateGame(Game updatedGame) {
        Optional<Game> savedGame = getGameById(updatedGame.getId());
        if (savedGame.isPresent()) {
            Game game = savedGame.get();
            game.setTitle(updatedGame.getTitle());
            game.setDescription(updatedGame.getDescription());
            game.setPlatform(updatedGame.getPlatform());
            game.setPrice(updatedGame.getPrice());
        }
    }

    public void deleteGame(int id){
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId() == id){
                games.remove(i);
                break;
            }
        }
    }
}
