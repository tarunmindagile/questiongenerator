package com.mindagile.questiongenerator.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.mutable.MutableBoolean;
import org.apache.commons.lang.mutable.MutableInt;

import com.google.gson.Gson;
import com.mindagile.questiongenerator.ExpressionTreeEvaluator;
import com.mindagile.questiongenerator.TreeConverter;
import com.mindagile.questiongenerator.TreeGenerator;
import com.mindagile.questiongenerator.TreeHelper;
import com.mindagile.questiongenerator.model.PrintableNode;
import com.mindagile.questiongenerator.model.SingleVariableMCQ;
import com.mindagile.questiongenerator.model.TreeNode;
import com.mindagile.questiongenerator.util.TreePrinter;

@WebServlet("/mcqQuestion/singleExpression")
public class QuestionGeneratorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintableNode node = TreeGenerator.buildExpressionTree();
		TreePrinter.print(node);
		String expression = TreeConverter.treeToInfixWithMinBracket(node, false);
		double answer = new ExpressionTreeEvaluator().evaluateExpressionTee(node);
		double answerWrong1 = answer + 1;
		double anserWrong2 = answer - 1;
		PrintWriter printWriter = response.getWriter();
		PrintableNode node2 = buildDifferentTree(node, new MutableBoolean(false));
		double answerWrong3 = new ExpressionTreeEvaluator().evaluateExpressionTee(node2);
		if (answer == answerWrong3) {
			answerWrong3 = Math.random() < 0.5 ? answer - 3 : answer + 3;
		}
		List<String> answerOptions = Arrays.asList(String.valueOf(answer), String.valueOf(answerWrong1),
				String.valueOf(anserWrong2), String.valueOf(answerWrong3));
		Collections.shuffle(answerOptions);
		printWriter.write(new Gson().toJson(new SingleVariableMCQ().setExpressionString(expression)

				.setCorrectAnswer(String.valueOf(answer)).setOptions(answerOptions)));
		printWriter.close();
	}

	PrintableNode buildDifferentTree(PrintableNode node, MutableBoolean mutableBoolean) {
		if (node == null) {
			return node;
		}
		if (mutableBoolean.isTrue()) {
			return node;
		}
		if (node.getLeft() != null && TreeHelper.isOperator(node.getLeft().getText())
				&& !node.getText().equals(node.getLeft().getText())) {
			String left = node.getLeft().getText();
			String main = node.getText();
			TreeNode nodeMain = (TreeNode) node;
			TreeNode nodeLeft = (TreeNode) node.getLeft();
			nodeMain.setText(left);
			nodeLeft.setText(main);
			mutableBoolean.setValue(true);
			return node;
		}

		if (node.getRight() != null && TreeHelper.isOperator(node.getRight().getText())
				&& !node.getText().equals(node.getRight().getText())) {
			String right = node.getRight().getText();
			String main = node.getText();
			TreeNode nodeMain = (TreeNode) node;
			TreeNode nodeRight = (TreeNode) node.getRight();
			nodeMain.setText(right);
			nodeRight.setText(main);
			mutableBoolean.setValue(true);
			return node;
		}

		buildDifferentTree(node.getLeft(), mutableBoolean);
		buildDifferentTree(node.getRight(), mutableBoolean);
		return node;
	}
}
