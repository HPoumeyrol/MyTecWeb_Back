package fr.bnpp.pf.mytecweb.rest.services;

import java.util.List;

import fr.bnpp.pf.mytecweb.rest.models.Parameter;

public interface ParameterService {
	
	public List<Parameter> findAll();
	
	public List<Parameter> findByParameterTypeLabel(String parameterTypeLabel);
	
	public List<Parameter> findByParameterTypeLabelAndLabelIgnoreCase(String parameterTypeLabel, String parameterLabel);

}
