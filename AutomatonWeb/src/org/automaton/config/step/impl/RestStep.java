package org.automaton.config.step.impl;

import java.util.List;
import java.util.Map;

import org.automaton.config.JSONParser;
import org.automaton.config.step.Step;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestStep extends Step{
	
	private static final String APPLICATION_JSON = "application/json";

	private String id;
	
	private String postRequest;
	
	private String url;
	
	private RestMethodType method;
	
	private List<String> outputParams;	

	public String getPostRequest() {
		return postRequest;
	}

	public void setPostRequest(String postRequest) {
		this.postRequest = postRequest;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public RestMethodType getMethod() {
		return method;
	}

	public void setMethod(RestMethodType method) {
		this.method = method;
	}
	
	public Map<String,List<String>> execute(Map<String,Map<String,List<String>>> globalResponse) {
		Map<String,List<String>> result = null;
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			ClientResponse response = null;
			if(method.equals(RestMethodType.POST))
				response = webResource.type(APPLICATION_JSON).post(ClientResponse.class, prepareRequest(globalResponse));
			else
				response = webResource.type(APPLICATION_JSON).get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			}
			String output = response.getEntity(String.class);
			JSONParser parser = new JSONParser(output);
			result = parser.getJSONMap();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	private String prepareRequest(Map<String, Map<String, List<String>>> globalResponse) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getOutputParams() {
		return outputParams;
	}

	public void setOutputParams(List<String> outputParams) {
		this.outputParams = outputParams;
	}

	public Map<String, String> getInputParams() {
		return inputParams;
	}

	public void setInputParams(Map<String, String> inputParams) {
		this.inputParams = inputParams;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestStep other = (RestStep) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RestStep [id=" + id + ", postRequest=" + postRequest + ", url=" + url + ", method=" + method
				+ ", outputParams=" + outputParams + ", inputParams=" + inputParams + "]";
	}
	
	
}
