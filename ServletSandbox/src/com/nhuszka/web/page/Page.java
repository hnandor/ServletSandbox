package com.nhuszka.web.page;

public abstract class Page {
	
	protected final static String HTML = "<html>";
	protected final static String HTML_END = "</html>";
	protected final static String BODY = "<body>";
	protected final static String BODY_END = "</body>";

	protected static final String BR = "<br/>";
	
	protected static final String TABLE = "<table>";
	protected static final String TABLE_END = "</table>";
	protected static final String TR = "<tr>";
	protected static final String TR_END = "</tr>";
	protected static final String TD = "<td>";
	protected static final String TD_END = "</td>";

	public String generateHTML() {
		StringBuilder errorHTML = new StringBuilder();
		errorHTML.append(getHeader());
		errorHTML.append(getHTMLBody());
		errorHTML.append(getFooter());
		return errorHTML.toString();
	}
	
	protected String getHeader() {
		StringBuilder header = new StringBuilder();
		header.append(HTML);
		header.append(BODY);
		return header.toString();
	}
	
	protected abstract String getHTMLBody();
	
	protected String getFooter() {
		StringBuilder footer = new StringBuilder();
		footer.append(BODY_END);
		footer.append(HTML_END);
		return footer.toString();
	}
}
