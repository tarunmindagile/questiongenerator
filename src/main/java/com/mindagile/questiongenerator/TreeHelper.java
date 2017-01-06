package com.mindagile.questiongenerator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import com.mindagile.questiongenerator.model.PrintableNode;

public class TreeHelper {

	public static List<String> buildPostFixExpression(PrintableNode node) {

		if (node.getLeft() == null && node.getRight() == null) {
			return Arrays.asList(node.getText());
		}

		List<String> result = new ArrayList<>();

		result.addAll(buildPostFixExpression(node.getLeft()));
		result.addAll(buildPostFixExpression(node.getRight()));
		result.add(node.getText());
		return result;
	}

	public static String convertPostfixIntoInfix(List<String> postFix) {
		Deque<String> stack = new ArrayDeque<>();
		for (String str : postFix) {
			if (isOperator(str)) {
				String first = stack.pop();
				String second = stack.pop();
				String result = "";
				result = result + "(" + second + str + first + ")";
				stack.push(result);
			} else {
				stack.push(str);
			}
		}
		return stack.pop();
	}

	public static boolean isOperator(String str) {
		return str.equals("+") || str.equals("*") || str.equals("-") || str.equals("/");
	}

	public static int getOperatorPriority(String str) {
		return str.equals("+") || str.equals("-") ? 1 : 2;
	}
}
