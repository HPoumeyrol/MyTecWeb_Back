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
import javax.persistence.Table;

import fr.bnpp.pf.mytecweb.rest.tools.tools;



@Entity
@Table(name = "user_account")
public class UserAccount {

	// ********************
	//     Attributes     
	// ********************
		
	
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    private boolean enabled;
    
    private String secret;
       
    @ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "fk_parameter_division", foreignKey = @ForeignKey(name = "fk_parameter_division"), nullable = false)
	private ParameterType division;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "fk_parameter_location", foreignKey = @ForeignKey(name = "fk_parameter_location"), nullable = false)
	private ParameterType location;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "fk_parameter_tecTeam", foreignKey = @ForeignKey(name = "fk_parameter_tecTeam"), nullable = false)
	private ParameterType tecTeam;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "fk_parameter_function", foreignKey = @ForeignKey(name = "fk_parameter_function"), nullable = false)
	private ParameterType function;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "fk_parameter_mytec_function", foreignKey = @ForeignKey(name = "fk_parameter_mytec_function"), nullable = false)
	private ParameterType myTecFunction;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "fk_parameter_workingTime", foreignKey = @ForeignKey(name = "fk_parameter_workingTime"), nullable = false)
	private ParameterType workingTime;
    
    private Integer dayOff;
    
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





	public boolean isEnabled() {
		return enabled;
	}





	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}





	public String getSecret() {
		return secret;
	}





	public void setSecret(String secret) {
		this.secret = secret;
	}





	public ParameterType getDivision() {
		return division;
	}





	public void setDivision(ParameterType division) {
		this.division = division;
	}





	public ParameterType getLocation() {
		return location;
	}





	public void setLocation(ParameterType location) {
		this.location = location;
	}





	public ParameterType getTecTeam() {
		return tecTeam;
	}





	public void setTecTeam(ParameterType tecTeam) {
		this.tecTeam = tecTeam;
	}





	public ParameterType getFunction() {
		return function;
	}





	public void setFunction(ParameterType function) {
		this.function = function;
	}





	public ParameterType getMyTecFunction() {
		return myTecFunction;
	}





	public void setMyTecFunction(ParameterType myTecFunction) {
		this.myTecFunction = myTecFunction;
	}





	public ParameterType getWorkingTime() {
		return workingTime;
	}





	public void setWorkingTime(ParameterType workingTime) {
		this.workingTime = workingTime;
	}





	public Integer getDayOff() {
		return dayOff;
	}





	public void setDayOff(Integer dayOff) {
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
        this.secret = tools.getB32Uid();
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
				+ enabled + ", secret=" + secret + ", division=" + division + ", location=" + location + ", tecTeam="
				+ tecTeam + ", function=" + function + ", myTecFunction=" + myTecFunction + ", workingTime="
				+ workingTime + ", dayOff=" + dayOff + ", teleworkingDay=" + teleworkingDay + ", workstations="
				+ workstations + "]";
	}

    
    
 
    
    
}