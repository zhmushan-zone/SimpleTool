package io.github.zhmushan.simpletool;


public class ExpressionTreeNode {
    private Object sympol;
    private ExpressionTreeNode left;
    private ExpressionTreeNode right;

    public ExpressionTreeNode(Object sympol) {
        this.sympol = sympol;
        this.left = null;
        this.right = null;
    }

    public Object getSympol() {
        return sympol;
    }

    public void setSympol(Object sympol) {
        this.sympol = sympol;
    }

    public ExpressionTreeNode getLeft() {
        return left;
    }

    public void setLeft(ExpressionTreeNode left) {
        this.left = left;
    }

    public ExpressionTreeNode getRight() {
        return right;
    }

    public void setRight(ExpressionTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return sympol.toString();
    }
}
