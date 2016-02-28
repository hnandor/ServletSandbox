package com.nhuszka.web.page;

public class ErrorPage extends Page {

	public String error;
	
	public ErrorPage(String error) {
		this.error = error;
	}
	
	@Override
	public String getHTMLBody() {
		StringBuilder body = new StringBuilder();
		body.append(error).append(BR);
		body.append("<form action='").append("StartServlet").append("' method='get' >");
		body.append("<input type='submit' value='Restart' />");
		body.append("</form>");
		return body.toString();
	}
}
