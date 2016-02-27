package com.nhuszka.web.page;

public class ErrorPage extends Page {

	public String error;
	
	public ErrorPage(String error) {
		this.error = error;
	}
	
	@Override
	public String getHTMLBody() {
		return error;
	}
}
