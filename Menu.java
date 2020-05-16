package tictactoe;

public class Menu {
    public static void main(String[] args) {
        System.out.println("Welcome to tic-tac-toe!");
        Human p1 = new Human("Human", 1);
        Bot p2 = new Bot("Minimax", 2);
        Game g = new Game(p1, p2);
        g.start();
        System.out.println("Goodbye!");
    }
}