package fr.bnpp.pf.mytecweb.rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.bnpp.pf.mytecweb.rest.models.Parameter;
import fr.bnpp.pf.mytecweb.rest.services.ParameterService;



@RequestMapping(value = "/")
@RestController

public class ParameterController {
	
	@Autowired
	private ParameterService parameterService;
	
	@RequestMapping(value = "/parameter", method = RequestMethod.GET)
    public ResponseEntity<List<Parameter>> list( 
    		@RequestParam("paramTypeLabel") Optional<String> paramTypeLabelOpt,
    		@RequestParam("paramLabel") Optional<String> paramLabelOpt ) {
			
		
		ResponseEntity<List<Parameter>> result = null;
        List<Parameter> parameterList = new ArrayList<Parameter>();;
		int factorSearch = 0;
		String paramTypeLabel = "";
		String paramLabel = "";
		
		if (paramTypeLabelOpt.isPresent()) {
			paramTypeLabel = paramTypeLabelOpt.get();
            factorSearch += 1;
        }
		
		if (paramLabelOpt.isPresent()) {
			paramLabel = paramLabelOpt.get();
            factorSearch += 10;
        }
		
		switch (factorSearch) {
			case 0  : { 
				// search all parameters
				parameterList = this.parameterService.findAll();
				break; 
			}
			
			case 1  : { 
				// search all parameters By ParamTypeLabel
				parameterList = this.parameterService.findByParameterTypeLabel(paramTypeLabel);
				break; 
			}
			
			case 10 : { 
				// search all parameters by ParamLabel  !!! not ALLOWED !!!
				factorSearch = -1;
				break; 
			}
			
			case 11 : { 
				// search all parameters By ParamTypeLabel and  ParamLabel
				parameterList = this.parameterService.findByParameterTypeLabelAndLabelIgnoreCase(paramTypeLabel, paramLabel);
				break; 
			}
			
			
		}
		
		
		if (factorSearch != -1) {
			result = new ResponseEntity<List<Parameter>>(parameterList,
					parameterList.size() == 0 ? HttpStatus.NO_CONTENT : HttpStatus.OK);

        } else {
            // if at least one parameter is not correct
            result = new ResponseEntity<List<Parameter>>(HttpStatus.BAD_REQUEST); 
		
		}
		
		
		return result;
		
		
	}
	

}
