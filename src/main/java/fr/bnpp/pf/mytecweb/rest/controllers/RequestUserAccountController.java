package fr.bnpp.pf.mytecweb.rest.controllers;


import java.time.LocalDateTime;
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

import fr.bnpp.pf.mytecweb.rest.models.RequestUserAccount;
import fr.bnpp.pf.mytecweb.rest.services.RequestUserAccountService;
import fr.bnpp.pf.mytecweb.rest.services.UserAccountException;



@RequestMapping(value = "/")
@RestController
public class RequestUserAccountController {

	@Autowired
	private RequestUserAccountService requestUserAccountService;
	
	
       
  
    
 
    //------------------- List RequestUserAccounts  --------------------------------------------------------
    // optional params : 
	// filter : can by uid or state
	// uid : uid
	// state : 0=New 1=NewEmailValidated  2=Allowed,  3=Refused
    @RequestMapping(value = "/request-user-account", method = RequestMethod.GET)
    public ResponseEntity<List<RequestUserAccount>> list( 
    		@RequestParam("filter") Optional<String> filterOpt,
    		@RequestParam("state") Optional<String> stateOpt,
    		@RequestParam("uid") Optional<String> uidOpt
    		) {
    	
    		if ( filterOpt.isPresent() )  { // if a param filter is provided 
    			System.out.println("RequestUserAccountController :  GET : filter present = >" + filterOpt.get().toLowerCase() + "<");
    			switch( filterOpt.get().toLowerCase() ) {
	    			case "state" : {
	    				if ( stateOpt.isPresent() ) { // if state parameter is present :  state : 1=New  2=Allowed,  3=Refused
	    					try {
	    						Integer state = Integer.parseInt(stateOpt.get());
		    					List<RequestUserAccount> requestUserAccounts =  requestUserAccountService.findByState(state);
		    			        if(requestUserAccounts.isEmpty()){
		    			            return new ResponseEntity<List<RequestUserAccount>>(requestUserAccounts, HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		    			        }
		    			        return new ResponseEntity<List<RequestUserAccount>>(requestUserAccounts, HttpStatus.OK);
	    					} catch (Exception e) {
	    	    	    				return new ResponseEntity<List<RequestUserAccount>>(HttpStatus.INTERNAL_SERVER_ERROR);
	    					}
	    					
	    				} else {
	    					System.err.println("RequestUserAccountController :  GET : bad request : state filter asked but no state provided !");
	    					return new ResponseEntity<List<RequestUserAccount>>(HttpStatus.BAD_REQUEST);
	    				}
	    				
	    			}
	    			
	    			case "uid" : {
	    				if (uidOpt.isPresent()) { // if a param uid is provided find by uid
		    	    			System.out.println("RequestUserAccountController : GET  : filter with uid = " + uidOpt.get());
		    	    			try {
		    	    		        Optional<RequestUserAccount> requestUserAccountOpt = requestUserAccountService.findByUid(uidOpt.get());
		    	    		        if(!requestUserAccountOpt.isPresent()) {
		    	    		            System.err.println("--> no request found with uid " + uidOpt.get());
		    	    		            return new ResponseEntity<List<RequestUserAccount>>(HttpStatus.NOT_FOUND);
		    			        }
		    	    		        
		    	    		        List<RequestUserAccount> requestUserAccounts = new ArrayList<RequestUserAccount>(); // create an empty RequestUserAccount List
		    	    		        requestUserAccounts.add(requestUserAccountOpt.get()); // add the requestUserAccount found
		    	    		        return new ResponseEntity<List<RequestUserAccount>>(requestUserAccounts, HttpStatus.OK);
		    	    		        
		    	    	        } catch (Exception e) {
		    	    	        		System.err.println("--> error : ");
		    	    	        		e.printStackTrace();
		    	    	    			return new ResponseEntity<List<RequestUserAccount>>(HttpStatus.INTERNAL_SERVER_ERROR);
		    	    	        }
	    				} else {
	    					System.err.println("RequestUserAccountController :  GET : bad request : uid filter asked but no uid provided !");
	    					return new ResponseEntity<List<RequestUserAccount>>(HttpStatus.BAD_REQUEST);
	    				}
	    				
	    			}
	    			
	    			default : {
	    				System.err.println("RequestUserAccountController : GET :  bad request : filter asked but filter is not state or uid !");
	    				return new ResponseEntity<List<RequestUserAccount>>(HttpStatus.BAD_REQUEST);
	    			}
    			}
       			
    		} else {
    			System.out.println("RequestUserAccountController : GET : listAllRequestUserAccounts");
	    		try {
	    			List<RequestUserAccount> requestUserAccounts =  requestUserAccountService.findAll();
		        if(requestUserAccounts.isEmpty()){
		            return new ResponseEntity<List<RequestUserAccount>>(requestUserAccounts, HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<List<RequestUserAccount>>(requestUserAccounts, HttpStatus.OK);
	    	    } catch (Exception e) {
	    	    		return new ResponseEntity<List<RequestUserAccount>>(HttpStatus.INTERNAL_SERVER_ERROR);
	    	    }
    		}
    	
    }

  
    
    
    
    //------------------- Count RequestUserAccounts by state --------------------------------------------------------
    // optional params : 
	// state : 1=New  2=Allowed,  3=Refused
    @RequestMapping(value = "/request-user-account/count", method = RequestMethod.GET)
    public ResponseEntity<Long> countByState( 
    		@RequestParam("state") Optional<String> stateOpt
    		) {
    	
  
		if ( stateOpt.isPresent() ) { // if state parameter is present :  state : 1=New  2=Allowed,  3=Refused
			System.out.println("RequestUserAccountController : GET : countByState");
			try {
				Integer state = Integer.parseInt(stateOpt.get());
				return new ResponseEntity<Long>(requestUserAccountService.countByState(state), HttpStatus.OK);
			} catch (Exception e) {
	    			return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} else {
    			System.out.println("RequestUserAccountController : GET : countAll");
	    		try {
	    			return new ResponseEntity<Long>(requestUserAccountService.countAll(), HttpStatus.OK);
	    	    } catch (Exception e) {
	    	    		return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
	    	    }
    		}
    	
    }

    
    
    
    
    
    //-------------------Retrieve Single RequestUserAccount--------------------------------------------------------
    
    @RequestMapping(value = "/request-user-account/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<RequestUserAccount> getRequestUserAccount(@PathVariable("id") long id) {
        System.out.println("RequestUserAccountController :  getRequestUserAccount with id " + id);
        try {
	        Optional<RequestUserAccount> requestUserAccountOpt = requestUserAccountService.read(id);
	        if(!requestUserAccountOpt.isPresent()) {
	            System.out.println("Request User account with id " + id + " not found");
	            return new ResponseEntity<RequestUserAccount>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<RequestUserAccount>(requestUserAccountOpt.get(), HttpStatus.OK);
        } catch (Exception e) {
    			return new ResponseEntity<RequestUserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }
 
    
    
    
    
    
    
    //------------------- validate RequestUserAccount email by uuid --------------------------------------------------------
    
    @RequestMapping(value = "/request-user-account/validate/{uuid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<RequestUserAccount> validateRequestUserAccount(@PathVariable("uuid") String uuid) {
        System.out.println("RequestUserAccountController :  validateRequestUserAccount with token " + uuid );
        try {
	        Optional<RequestUserAccount> requestUserAccountOpt = requestUserAccountService.findByUuid(uuid);
	        if(!requestUserAccountOpt.isPresent()) {
	            System.out.println("Request User account with token " + uuid + " not found");
	            return new ResponseEntity<RequestUserAccount>(HttpStatus.NOT_FOUND);
	        }
	        
	        // Get the RequestUserAccount found 
	        RequestUserAccount requestUserAccountFound = requestUserAccountOpt.get();
	        
	        System.out.println("Request User account with token " + uuid + "  found :  id = " + requestUserAccountFound.getId() + " uid = " + requestUserAccountFound.getUid());
	        System.out.println("Request User account previos state is " + requestUserAccountFound.getState());
	        
	        // Verify if state == 0 ie if it's a new request not yet email validated
	        if (requestUserAccountFound.getState() == 0) {
	        	
	        		//Change the state to 1 to indicate that this request is email validated
	        		requestUserAccountFound.setState(1); 
	        		requestUserAccountFound.setVerifyEmailAdressDate(LocalDateTime.now());
		        
		       
		        try {
		        	
		        	 	// save the state to database
		    			RequestUserAccount requestUserAccountUpdated = requestUserAccountService.update(requestUserAccountFound);
		    			
		    			System.out.println("Request User account correctly validated");
		    					
		    			// return the saved RequestUserAccount
		            return new ResponseEntity<RequestUserAccount>(requestUserAccountUpdated, HttpStatus.OK);
		            
		    		} catch (Exception e) {
		    			
		    			switch (e.getMessage()) {
		    				case "User account already exists" : { 
		    					System.err.println("Request User account not validated : User account already exists");
		    					return new ResponseEntity<RequestUserAccount>(HttpStatus.CONFLICT);
		    				}
		    				
		    				
		    				case "RequestUserAccount not found" : { 
		    					System.err.println("Request User account not validated : RequestUserAccount not found");
		    					return new ResponseEntity<RequestUserAccount>(HttpStatus.NOT_FOUND);
		    				}
		    				
		    				
		    				case "Bad previous state" : {
		    					System.err.println("Request User account not validated : Bad previous state");
		    					return new ResponseEntity<RequestUserAccount>(HttpStatus.BAD_REQUEST); 
		    				}
		    				
		    				
		    				default : { 
		    					return new ResponseEntity<RequestUserAccount>(HttpStatus.INTERNAL_SERVER_ERROR); 
		    				}
		    			}
	    			
	    			}
		    			
		    	
	        } else {
	        	  // if requestUserAccount is found and already email validated
	        	  System.err.println("Request User account not validated : déja validé");
	        	  return new ResponseEntity<RequestUserAccount>(requestUserAccountFound, HttpStatus.ALREADY_REPORTED);
	        }
	        
	       
        } catch (Exception e) {
        		e.printStackTrace();
        		System.err.println("Request User account not validated : erreur interne");
    			return new ResponseEntity<RequestUserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }
 
    
     
     
    //-------------------Create a RequestUserAccount--------------------------------------------------------
     
    @RequestMapping(value = "/request-user-account", method = RequestMethod.POST)
    public ResponseEntity<RequestUserAccount> createRequestUserAccount(@RequestBody RequestUserAccount requestUserAccountReceived, UriComponentsBuilder ucBuilder) throws UserAccountException {
        System.out.println("RequestUserAccountController :  Creating RequestUserAccount " + requestUserAccountReceived.getUid());
       
        try {
        	
        		requestUserAccountService.create(requestUserAccountReceived);
        		HttpHeaders headers = new HttpHeaders();
		    headers.setLocation(ucBuilder.path("/RequestUserAccount/{id}").buildAndExpand(requestUserAccountReceived.getId()).toUri());
		    System.out.println("RequestUserAccount created");
		    return new ResponseEntity<RequestUserAccount>(headers, HttpStatus.CREATED);
        	
        } catch (Exception e ) {
	        switch (e.getMessage()) {
	        	        
				case "User account request already exists" : { 
					System.err.println("User account already exists");
					UserAccountException eUAE = (UserAccountException) e;
					return new ResponseEntity<RequestUserAccount>(eUAE.getRequestUserAccount(), HttpStatus.ALREADY_REPORTED);
				}
				
				case "User account already exists" : { 
					System.err.println("User account already exists");
					return new ResponseEntity<RequestUserAccount>(HttpStatus.CONFLICT);
				}
				
				default : { 
					System.err.println("InternalServeur Error");
					e.printStackTrace();
					return new ResponseEntity<RequestUserAccount>(HttpStatus.INTERNAL_SERVER_ERROR); 
				}
	        }
        }
        
    }
 
    
    
    
    
    
     
    //------------------- Update a RequestUserAccount --------------------------------------------------------
     
    @RequestMapping(value = "/request-user-account", method = RequestMethod.PUT)
    public ResponseEntity<RequestUserAccount> updateRequestUserAccount(@RequestBody RequestUserAccount requestUserAccountToUpdate) {
    		System.out.println("RequestUserAccountController :  Updating RequestUserAccount " + requestUserAccountToUpdate.getId());
    		
    		
    		try {
    			// save the new state in database
    			RequestUserAccount requestUserAccountUpdated = requestUserAccountService.update(requestUserAccountToUpdate);
    			return new ResponseEntity<RequestUserAccount>(requestUserAccountUpdated, HttpStatus.OK);
    			
    		} catch (Exception e) {
    			
    			switch (e.getMessage()) {
    				case "User account already exists" : { 
    					System.err.println("User account already exists");
    					return new ResponseEntity<RequestUserAccount>(HttpStatus.CONFLICT);
    				}
    				
    				
    				case "RequestUserAccount not found" : { 
    					System.err.println("RequestUserAccount not found");
    					return new ResponseEntity<RequestUserAccount>(HttpStatus.NOT_FOUND);
    				}
    				
    				case "Bad previous state" : {
    					System.err.println("Bad previous state");
    					return new ResponseEntity<RequestUserAccount>(HttpStatus.BAD_REQUEST); 
    				}
    				
    				
    				default : { 
    					System.err.println("InternalServeur Error");
    					e.printStackTrace();
    					return new ResponseEntity<RequestUserAccount>(HttpStatus.INTERNAL_SERVER_ERROR); 
    				}
    			
    			}
      			
    		}
    }

    
    
    
    
    //------------------- Delete a RequestUserAccount --------------------------------------------------------
     
//    @RequestMapping(value = "/User-account/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<RequestUserAccount> deleteRequestUserAccount(@PathVariable("id") long id) {
//        System.out.println("Fetching & Deleting RequestUserAccount with id " + id);
// 
//        RequestUserAccount RequestUserAccount = RequestUserAccountService.findById(id);
//        if (RequestUserAccount == null) {
//            System.out.println("Unable to delete. RequestUserAccount with id " + id + " not found");
//            return new ResponseEntity<RequestUserAccount>(HttpStatus.NOT_FOUND);
//        }
// 
//        RequestUserAccountService.deleteRequestUserAccountById(id);
//        return new ResponseEntity<RequestUserAccount>(HttpStatus.NO_CONTENT);
//    }
 
     
	
	
	
}
