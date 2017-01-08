package com.mindagile.questiongenerator.model;

import java.util.List;

public class SingleVariableMCQ {

	private String expressionString;
	private List<String> options;
	private String correctAnswer;

	public String getExpressionString() {
		return expressionString;
	}

	public SingleVariableMCQ setExpressionString(String expressionString) {
		this.expressionString = expressionString;
		return this;
	}

	public List<String> getOptions() {
		return options;
	}

	public SingleVariableMCQ setOptions(List<String> options) {
		this.options = options;
		return this;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public SingleVariableMCQ setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
		return this;
	}

}
