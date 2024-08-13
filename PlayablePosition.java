
public class PlayablePosition extends Position{

	public PlayablePosition() {
        super(Position.EMPTY); // Set the piece to UNPLAYABLE
    }
	
		@Override
	    public boolean canPlay() {
	    	
	        return true;
	    }

}
