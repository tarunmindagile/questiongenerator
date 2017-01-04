package com.mindagile.questiongenerator;

import java.util.ArrayList;
import java.util.List;

import com.mindagile.questiongenerator.model.TreeNode;
import com.mindagile.questiongenerator.util.TreePrinter;

public class TreeGenerator {

	private static final String[] operators = { "+", "-", "/", "*" };
	private static final int MAX_INTEGER_VALUE = 100;

	public static void main(String args[]) {
		new ArrayList<>();
		TreeNode treeNode = new TreeNode();
		treeNode.setText(getRandomOperator());
		populateNode(treeNode, 3);
		TreePrinter.print(treeNode);
	}

	public static TreeNode buildExpressionTree() {
		TreeNode treeNode = new TreeNode();
		treeNode.setText(getRandomOperator());
		populateNode(treeNode, 3);
		return treeNode;
	}

	private static void populateNode(TreeNode treeNode, int height) {
		if (height == 1) {
			treeNode.setLeft(new TreeNode().setText(getRandomOperand()))
					.setRight(new TreeNode().setText(getRandomOperand()));
			return;
		}
		height--;
		if (Math.random() < 0.5) {
			treeNode.setLeft(new TreeNode().setText(getRandomOperand()));
		} else {
			treeNode.setLeft(new TreeNode().setText(getRandomOperator()));
			populateNode((TreeNode) treeNode.getLeft(), height);
		}

		if (Math.random() < 0.5) {
			treeNode.setRight(new TreeNode().setText(getRandomOperand()));
		} else {
			treeNode.setRight(new TreeNode().setText(getRandomOperator()));
			populateNode((TreeNode) treeNode.getRight(), height);
		}
	}

	public static String getRandomOperator() {
		int index = (int) (Math.random() * operators.length);
		return operators[index <= 0 ? 0 : index - 1];
	}

	public static String getRandomOperand() {
		int operand = (int) (Math.random() * MAX_INTEGER_VALUE);
		return String.valueOf(operand);
	}

	public TreeNode buildTreeCopy(TreeNode treeNode) {
		if (treeNode != null) {
			return new TreeNode().setText(treeNode.getText()).setRight((TreeNode) treeNode.getRight())
					.setLeft((TreeNode) treeNode.getLeft());
		}
		return null;
	}
}
