package com.roberto.api.util;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;

public class ConfigInfo implements Serializable {

	private static final long serialVersionUID = -5415890360028454823L;

	@Getter
	@Setter
	private String env;

	@Getter
	@Setter
	private String version;

	@Getter
	@Setter
	private String ownerBd;

	@Getter
	@Setter
	private String appName;

	@Getter
	@Setter
	private String baseUrl;

	@Getter
	@Setter
	private String port;

	@Getter
	@Setter
	private String database;

	@Getter
	@Setter
	private List<String> serviceAdress;

	@Getter
	@Setter
	private Locale locale;

	@Getter
	@Setter
	private InetAddress localMachine;

	private static ConfigInfo instance;

	public static ConfigInfo config() throws UnknownHostException {
		
		if (instance == null) {
			instance = new ConfigInfo();
		}
		
		return instance;
	}

	public ConfigInfo() throws UnknownHostException {

		localMachine = InetAddress.getLocalHost();
	}

	public String getHostName() {
		return localMachine.getHostName();
	}
}
