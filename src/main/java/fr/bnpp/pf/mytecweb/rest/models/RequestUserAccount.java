package fr.bnpp.pf.mytecweb.rest.models;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "request_user_account")
public class RequestUserAccount {
	
	// ********************
	//     Attributes     
	// ********************
		
	
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_user_account")
	@SequenceGenerator(name = "request_user_account", sequenceName = "request_user_account_seq", allocationSize = 1)
    private Long id;

    
    
    private java.time.LocalDateTime requestDate;
    private java.time.LocalDateTime replyDate;
    
    
    private Integer state; // 1=New  2=Allowed,  3=Refused
    private String uid;
    private String firstName;
    private String lastName;
    private String email;
    @Column(length = 60)
    private String desiredPassword;
    private String requestArgumentative;
    private String refuseReason;
    private Boolean tecMember;

    
    
    
    // ********************
  	// Getters And Setters
    // ********************
  	

    /**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the requestDate
	 */
	public java.time.LocalDateTime getRequestDate() {
		return requestDate;
	}
	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(java.time.LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * @return the replyDate
	 */
	public java.time.LocalDateTime getReplyDate() {
		return replyDate;
	}
	/**
	 * @param replyDate the replyDate to set
	 */
	public void setReplyDate(java.time.LocalDateTime replyDate) {
		this.replyDate = replyDate;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the desiredPassword
	 */
	public String getDesiredPassword() {
		return desiredPassword;
	}
	/**
	 * @param desiredPassword the desiredPassword to set
	 */
	public void setDesiredPassword(String desiredPassword) {
		this.desiredPassword = desiredPassword;
	}
	/**
	 * @return the requestArgumentative
	 */
	public String getRequestArgumentative() {
		return requestArgumentative;
	}
	/**
	 * @param requestArgumentative the requestArgumentative to set
	 */
	public void setRequestArgumentative(String requestArgumentative) {
		this.requestArgumentative = requestArgumentative;
	}
	/**
	 * @return the refuseReason
	 */
	public String getRefuseReason() {
		return refuseReason;
	}
	/**
	 * @param refuseReason the refuseReason to set
	 */
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	/**
	 * @return the tecMember
	 */
	public Boolean getTecMember() {
		return tecMember;
	}
	/**
	 * @param tecMember the tecMember to set
	 */
	public void setTecMember(Boolean tecMember) {
		this.tecMember = tecMember;
	}
    

    
    
}
