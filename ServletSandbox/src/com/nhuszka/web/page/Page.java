package com.nhuszka.web.page;

public abstract class Page {
	
	protected String BR = "<br/>";
	
	public String generateHTML() {
		StringBuilder errorHTML = new StringBuilder();
		errorHTML.append(getHeader());
		errorHTML.append(getHTMLBody());
		errorHTML.append(getFooter());
		return errorHTML.toString();
	}
	
	protected String getHeader() {
		StringBuilder header = new StringBuilder();
		header.append("<html>");
		header.append("<body>");
		return header.toString();
	}
	
	protected abstract String getHTMLBody();
	
	protected String getFooter() {
		StringBuilder footer = new StringBuilder();
		footer.append("</body>");
		footer.append("</html>");
		return footer.toString();
	}
}
