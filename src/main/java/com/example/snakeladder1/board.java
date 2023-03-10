package com.example.snakeladder1;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {

    private Circle coin;
    private String name;
    private int coinPosition;
    private static Board gameBoard = new Board();

    // Constructor
    public Player(int tileSize, Color coinColor, String playerName) {

        coinPosition = 1;
        name = playerName;
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        coin.setTranslateX(20);
        coin.setTranslateY(380);
    }

    // Moving coin according to the dice value
    public void movePlayer(int diceValue) {

        if(coinPosition + diceValue <= 100) {
            coinPosition += diceValue;
            translatePlayer();
            int newPosition = gameBoard.getNextPosition(coinPosition);
            if(newPosition != coinPosition){
                coinPosition = newPosition;
                translatePlayer();
            }
        }
    }

    // If coin position is 100 player won
    public String playerWon() {

        if(coinPosition==100) {
            return name +" Won the Game";
        }

        return null;
    }

    // Giving moving animation effect to the coin
    private void translatePlayer() {

        TranslateTransition move = new TranslateTransition(Duration.millis(1000), this.coin);
        move.setToX(gameBoard.getXCoordinate(coinPosition));
        move.setToY(gameBoard.getYCoordinate(coinPosition));
        move.setAutoReverse(false);
        move.play();
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCoinPosition() {
        return coinPosition;
    }

    public String getName() {
        return name;
    }
}