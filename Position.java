public class Position {
    private Character piece;

    static final char UNPLAYABLE = '*';
    static final char EMPTY = '.'; //changed EMPTY to a dot for better clarity insteaf of using blank spaces 
    static final char BLACK = 'B';
    static final char WHITE = 'W';

    public Position() {
        this.piece = EMPTY; // Default to null indicating an empty position
    }

    public Position(Character piece) {
        this.piece = piece;
    }

    public boolean canPlay() {
        return this.piece == EMPTY;
    }

    public Character getPiece() {
        return piece;
    }

    public void setPiece(Character piece) {
        this.piece = piece;
    }
}
