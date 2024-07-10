package org.example;

import org.example.model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Deque<Player> players;
    Board board;

    public void initializeGame() {
        players = new LinkedList<>();
        PlayingPiece pieceX = new PlayingPieceX();
        PlayingPiece pieceO = new PlayingPieceO();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Board size : ");
        int size = sc.nextInt();
        System.out.println();
        board = new Board(size);

        System.out.print("Enter Player1 Name : ");
        String p1 = sc.next();
        System.out.println();
        System.out.print("Enter Player2 Name : ");
        String p2 = sc.next();

        System.out.println();
        System.out.println(p1 + " takes playing piece X");
        System.out.println(p2 + " takes playing piece O");

        Player player1 = new Player(p1, pieceX);
        Player player2 = new Player(p2, pieceO);
        players.add(player1);
        players.add(player2);


    }

    public String startGame() {
        Scanner sc = new Scanner(System.in);
        boolean noWinner = true;
        int size = board.getSize();
        while(noWinner) {
            board.printBoard();
            Player player = players.removeFirst();
            List<List<Integer>> freeSpaces = board.getFreeCells();
            if(freeSpaces.isEmpty()){
                noWinner=false;
                continue;
            }
            System.out.print(player.getPlayerName()+" Please enter row and column to continue playing : ");
            String position = sc.next();
            System.out.println();
            String[] values = position.split(",");
            int row = Integer.parseInt(values[0]);
            int column = Integer.parseInt(values[1]);
            if(row>size || column>size){
                System.out.println("Incorrect position, try again.");
                players.addFirst(player);
                continue;
            }
            boolean isPieceAdded = board.addPiece(row-1,column-1,player.getPlayingPiece());
            if(!isPieceAdded){
                System.out.println("Incorrect position, try again.");
                players.addFirst(player);
                continue;
            }
            players.addLast(player);
            if(isWinnerFound(row,column,player.getPlayingPiece().getPieceType())){
                board.printBoard();
                return player.getPlayerName();
            }
        }
        return "tie";
    }

    private boolean isWinnerFound(int row,int column, PieceType piece){

        int size = board.getSize();
        PlayingPiece[][] board = this.board.getBoard();
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;
        for(int i=0;i<size;i++){
            if(board[row-1][i]==null || board[row-1][i].getPieceType()!=piece){
                rowMatch = false;
            }
            if(board[i][column-1]==null || board[i][column-1].getPieceType()!=piece){
                columnMatch = false;
            }
            if(board[i][i]==null || board[i][i].getPieceType()!=piece){
                diagonalMatch = false;
            }
            if(board[i][size-i-1]==null || board[i][size-i-1].getPieceType()!=piece){
                antiDiagonalMatch = false;
            }
        }
        return (rowMatch||columnMatch||diagonalMatch||antiDiagonalMatch);
    }

}
