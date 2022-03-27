package com.system.design.practice.snakeladder.service;

import com.system.design.practice.snakeladder.model.Ladder;
import com.system.design.practice.snakeladder.model.Player;
import com.system.design.practice.snakeladder.model.Snake;
import com.system.design.practice.snakeladder.model.SnakeAndLadderBoard;

import java.util.*;

public class SnakeAndLadderGameService {
    private SnakeAndLadderBoard snakeAndLadderBoard;
    private int initialNumberOfPlayers;
    private Queue<Player> players;
    private boolean isGameCompleted;

    private int numberOfDices;
    private boolean shouldGameContinueTillLastPlayer;
    private boolean shouldAllowMultipleDiceRollOnSix;

    private static final int DEFAULT_BOARD_SIZE = 100;
    private static final int DEFAULT_NUMBER_OF_DICES = 1;

    public SnakeAndLadderGameService(int boardSize) {
        this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
        this.players = new LinkedList<>();
        this.numberOfDices = DEFAULT_NUMBER_OF_DICES;
    }

    public SnakeAndLadderGameService() {
        this(DEFAULT_BOARD_SIZE);
    }

    public void setShouldGameContinueTillLastPlayer(boolean shouldGameContinueTillLastPlayer) {
        this.shouldGameContinueTillLastPlayer = shouldGameContinueTillLastPlayer;
    }

    public void setShouldAllowMultipleDiceRollOnSix(boolean shouldAllowMultipleDiceRollOnSix) {
        this.shouldAllowMultipleDiceRollOnSix = shouldAllowMultipleDiceRollOnSix;
    }

    public void setPlayers(Queue<Player> players) {
        initialNumberOfPlayers = players.size();
        this.players.addAll(players);
        Map<Player, Integer> playerPieces = new HashMap<>();
        for (Player player : this.players) {
            playerPieces.put(player, 0);
        }

        snakeAndLadderBoard.setPlayerPieces(playerPieces);
    }

    public void setSnakes(List<Snake> snakes){
        snakeAndLadderBoard.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders) {
        snakeAndLadderBoard.setLadders(ladders); // Add ladders to board
    }

    public int getNewPositionAfterGoingThroughSnakesOrLadder(int newPosition){
        int prevPosition;
        do{
            prevPosition = newPosition;
            for(Snake snake:snakeAndLadderBoard.getSnakes()){
                if(snake.getStart() == newPosition){
                    newPosition = snake.getEnd();
                }
            }
            for(Ladder ladder:snakeAndLadderBoard.getLadders()){
                if(ladder.getStart() == newPosition){
                    newPosition = ladder.getEnd();
                }
            }
        }while (newPosition != prevPosition);

        return newPosition;
    }

    public void movePlayer(Player player, int position){
        int oldPosition = snakeAndLadderBoard.getPlayerPieces().get(player);
        int newPosition = oldPosition + position;

        int boardSize = snakeAndLadderBoard.getBoardSize();

        if(newPosition > boardSize){
            newPosition = oldPosition;
        }
        else{
            newPosition = getNewPositionAfterGoingThroughSnakesOrLadder(newPosition);
            snakeAndLadderBoard.getPlayerPieces().put(player,newPosition);
        }

        System.out.println(player.getPlayerName() + " rolled a " + position + " and moved from " + oldPosition +" to " + newPosition);

    }

    private int getTotalValueAfterDiceRolls() {
        // Can use noOfDices and setShouldAllowMultipleDiceRollOnSix here to get total value (Optional requirements)
        return DiceService.roll();
    }

    private boolean hasPlayerWon(Player player){
        int playerPosition = snakeAndLadderBoard.getPlayerPieces().get(player);
        int winningSize = snakeAndLadderBoard.getBoardSize();
        return playerPosition == winningSize;
    }

    private boolean isGameCompleted() {
        // Can use shouldGameContinueTillLastPlayer to change the logic of determining if game is completed (Optional requirements)
        int currentNumberOfPlayers = players.size();
        return currentNumberOfPlayers < initialNumberOfPlayers;
    }

    public void startGame(){
        while(!isGameCompleted){
            int totalDiceValue = getTotalValueAfterDiceRolls();
            Player currentPlayer = players.poll();
            movePlayer(currentPlayer,totalDiceValue);
            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getPlayerName() + " wins the game");
                snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer);
                break;
            } else {
                players.add(currentPlayer);
            }
        }
    }

}
