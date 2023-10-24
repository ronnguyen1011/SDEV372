package edu.greenriver.sdev.webapi.model;

/**
 * @author Ron Nguyen
 * @version 1.0
 * This is player class have username, score , and level field.
 */


public class Player {
    private static int nextId = 0;
    private int id;
    private String username;
    private int score;
    private int level;


    public Player(String username, int score, int level) {
        this.id = nextId++;
        this.username = username;
        this.score = score;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", level=" + level +
                '}';
    }
}
