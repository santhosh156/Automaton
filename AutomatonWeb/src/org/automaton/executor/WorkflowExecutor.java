package org.automaton.executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.automaton.config.Workflow;
import org.automaton.config.step.Step;

public class WorkflowExecutor {
 
	private Workflow workflow;
	
	private Map<String,Map<String,List<String>>> output;
	
	public void executeWorkflow(Map<String,List<String>> userInput) {
		output = new HashMap<String, Map<String,List<String>>>();
		output.put("USER_RESPONSE", userInput);
		Step step = workflow.getStep();
		do {
			Map<String, List<String>> stepResult = step.execute(output);
			output.put(step.getName(), stepResult);
			step = step.getNextStep();
		}while(step!= null);

	}
	
	public List<String> checkForInputParams() {
		List<String> inputParam = new ArrayList<String>();
		Step step = workflow.getStep();
		do {
			Map<String, String> params = step.getInputParams();
			for(String key : params.keySet())
				if(params.get(key)== null)
					inputParam.add(key);
			step = step.getNextStep();
		}while(step!= null);
		return inputParam;
	}
}
