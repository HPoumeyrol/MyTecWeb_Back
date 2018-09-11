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
@Table(name ="parameter")
public class Parameter {

	// ********************
	//     Attributes     
	// ********************
	
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameter")
	@SequenceGenerator(name = "parameter", sequenceName = "parameter_seq", allocationSize = 1)

    private Long id;

    // link to parameter type
    @ManyToOne(fetch = FetchType.EAGER) // type of relation with category entity to have information about category
	@JoinColumn(name = "fk_parameter_type", foreignKey = @ForeignKey(name = "fk_parameter_type"), nullable = false)
	private ParameterType parameterType;
    
    @Column
    private String label;

    @Column
    private String strValue;
    
    @Column
    private Double numValue;
    
    
    
    
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
	 * @return the parameterType
	 */
	public ParameterType getParameterType() {
		return parameterType;
	}

	/**
	 * @param parameterType the parameterType to set
	 */
	public void setParameterType(ParameterType parameterType) {
		this.parameterType = parameterType;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the strValue
	 */
	public String getStrValue() {
		return strValue;
	}

	/**
	 * @param strValue the strValue to set
	 */
	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	/**
	 * @return the numValue
	 */
	public Double getNumValue() {
		return numValue;
	}

	/**
	 * @param numValue the numValue to set
	 */
	public void setNumValue(Double numValue) {
		this.numValue = numValue;
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
