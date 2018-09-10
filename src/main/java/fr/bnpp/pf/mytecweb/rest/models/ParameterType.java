package fr.bnpp.pf.mytecweb.rest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="parameter_type")
public class ParameterType {

	// ********************
	//     Attributes     
	// ********************
	
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String label;

    
    
    
    
	// ****************************
    //    Getters And Setters
	// ****************************
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
    
	
	
	
	// ****************************
	//          Methods
	// ****************************
	
		
    public ParameterType() {
        super();
    }

    
	@Override
	public String toString() {
		return "ParameterType [id=" + id + ", label=" + label + "]";
	}


    
    
}
