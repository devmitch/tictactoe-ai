package tictactoe;
import java.util.Scanner;

public class Human extends Player {
    public Human(String playerName, int boardPieces) {
        name = playerName;
        pieces = boardPieces;
    }

    public int makeMove(int[] board) {
        Scanner s = new Scanner(System.in);
        System.out.print(name + "'s move (0-8)?: ");

        int move = (int) s.nextInt();
        return move;
    }
}