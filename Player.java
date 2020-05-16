package tictactoe;

abstract class Player {
    public String name;
    public int pieces;
    public boolean playing = false;
    public boolean bot = false;
    
    public abstract int makeMove(int[] board);

    public void forfeit() {
        playing = false;
    }
}