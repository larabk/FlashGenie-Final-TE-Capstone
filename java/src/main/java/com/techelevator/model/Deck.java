package com.techelevator.model;

public class Deck {
    private Long deckId;
    private Long userId;
    private String name;
    private Integer clickCount;


    public Deck(Long deckId, Long userId, String name, Integer clickCount) {
        this.deckId = deckId;
        this.userId = userId;
        this.name = name;
        this.clickCount = clickCount;
    }

    public Deck() {

    }

    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }


    @Override
    public String toString() {
        return "Deck{" +
                "deckId=" + deckId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", clickCount=" + clickCount +
                '}';
    }
}