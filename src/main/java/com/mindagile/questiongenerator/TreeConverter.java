package com.mindagile.questiongenerator;

import com.mindagile.questiongenerator.model.PrintableNode;

public class TreeConverter {

	public static String treeToInfixWithMinBracket(PrintableNode treeNode, boolean isBracketApplicable) {
		if (treeNode.getLeft() == null && treeNode.getRight() == null) {
			return treeNode.getText();
		}

		String value = treeNode.getText();
		String leftValue = treeNode.getLeft().getText();
		String rightValue = treeNode.getRight().getText();
		String right = "";
		String left = "";
		if (isOperator(rightValue) && (getOperatorPriority(value) > getOperatorPriority(rightValue)
				|| (getOperatorPriority(value) == getOperatorPriority(rightValue)
						&& (value.equals("-") || value.equals("/"))))) {
			right = treeToInfixWithMinBracket(treeNode.getRight(), true);
		} else {
			right = treeToInfixWithMinBracket(treeNode.getRight(), false);
		}

		if (isOperator(leftValue) && (getOperatorPriority(value) > getOperatorPriority(leftValue))) {
			left = treeToInfixWithMinBracket(treeNode.getLeft(), true);
		} else {
			left = treeToInfixWithMinBracket(treeNode.getLeft(), false);
		}

		if (isBracketApplicable) {
			return "(" + left + value + right + ")";
		} else {
			return left + value + right;
		}

	}

	public static boolean isOperator(String str) {
		return str.equals("+") || str.equals("*") || str.equals("-") || str.equals("/");
	}

	public static int getOperatorPriority(String str) {
		return str.equals("+") || str.equals("-") ? 1 : 2;
	}

	public static double evaluate(String leftStr, String rightStr, String operator) {
		double left = Double.parseDouble(leftStr);
		double right = Double.parseDouble(rightStr);
		double result = 0;
		switch (operator) {
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
