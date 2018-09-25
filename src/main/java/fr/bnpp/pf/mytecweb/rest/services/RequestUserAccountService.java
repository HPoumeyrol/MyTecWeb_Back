package fr.bnpp.pf.mytecweb.rest.services;

import java.util.List;
import java.util.Optional;

import fr.bnpp.pf.mytecweb.rest.models.RequestUserAccount;

public interface RequestUserAccountService {
	
	// method to create an RequestUserAccount
	public RequestUserAccount create(RequestUserAccount requestUserAccountNew) throws Exception;

		
	// method to display details of an RequestUserAccount
	public Optional<RequestUserAccount> read(Long id)  throws Exception;

	// method to modify an RequestUserAccount if user is the owner of it
	public RequestUserAccount update(RequestUserAccount requestUserAccount)  throws Exception;

	
	// to list RequestUserAccounts 
	public List<RequestUserAccount> findAll()  throws Exception;
	
	
	// to count RequestUserAccounts by state
	public Long countAll()  throws Exception;
	
	// to verify if RequestUserAccount exists
	public boolean isRequestUserAccountExist(RequestUserAccount RequestUserAccount);
	
	// to find by uid
	public Optional<RequestUserAccount> findByUid(String uid)  throws Exception;

	// to list RequestUserAccounts by state
	public List<RequestUserAccount> findByState(Integer state)  throws Exception;
	
	// to count RequestUserAccounts by state
	public Long countByState(Integer state)  throws Exception;
		
}
