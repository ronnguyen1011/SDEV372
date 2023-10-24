package edu.greenriver.sdev.webapi.model;

public class Game {
    private static int nextId = 0;
    private int id;
    private String title;
    private String description;
    private String platform;
    private double price;

    public Game(String title, String description, String platform, double price) {
        this.id = nextId++;
        this.title = title;
        this.description = description;
        this.platform = platform;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", platform='" + platform + '\'' +
                ", price=" + price +
                '}';
    }
}

