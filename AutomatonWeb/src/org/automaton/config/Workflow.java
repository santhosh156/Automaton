package org.automaton.config;

import org.automaton.config.step.Step;

public class Workflow {

	private Step step;
	
	private String name;
	
	private String uiString;
	
	private String id;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUiString() {
		return uiString;
	}

	public void setUiString(String uiString) {
		this.uiString = uiString;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}
	
	
}
