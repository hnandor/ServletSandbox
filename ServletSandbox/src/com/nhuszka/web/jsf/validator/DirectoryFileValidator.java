package com.nhuszka.web.jsf.validator;

import java.io.File;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("directoryFileValidator")
public class DirectoryFileValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent directoryFileInput, Object inputValue)
			throws ValidatorException {
		String directoryFile = (String) inputValue;

		File file = new File(directoryFile);
		if (!file.exists()) {
			FacesMessage msg = new FacesMessage("Directory/file does not exist");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}