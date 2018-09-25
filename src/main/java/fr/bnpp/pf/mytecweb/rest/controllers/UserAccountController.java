package fr.bnpp.pf.mytecweb.rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fr.bnpp.pf.mytecweb.rest.models.UserAccount;
import fr.bnpp.pf.mytecweb.rest.services.UserAccountService;

@RequestMapping(value = "/")
@RestController
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;
	
	
    //-------------------Retrieve All UserAccounts--------------------------------------------------------
    
    @RequestMapping(value = "/user-account", method = RequestMethod.GET)
    public ResponseEntity<List<UserAccount>> listAllUserAccounts() {
    	
		System.out.println("UserAccountController : listAllUserAccounts");
    		try {
    			List<UserAccount> userAccounts =  userAccountService.findAll();
	        if(userAccounts.isEmpty()){
	            return new ResponseEntity<List<UserAccount>>(userAccounts, HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<UserAccount>>(userAccounts, HttpStatus.OK);
    	    } catch (Exception e) {
    	    		return new ResponseEntity<List<UserAccount>>(HttpStatus.INTERNAL_SERVER_ERROR);
    	    }
		
    }
    
    
    
    
    
 
    //-------------------Search UserAccounts by uid --------------------------------------------------------
    
    @RequestMapping(value = "/user-account/search", method = RequestMethod.GET)
    public ResponseEntity<List<UserAccount>> listByUid( @RequestParam("uid") Optional<String> uidOpt) {
    	
    		if (uidOpt.isPresent()) { // if a param uid is provided find by uid
    			System.out.println("UserAccountController : listAllUserAccounts with uid = " + uidOpt.get());
    			try {
    		        Optional<UserAccount> userAccountOpt = userAccountService.findByUid(uidOpt.get());
    		        if(!userAccountOpt.isPresent()) {
    		            System.out.println("User account with uid " + uidOpt.get() + " not found");
    		            return new ResponseEntity<List<UserAccount>>(HttpStatus.NOT_FOUND);
		        }
    		        
    		        List<UserAccount> userAccounts = new ArrayList<UserAccount>(); // create an empty UserAccount List
    		        userAccounts.add(userAccountOpt.get()); // add the userAccount found
    		        
		        return new ResponseEntity<List<UserAccount>>(userAccounts, HttpStatus.OK);
    	        } catch (Exception e) {
    	    			return new ResponseEntity<List<UserAccount>>(HttpStatus.INTERNAL_SERVER_ERROR);
    	        }
    			
    			
    		} else { // if no param provided find all
	    		System.out.println("UserAccountController : listAllUserAccounts");
	    		try {
	    			List<UserAccount> userAccounts =  userAccountService.findAll();
		        if(userAccounts.isEmpty()){
		            return new ResponseEntity<List<UserAccount>>(userAccounts, HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<List<UserAccount>>(userAccounts, HttpStatus.OK);
	    	    } catch (Exception e) {
	    	    		return new ResponseEntity<List<UserAccount>>(HttpStatus.INTERNAL_SERVER_ERROR);
	    	    }
    		}
    }

  
    
    
    //-------------------Retrieve Single UserAccount--------------------------------------------------------
    
    @RequestMapping(value = "/user-account/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserAccount> getUserAccount(@PathVariable("id") long id) {
        System.out.println("UserAccountController :  getUserAccount with id " + id);
        try {
	        Optional<UserAccount> userAccountOpt = userAccountService.read(id);
	        if(!userAccountOpt.isPresent()) {
	            System.out.println("User account with id " + id + " not found");
	            return new ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<UserAccount>(userAccountOpt.get(), HttpStatus.OK);
        } catch (Exception e) {
    			return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }
 
     
     
    //-------------------Create a UserAccount--------------------------------------------------------
     
    @RequestMapping(value = "/user-account", method = RequestMethod.POST)
    public ResponseEntity<Void> createUserAccount(@RequestBody UserAccount userAccount, UriComponentsBuilder ucBuilder) {
        System.out.println("UserAccountController :  Creating UserAccount " + userAccount.getUid());
 
        if (userAccountService.isUserAccountExist(userAccount)) {
            System.err.println("A UserAccount with uid " + userAccount.getUid() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        
        try {
	        userAccountService.create(userAccount);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/UserAccount/{id}").buildAndExpand(userAccount.getId()).toUri());
	        System.out.println("RequestUserAccount created");
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch (Exception e) {
    			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    
    
     
    //------------------- Update a UserAccount --------------------------------------------------------
     
//    @RequestMapping(value = "/User-account/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<UserAccount> updateUserAccount(@PathVariable("id") long id, @RequestBody UserAccount UserAccount) {
//        System.out.println("UserAccountController :  Updating UserAccount " + id);
//         
//        Optional<UserAccount> currentUserAccount = userAccountService.read(id);
//         
//        if (currentUserAccount==null) {
//            System.out.println("UserAccount with id " + id + " not found");
//            return new ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND);
//        }
// 
//        currentUserAccount.setName(UserAccount.getName());
//        currentUserAccount.setAge(UserAccount.getAge());
//        currentUserAccount.setSalary(UserAccount.getSalary());
//         
//        UserAccountService.updateUserAccount(currentUserAccount);
//        return new ResponseEntity<UserAccount>(currentUserAccount.get(), HttpStatus.OK);
//    }
 
    //------------------- Delete a UserAccount --------------------------------------------------------
     
//    @RequestMapping(value = "/User-account/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<UserAccount> deleteUserAccount(@PathVariable("id") long id) {
//        System.out.println("Fetching & Deleting UserAccount with id " + id);
// 
//        UserAccount UserAccount = UserAccountService.findById(id);
//        if (UserAccount == null) {
//            System.out.println("Unable to delete. UserAccount with id " + id + " not found");
//            return new ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND);
//        }
// 
//        UserAccountService.deleteUserAccountById(id);
//        return new ResponseEntity<UserAccount>(HttpStatus.NO_CONTENT);
//    }
 
     
	
	
	
}
