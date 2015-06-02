package fortress.bid.exceptions;

public class BidLowerThanCurrentBidException extends Exception{
	
private String message;
	
	public String getMessage(){
		return message;
	}

	public BidLowerThanCurrentBidException(String message) {
		this.message = message;
	}
	
	public String toString(){
		return message;
		
	}
	
}



