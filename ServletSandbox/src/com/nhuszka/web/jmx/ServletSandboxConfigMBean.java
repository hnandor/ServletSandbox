package com.nhuszka.web.jmx;

public interface ServletSandboxConfigMBean {

	void setNumericValue(int numericValue);
	int getNumericValue();

	void setTextValue(String textValue);
	String getTextValue();

	String returnConfigValues();
}