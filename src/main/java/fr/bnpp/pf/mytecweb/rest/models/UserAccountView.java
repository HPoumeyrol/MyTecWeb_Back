package fr.bnpp.pf.mytecweb.rest.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserAccountView {
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
	
	@Autowired
	static private PasswordEncoder passwordEncoder;
	
	
    // ********************
 	//     Attributes     
 	// ********************
 	
    private String uid;
    private String password;
    
    
    
    
    // ********************
 	// Getters And Setters
    // ********************
 	
    
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	
	
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
    
    
	// ********************
	//       Methods
	// ********************
	

    public static UserAccount toUserAccount(UserAccountView dataTransfertObject) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUid(dataTransfertObject.getUid());
        userAccount.setPassword(passwordEncoder.encode(dataTransfertObject.getPassword()));
        return userAccount;
    }


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserAccountView [uid=" + uid + ", password=" + password + "]";
	}

	
}
