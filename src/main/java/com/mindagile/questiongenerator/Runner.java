package com.mindagile.questiongenerator;

import com.mindagile.questiongenerator.model.PrintableNode;
import com.mindagile.questiongenerator.util.TreePrinter;

public class Runner {
	
	public static void main(String args[]){
		PrintableNode node = TreeGenerator.buildExpressionTree();
		System.out.println(new ExpressionTreeEvaluator().evaluateExpressionTee(node));
		TreePrinter.print(node);
		System.out.println(TreeHelper.buildPostFixExpression(node));
		System.out.println(TreeHelper.convertPostfixIntoInfix(TreeHelper.buildPostFixExpression(node)));
		System.out.println(TreeConverter.treeToInfixWithMinBracket(node, false));
		String ttt[] = {"(", "2","+", "3", ")"};
		int two =2;
		int three = 3;
		
	}
}
