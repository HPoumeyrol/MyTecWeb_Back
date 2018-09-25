package fr.bnpp.pf.mytecweb.rest.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.bnpp.pf.mytecweb.rest.models.UserAccount;
import fr.bnpp.pf.mytecweb.rest.models.UserAccountView;
import fr.bnpp.pf.mytecweb.rest.services.UserAccountService;

// @CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(value = "/myauth")
@RestController
public class AuthController {
	
	@Autowired
	private UserAccountService userAccountService;
	

	// SignIn method
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@RequestBody UserAccountView userAccountView) {
		ResponseEntity<?> result = new ResponseEntity<>("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR); //default response
		System.out.println("AuthController : signIn : " + userAccountView);
		try {
			final UserAccount userAccountFound = userAccountService.login(userAccountView.getUid(), userAccountView.getPassword());
			result = new ResponseEntity<>(userAccountFound, HttpStatus.OK);
			
		} catch (Exception e) {
			switch(e.getMessage()) {
				case "User account not found" :
				case "bad password" :
					System.err.println("AuthController : loginWithEmailAndPassword : Bad Credentials");
					result =  new ResponseEntity<>("bad credential", HttpStatus.UNAUTHORIZED);
					break;
			}
			
		}
		return result;
	}
	
	
	
	// Login method
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginWithEmailAndPassword(@RequestBody UserAccountView userAccountView) {
		ResponseEntity<?> result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); //default response
		System.out.println("AuthController : loginWithEmailAndPassword : " + userAccountView);
		try {
			final UserAccount userAccountFound = userAccountService.login(userAccountView.getUid(), userAccountView.getPassword());
			result = new ResponseEntity<>(userAccountFound, HttpStatus.OK);
			
		} catch (Exception e) {
			switch(e.getMessage()) {
				case "UserAccount not found" :
				case "bad password" :
					System.err.println("AuthController : loginWithEmailAndPassword : Bad Credentials");
					result =  new ResponseEntity<>("bad credential", HttpStatus.UNAUTHORIZED);
					break;
			}
			
		}
		return result;
	}
		
	
	
}

