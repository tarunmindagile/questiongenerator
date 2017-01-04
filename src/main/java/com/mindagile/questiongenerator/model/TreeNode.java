package com.mindagile.questiongenerator.model;

public class TreeNode implements PrintableNode {
	private TreeNode left;
	private TreeNode right;
	String text;

	@Override
	public PrintableNode getLeft() {
		return left;
	}

	@Override
	public PrintableNode getRight() {
		return right;
	}

	@Override
	public String getText() {
		return text;
	}

	public TreeNode setLeft(TreeNode left) {
		this.left = left;
		return this;
	}

	public TreeNode setRight(TreeNode right) {
		this.right = right;
		return this;
	}

	public TreeNode setText(String text) {
		this.text = text;
		return this;
	}

}
