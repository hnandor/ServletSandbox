package com.nhuszka.web.page;

public class ErrorPage extends Page {

	public String error;

	public ErrorPage(String error) {
		this.error = error;
	}

	@Override
	public String getHTMLBody() {
		StringBuilder body = new StringBuilder();
		body.append(error)
				.append(BR)
				.append(BR);
		return body.toString();
	}
}
