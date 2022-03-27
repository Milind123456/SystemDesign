package com.system.design.practice.snakeladder;

import com.system.design.practice.snakeladder.model.Ladder;
import com.system.design.practice.snakeladder.model.Player;
import com.system.design.practice.snakeladder.model.Snake;
import com.system.design.practice.snakeladder.service.SnakeAndLadderGameService;

import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of snakes");
        int noOfSnakes = scanner.nextInt();

        List<Snake> snakes = new ArrayList<Snake>();
        for (int i = 0; i < noOfSnakes; i++) {
            System.out.println("Enter snakes details");
            snakes.add(new Snake(scanner.nextInt(), scanner.nextInt()));
        }
        System.out.println("Enter number of ladders");
        int noOfLadders = scanner.nextInt();
        List<Ladder> ladders = new ArrayList<Ladder>();
        for (int i = 0; i < noOfLadders; i++) {
            System.out.println("Enter ladder details");
            ladders.add(new Ladder(scanner.nextInt(), scanner.nextInt()));
        }

        int noOfPlayers = scanner.nextInt();
        System.out.println("Enter number of players");
        Queue<Player> players = new LinkedList<>();
        for (int i = 0; i < noOfPlayers; i++) {
            System.out.println("Enter player details");
            players.add(new Player(scanner.next()));
        }

        SnakeAndLadderGameService snakeAndLadderService = new SnakeAndLadderGameService();
        snakeAndLadderService.setPlayers(players);
        snakeAndLadderService.setSnakes(snakes);
        snakeAndLadderService.setLadders(ladders);

        snakeAndLadderService.startGame();
    }
}
