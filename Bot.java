package tictactoe;

public class Bot extends Player {
    public Bot(String botName, int boardPieces) {
        name = "(Bot) " + botName;
        pieces = boardPieces;
        bot = true;
    }

    public int makeMove(int[] board) {
        int res = minimax(board, true, pieces)[1];
        return res;
    }
    
    // first item is value, second is move index
    public int[] minimax(int[] board, boolean maximising, int piece) {
        int[] b = board.clone();
        int state = checkState(board, piece, maximising);
        if (state != 2) {
            return new int[] {state, -1};
        }
        if (maximising) {
            int value = -2;
            int move = -1;
            for (int i = 0; i < board.length; i++) {
                if (b[i] == 0) {
                    b[i] = piece;
                    int[] child = minimax(b, !maximising, otherPiece(piece));
                    if (child[0] > value) {
                        value = child[0];
                        move = i;
                    }
                    b[i] = 0;
                }
            }
            return new int[] {value, move};
        } else { // minimising player
            int value = 2;
            int move = -1;
            for (int i = 0; i < board.length; i++) {
                if (b[i] == 0) {
                    b[i] = piece;
                    int[] child = minimax(b, !maximising, otherPiece(piece));
                    if (child[0] < value) {
                        value = child[0];
                        move = i;
                    }
                    b[i] = 0;
                }
            }
            return new int[] {value, move};
        }
    }

    public int checkState(int[] b, int p, boolean maximising) {
        int rows = checkThree(b, p, 0, 1, 2) + checkThree(b, p, 3, 4, 5) + checkThree(b, p, 6, 7, 8);
        int cols = checkThree(b, p, 0, 3, 6) + checkThree(b, p, 1, 4, 7) + checkThree(b, p, 2, 5, 8);
        int diag = checkThree(b, p, 0, 4, 8) + checkThree(b, p, 2, 4, 6);
        int sum = rows + cols + diag;
        if (sum != 0) {
            if (sum < 0 && !maximising) {
                return 1;
            } else if (sum > 0 && !maximising) {
                return -1;
            } else if (sum < 0 && maximising) {
                return -1;
            } else {
                return 1;
            }
        }
        // at this point, nobody has won, could be a draw or game continues
        if (checkDraw(b)) {
            return 0; //draw
        } else {
            return 2; //nobody has won yet
        }
    }

    private int checkThree(int[] board, int piece, int a, int b, int c) {
        boolean res = (board[a] != 0 && board[a] == board[b] && board[b] == board[c]);
        if (!res) {
            return 0;
        } else {
            if (board[a] == piece) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public boolean checkDraw(int[] board) {
        for (int i : board) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public int otherPiece(int piece) {
        if (piece == 1) {
            return 2;
        } else {
            return 1;
        }
    }
}