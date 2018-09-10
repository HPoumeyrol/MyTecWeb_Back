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



@Entity
@Table(name ="parameter")
public class Parameter {

	// ********************
	//     Attributes     
	// ********************
	
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // link to parameter type
    @ManyToOne(fetch = FetchType.EAGER) // type of relation with category entity to have information about category
	@JoinColumn(name = "fk_parameter_type", foreignKey = @ForeignKey(name = "fk_parameter_type"), nullable = false)
	private ParameterType parameterType;
    
    
    private String label;

    
    
    
    
    // ********************
	// Getters And Setters
    // ********************
	
    
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
    
	
	
	
	
	
	
	// ********************
	//       Methods
	// ********************
	
	
	
    public Parameter() {
        super();
    }

	@Override
	public String toString() {
		return "Parameter [id=" + id + ", parameterType=" + parameterType + ", label=" + label + "]";
	}

    
	

    
    
}
