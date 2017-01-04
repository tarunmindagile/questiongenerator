package com.mindagile.questiongenerator;

import com.mindagile.questiongenerator.model.PrintableNode;
import com.mindagile.questiongenerator.model.TreeNode;

public class ExpressionTreeEvaluator {

	public double evaluateExpressionTee(PrintableNode printableNode) {
		if (printableNode.getLeft() == null && printableNode.getRight() == null) {
			return Double.parseDouble(printableNode.getText());
		}

		double left = evaluateExpressionTee(printableNode.getLeft());
		double right = evaluateExpressionTee(printableNode.getRight());
		double result = 0;
		switch (printableNode.getText()) {
		case "+":
			result = left + right;
			break;
		case "-":
			result = left - right;
			break;
		case "*":
			result = left * right;
			break;
		case "/":
			result = left / right;
			break;
		}
		return result;
	}
}
