package com.nhuszka.web.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.nhuszka.web.algorithm.SearchAlgorithm;

@FacesValidator("algorithmSelectorValidator")
public class AlgorithmSelectorValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent algorithmSelector, Object selectedValue)
			throws ValidatorException {
		String algorithmName = (String) selectedValue;

		if (!SearchAlgorithm.existsAlgorithm(algorithmName)) {
			FacesMessage msg = new FacesMessage("Invalid algorithm: " + algorithmName);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
