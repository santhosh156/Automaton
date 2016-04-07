package org.automaton.config.step.impl;

import java.util.List;
import java.util.Map;

import org.automaton.config.step.Step;

public class ShellStep extends Step{
	
	private String serverId;
	
	private String executionString;

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getExecutionString() {
		return executionString;
	}

	public void setExecutionString(String executionString) {
		this.executionString = executionString;
	}

	@Override
	public Map<String, List<String>> execute(Map<String, Map<String, List<String>>> globalResponse) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
