package org.automaton.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {

	private Map<String,List<String>> jsonMap;
	
	public JSONParser(JSONObject jsonObject) {
		jsonMap = new HashMap<String,List<String>>();
		parse(jsonObject);
	}
	
	public JSONParser(String jsonString) {
		this(new JSONObject(jsonString));
	}
	
	private void parse(JSONObject jsonObject) {
		for(String key: jsonObject.keySet()) {
			if(jsonObject.get(key) instanceof JSONObject) {
				parse(jsonObject.getJSONObject(key));
			} else if (jsonObject.get(key) instanceof JSONArray) {
				JSONArray array = jsonObject.getJSONArray(key);
				if(array.get(0) instanceof JSONObject) {
					for(int i =0; i <array.length(); i++) {
						parse(array.getJSONObject(i));
					}
				} else {
					List<String> values = new ArrayList<String>();
					for(int i =0; i <array.length(); i++) {
						values.add(String.valueOf(array.get(i)));
					}
					jsonMap.put(key, values);
				}
			} else {
				List<String> values = jsonMap.get(key)!=null? jsonMap.get(key):new ArrayList<String>();
				values.add(String.valueOf(jsonObject.get(key)));
				jsonMap.put(key, values);
			}
		}
	}
	
	public List<String> getValue(String key) {
		return jsonMap.get(key);
	}
	
	public Map<String,List<String>> getJSONMap() {
		return jsonMap;
	}
}
