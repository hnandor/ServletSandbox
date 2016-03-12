package com.nhuszka.web.jsf.bean;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "searchBean", eager=true)
@RequestScoped
public class SearchBean {

	private String keyword;
	private String directoryFile;
	private String extension;
	private String algorithmName;
	private String algorithmDescription;
	private Collection<String> searchResults;
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getDirectoryFile() {
		return directoryFile;
	}
	
	public void setDirectoryFile(String directoryFile) {
		this.directoryFile = directoryFile;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public String getAlgorithmName() {
		return algorithmName;
	}
	
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public String getAlgorithmDescription() {
		return algorithmDescription;
	}

	public void setAlgorithmDescription(String algorithmDescription) {
		this.algorithmDescription = algorithmDescription;
	}
	
	public Collection<String> getSearchResults() {
		return searchResults;
	}
	
	public void setSearchResults(Collection<String> searchResults) {
		this.searchResults = searchResults;
	}
}
