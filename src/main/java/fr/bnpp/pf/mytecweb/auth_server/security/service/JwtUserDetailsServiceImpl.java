package fr.bnpp.pf.mytecweb.auth_server.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.bnpp.pf.mytecweb.auth_server.security.JwtUserFactory;
import fr.bnpp.pf.mytecweb.rest.models.UserAccount;
import fr.bnpp.pf.mytecweb.rest.services.UserAccountService;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

//    // CODE ORIGINAL
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
//        } else {
//            return JwtUserFactory.create(user);
//        }
//    }
//    
    
    
    @Autowired
    private UserAccountService userAccountService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    		try {
    			Optional<UserAccount>  userAccountOpt = userAccountService.findByUid(username);

	        if (userAccountOpt.isPresent()) {
	        	 	return JwtUserFactory.create(userAccountOpt.get());
	        } else {
	            throw new UsernameNotFoundException(String.format("No user found with uid '%s'.", username));
	        }
    		} catch (Exception e) {
    			throw new UsernameNotFoundException("Internal Error No user found");
    		}
    }
    
}
