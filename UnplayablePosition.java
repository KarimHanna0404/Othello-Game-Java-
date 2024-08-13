public class UnplayablePosition extends Position {
   
	//this class explicitly sets the piece to unplayable whereas the Position class has the 
	//can play method that return false this also returns false but sets the position as unplayable in the super
	public UnplayablePosition() {
        super(Position.UNPLAYABLE); // Set the piece to UNPLAYABLE
    }

    @Override
    public boolean canPlay() {
    	
        return false;
    }
}
