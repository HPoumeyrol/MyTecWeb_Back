package fr.bnpp.pf.mytecweb.rest.models;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "user_account")
public class UserAccount {

	// ********************
	//     Attributes     
	// ********************
		
	
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_account")
	@SequenceGenerator(name = "user_account", sequenceName = "user_account_seq", allocationSize = 1)
    private Long id;

    private boolean tecMember;
    
    private String uid;
    
    private String firstName;

    private String lastName;

    private String officeDepartment;
    
    private String trigram;
    
    private String diaporamaId;
    
    private String email;

    @Column(length = 60)
    private String password;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    
    
           
    @ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "fk_parameter_division", foreignKey = @ForeignKey(name = "fk_parameter_division"), nullable = true)
	private Parameter division;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "fk_parameter_location", foreignKey = @ForeignKey(name = "fk_parameter_location"), nullable = true)
	private Parameter location;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "fk_parameter_tecTeam", foreignKey = @ForeignKey(name = "fk_parameter_tecTeam"), nullable = true)
	private Parameter tecTeam;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "fk_parameter_function", foreignKey = @ForeignKey(name = "fk_parameter_function"), nullable = true)
	private Parameter function;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "fk_parameter_rolemytec", foreignKey = @ForeignKey(name = "fk_parameter_fk_parameter_rolemytec"), nullable = true)
	private Parameter rolemytec;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "fk_parameter_workingTime", foreignKey = @ForeignKey(name = "fk_parameter_workingTime"), nullable = true)
	private Parameter workingTime;
    
    private String dayOff;
    
    private Integer teleworkingDay;
    
    private String workstations;
    
    
    
    // ********************
 	// Getters And Setters
    // ********************
 	
    
    public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public boolean isTecMember() {
		return tecMember;
	}





	public void setTecMember(boolean tecMember) {
		this.tecMember = tecMember;
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





	public String getOfficeDepartment() {
		return officeDepartment;
	}





	public void setOfficeDepartment(String officeDepartment) {
		this.officeDepartment = officeDepartment;
	}





	public String getTrigram() {
		return trigram;
	}





	public void setTrigram(String trigram) {
		this.trigram = trigram;
	}





	public String getDiaporamaId() {
		return diaporamaId;
	}





	public void setDiaporamaId(String diaporamaId) {
		this.diaporamaId = diaporamaId;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the lastPasswordResetDate
	 */
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}





	/**
	 * @param lastPasswordResetDate the lastPasswordResetDate to set
	 */
	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}



	public boolean isEnabled() {
		return enabled;
	}





	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}







	public Parameter getDivision() {
		return division;
	}





	public void setDivision(Parameter division) {
		this.division = division;
	}





	public Parameter getLocation() {
		return location;
	}





	public void setLocation(Parameter location) {
		this.location = location;
	}





	public Parameter getTecTeam() {
		return tecTeam;
	}





	public void setTecTeam(Parameter tecTeam) {
		this.tecTeam = tecTeam;
	}





	public Parameter getFunction() {
		return function;
	}





	public void setFunction(Parameter function) {
		this.function = function;
	}





	public Parameter getRolemytec() {
		return rolemytec;
	}





	public void setRolemytec(Parameter rolemytec) {
		this.rolemytec = rolemytec;
	}





	public Parameter getWorkingTime() {
		return workingTime;
	}





	public void setWorkingTime(Parameter workingTime) {
		this.workingTime = workingTime;
	}





	public String getDayOff() {
		return dayOff;
	}





	public void setDayOff(String dayOff) {
		this.dayOff = dayOff;
	}





	public Integer getTeleworkingDay() {
		return teleworkingDay;
	}





	public void setTeleworkingDay(Integer teleworkingDay) {
		this.teleworkingDay = teleworkingDay;
	}





	public String getWorkstations() {
		return workstations;
	}





	public void setWorkstations(String workstations) {
		this.workstations = workstations;
	}

	
	
	

	// ********************
	//       Methods
	// ********************
	



	public UserAccount() {
        super();
        this.enabled = false;
    }

    
    
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    
    
    
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserAccount userAccount = (UserAccount) obj;
        if (!email.equals(userAccount.email)) {
        return false;
        }
        return true;
    }





	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", tecMember=" + tecMember + ", uid=" + uid + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", officeDepartment=" + officeDepartment + ", trigram=" + trigram
				+ ", diaporamaId=" + diaporamaId + ", email=" + email + ", password=" + password + ", enabled="
				+ enabled + ",  division=" + division + ", location=" + location + ", tecTeam="
				+ tecTeam + ", function=" + function + ", myTecFunction=" + rolemytec + ", workingTime="
				+ workingTime + ", dayOff=" + dayOff + ", teleworkingDay=" + teleworkingDay + ", workstations="
				+ workstations + "]";
	}





	
    
    
 
    
    
}