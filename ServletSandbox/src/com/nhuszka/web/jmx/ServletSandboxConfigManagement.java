package com.nhuszka.web.jmx;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class ServletSandboxConfigManagement {

	public static void main(String[] args) throws InterruptedException, MalformedObjectNameException,
	InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
		new ServletSandboxConfigManagement().run();
	}
	
	private void run() throws InterruptedException, MalformedObjectNameException,
	InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
		ServletSandboxConfig mBean = registerConfigMBean();
		do {
			Thread.sleep(2000);
			System.out.println(mBean.returnConfigValues());
		} while (mBean.getNumericValue() != 0);
	}

	private static ServletSandboxConfig registerConfigMBean() throws MalformedObjectNameException,
			InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		ServletSandboxConfig mBean = new ServletSandboxConfig(5, "a text");
		ObjectName configName = new ObjectName("com.nhuszka.web.jmx:type=ServletSandboxConfig");
		
		mBeanServer.registerMBean(mBean, configName);
		return mBean;
	}
}