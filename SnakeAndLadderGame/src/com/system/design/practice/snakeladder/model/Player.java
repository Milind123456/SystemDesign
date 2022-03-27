package com.system.design.practice.snakeladder.model;

import java.util.UUID;

public class Player {
    private String playerName;
    private UUID id;

    public Player(String playerName) {
        this.playerName = playerName;
        this.id = UUID.randomUUID();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public UUID getId() {
        return id;
    }

}
