package com.nhuszka.web.page;

public abstract class Page {
	
	protected final static String HTML = "<html>";
	protected final static String HTML_END = "</html>";
	protected final static String BODY = "<body>";
	protected final static String BODY_END = "</body>";
	
	protected final static String STYLE = "<link type='text/css' rel='stylesheet' href='%s'>";
	protected final static String JAVASCRIPT = "<script src='%s'>";
	protected final static String JAVASCRIPT_END = "</script>";

	protected static final String BR = "<br/>";
	
	protected static final String TABLE = "<table>";
	protected static final String TABLE_END = "</table>";
	protected static final String TR = "<tr>";
	protected static final String TR_END = "</tr>";
	protected static final String TD = "<td>";
	protected static final String TD_END = "</td>";

	public String generateHTML() {
		StringBuilder html = new StringBuilder();
		html.append(getHeader());
		html.append(getStyle());
		html.append(getHTMLBody());
		html.append(getFooter());
		return html.toString();
	}
	
	protected String getHeader() {
		StringBuilder header = new StringBuilder();
		header.append(HTML);
		header.append(BODY);
		return header.toString();
	}
	
	protected String getStyle() {
		StringBuilder style = new StringBuilder();
		style.append(String.format(STYLE, "css/reset.css"));
		style.append(String.format(STYLE, "css/bootstrap.css"));
		style.append(String.format(STYLE, "css/main.css"));
		return style.toString();
	}
	
	protected abstract String getHTMLBody();
	
	protected String getFooter() {
		StringBuilder footer = new StringBuilder();
		footer.append(String.format(JAVASCRIPT, "js/jquery.js"));
		footer.append(JAVASCRIPT_END);
		footer.append(String.format(JAVASCRIPT, "js/bootstrap.js"));
		footer.append(JAVASCRIPT_END);
		footer.append(String.format(JAVASCRIPT, "js/easing.js"));
		footer.append(JAVASCRIPT_END);
		footer.append(String.format(JAVASCRIPT, "js/nicescroll.js"));
		footer.append(JAVASCRIPT_END);
		footer.append(BODY_END);
		footer.append(HTML_END);
		return footer.toString();
	}
}
