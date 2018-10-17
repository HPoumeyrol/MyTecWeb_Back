package fr.bnpp.pf.mytecweb.rest.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.bnpp.pf.mytecweb.rest.models.RequestUserAccount;
import fr.bnpp.pf.mytecweb.rest.models.UserAccount;
import fr.bnpp.pf.mytecweb.rest.repositories.UserAccountRepository;



@Service
public class UserAccountServiceImpl implements UserAccountService { 
	
	
	
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	
	
	
	/**
	 * method to create an userAccount
	 * 
	 * @param userAccount: an userAccount
	 * 
	 */
		
	@Override
	public UserAccount create(UserAccount userAccountNew) throws Exception {
		
		// verify if an user account with this uid doesn't already exists
		Optional<UserAccount> userAccountOpt = userAccountRepository.findByUidIgnoreCase(userAccountNew.getUid());
		if (userAccountOpt.isPresent()) {
			System.err.println("User account with uid " + userAccountNew.getUid() + " already exists !");
			throw new Exception("User account already exists");
		}
		
		//encode password
		userAccountNew.setPassword(passwordEncoder.encode(userAccountNew.getPassword())); 
		
		// save to database
		final UserAccount userAccountCreated = userAccountRepository.save(userAccountNew);
		
		// return saved object with is id
		return userAccountCreated;
		
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * method to create an userAccount from a RequestUserAccount
	 * 
	 * @paramrequestUserAccount: an RequestUserAccount
	 * 
	 */
	
	@Override
	public UserAccount createFromRequestUserAccount(RequestUserAccount requestUserAccountNew) throws Exception {
		
		
		// verify if an user account with this uid doesn't already exists
		Optional<UserAccount> userAccountOpt = userAccountRepository.findByUidIgnoreCase(requestUserAccountNew.getUid());
		if (userAccountOpt.isPresent()) {
			System.err.println("User account with uid " + requestUserAccountNew.getUid() + " already exists !");
			throw new Exception("User account already exists");
		}
		
		// Create a new User Account
		UserAccount userAccountNew = new UserAccount();
		
		// Set user account attributes from request user account attributes
		userAccountNew.setUid(requestUserAccountNew.getUid());
		userAccountNew.setFirstName(requestUserAccountNew.getFirstName());
		userAccountNew.setLastName(requestUserAccountNew.getLastName());
		userAccountNew.setEmail(requestUserAccountNew.getEmail());
		userAccountNew.setTecMember(requestUserAccountNew.getTecMember());
		userAccountNew.setPassword(requestUserAccountNew.getDesiredPassword());
		userAccountNew.setLastPasswordResetDate(new Date());
		userAccountNew.setEnabled(true);
		
		// save to database
		final UserAccount userAccountCreated = userAccountRepository.save(userAccountNew);
		
		// return saved object with is id
		return userAccountCreated;
			
	}
	

	
	
	
	
	
	
	
	

	/**
	 * method to read informations about a userAccount
	 * 
	 * @param id:  userAccount id
	 *
	 */
	@Override
	public Optional<UserAccount> read(Long id)  throws Exception{
		
		return  userAccountRepository.findById(id);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	/**
	 * method to read informations about a userAccount finding it by uid
	 * 
	 * @param uid:  userAccount uid
	 *
	 */
	@Override
	public Optional<UserAccount> findByUid(String uid)  throws Exception{
		
		return  userAccountRepository.findByUidIgnoreCase(uid);
		
	}
	
	
	
	
	/**
	 * method to read informations about a userAccount finding it by enabled
	 * 
	 * @param uid:  userAccount uid
	 *
	 */
	@Override
	public List<UserAccount> findByEnabled(Boolean enabled)  throws Exception {
		return  userAccountRepository.findAllByEnabled(enabled);
		
	}
	
	
	
	/**
	 * method to read informations about a userAccount finding it by enabled And tecMember
	 * 
	 * @param uid:  userAccount uid
	 *
	 */
	@Override
	public List<UserAccount> findByEnabledAndTecMember(Boolean enabled, Boolean tecMember)  throws Exception {
		return  userAccountRepository.findAllByEnabledAndTecMember(enabled, tecMember);
		
	}
		
	
	
	
	
	
	
	/**
	 * method to update an userAccount 
	 * 
	 * @param id: userAccount id
	 * @param userAccount: userAccount with new informations
	 *
	 */
	@Override
	public UserAccount update(UserAccount userAccountNew)  throws Exception{

		Optional<UserAccount> userAccountOpt = userAccountRepository.findById(userAccountNew.getId());

		if (!userAccountOpt.isPresent()) {
			throw new Exception("User account not found");
		}
		
		System.out.println("UserAccountServiceImpl :  update for : " + userAccountNew.toString());
		UserAccount userAccountSaved = userAccountRepository.save(userAccountNew);
		System.out.println("UserAccountServiceImpl :  saved  : " + userAccountSaved.toString());
		return userAccountSaved;
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * method to delete an userAccount 
	 * 
	 * @param id: userAccount id
	 */
	@Override
	public void  delete(Long id)  throws Exception{
		Optional<UserAccount> userAccountOpt = userAccountRepository.findById(id);
		
		if (!userAccountOpt.isPresent()) {
			throw new Exception("User account not found");
		}
		
		userAccountRepository.deleteById(id);
		
	}

	
	
	
	
	
	
	

	
	
	
	/**
	 * method to list userAccounts
	 * 
	 */
	@Override
	public List<UserAccount> findAll()  throws Exception {
		List<UserAccount> list = new ArrayList<>();
		list.addAll(userAccountRepository.findAll());
		return list;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * method to authenticate
	 * 
	 */
	public UserAccount login(String uid, String password)  throws Exception {
		Optional<UserAccount> userAccountOpt = userAccountRepository.findByUidIgnoreCase(uid);
		
		if (!userAccountOpt.isPresent()) {
			throw new Exception("UserAccount not found");
		}
		
		if(! userAccountOpt.get().getPassword().equals(password)) {
			throw new Exception("bad password");
		}
		
		return userAccountOpt.get();
	};
	
	
	
	
	
	
	
	
	
	
	/**
	 * method to verify existence of an UserAccount
	 * 
	 */
	
	public boolean isUserAccountExist(UserAccount userAccount) {
		Optional<UserAccount> userAccountOpt = userAccountRepository.findByUidIgnoreCase(userAccount.getUid());
		return  userAccountOpt.isPresent();
	}
	
}
