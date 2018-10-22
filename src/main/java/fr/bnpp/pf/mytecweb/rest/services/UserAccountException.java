package fr.bnpp.pf.mytecweb.rest.services;
import fr.bnpp.pf.mytecweb.rest.models.RequestUserAccount;

public class UserAccountException extends Exception { 

	private static final long serialVersionUID = 3281058527722523048L;

	private RequestUserAccount requestUserAccount = null;

	public UserAccountException() {
	}
	
	public UserAccountException(String errorMessage, RequestUserAccount requestUserAccount, Throwable err) {
		
		super(errorMessage, err);
		this.requestUserAccount = requestUserAccount;
    }

	public RequestUserAccount getRequestUserAccount() {
		return requestUserAccount;
	}
		
	
}