package fr.bnpp.pf.mytecweb.rest.models;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private java.time.LocalDateTime verifyEmailAdressDate;
    
    
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
    private String uuid;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "fk_parameter_rolemytec", foreignKey = @ForeignKey(name = "fk_parameter_fk_parameter_rolemytec"), nullable = true)
	private Parameter rolemytec;
    
    
    
    
    // ********************
  	// Getters And Setters
    // ********************
  	

    public Long getId() {
		return id;
	}
	
    public void setId(Long id) {
		this.id = id;
	}
	
    public java.time.LocalDateTime getRequestDate() {
		return requestDate;
	}
	
    public void setRequestDate(java.time.LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}
	
    public java.time.LocalDateTime getReplyDate() {
		return replyDate;
	}
	
    public void setReplyDate(java.time.LocalDateTime replyDate) {
		this.replyDate = replyDate;
	}
	
    public Integer getState() {
		return state;
	}
	
    public void setState(Integer state) {
		this.state = state;
	}
	
    public String getUid() {
		return uid;
	}
	
    public void setUid(String uid) {
		this.uid = uid;
	}
	
    public String getFirstName() {
		return firstName;
	}
	
    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
    public String getLastName() {
		return lastName;
	}
	
    public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
    public String getEmail() {
		return email;
	}
	
    public void setEmail(String email) {
		this.email = email;
	}
	
    public String getDesiredPassword() {
		return desiredPassword;
	}
	
    public void setDesiredPassword(String desiredPassword) {
		this.desiredPassword = desiredPassword;
	}
	
    public String getRequestArgumentative() {
		return requestArgumentative;
	}
	
    public void setRequestArgumentative(String requestArgumentative) {
		this.requestArgumentative = requestArgumentative;
	}
	
    public String getRefuseReason() {
		return refuseReason;
	}
	
    public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	
    public Boolean getTecMember() {
		return tecMember;
	}
	
    public void setTecMember(Boolean tecMember) {
		this.tecMember = tecMember;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public java.time.LocalDateTime getVerifyEmailAdressDate() {
		return verifyEmailAdressDate;
	}

	public void setVerifyEmailAdressDate(java.time.LocalDateTime verifyEmailAdressDate) {
		this.verifyEmailAdressDate = verifyEmailAdressDate;
	}

	public Parameter getRolemytec() {
		return rolemytec;
	}

	public void setRolemytec(Parameter rolemytec) {
		this.rolemytec = rolemytec;
	}
    

    
    
}
