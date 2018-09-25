package fr.bnpp.pf.mytecweb.rest.services;

import java.util.List;
import java.util.Optional;



import fr.bnpp.pf.mytecweb.rest.models.UserAccount;



public interface UserAccountService {
	
	// method to create an UserAccount
	public UserAccount create(UserAccount userAccountNew) throws Exception;

		
	// method to display details of an UserAccount
	public Optional<UserAccount> read(Long id)  throws Exception;

	// method to modify an UserAccount if user is the owner of it
	public UserAccount update(UserAccount userAccount)  throws Exception;

	// method to delete an UserAccount if user is the owner of it
	public void delete(Long id)  throws Exception;
	
	// to list UserAccounts 
	public List<UserAccount> findAll()  throws Exception;
	
	// to login an UserAccount
	public UserAccount login(String uid, String password)  throws Exception;
	
	// to verify if UserAccount exists
	public boolean isUserAccountExist(UserAccount userAccount);
	
	// to find by uid
	public Optional<UserAccount> findByUid(String uid)  throws Exception;
		
		
}


