package fr.bnpp.pf.mytecweb.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
		
		Optional<UserAccount> userAccountOpt = userAccountRepository.findByUidIgnoreCase(userAccountNew.getUid());

		if (userAccountOpt.isPresent()) {
			throw new Exception("User account already exists");
		}
		
		userAccountNew.setPassword(passwordEncoder.encode(userAccountNew.getPassword())); //encode password
		
		
		final UserAccount userAccountCreated = userAccountRepository.save(userAccountNew);
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
		
		return  userAccountRepository.save(userAccountNew);
		
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
