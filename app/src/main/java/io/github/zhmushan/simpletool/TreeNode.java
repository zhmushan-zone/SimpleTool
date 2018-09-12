package io.github.zhmushan.simpletool;

/*
    Create 2018/9/11
 */

public class TreeNode {
    private int value;
    private ExpressionTreeNode eNode;

    public TreeNode(int value, Object sympol) {
        this.value = value;
        this.eNode = new ExpressionTreeNode(sympol);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ExpressionTreeNode geteNode() {
        return eNode;
    }

    public void seteNode(ExpressionTreeNode eNode) {
        this.eNode = eNode;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", eNode=" + eNode.toString() +
                '}';
    }
}
