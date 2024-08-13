public class Player {
    private String name;
    private boolean isFirst;
    private boolean isSecond;
    private boolean isCurrent;
    private boolean isBlack; //keep track of the Player's current color

    public Player(String name, boolean isFirst, boolean isSecond, boolean isCurrent, boolean isBlack) {
        this.name = name;
        this.isFirst = isFirst;
        this.isSecond = isSecond;
        this.isCurrent = isCurrent;
        this.isBlack = isBlack;
    }
    
    
    public boolean getisBlack() {
    	return isBlack;
    }
    
    public void setBlack(boolean isBlack) {
    	this.isBlack= isBlack;
    }
    
    public char getPieceColor() {
    	
    	if(isBlack) {
    		return Position.BLACK;
    	}
    	else {
    		return Position.WHITE;
    	}
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public boolean isSecond() {
        return isSecond;
    }

    public void setSecond(boolean isSecond) {
        this.isSecond = isSecond;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }
}
