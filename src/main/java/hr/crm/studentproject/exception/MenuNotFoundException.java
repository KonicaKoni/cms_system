package hr.crm.studentproject.exception;

public class MenuNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6757475139861550876L;
	
	public MenuNotFoundException(String msg) {
		super(msg);
	}
}
