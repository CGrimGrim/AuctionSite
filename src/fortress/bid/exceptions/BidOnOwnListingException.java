package fortress.bid.exceptions;

public class BidOnOwnListingException extends Exception {
	
private String message;
	
	public String getMessage(){
		return message;
	}

	public BidOnOwnListingException(String message) {
		this.message = message;
	}
	
	public String toString(){
		return message;
		
	}
	

}
