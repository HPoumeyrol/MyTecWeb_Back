package fr.bnpp.pf.mytecweb.rest.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.bnpp.pf.mytecweb.rest.models.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	// method to find an UserAccount By uid
	public Optional<UserAccount> findByUidIgnoreCase(String uid);
	
	public List<UserAccount> findAllByEnabledAndTecMember(Boolean enabled, Boolean tecMember);

	public List<UserAccount> findAllByEnabled(Boolean enabled);

}


