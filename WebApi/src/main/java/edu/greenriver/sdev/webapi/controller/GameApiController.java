package edu.greenriver.sdev.webapi.controller;

import edu.greenriver.sdev.webapi.model.Game;
import edu.greenriver.sdev.webapi.model.Player;
import edu.greenriver.sdev.webapi.services.GameService;
import edu.greenriver.sdev.webapi.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GameApiController {
    private final GameService gameService;
    private final PlayerService playerService;

    public GameApiController(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
    }

    @GetMapping("/games")
    public List<Game> allGames() {
        return gameService.readGame();
    }

    @GetMapping("/players")
    public List<Player> allPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping("/games")
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game.getTitle(), game.getDescription(), game.getPlatform(), game.getPrice());
    }

    @PostMapping("/players")
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player.getUsername(), player.getScore(), player.getLevel());
    }

    @GetMapping("/games/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable int gameId) {
        Optional<Game> game = gameService.getGameById(gameId);
        return game.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/players/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable int playerId) {
        Optional<Player> player = playerService.getPlayerById(playerId);
        return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Game> editGame(@PathVariable int id, @RequestBody Game updatedGame) {
        Optional<Game> savedGame = gameService.getGameById(id);

        if (savedGame.isPresent()) {
            Game game = savedGame.get();
            game.setTitle(updatedGame.getTitle());
            game.setDescription(updatedGame.getDescription());
            game.setPlatform(updatedGame.getPlatform());
            game.setPrice(updatedGame.getPrice());
            return ResponseEntity.ok(game);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<Player> editPlayer(@PathVariable int id, @RequestBody Player updatedPlayer) {
        Optional<Player> savedPlayer = playerService.getPlayerById(id);

        if (savedPlayer.isPresent()) {
            Player player = savedPlayer.get();
            player.setUsername(updatedPlayer.getUsername());
            player.setScore(updatedPlayer.getScore());
            player.setLevel(updatedPlayer.getLevel());
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/games/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable int gameId) {
        if (gameService.deleteGame(gameId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<Void> deletePlayer(@PathVariable int playerId) {
        if (playerService.deletePlayer(playerId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
