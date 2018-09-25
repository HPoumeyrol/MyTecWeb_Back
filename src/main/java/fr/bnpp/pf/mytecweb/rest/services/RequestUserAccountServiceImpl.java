package fr.bnpp.pf.mytecweb.rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bnpp.pf.mytecweb.rest.models.RequestUserAccount;
import fr.bnpp.pf.mytecweb.rest.repositories.RequestUserAccountRepository;



@Service
public class RequestUserAccountServiceImpl implements RequestUserAccountService { 
	
	
	
	
	@Autowired
	private RequestUserAccountRepository requestUserAccountRepository;
	
    
	/**
	 * method to CREATE an requestUserAccount
	 * 
	 * @param requestUserAccount: an requestUserAccount
	 * 
	 */
	@Override
	public RequestUserAccount create(RequestUserAccount requestUserAccountNew) throws Exception {
		
		Optional<RequestUserAccount> requestUserAccountOpt = requestUserAccountRepository.findByUidIgnoreCase(requestUserAccountNew.getUid());
	
		if (requestUserAccountOpt.isPresent()) {
			throw new Exception("User account request already exists");
		}
		
		final RequestUserAccount requestUserAccountCreated = requestUserAccountRepository.save(requestUserAccountNew);
		return requestUserAccountCreated;
		
			
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
	 * method to UPDATE an requestUserAccount 
	 * 
	 * @param id: requestUserAccount id
	 * @param requestUserAccount: requestUserAccount with new informations
	 *
	 */
	@Override
	public RequestUserAccount update(RequestUserAccount requestUserAccountNew)  throws Exception{

		Optional<RequestUserAccount> requestUserAccountOpt = requestUserAccountRepository.findById(requestUserAccountNew.getId());

		if (!requestUserAccountOpt.isPresent()) {
			throw new Exception("RequestUserAccount not found");
		}
		
		return  requestUserAccountRepository.save(requestUserAccountNew);
		
	}

	
	

	
	
	
	
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
