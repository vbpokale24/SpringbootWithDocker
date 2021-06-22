package com.org.service.business.utils;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.org.service.model.PersonResourceModel;

@Component
public class PopulateHref {

	private String contextPath = "/personmanagement/v1";
	private static final String GET_PERSON_RESOURCE = "/person/{personId}";

	private static Logger Logger = LoggerFactory.getLogger(PopulateHref.class);

	@Autowired
	Environment environment;

	private String port;
	private String hostname;

	public String getPort() {
		if (port == null)
			port = environment.getProperty("local.server.port");
		return port;
	}

	public Integer getPortAsInt() {
		return Integer.valueOf(getPort());
	}

	public String getHostname() throws UnknownHostException {
		if (hostname == null)
			hostname = InetAddress.getLocalHost().getHostAddress();
		return hostname;
	}

	public String getServerUrlPrefix() throws UnknownHostException {
		return "http://" + getHostname() + ":" + getPort();
	}

	public PersonResourceModel buildHref(PersonResourceModel personResourceModel) {

		if (personResourceModel != null) {
			personResourceModel.setHref(getHref(personResourceModel.getId()));
		}
		return personResourceModel;
	}

	private String getHref(Integer id) {

		Map<String, Object> hrefUriValues = new HashMap<>();
		hrefUriValues.put("personId", id);
		return buildHref(GET_PERSON_RESOURCE, hrefUriValues);
	}

	private String buildHref(String resource, Map<String, ?> hrefUriValues) {

		UriComponentsBuilder uriComponents = UriComponentsBuilder.fromUriString(contextPath + resource);
		URI internalUri = uriComponents.buildAndExpand(hrefUriValues).toUri();
		try {
			return getServerUrlPrefix().concat(internalUri.toString());
		} catch (Exception e) {
			Logger.error("Failed to create Href for billingAccount: " + internalUri, e);
			return null;
		}
	}
}
