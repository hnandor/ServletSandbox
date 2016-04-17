package com.nhuszka.web.jsf.action;

import java.util.Collection;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.jsf.bean.SearchBean;

public class SearchSubmitAction implements ActionListener {

	@Override
	public void processAction(ActionEvent actionEvent) throws AbortProcessingException {
		SearchBean searchBean = (SearchBean) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("searchBean");

		SearchAlgorithm algorithm = SearchAlgorithm.valueOf(searchBean.getAlgorithmName());
		searchBean.setAlgorithmDescription(algorithm.getDescription());

		// We could get the results from the controller in searchResult.xhtml
		// Currently not used
		Collection<String> searchResults = new SearchTask().computeSearchResults(searchBean);
		searchBean.setSearchResults(searchResults);
	}
}