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
    public ResponseEntity<List<UserAccount>> list(
    		@RequestParam("uid") Optional<String> uidOpt,
		@RequestParam("enabled") Optional<String> enabledOpt,
		@RequestParam("tecMember") Optional<String> tecMemberOpt ) {
		
		System.out.println("UserAccountController : list");
		
		ResponseEntity<List<UserAccount>> result = null;
	    List<UserAccount> userAccountList = new ArrayList<UserAccount>();;
		int factorSearch = 0;
		String uid = "";
		Boolean enabled = null;
		Boolean tecMember = null;
		
		
		// Get uid Parameter value if present
		if (uidOpt.isPresent()) {
			uid = uidOpt.get();
	        factorSearch += 1;
	    }
		
		// Get enabled Parameter value if present
		if (enabledOpt.isPresent()) {
			try {
				enabled = Boolean.parseBoolean(enabledOpt.get());
				System.out.println("Parameter enabled found. Value = " + enabled);
				factorSearch += 10;
			} catch (Exception e) {
				System.out.println("UserAccountController : list : erreur Enabled parameter is not boolean type");
				result = new ResponseEntity<List<UserAccount>>(HttpStatus.BAD_REQUEST);
				e.printStackTrace();
			}
	    }
		
		// Get tecMember Parameter value if present
		if (tecMemberOpt.isPresent()) {
			try {
				System.out.println("Parameter tecMember found raw value = " + tecMemberOpt.get());
				if (tecMemberOpt.get().equals("null")) {
					tecMember = null;
				} else {
					tecMember = Boolean.parseBoolean(tecMemberOpt.get());
				}
				System.out.println("Parameter tecMember found. Value = " + tecMember);
				
				if(tecMember != null) { factorSearch += 100; }
			} catch (Exception e) {
				result = new ResponseEntity<List<UserAccount>>(HttpStatus.BAD_REQUEST);
				e.printStackTrace();
				System.out.println("UserAccountController : list : erreur tecMember parameter is not boolean type");
			}
	    }
		
		
		System.out.println("UserAccountController : list : factorSearch = " + factorSearch);
		
		try {
			// process the search according to combination of parameters
			switch (factorSearch) {
			
				case 0  : { 	// search all parameters
					userAccountList = this.userAccountService.findAll();	break; 
				}
				
				
				case 1: { // search By uid
					Optional<UserAccount> userAccountOpt = userAccountService.findByUid(uid);
					if(userAccountOpt.isPresent()) {
						userAccountList.add(userAccountOpt.get());
					}
					break; 
				}
					
				case 10 : { 	// search all By enabled and TechMember
					 userAccountList = this.userAccountService.findByEnabled(enabled);	break; 
				}
				
				case 110 : { 	// search all By enabled and TechMember
					 userAccountList = this.userAccountService.findByEnabledAndTecMember(enabled, tecMember);	break; 
				}
				
				default  : { // combination of parameters not allowed
					factorSearch = -1; break; 
				}
				
			}
			
			if (factorSearch != -1) {
				// if search is correct
				result = new ResponseEntity<List<UserAccount>>(userAccountList, userAccountList.size() == 0 ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	
		    } else {
		        // if at least one parameter is not correct
		    		System.err.println("UserAccountController : list : mauvais parametres");
		    		System.err.println("uid = " + uid + " enabled = " + enabled + " tecMember = " + tecMember);
		        result = new ResponseEntity<List<UserAccount>>(HttpStatus.BAD_REQUEST); 
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("UserAccountController : list : erreur interne");
			result = new ResponseEntity<List<UserAccount>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
		
    }
    
    
    
    
    
 
//    //-------------------Search UserAccounts by uid --------------------------------------------------------
//    
//    @RequestMapping(value = "/user-account/search", method = RequestMethod.GET)
//    public ResponseEntity<List<UserAccount>> listByUid( @RequestParam("uid") Optional<String> uidOpt) {
//    	
//    		if (uidOpt.isPresent()) { // if a param uid is provided find by uid
//    			System.out.println("UserAccountController : listAllUserAccounts with uid = " + uidOpt.get());
//    			try {
//    		        Optional<UserAccount> userAccountOpt = userAccountService.findByUid(uidOpt.get());
//    		        if(!userAccountOpt.isPresent()) {
//    		            System.out.println("User account with uid " + uidOpt.get() + " not found");
//    		            return new ResponseEntity<List<UserAccount>>(HttpStatus.NOT_FOUND);
//		        }
//    		        
//    		        List<UserAccount> userAccounts = new ArrayList<UserAccount>(); // create an empty UserAccount List
//    		        userAccounts.add(userAccountOpt.get()); // add the userAccount found
//    		        
//		        return new ResponseEntity<List<UserAccount>>(userAccounts, HttpStatus.OK);
//    	        } catch (Exception e) {
//    	    			return new ResponseEntity<List<UserAccount>>(HttpStatus.INTERNAL_SERVER_ERROR);
//    	        }
//    			
//    			
//    		} else { // if no param provided find all
//	    		System.out.println("UserAccountController : listAllUserAccounts");
//	    		try {
//	    			List<UserAccount> userAccounts =  userAccountService.findAll();
//		        if(userAccounts.isEmpty()){
//		            return new ResponseEntity<List<UserAccount>>(userAccounts, HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//		        }
//		        return new ResponseEntity<List<UserAccount>>(userAccounts, HttpStatus.OK);
//	    	    } catch (Exception e) {
//	    	    		return new ResponseEntity<List<UserAccount>>(HttpStatus.INTERNAL_SERVER_ERROR);
//	    	    }
//    		}
//    }

  
    
    
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
     
    @RequestMapping(value = "/user-account/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserAccount> updateUserAccount(@PathVariable("id") long id, @RequestBody UserAccount userAccountReceived) {
        System.out.println("UserAccountController :  Updating UserAccount " + id);
         
        Optional<UserAccount> currentUserAccountOpt;
		try {
			currentUserAccountOpt = userAccountService.read(id);
		
			
			if (!currentUserAccountOpt.isPresent()) {
	            System.out.println("UserAccount with id " + id + " not found");
	            return new ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND);
	        }
	 
	        UserAccount currentUserAccount = currentUserAccountOpt.get();
	        
	        // Transfer informations form userAccountReceived  to currentUserAccount
	        currentUserAccount.setEnabled(userAccountReceived.isEnabled());
	        currentUserAccount.setTecMember(userAccountReceived.isTecMember());
	        currentUserAccount.setUid(userAccountReceived.getUid());
	        currentUserAccount.setFirstName(userAccountReceived.getFirstName());
	        currentUserAccount.setLastName(userAccountReceived.getLastName());
	        currentUserAccount.setOfficeDepartment(userAccountReceived.getOfficeDepartment());
	        currentUserAccount.setTrigram(userAccountReceived.getTrigram());
	        currentUserAccount.setDiaporamaId(userAccountReceived.getDiaporamaId());
	        currentUserAccount.setEmail(userAccountReceived.getEmail());
	        currentUserAccount.setDivision(userAccountReceived.getDivision());
	        currentUserAccount.setLocation(userAccountReceived.getLocation());
	        currentUserAccount.setTecTeam(userAccountReceived.getTecTeam());
	        currentUserAccount.setFunction(userAccountReceived.getFunction());
	        currentUserAccount.setRolemytec(userAccountReceived.getRolemytec());
	        currentUserAccount.setWorkingTime(userAccountReceived.getWorkingTime());
	        currentUserAccount.setDayOff (userAccountReceived.getDayOff());
	        currentUserAccount.setTeleworkingDay(userAccountReceived.getTeleworkingDay());
	        currentUserAccount.setWorkstations(userAccountReceived.getWorkstations());
	        
	        
	        try { 
	        		userAccountService.update(currentUserAccount);
	        		return new ResponseEntity<UserAccount>(currentUserAccount, HttpStatus.OK);
	        } catch (Exception e) {
	    			return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
         
        
    }
 
    
    
    
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
