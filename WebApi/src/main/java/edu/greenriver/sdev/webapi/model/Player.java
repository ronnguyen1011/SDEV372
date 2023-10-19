package edu.greenriver.sdev.webapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private int id;
    private String username;
    private int score;
    private int level;

    public Player(String username, int score, int level) {
        this.username = username;
        this.score = score;
        this.level = level;
    }
}
