package org.automaton.config.step.impl;

public enum RestMethodType {

	GET("get"),POST("post");
	
	
	private String indicator;

	
	private RestMethodType(String indicator) {
		this.indicator = indicator;
	}
	
	public String getValue(){
		return indicator;
	}
}
