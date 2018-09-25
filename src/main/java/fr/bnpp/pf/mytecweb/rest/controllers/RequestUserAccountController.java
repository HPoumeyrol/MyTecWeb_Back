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

import fr.bnpp.pf.mytecweb.rest.models.RequestUserAccount;
import fr.bnpp.pf.mytecweb.rest.models.UserAccount;
import fr.bnpp.pf.mytecweb.rest.services.RequestUserAccountService;
import fr.bnpp.pf.mytecweb.rest.services.UserAccountService;

// @CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(value = "/")
@RestController
public class RequestUserAccountController {

	@Autowired
	private RequestUserAccountService requestUserAccountService;
	
	@Autowired
	private UserAccountService userAccountService;
	
       
    
    
    
 
    //------------------- List RequestUserAccounts  --------------------------------------------------------
    // optional params : 
	// filter : can by uid or state
	// uid : uid
	// state : 1=New  2=Allowed,  3=Refused
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
	            System.out.println("User account with id " + id + " not found");
	            return new ResponseEntity<RequestUserAccount>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<RequestUserAccount>(requestUserAccountOpt.get(), HttpStatus.OK);
        } catch (Exception e) {
    			return new ResponseEntity<RequestUserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }
 
     
     
    //-------------------Create a RequestUserAccount--------------------------------------------------------
     
    @RequestMapping(value = "/request-user-account", method = RequestMethod.POST)
    public ResponseEntity<Void> createRequestUserAccount(@RequestBody RequestUserAccount requestUserAccount, UriComponentsBuilder ucBuilder) {
        System.out.println("RequestUserAccountController :  Creating RequestUserAccount " + requestUserAccount.getUid());
 
        // Verifiy if a request already exist for the requested uid
        if (requestUserAccountService.isRequestUserAccountExist(requestUserAccount)) {
            System.err.println("A RequestUserAccount with uid " + requestUserAccount.getUid() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.ALREADY_REPORTED);
        }
        
        // verify if an user account already exists for the requested uid
        UserAccount userAccount = new UserAccount();
        userAccount.setUid(requestUserAccount.getUid());
        if (userAccountService.isUserAccountExist(userAccount)) {
            System.err.println("A UserAccount with uid " + requestUserAccount.getUid() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        
        
        
        try {
	        requestUserAccountService.create(requestUserAccount);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/RequestUserAccount/{id}").buildAndExpand(requestUserAccount.getId()).toUri());
	        System.out.println("RequestUserAccount created");
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch (Exception e) {
    			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    
    
     
    //------------------- Update a RequestUserAccount --------------------------------------------------------
     
    @RequestMapping(value = "/request-user-account", method = RequestMethod.PUT)
    public ResponseEntity<RequestUserAccount> updateRequestUserAccount(@RequestBody RequestUserAccount requestUserAccountToUpdate) {
    		System.out.println("RequestUserAccountController :  Updating RequestUserAccount " + requestUserAccountToUpdate.getId());
         
    		try {
    			RequestUserAccount requestUserAccountUpdated = requestUserAccountService.update(requestUserAccountToUpdate);
            return new ResponseEntity<RequestUserAccount>(requestUserAccountUpdated, HttpStatus.OK);
    		} catch (Exception e) {
    			return new ResponseEntity<RequestUserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
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
