import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Board {
    private Position[][] boardPieces;
    private Player first;
    private Player second;
    private Player current;

  public Board(Player p1, Player p2, int start, boolean isStandard) {
        this.first = p1;
        this.second = p2;

        if (start == 1) {
            this.current = p1;
        } else {
            this.current = p2;
        }

        //boardInitialization for both standard and non standard board
        this.boardPieces = new Position[8][8]; 
        if (isStandard) {
            initializeStandardBoard();
        } else {
            nonstandardboardInitialize();
        }
    }
    
    //checking winner
    public void Winner(boolean conceded, Player concedingPlayer) {
        int BlackCount = 0;
        int WhiteCount = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position pos = boardPieces[i][j];
                if (pos.getPiece() == Position.BLACK) {
                    BlackCount++;
                } else if (pos.getPiece() == Position.WHITE) {
                    WhiteCount++;
                }
            }
        }

        System.out.println("*****************************************");
        System.out.println("*                Game Over              *");
        System.out.println("*****************************************");
        System.out.println("*                                       *");
        System.out.println("*              Final Scores             *");
        System.out.println("*                                       *");
        System.out.println("*****************************************");
        System.out.printf("*  Black: %-29d *\n", BlackCount);
        System.out.printf("*  White: %-29d *\n", WhiteCount);
        System.out.println("*****************************************");

        if (conceded) {
            Player winningPlayer;
            if (concedingPlayer == first) {
                winningPlayer = second;
            } else {
                winningPlayer = first;
            }

            System.out.println("*****************************************");
            System.out.println("*                Game Over              *");
            System.out.println("*****************************************");
            System.out.println("*                                       *");
            System.out.printf("*   %-31s has conceded *\n", concedingPlayer.getName());
            System.out.println("*                                       *");
            System.out.printf("*   %-31s wins!        *\n", winningPlayer.getName());
            System.out.println("*                                       *");
            System.out.println("*****************************************");
        } else {
            if (WhiteCount > BlackCount) {
                System.out.println("*****************************************");
                System.out.println("*                Game Over              *");
                System.out.println("*****************************************");
                System.out.println("*                                       *");
                System.out.println("*             White Has Won             *");
                System.out.println("*                                       *");
                System.out.println("*****************************************");
                System.out.printf("*  Black: %-29d *\n", BlackCount);
                System.out.printf("*  White: %-29d *\n", WhiteCount);
                System.out.println("*****************************************");
            } else if (BlackCount > WhiteCount) {
                System.out.println("*****************************************");
                System.out.println("*                Game Over              *");
                System.out.println("*****************************************");
                System.out.println("*                                       *");
                System.out.println("*            Black Has Won              *");
                System.out.println("*                                       *");
                System.out.println("*****************************************");
                System.out.printf("*  Black: %-29d *\n", BlackCount);
                System.out.printf("*  White: %-29d *\n", WhiteCount);
                System.out.println("*****************************************");
            } else {
                System.out.println("*****************************************");
                System.out.println("*                Game Over              *");
                System.out.println("*****************************************");
                System.out.println("*                                       *");
                System.out.println("*                It's a Tie             *");
                System.out.println("*                                       *");
                System.out.println("*****************************************");
            }
        }
    }

    private void initializeStandardBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardPieces[i][j] = new PlayablePosition();
            }
        }
        // Set up initial pieces for standard starting position
        boardPieces[3][3].setPiece(Position.WHITE);
        boardPieces[3][4].setPiece(Position.BLACK);
        boardPieces[4][3].setPiece(Position.BLACK);
        boardPieces[4][4].setPiece(Position.WHITE);

        // Set specific unplayable positions
        boardPieces[0][3] = new UnplayablePosition();
        boardPieces[0][4] = new UnplayablePosition();
    }

    private void nonstandardboardInitialize() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardPieces[i][j] = new PlayablePosition();

            }
        }

        // Playable positions for non-standard board    
        boardPieces[2][3].setPiece(Position.WHITE);
        boardPieces[2][2].setPiece(Position.WHITE);
        boardPieces[3][2].setPiece(Position.WHITE);
        boardPieces[3][3].setPiece(Position.WHITE);
        boardPieces[3][4].setPiece(Position.BLACK);
        boardPieces[2][4].setPiece(Position.BLACK);
        boardPieces[2][5].setPiece(Position.BLACK);
        boardPieces[3][5].setPiece(Position.BLACK);
        boardPieces[4][3].setPiece(Position.BLACK);
        boardPieces[4][2].setPiece(Position.BLACK);
        boardPieces[5][2].setPiece(Position.BLACK);
        boardPieces[5][3].setPiece(Position.BLACK);
        boardPieces[4][4].setPiece(Position.WHITE);
        boardPieces[4][5].setPiece(Position.WHITE);
        boardPieces[5][4].setPiece(Position.WHITE);
        boardPieces[5][5].setPiece(Position.WHITE);

        // Set specific unplayable positions for non-standard board
        boardPieces[0][3] = new UnplayablePosition();
        boardPieces[0][4] = new UnplayablePosition();
    }

    public void drawBoard() {
        System.out.println("  1 2 3 4 5 6 7 8"); // Col labels
        for (int i = 0; i < 8; i++) { 
            System.out.print((i + 1) + " "); // Row labels
            for (int j = 0; j < 8; j++) {
                System.out.print(boardPieces[i][j].getPiece() + " ");
            }
            System.out.println();
        }
    }

    //save Game method
    private void save(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    writer.print(boardPieces[i][j].getPiece());
                }
                writer.println();
            }

            writer.println(first.getName());
            writer.println(second.getName());
            writer.println(current.getName());

        } catch (IOException e) {
            System.out.println("Failed to save game: " + e.getMessage());
        }
    }

    //load game method
    public void load(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            for (int i = 0; i < 8; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < 8; j++) {
                    char piece = line.charAt(j);
                    switch (piece) {
                        case 'B':
                            boardPieces[i][j] = new Position(Position.BLACK);
                            break;
                        case 'W':
                            boardPieces[i][j] = new Position(Position.WHITE);
                            break;
                        case '*':
                            boardPieces[i][j] = new UnplayablePosition();
                            break;
                        case '.':
                            boardPieces[i][j] = new Position();
                            break;
                    }
                }
            }

            String firstPlayerName = sc.nextLine().trim();
            String secondPlayerName = sc.nextLine().trim();
            String currentPlayerName = sc.nextLine().trim();

            boolean isFirstPlayerCurrent = currentPlayerName.equals(firstPlayerName);

            first = new Player(firstPlayerName, true, false, isFirstPlayerCurrent, false);
            second = new Player(secondPlayerName, false, true, !isFirstPlayerCurrent, true);

            if (currentPlayerName.equals(firstPlayerName)) {
                current = first;
            } else {
                current = second;
            }

        }
        catch (IOException e) {
            System.out.println("Failed to load game: " + e.getMessage());
        }
        
        catch(NullPointerException e) {
        	System.out.println(e.getMessage());
        }
        
      
       
      
    }

    private boolean move(Player player, int row, int col) {
        System.out.println("Attempting move at: " + row + ", " + col); // Db
        if (row < 0 || row >= 8 || col < 0 || col >= 8 || !boardPieces[row][col].canPlay()) {
            System.out.println("Move out of bounds or invalid position"); // Db
            return false;
        }

        boolean validMove = false;
        char opponentPiece;
        if (player.getPieceColor() == Position.BLACK) {
            opponentPiece = Position.WHITE;
        } else {
            opponentPiece = Position.BLACK;
        }
        int[][] directions = {
        	    {-1, -1}, {-1, 0}, {-1, 1},
        	    { 0, -1},         { 0, 1},
        	    { 1, -1}, { 1, 0}, { 1, 1}
        	};;

        for (int i = 0; i < directions.length; i++) {
            for (int j = 0; j < directions.length; j++) {
                int directionRow = directions[i][0];
                int directionCol = directions[j][1];
                if (directionRow == 0 && directionCol == 0) continue;
                if (CheckFlip(player.getPieceColor(), opponentPiece, row, col, directionRow, directionCol)) {
                    flipPieces(player.getPieceColor(), row, col, directionRow, directionCol);
                    validMove = true;
                }
            }
        }

        if (validMove) {
            boardPieces[row][col].setPiece(player.getPieceColor());
            return true;
        }
        return false;
    }

    //check if a piece can be flipped
    private boolean CheckFlip(char playerPiece, char opponentPiece, int row, int col, int dr, int dc) {
        int r = row + dr;
        int c = col + dc;
        boolean foundOpponent = false;

        while (r >= 0 && r < 8 && c >= 0 && c < 8) {
            if (boardPieces[r][c].getPiece() == opponentPiece) {
                foundOpponent = true;
            } else if (boardPieces[r][c].getPiece() == playerPiece) {
                return foundOpponent;
            } else {
                break;
            }
            r += dr;
            c += dc;
        }
        return false;
    }

    //actually flipping the piece
    private void flipPieces(char playerPiece, int row, int col, int deltaRow, int deltaCol) {
        int r = row + deltaRow;
        int c = col + deltaCol;

        while (r >= 0 && r < 8 && c >= 0 && c < 8 && boardPieces[r][c].getPiece() != playerPiece) {
            boardPieces[r][c].setPiece(playerPiece);
            r += deltaRow;;
            c += deltaCol;
        }
    }
    
    //the deltaRow and deltaCol determine the direction of movement on the board They are used to navigate horizontally, vertically, or diagonally based on their values.

    //check if a player can make a valid move
    private boolean hasValidMoves(Player player, int row, int col) {
        char opponentPiece;        
        if (player.getPieceColor() == Position.BLACK) {
            opponentPiece = Position.WHITE;
        } else {
            opponentPiece = Position.BLACK;
        }
        int[][] directions = {
        	    {-1, -1}, {-1, 0}, {-1, 1},
        	    { 0, -1},         { 0, 1},
        	    { 1, -1}, { 1, 0}, { 1, 1}
        	};;

        for (int i = 0; i < directions.length; i++) {
            for (int j = 0; j < directions.length; j++) {
                int dr = directions[i][0];
                int dc = directions[j][1];
                if (dr == 0 && dc == 0) continue;
                if (CheckFlip(player.getPieceColor(), opponentPiece, row, col, dr, dc)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        boolean OnGoing = true;

        while (OnGoing) {
            boolean currentHasMoves = hasAnyValidMoves(current);
            Player otherPlayer;
            if (current == first) {
                otherPlayer = second;
            } else {
                otherPlayer = first;
            }
            boolean otherHasMoves = hasAnyValidMoves(otherPlayer);
            if (!currentHasMoves && !otherHasMoves) {
                System.out.println("Neither player has any valid moves. Game over.");
                drawBoard();
                Winner(false, null);
                return;
            }

            while (!currentHasMoves) {
                System.out.println(current.getName() + " does not have any valid moves. Please pick from the options below (1, 2, 3): 1 to save, 2 to concede, or 3 to forfeit turn to the other player.");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Please enter the filename of the game to save:");
                        sc.nextLine(); 
                        String filename = sc.nextLine();
                        save(filename);
                        System.out.println("File has been saved as " + filename + ". Thank you for playing.");
                        break;
                    case 2:
                        System.out.println(current.getName() + " has conceded.");
                        drawBoard();
                        Winner(true, current);
                        return;
                    case 3:
                        System.out.println("Forfeiting turn to the other player.");
                        switchPlayer();
                        currentHasMoves = hasAnyValidMoves(current);
                        drawBoard();
                        break;
                    default:
                        System.out.println("Please pick from the options given.");
                }
            }

            if (!hasAnyValidMoves(first) && !hasAnyValidMoves(second)) {
                System.out.println("There are no possible moves to be made...Game ending.");
                Winner(false, null);
                return;
            }

            drawBoard();
            System.out.println("Current player: " + current.getName() + " (" + current.getPieceColor() + ")");
            System.out.println("Enter your move (row and column) or -1 to return to the menu: ");

            int row = sc.nextInt();
            if (row == -1) {
                System.out.println("1. Save game");
                System.out.println("2. Concede game");
                System.out.println("3. Return to game");
                int option = sc.nextInt();
                sc.nextLine(); // Consume the newline

                switch (option) {
                    case 1:
                        System.out.println("Please enter the filename of the game to save:");
                        String filename = sc.nextLine();
                        System.out.println("Saving...");
                        save(filename);
                        System.out.println("Successfully saved game as " + filename);
                        break;
                    case 2:
                        OnGoing = false;
                        System.out.println("Thank you for playing. " + current.getName() + " has conceded.");
                        Winner(true, current);
                        return;
                    case 3:
                        System.out.println("Returning to game...");
                        continue;
                    default:
                        System.out.println("Invalid option. Returning to the game...");
                        continue;
                }
                continue;
            }

            int col = sc.nextInt();

            // Adjust the indices for zero-indexed array
            if (move(current, row - 1, col - 1)) {
                if (!hasAnyValidMoves(first) && !hasAnyValidMoves(second)) {
                    OnGoing = false;
                    drawBoard();
                    Winner(false, null);
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        sc.close();
    }


    //checks if there are any valid moves on the entire board
    private boolean hasAnyValidMoves(Player player) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (boardPieces[row][col].canPlay() && hasValidMoves(player, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void switchPlayer() {
        if (current == first) {
            current = second;
        } else {
            current = first;
        }
    }
}
