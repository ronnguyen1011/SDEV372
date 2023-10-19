package edu.greenriver.sdev.webapi.controller;

import edu.greenriver.sdev.webapi.model.Game;
import edu.greenriver.sdev.webapi.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class GameApi {
    private GameService service;

    public GameApi(GameService service) {
        this.service = service;
    }

    @GetMapping("games")
    public List<Game> allGames(){
        return service.readGame();
    }
}
