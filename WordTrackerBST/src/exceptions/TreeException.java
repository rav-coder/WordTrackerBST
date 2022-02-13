package exceptions;

public class TreeException extends Exception
{

	/**
	 * auto generated serial ID
	 * The exception thrown when the tree is empty
	 */
	private static final long serialVersionUID = 3453003137947098627L;
	
	public TreeException() {
		super("Error encountered performing action!");
	}
	
	public TreeException(String userMessage) {
		super(userMessage);
	}
}
