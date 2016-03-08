package com.nhuszka.web.page;

public abstract class Page {
	
	protected final static String JQUERY_URL = "https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js";
	
	protected final static String HTML = "<html>";
	protected final static String HTML_END = "</html>";
	protected final static String HEAD = "<head>";
	protected final static String HEAD_END = "</head>";
	protected final static String BODY = "<body>";
	protected final static String BODY_END = "</body>";
	
	protected final static String STYLE = "<link type='text/css' rel='stylesheet' href='%s'>";
	protected final static String JAVASCRIPT = "<script src='%s'>";
	protected final static String JAVASCRIPT_END = "</script>";
	
	protected final static String DIV_FLUID = "<div class='container-fluid'>";
	protected final static String DIV_END = "</div>";
	protected final static String TITLE = "<h3>%s</h3>";

	protected static final String BR = "<br/>";
	
	protected static final String TABLE_STRIPED = "<table class='table table-striped'>";
	protected static final String TABLE_END = "</table>";
	protected static final String TR = "<tr>";
	protected static final String TR_END = "</tr>";
	protected static final String TD = "<td>";
	protected static final String TD_END = "</td>";

	public String generateHTML() {
		StringBuilder html = new StringBuilder();
		html.append(getHeader());
		html.append(getHTMLBody());
		html.append(getFooter());
		return html.toString();
	}
	
	protected String getHeader() {
		StringBuilder header = new StringBuilder();
		header.append(HTML);
		header.append(HEAD);
		header.append("<meta charset='utf-8'>");
		header.append(String.format(STYLE, "css/bootstrap.min.css"));
		header.append(HEAD_END);
		header.append(BODY);
		return header.toString();
	}
	
	protected abstract String getHTMLBody();
	
	protected String getFooter() {
		StringBuilder footer = new StringBuilder();
		footer.append(String.format(JAVASCRIPT, JQUERY_URL));
		footer.append(JAVASCRIPT_END);
		footer.append(String.format(JAVASCRIPT, "js/bootstrap.min.js"));
		footer.append(JAVASCRIPT_END);
		footer.append(BODY_END);
		footer.append(HTML_END);
		return footer.toString();
	}
}
