package fortress.bid.exceptions;

public class NegativeBidException extends Exception{
	
private String message;
	
	public String getMessage(){
		return message;
	}

	public NegativeBidException(String message) {
		this.message = message;
	}
	
	public String toString(){
		return message;
		
	}
	
}
