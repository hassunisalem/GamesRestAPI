package com.model;

// extends class ResourceSupport, which provides method add() for links to other resources
// add HATEOAS dependency in maven for it

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String releaseYear;
    private int price;
    private boolean broken;

    public Game() {
        super();
    }

    public Game(Long id, String title, String releaseYear, int price, boolean broken) {
        super();
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.price = price;
        this.broken = broken;
    }

    public int getPrice() { return price; }
    public boolean isBroken() { return broken; }
    public void setPrice(int price) { this.price = price; }
    public void setBroken(boolean broken) { this.broken = broken; }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; }
    public String getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
}