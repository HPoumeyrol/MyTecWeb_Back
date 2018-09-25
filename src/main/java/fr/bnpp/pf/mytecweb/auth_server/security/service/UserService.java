package fr.bnpp.pf.mytecweb.auth_server.security.service;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.bnpp.pf.mytecweb.auth_server.model.security.User;
import fr.bnpp.pf.mytecweb.auth_server.security.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	 
	@Autowired
	private UserRepository userRepository;

	
	public User registerNewUserAccount(User userNew) throws Exception {
//	    if (emailExist(accountDto.getEmail())) {
//	        throw new Exception(
//	          "There is an account with that email adress:" + accountDto.getEmail());
//	    }
		userNew.setFirstname("toto");
		userNew.setLastname("toto");
		userNew.setEmail("toto");
		userNew.setEnabled(true);
		userNew.setLastPasswordResetDate(new Date());
	    userNew.setPassword(passwordEncoder.encode(userNew.getPassword()));
	    return userRepository.save(userNew);
	}

}

