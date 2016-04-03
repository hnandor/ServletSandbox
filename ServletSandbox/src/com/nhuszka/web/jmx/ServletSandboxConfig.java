package com.nhuszka.web.jmx;

public class ServletSandboxConfig implements ServletSandboxConfigMBean {

	private int numericValue;
	private String textValue;

	public ServletSandboxConfig(int numericValue, String textValue) {
		this.numericValue = numericValue;
		this.textValue = textValue;
	}

	@Override
	public void setNumericValue(int numericValue) {
		this.numericValue = numericValue;
	}

	@Override
	public int getNumericValue() {
		return numericValue;
	}

	@Override
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	@Override
	public String getTextValue() {
		return textValue;
	}
	
	@Override
	public String returnConfigValues() {
		return "Numeric value=" + this.numericValue + ", Text value=" + this.textValue;
	}
}