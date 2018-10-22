package fr.bnpp.pf.mytecweb.rest.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.bnpp.pf.mytecweb.rest.models.Mail;
import fr.bnpp.pf.mytecweb.rest.models.RequestUserAccount;
import fr.bnpp.pf.mytecweb.rest.models.UserAccount;
import fr.bnpp.pf.mytecweb.rest.repositories.RequestUserAccountRepository;


import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

@Service
public class RequestUserAccountServiceImpl implements RequestUserAccountService { 
	
	
	@Value( "${spring.mail.username}" )
	private String mailFrom;
	
	@Value( "${mytec.mail.createRequestUserAccount.CC}" )
	private String mytecMmailcreateRequestUserAccountCC;
	
	@Value( "${mytec.mail.createRequestUserAccount.BCC}" )
	private String mytecMmailcreateRequestUserAccountBCC;
	
	@Autowired
	private RequestUserAccountRepository requestUserAccountRepository;
	
	    
	@Autowired
    private EmailService emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Autowired
	private UserAccountService userAccountService;
  
	
	
	/**
	 * method to CREATE an requestUserAccount
	 * 
	 * @param requestUserAccount: an requestUserAccount
	 * 
	 */
	@Override
	public RequestUserAccount create(RequestUserAccount requestUserAccountNew) throws  UserAccountException {
		
		RequestUserAccount requestUserAccountCreated	= null;
		
		// check if a request already exist for this uid
		Optional<RequestUserAccount> requestUserAccountOpt = requestUserAccountRepository.findByUidIgnoreCase(requestUserAccountNew.getUid());
	
		if(requestUserAccountOpt.isPresent()) {
	    		RequestUserAccount requestUserAccountFound = requestUserAccountOpt.get();
	    		
	    		// if this request was not email checked
	    		if(requestUserAccountFound.getState() == 0) { 
	    			// delete the found request
	    			try {
	    				this.delete(requestUserAccountFound);
	    			} catch (Exception e) {
	    				throw new UserAccountException(e.getMessage(), null, e);
	    			}
	    			
	    			//create a new one with received info
	    			requestUserAccountCreated = this.create(requestUserAccountNew);
	    			
	    			
	    		} else {
	    			// if this request was already email checked	
	    			System.err.println("A RequestUserAccount with uid " + requestUserAccountNew.getUid() + " already exist !");
	        		System.err.println("requestUserAccountFound = " + requestUserAccountFound);
	    			throw new UserAccountException("User account request already exists", requestUserAccountFound, null );
	    		}
		} else {
			// no request with this uid already exists
			
			
			// verify if an user account already exists for the requested uid
	        UserAccount userAccount = new UserAccount();
	        userAccount.setUid(requestUserAccountNew.getUid());
	        if (userAccountService.isUserAccountExist(userAccount)) {
	        		System.err.println("A UserAccount with uid " + requestUserAccountNew.getUid() + " already exist !");
	        		throw new UserAccountException("User account already exists", null, null);
	        } else {
	        	
			    requestUserAccountNew.setState(0); // set the state to Zero to indicate that the email is not yet validated for this request
				requestUserAccountNew.setUuid(UUID.randomUUID().toString());    //generate an uuid for this request  
				requestUserAccountNew.setDesiredPassword( passwordEncoder.encode( requestUserAccountNew.getDesiredPassword() ) ); //encode password
				
				// save request user account to database
				requestUserAccountCreated = requestUserAccountRepository.save(requestUserAccountNew);
				
				// prepare and send email and save date if email was successfully send, set to null if email was not sent
		        if(prepareAndSendRequestUserAccountMail(requestUserAccountCreated)) {
		        		requestUserAccountCreated.setVerifyEmailAdressDate(LocalDateTime.now());
		        } else {
		        		requestUserAccountCreated.setVerifyEmailAdressDate(null);
		        };
		        requestUserAccountRepository.save(requestUserAccountCreated);
			
	        }
		}
		
		return requestUserAccountCreated;
	}
	
	
	
	
	private boolean prepareAndSendRequestUserAccountMail(RequestUserAccount requestUserAccount) {
		System.out.println("Prepare RequestUserAccount Email");
		
		Mail mail = new Mail();
        mail.setFrom(mailFrom);
        mail.setTo(requestUserAccount.getEmail());
        mail.setCc(mytecMmailcreateRequestUserAccountCC);
        mail.setBcc(mytecMmailcreateRequestUserAccountBCC);
        mail.setSubject("Votre demande de création de compte pour MyTecWeb");

        mail.setTemplateName("createRequestAccountVerifyEmail_Template.ftl");
        Map<String, Object> templateVariables = new HashMap<String, Object>();
        templateVariables.put("firstName", requestUserAccount.getFirstName());
        templateVariables.put("uid", requestUserAccount.getUid());
        templateVariables.put("confirmLink",  "http://mytecweb.ddns.net:4200/request-account/confirm/" + requestUserAccount.getUuid());
        mail.setTemplateVariables(templateVariables);

        List<String> templateInlineImgs = new ArrayList<String>();
        templateInlineImgs.add("MTW_Banner_wide.PNG");
        mail.setTemplateInlineImgs(templateInlineImgs);
        
        try {
        		return emailService.sendSimpleMessage(mail);
        } catch (Exception e) {
        		return false;
        }
        
	}
	
	
	
	
	
	
	

	/**
	 * method to read informations about a requestUserAccount
	 * 
	 * @param id:  requestUserAccount id
	 *
	 */
	@Override
	public Optional<RequestUserAccount> read(Long id)  throws Exception{
		
		return  requestUserAccountRepository.findById(id);
		
	}

	
	
	
	
	
	
	
	
	


	/**
	 * method to read informations about a requestUserAccount finding it by uid
	 * 
	 * @param uid:  requestUserAccount uid
	 *
	 */
	@Override
	public Optional<RequestUserAccount> findByUid(String uid)  throws Exception{
		
		return  requestUserAccountRepository.findByUidIgnoreCase(uid);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * method to read informations about a requestUserAccount finding it by token
	 * 
	 * @param uuid:  requestUserAccount uuid 
	 *
	 */
	public Optional<RequestUserAccount> findByUuid(String uuid)  throws Exception {
		return  requestUserAccountRepository.findByUuidIgnoreCase(uuid);
	}

	
	
	
		
	
	
	
	/**
	 * method to UPDATE an requestUserAccount 
	 * 
	 * @param id: requestUserAccount id
	 * @param requestUserAccount: requestUserAccount with new informations
	 *
	 */
	@Override
	public RequestUserAccount update(RequestUserAccount requestUserAccountToUpdate)  throws Exception{
		
		// initialize return variable
		RequestUserAccount requestUserAccountUpdated = null;
		
		// verify if RequestUserAccount exists 
		Optional<RequestUserAccount> requestUserAccountOpt = requestUserAccountRepository.findById(requestUserAccountToUpdate.getId());
		if (!requestUserAccountOpt.isPresent()) {
			System.err.println("RequestUserAccount not found");
			throw new Exception("RequestUserAccount not found");
		} 
		
	
		System.out.println("RequestUserAccountServiceImpl : update : previous state  = " + requestUserAccountOpt.get().getState());
		System.out.println("RequestUserAccountServiceImpl : update : state asked     = " + requestUserAccountToUpdate.getState());
		
		
        // Which new state is asked ?
        switch( requestUserAccountToUpdate.getState() ) {
        
        
        		case 1 : {   // Case of validate email address
        			
        					// update the request
        					requestUserAccountUpdated = requestUserAccountRepository.save(requestUserAccountToUpdate);
        					break;
        		}
        		
        		
        		
        		
        		case 2 : {  // Case of approval request
        				   
		        			// verify if current state is New
						if(requestUserAccountOpt.get().getState() == 1 || requestUserAccountOpt.get().getState() == 3) {	
							
						
				    			// Create a new UserAccount from requestUserAccount
				    			this.userAccountService.createFromRequestUserAccount(requestUserAccountToUpdate);
				    			
	        			
				    			// Prepare and send an email
			        			Mail mail = new Mail();
			        	        mail.setFrom(mailFrom);
			        	        mail.setTo(requestUserAccountToUpdate.getEmail());
			        	        mail.setCc(mytecMmailcreateRequestUserAccountCC);
			        	        mail.setBcc(mytecMmailcreateRequestUserAccountBCC);
			        	        mail.setSubject("Approbation de votre demande de création de compte pour MyTecWeb");
			
			        	        mail.setTemplateName("createRequestAccountNotifyDecisionEmail_Template.ftl");
			        	        Map<String, Object> templateVariables = new HashMap<String, Object>();
			        	        templateVariables.put("firstName", requestUserAccountToUpdate.getFirstName());
			        	        templateVariables.put("uid", requestUserAccountToUpdate.getUid());
			        	        
			        	        String decision = "<p>Nous sommes heureux de vous annoncer que l'administrateur de MyTecWeb vient d'approuver votre demande.</p>" + 
			        	        					 "<p>Vous pouvez donc dès à présent vous connecter sur <a href=\"http://mytecweb.ddns.net:4200/login\" target=\"_blank\">MyTecWeb</a>;" +
			        	        					 "<p></p>" +
			        	        					 "<p>Nous vous souhaitons une bonne utilisation.</p>";
			        			
			        	        templateVariables.put("decision", decision);
			        	        mail.setTemplateVariables(templateVariables);
			
			        	        List<String> templateInlineImgs = new ArrayList<String>();
			        	        templateInlineImgs.add("MTW_Banner_wide.PNG");
			        	        mail.setTemplateInlineImgs(templateInlineImgs);
			        	  
			        			System.out.println("Sending approve Email to " + requestUserAccountToUpdate.getEmail());
			        	        emailService.sendSimpleMessage(mail);
			        	        
			        	        // save in database
        						requestUserAccountUpdated = requestUserAccountRepository.save(requestUserAccountToUpdate);
		        	        
						} else {
		        	        		throw new Exception("Bad previous state");
		        	        }
		        	        break;
			}
        		
        		
        		
        		
        		case 3 : {  // Case of refuse request
        			
        					// verify if current state is New
						if(requestUserAccountOpt.get().getState() == 1 ) {	
					
        			
	        					// Prepare and send an email
			        			Mail mail = new Mail();
			        	        mail.setFrom(mailFrom);
			        	        mail.setTo(requestUserAccountToUpdate.getEmail());
			        	        mail.setCc(mytecMmailcreateRequestUserAccountCC);
			        	        mail.setBcc(mytecMmailcreateRequestUserAccountBCC);
			        	        mail.setSubject("Refus de votre demande de création de compte pour MyTecWeb");
			
			        	        mail.setTemplateName("createRequestAccountNotifyDecisionEmail_Template.ftl");
			        	        Map<String, Object> templateVariables = new HashMap<String, Object>();
			        	        templateVariables.put("firstName", requestUserAccountToUpdate.getFirstName());
			        	        templateVariables.put("uid", requestUserAccountToUpdate.getUid());
			        	        
			        	        String decision = "<p>Nous sommes au regret de vous annoncer que l'administrateur de MyTecWeb vient de refuser votre demande," + 
			        	        					 "pour le motif suivant : " +  requestUserAccountToUpdate.getRefuseReason() + "." +
			        	        					 "<p></p>";
			        			
			        	        templateVariables.put("decision", decision);
			        	        mail.setTemplateVariables(templateVariables);
			
			        	        List<String> templateInlineImgs = new ArrayList<String>();
			        	        templateInlineImgs.add("MTW_Banner_wide.PNG");
			        	        mail.setTemplateInlineImgs(templateInlineImgs);
			        	        
			        	        System.out.println("Sending refuse Email to " + requestUserAccountToUpdate.getEmail());
			        	        emailService.sendSimpleMessage(mail);
			        	        
			        	        // save in database
        						requestUserAccountUpdated = requestUserAccountRepository.save(requestUserAccountToUpdate);
		        	            
		        	        
						} else {
							throw new Exception("Bad previous state");
						}
		        	       
						break;
        		}
				 	

        }
        
      		
		return requestUserAccountUpdated;
		
	}

	
	

	// method to DELETE an RequestUserAccount if user is the owner of it
	public void delete(RequestUserAccount requestUserAccount)  throws Exception {
		requestUserAccountRepository.delete(requestUserAccount);
	};

	
	
	
	/**
	 * method to list requestUserAccounts
	 * 
	 */
	@Override
	public List<RequestUserAccount> findAll()  throws Exception {
//		List<RequestUserAccount> list = new ArrayList<>();
//		list.addAll(requestUserAccountRepository.findAll());
//		return list;
		
		return requestUserAccountRepository.findAllByOrderByRequestDateDesc();
	}

	
	/**
	 * method to list requestUserAccounts filter by state
	 * 
	 * @param state:  requestUserAccount.state :  state : 1=New  2=Allowed,  3=Refused
	 *
	 */
	public List<RequestUserAccount> findByState(Integer state)  throws Exception {
		if(state == 1) {
			return requestUserAccountRepository.findByStateOrderByRequestDateDesc(state);
		} else {
			return requestUserAccountRepository.findByStateOrderByReplyDateDesc(state);
		}
	}
	
	

	/**
	 * method to count requestUserAccounts filter by state
	 * 
	 * @param state:  requestUserAccount.state :  state : 1=New  2=Allowed,  3=Refused
	 *
	 */
	public Long countByState(Integer state)  throws Exception {
		return  requestUserAccountRepository.countByState(state);
	}
		
	
	
	/**
	 * method to count requestUserAccounts All
	 * 
	 * 
	 *
	 */
	public Long countAll()  throws Exception {
		return  requestUserAccountRepository.count();
	};
	
	
	/**
	 * method to verify existence of an RequestUserAccount
	 * 
	 */
	
	public boolean isRequestUserAccountExist(RequestUserAccount requestUserAccount) {
		// System.out.println("isRequestUserAccountExist for uid " + requestUserAccount.getUid());
		Optional<RequestUserAccount> requestUserAccountOpt = requestUserAccountRepository.findByUidIgnoreCase(requestUserAccount.getUid());
		// System.out.println("Is present : " + requestUserAccountOpt.isPresent());
		return requestUserAccountOpt.isPresent();
	}
	
	
	
	
}
