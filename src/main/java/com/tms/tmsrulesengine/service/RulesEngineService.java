package com.tms.tmsrulesengine.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tms.tmsrulesengine.model.Rule;
import com.tms.tmsrulesengine.model.User;


@Service
public class RulesEngineService {

	private List<Rule> rules;

	@PostConstruct
	public void init() throws IOException {
		// Load JSON rules from a file or a database
		ObjectMapper objectMapper = new ObjectMapper();
		rules = objectMapper.readValue(
				new File("C:/Users/Sumitha.N/Stonex/Training/tms-rules-engine/src/main/resources/jsonRules.json"),
				new TypeReference<List<Rule>>() {
				});
	}

	public String executeRules(User data) {
		for (Rule rule : rules) {
			ExpressionParser parser = new SpelExpressionParser();
			StandardEvaluationContext context = new StandardEvaluationContext(data);

			if (parser.parseExpression(rule.getCondition()).getValue(context, Boolean.class)) {
				// Perform the action based on the rule
				return rule.getAction();
			}
		}

		// Default action if no rule matches
		return "DEFAULT_ACTION";
	}

}
