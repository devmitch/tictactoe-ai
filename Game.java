package tictactoe;

public class Game {

    public boolean running = false;
    public int[] board;
    public Player p1;
    public Player p2;
    public int turn = 1;
    public int winner = 0;

    public Game(Player a, Player b) {
        board = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        p1 = a;
        p2 = b;
    }

    public void start() {
        running = true;
        while (running) {
            if (turn == 1) {
                if (!p1.bot) {
                    displayBoard();
                }
                int move = p1.makeMove(board);
                if (move < 0 || move > 8 || board[move] != 0) {
                    System.out.println("Invalid move! Please try again.");
                } else {
                    board[move] = p1.pieces;
                    turn = 2;
                }
            } else {
                if (!p2.bot) {
                    displayBoard();
                }
                int move = p2.makeMove(board);
                if (move < 0 || move > 8 || board[move] != 0) {
                    System.out.println("Invalid move! Please try again.");
                } else {
                    board[move] = p2.pieces;
                    turn = 1;
                }
            }

            checkState();
        }
        displayBoard();
        if (winner == 1) {
            System.out.println("Winner is " + p1.name + "!");
        } else if (winner == 2) {
            System.out.println("Winner is " + p2.name + "!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 1) {
                System.out.print("x ");
            } else if (board[i] == 2) {
                System.out.print("o ");
            } else {
                System.out.print("_ ");
            }

            if (i % 3 == 2) {
                System.out.println("");
            }
        }
    }

    public void checkState() {
        boolean rows = checkThree(0, 1, 2) || checkThree(3, 4, 5) || checkThree(6, 7, 8);
        boolean cols = checkThree(0, 3, 6) || checkThree(1, 4, 7) || checkThree(2, 5, 8);
        boolean diag = checkThree(0, 4, 8) || checkThree(2, 4, 6);
        if (rows || cols || diag) {
            running = false;
            if (turn == 1) {
                winner = 2;
            } else {
                winner = 1;
            }
        } else if (checkDraw()) {
            running = false;
        }
    }

    private boolean checkThree(int a, int b, int c) {
        return board[a] != 0 && board[a] == board[b] && board[b] == board[c];
    }

    public boolean checkDraw() {
        for (int i : board) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }
}