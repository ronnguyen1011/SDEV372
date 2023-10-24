package edu.greenriver.sdev.webapi.controller;

import edu.greenriver.sdev.webapi.model.Game;
import edu.greenriver.sdev.webapi.model.Player;
import edu.greenriver.sdev.webapi.services.GameService;
import edu.greenriver.sdev.webapi.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Ron Nguyen
 * @version 1.0
 * This is a controller for game API that track
 * CRUD game and player in the record
 */

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
    public ResponseEntity<?> createGame(@RequestBody Game game) {
        if (game.getTitle() == null || game.getDescription() == null || game.getPlatform() == null || game.getPrice() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Game createdGame = gameService.createGame(game.getTitle(), game.getDescription(), game.getPlatform(), game.getPrice());
            return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
        }
    }


    @PostMapping("/players")
    public ResponseEntity<?> createPlayer(@RequestBody Player player) {
        if (player.getUsername() == null || player.getScore() < 0 || player.getLevel() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Player createdPlayer = playerService.createPlayer(player.getUsername(), player.getScore(), player.getLevel());
            return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
        }
    }


    @GetMapping("/games/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable int gameId) {
        Optional<Game> game = gameService.getGameById(gameId);
        return game.map(g -> new ResponseEntity<>(g, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/players/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable int playerId) {
        Optional<Player> player = playerService.getPlayerById(playerId);
        return player.map(g -> new ResponseEntity<>(g, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
            return new ResponseEntity<>(game, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/games/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable int gameId) {
        if (gameService.deleteGame(gameId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<Void> deletePlayer(@PathVariable int playerId) {
        if (playerService.deletePlayer(playerId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
