import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private Player p1;
    private Player p2;
    private Board board;

    //default constructor
    public Game() {
    }

    public void startNewGame() {
        Scanner sc = new Scanner(System.in);

        board.play();
    }

    public void loadGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the filename you wish to load");
        String filename = sc.nextLine();

        board = new Board(p1, p2, 1, true); //default value to initialize
        board.load(filename);
        board.play();
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("******** Welcome to Karim's Othello Game ********");
        System.out.println("1- Exit Game");
        System.out.println("2- Start a new game");
        System.out.println("3- Load Game");

        try {
            System.out.println("Please choose from the options given:");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Exiting game. Thank you for playing...");
                    Thread.sleep(500);
                    break;
                case 2:
                    System.out.println("Starting a new game...");
                    Thread.sleep(500);

                    System.out.println("Enter the name of the first player (White):");
                    String firstName = sc.nextLine();
                    System.out.println("Enter the name of the second player (Black):");
                    String secondName = sc.nextLine();

                    p1 = new Player(firstName, true, false, true, false);
                    p2 = new Player(secondName, false, true, false, true);

                    System.out.println("Press 1 if you want to play on the standard board and 2 if you would like to play on the non-standard board");
                    int boardChoice = sc.nextInt();
                    boolean isStandard = boardChoice == 1;

                    board = new Board(p1, p2, 1, isStandard);
                    board.play();
                    break;
                case 3:
                    System.out.println("Loading game...");
                    Thread.sleep(500);
                    loadGame();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please input a number ");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.menu();
    }
}
