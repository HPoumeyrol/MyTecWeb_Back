package fr.bnpp.pf.mytecweb.rest.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bnpp.pf.mytecweb.rest.models.RequestUserAccount;

@Repository
public interface RequestUserAccountRepository  extends JpaRepository<RequestUserAccount, Long> {
	
	// method to find an UserAccount By uid
	public Optional<RequestUserAccount> findByUidIgnoreCase(String uid);
	
	// method to find all RequestUserAccount with provided state and ordered by RequestDate Desc
	public List<RequestUserAccount> findByStateOrderByRequestDateDesc(Integer state);
	
	// method to find all RequestUserAccount with provided state and ordered by ReplyDate Desc
	public List<RequestUserAccount> findByStateOrderByReplyDateDesc(Integer state);
		
	
	// method to count all RequestUserAccount with provided state
	public Long countByState(Integer state);
		
	// method to count all RequestUserAccount 
	public long count();
	
	// method to find all
	public List<RequestUserAccount> findAllByOrderByRequestDateDesc();

}
