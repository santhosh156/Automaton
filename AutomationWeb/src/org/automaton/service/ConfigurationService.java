package org.automaton.service;

import org.automaton.config.Workflow;
import org.automaton.config.WorkflowRepository;
import org.automaton.config.step.StepRepository;
import org.automaton.config.step.impl.DatabaseStep;
import org.automaton.config.step.impl.RestStep;
import org.automaton.config.step.impl.ShellStep;
import org.automaton.config.step.impl.SoapStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationService {

	@Autowired
	private StepRepository repository;
	
	@Autowired
	private WorkflowRepository wfRepository;
	
	@CrossOrigin
	@RequestMapping(path="/shell/save",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public String saveStep(@RequestBody ShellStep step) {
		step.setId(generateStepId());
		step =repository.save(step);
		return step.getId();
	}
	
	@CrossOrigin(origins={"*"},methods={RequestMethod.POST},allowedHeaders="*",maxAge=1209600)
	@RequestMapping(path="/soap/save",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public String saveStep(@RequestBody SoapStep step) {
		System.out.println("TEsting");
		step.setId(generateStepId());
		step =repository.save(step);
		return step.getId();
	}

	@RequestMapping(path="/rest/save",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public String saveStep(@RequestBody RestStep step) {
		step.setId(generateStepId());
		step =repository.save(step);
		return step.getId();
	}

	@RequestMapping(path="/database/save",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public String saveStep(@RequestBody DatabaseStep step) {
		step.setId(generateStepId());
		step =repository.save(step);
		return step.getId();
	}
	
	private String generateStepId(){
		long id =repository.count();
		return String.valueOf(id);
	}
	
	private String generateWorkflowId(){
		long id =repository.count();
		return String.valueOf(id);
	}
	
	@RequestMapping(path="/workflow/save",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public String saveWorkflow(@RequestBody Workflow flow) {
		flow.setId(generateWorkflowId());
		flow = wfRepository.save(flow);
		return flow.getId();
	}

	@RequestMapping(path="/alive",method=RequestMethod.GET)
	public String isAlive() {
		return "alive";
	}
}
