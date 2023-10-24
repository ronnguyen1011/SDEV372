package edu.greenriver.sdev.webapi.services;

import edu.greenriver.sdev.webapi.model.Game;
import edu.greenriver.sdev.webapi.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private List<Game> games = new ArrayList<>(List.of(
            new Game("Pubg", "Battle royale game", "FPS", 0),
            new Game("Minecraft", "Sandbox game", "Adventure", 15.99),
            new Game("The Witcher 3", "Action RPG", "Adventure", 40.99)
    ));

    public Game createGame(String title, String description, String platform, double price) {
        Game game = new Game(title, description, platform, price);
        int id = games.size();
        game.setId(id);
        games.add(game);
        return game;
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

    public boolean deleteGame(int id) {
        Optional<Game> game = getGameById(id);
        if (game.isPresent()) {
            games.remove(game.get());
            return true;
        }
        return false;
    }
}
