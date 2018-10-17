package fr.bnpp.pf.mytecweb.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bnpp.pf.mytecweb.rest.models.Parameter;
import fr.bnpp.pf.mytecweb.rest.repositories.ParameterRepository;


@Service
public class ParameterServiceImpl implements ParameterService {
	
	@Autowired
    private ParameterRepository parameterRepository;
	
	public List<Parameter> findAll() {
		return this.parameterRepository.findAll();
	}
	
	public List<Parameter> findByParameterTypeLabel(String parameterTypeLabel) {
		return this.parameterRepository.findByParameterTypeLabelIgnoreCase(parameterTypeLabel);
	}
	
	public List<Parameter> findByParameterTypeLabelAndLabelIgnoreCase(String parameterTypeLabel, String parameterLabel) {
		return this.parameterRepository.findByParameterTypeLabelIgnoreCaseAndLabelIgnoreCase(parameterTypeLabel, parameterLabel);
	}

	
}
