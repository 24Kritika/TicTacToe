package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Game ticTacToe = new Game();
        ticTacToe.initializeGame();
        String winner = ticTacToe.startGame();
        if(winner.equals("tie")){
            System.out.println("Its a tie.");
        }else{
            System.out.println("Winner is "+winner);
        }
    }
}