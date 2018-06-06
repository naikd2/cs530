package com.dhruvitnaik.service;


import com.dhruvitnaik.model.Filter;
import com.dhruvitnaik.model.Pin;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CriteriaFilterService {

	private static final String PIN_VARIABLE = "pin";


	public Filter buildExpression(Filter filter) {
		String attribute = filter.getAttribute();
		String condition = filter.getCondition();
		String value = filter.getValue();
		Double number = parseToNumber(value);

		String lhs = String.format("#%s.get%s()", PIN_VARIABLE, attribute);
		String rhs;


		if (condition.equals("Equals") && number == null) {
			rhs = String.format(".equals('%s')", value);
		}
		else if (condition.equals("Equals") && number != null) {
			rhs = " == " + number;
		}
		else if (condition.equals("Contains")) {
			rhs = String.format(".contains('%s')", value);
		}
		else if (condition.equals("Less Than") && number != null) {
			rhs = " < " + number;
		}
		else if (condition.equals("Greater Than") && number != null) {
			rhs = " > " + number;
		}
		else {
			return filter;
		}

		filter.setExpresion(lhs+rhs);
		return filter;
	}

	private Double parseToNumber(String value) {
		try {
			return new Double(value);
		} catch (Exception ex) {
			return null;
		}
	}

	public boolean evaluate(Pin pin, Filter filter) {
		SpelExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setVariable(PIN_VARIABLE, pin);
		Expression exp = parser.parseExpression(filter.getExpresion());
		boolean result = exp.getValue(context,Boolean.class);
		return  result;

	}

}
