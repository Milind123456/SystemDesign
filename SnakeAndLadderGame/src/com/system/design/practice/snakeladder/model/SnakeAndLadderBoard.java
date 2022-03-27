package com.system.design.practice.snakeladder.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnakeAndLadderBoard {
    private int boardSize;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private Map<Player,Integer> playerPieces;

    public SnakeAndLadderBoard(int size) {
        this.boardSize = size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.playerPieces = new HashMap<>();
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public Map<Player, Integer> getPlayerPieces() {
        return playerPieces;
    }

    public void setPlayerPieces(Map<Player, Integer> playerPieces) {
        this.playerPieces = playerPieces;
    }
}
