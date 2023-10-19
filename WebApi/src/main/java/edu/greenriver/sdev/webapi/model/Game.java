package edu.greenriver.sdev.webapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private int id;
    private String title;
    private String description;
    private String platform;
    private double price;

    public Game(String title, String description, String platform, double price) {
        this.title = title;
        this.description = description;
        this.platform = platform;
        this.price = price;
    }
}

