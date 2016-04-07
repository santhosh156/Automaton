package org.automaton.config.step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Step {
	
	private String id;

	private Step nextStep;
	
	private String name;
	
	protected Map<String,String> inputParams;
	
	public abstract Map<String,List<String>> execute(Map<String,Map<String,List<String>>> globalResponse); 
	
	/**
	 * This method is used to identify the request parameters and fill the request parameters.
	 * @param globalResponse
	 * @return
	 */
	 protected Map<String,List<String>> prepareInputs(Map<String,Map<String,List<String>>> globalResponse) {
		Map<String,List<String>> input = new HashMap<String,List<String>> ();
		Map<String,List<String>> userParams = globalResponse.get("USER_RESPONSE");
		for(String param : inputParams.keySet()) {
			if(inputParams.get(param)==null) {
				if(userParams.get(param)==null || userParams.get(param).isEmpty()) {
					throw new RuntimeException("All inputs are not binded");
				} else {
					input.put(param, userParams.get(param));
				}
			} else {
				String step[] = inputParams.get(param).split(".");
				if(globalResponse.get(step[0]).get(step[1]) == null || globalResponse.get(step[0]).get(step[1]).isEmpty())
					throw new RuntimeException("Inproper binding of the inputs from previous steps");
				else {
					input.put(param, globalResponse.get(step[0]).get(step[1]));
				}
			}
		}
		return input;
	}

	public Step getNextStep() {
		return nextStep;
	}

	public void setNextStep(Step nextStep) {
		this.nextStep = nextStep;
	}

	public Map<String, String> getInputParams() {
		return inputParams;
	}

	public void setInputParams(Map<String, String> inputParams) {
		this.inputParams = inputParams;
	}
	
	
	public boolean hasNextStep(){
		if(nextStep != null )
			return true;
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id  = id;
	}
	
	
}
