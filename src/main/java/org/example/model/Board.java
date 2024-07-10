package org.example.model;

import org.example.model.PlayingPiece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    Integer size;
    PlayingPiece[][] board;

    public Board(Integer size) {
        this.size = size;
        this.board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int column,PlayingPiece playingPiece){
        if(board[row][column]!=null){
            return false;
        }
        board[row][column] = playingPiece;
        return true;
    }

    public List<List<Integer>> getFreeCells(){
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]==null){
                    result.add(Arrays.asList(i,j));
                }
            }
        }
        return result;
    }

    public void printBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(null==board[i][j]){
                    System.out.print("|   |" );
                }else {
                    System.out.print("| " + board[i][j].getPieceType() + " |");
                }
            }
            System.out.println();
        }
    }

    public PlayingPiece[][] getBoard() {
        return board;
    }

    public void setBoard(PlayingPiece[][] board) {
        this.board = board;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
