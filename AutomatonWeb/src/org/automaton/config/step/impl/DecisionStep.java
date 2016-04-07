package org.automaton.config.step.impl;

import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.automaton.config.step.Step;

public class DecisionStep extends Step{

	private Step positiveStep;
	
	private boolean result;
	
	private String condition;
	
	@Override
	public Map<String, List<String>> execute(Map<String, Map<String, List<String>>> globalResponse) {
		// TODO Auto-generated method stub
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("js");
				try {
					engine.eval("4*5");
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
	}
	
	public Step getNextStep() {
		if(result)
			return positiveStep;
		return super.getNextStep();
	}

}
