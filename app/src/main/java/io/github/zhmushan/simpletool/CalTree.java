package io.github.zhmushan.simpletool;

import java.util.Objects;
import java.util.Stack;


public class CalTree {
    private static boolean isDouble(char c) {
        return c != '(' &&
                c != ')' &&
                c != '＋' &&
                c != '－' &&
                c != '×' &&
                c != '÷';
    }

    private static Stack<Object> handleCommon(String common) throws java.lang.NumberFormatException {
        Stack<Object> expression = new Stack<Object>();

        for (int i = 0; i < common.length(); i++) {
            if (!isDouble(common.charAt(i))) {
                switch (common.charAt(i)) {
                    case '＋':
                        if (Objects.equals(expression.peek().getClass().getName(), "java.lang.Double") || Objects.equals(expression.peek(), ')')) {
                            expression.push('＋');
                        }
                        break;

                    case '－':
                        if (Objects.equals(expression.peek().getClass().getName(), "java.lang.Double") || Objects.equals(expression.peek(), ')')) {
                            expression.push('－');
                        } else {
                            expression.push('#');
                        }
                        break;

                    case '×':
                    case '÷':
                    case '(':
                    case ')':
                        expression.push(common.charAt(i));
                        break;
                }
            } else {
                StringBuilder temp = new StringBuilder();
                while (isDouble(common.charAt(i))) {
                    temp.append(common.charAt(i));
                    i++;
                    if (i >= common.length()) {
                        break;
                    }
                }

                double num = 0;
                try {
                    num = Double.valueOf(temp.toString());
                } catch (java.lang.NumberFormatException e) {
                    throw new java.lang.NumberFormatException(String.valueOf(i));
                }

                if (!expression.isEmpty()) {
                    while (Objects.equals(expression.peek(), '#')) {
                        num = -1 * num;
                        expression.pop();
                    }
                }

                expression.push(num);

                i--;
            }
        }

        return expression;
    }

    private static int getWeight(int base, Object sympol) {
        if (Objects.equals(sympol, '＋') || Objects.equals(sympol, '－')) {
            return base + 1;
        }
        if (Objects.equals(sympol, '×') || Objects.equals(sympol, '÷')) {
            return base + 2;
        }
        return Integer.MAX_VALUE;
    }

    private static ExpressionTreeNode build(Stack<Object> expression) {
        if (expression.isEmpty()) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        int base = 0;

        for (Object i :
                expression) {
            int value = 0;

            if (Objects.equals(i, '(')) {
                base += 10;
                continue;
            }
            if (Objects.equals(i, ')')) {
                base -= 10;
                continue;
            }

            value = getWeight(base, i);
            TreeNode node = new TreeNode(value, i);

            while (!stack.isEmpty() && node.getValue()<=stack.peek().getValue()) {
                node.geteNode().setLeft(stack.pop().geteNode());
            }

            if (!stack.isEmpty()) {
                stack.peek().geteNode().setRight(node.geteNode());
            }

            stack.push(node);
        }

        if (stack.isEmpty()) {
            return null;
        }

        TreeNode ret = stack.pop();
        while (!stack.isEmpty()) {
            ret = stack.pop();
        }

        return ret.geteNode();
    }

    private static Stack<Object> preOrder(ExpressionTreeNode eNode){
        Stack<Object> ret = new Stack<Object>();

        Stack<Object> temp = null;
        if(eNode!=null){
            ret.push(eNode.getSympol());

            temp = preOrder(eNode.getLeft());
            if (!temp.isEmpty()) {
                ret.addAll(temp);
            }
            temp = preOrder(eNode.getRight());
            if (!temp.isEmpty()) {
                ret.addAll(temp);
            }
        }

        return ret;
    }

    private static double cal(Stack<Object> expression) {
        Stack<Object> temp = new Stack<Object>();

        for (Object i : expression) {
            if (Objects.equals(i.getClass().getName(), "java.lang.Double")) {
                // 从右至左扫描，将操作数压入堆栈
                temp.push(i);
            } else {
                // 遇到运算符，弹出栈顶元素和次顶元素，计算出值，再将值入栈
                temp.push(calc((Double)temp.pop(), (Character)i, (Double) temp.pop()));
            }
        }

        return (Double)temp.pop();
    }

    private static Double calc(Double a, Character operator, Double b) {
        Double ret = null;
        switch (operator) {
            case '＋':
                ret = a+b;
                break;
            case '－':
                ret = a-b;
                break;
            case '×':
                ret = a*b;
                break;
            case '÷':
                ret = a/b;
                break;
            default:
                break;
        }
        return ret;
    }

    private static Stack<Object> reverseStack(Stack<Object> stack) {
        Stack<Object> m_stack = new Stack<Object>();

        while (!stack.empty()) {
            m_stack.push(stack.pop());
        }

        return m_stack;
    }

    Double start(String common) {
        Stack<Object> commonList = null;

        try {
            commonList = handleCommon(common);
        } catch (java.lang.NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(commonList);

        if (commonList == null) {
            return null;
        }

        ExpressionTreeNode eNode = build(commonList);

        Stack<Object> expression = null;
        expression = preOrder(eNode);
        System.out.println(preOrder(eNode));

        return cal(reverseStack(expression));
    }
}
