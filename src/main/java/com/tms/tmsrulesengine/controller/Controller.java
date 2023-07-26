package com.tms.tmsrulesengine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tms.tmsrulesengine.model.User;
import com.tms.tmsrulesengine.service.RulesEngineService;

@RestController
public class Controller {
	
	 @Autowired
	    private RulesEngineService rulesEngineService;

	    @PostMapping("/evaluate")
	    public String evaluateRules(@RequestBody User data) {
	        // Process the input data and execute rules
	        return rulesEngineService.executeRules(data);
	    }

}
