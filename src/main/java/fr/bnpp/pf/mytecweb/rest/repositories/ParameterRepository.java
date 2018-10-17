package fr.bnpp.pf.mytecweb.rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bnpp.pf.mytecweb.rest.models.Parameter;


@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {

	public List<Parameter> findByParameterTypeLabelIgnoreCase(String parameterTypeLabel);
	
	public List<Parameter> findByParameterTypeLabelIgnoreCaseAndLabelIgnoreCase(String parameterTypeLabel, String parameterLabel);
	
}
