package org.automaton.config.step.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.automaton.config.JSONParser;
import org.automaton.config.step.Step;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

public class SoapStep extends Step{

	private String soapRequest;
	
	private String endPoint;

	public String getSoapRequest() {
		return soapRequest;
	}

	public void setSoapRequest(String soapRequest) {
		this.soapRequest = soapRequest;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public Map<String, List<String>> execute(Map<String, Map<String, List<String>>> globalResponse) {

		StringEntity strEntity = null;
		try {
		    Map<String,List<String>> res = new HashMap<String,List<String>>();
			HttpClient httpclient = new DefaultHttpClient();
			Map<String,List<String>> input = prepareInputs(globalResponse);
			int index = 0;
			int size = input.values().iterator().next().size();
			for(index =0; index < size; index++) {
				String inputString = soapRequest;
				for(String key : input.keySet()) {
					inputString = inputString.replace(key, input.get(key).get(index));
				}
				strEntity = new StringEntity(soapRequest, "text/xml", "UTF-8");
				HttpPost post = new HttpPost(endPoint);
				post.setHeader("SOAPAction","CAI3G#Create");
				post.setEntity(strEntity);
			    HttpResponse response = null;
			    response = httpclient.execute(post);
			    HttpEntity respEntity = response.getEntity();
			    String result = EntityUtils.toString(respEntity);
			    XMLSerializer serializer = new XMLSerializer();
			    JSONParser parser = new JSONParser(serializer.read(result).toString());
			    System.out.println("response");
			    Map<String,List<String>> map = parser.getJSONMap();
			    System.out.println(map);
			    for(String key :map.keySet())
			    	if(!key.contains("xmlns"))
			    		res.put(key,map.get(key));
			}
		    return res;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
