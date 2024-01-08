package net.nickyecen.clientsidebonanza.calculator;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class Evaluator {

	public static String evaluate(String mainExpression) {
		
		LinkedList<Operator> operators = new LinkedList<Operator>();
		LinkedList<Value> values = new LinkedList<Value>();
		Operator root;
		
		buildLists(mainExpression, operators, values);
		operators.sort((o1, o2) -> {return o1.weight - o2.weight;});
		
		root = buildTree(operators);
		
		return new DecimalFormat("#.###").format(solveTree(root, values));
	}
	
	private static double solveTree(Operator root, LinkedList<Value> values) {
		
		if(root == null) return values.pop().value;
		
		switch(root.operator) {
		
			case '+':
				return solveTree(root.left, values) + solveTree(root.right, values);
			case '-':
				return solveTree(root.left, values) - solveTree(root.right, values);
			case '*':
				return solveTree(root.left, values) * solveTree(root.right, values);
			case '/':
				return solveTree(root.left, values) / solveTree(root.right, values);
			case '%':
				return solveTree(root.left, values) % solveTree(root.right, values);
			case '^':
				return Math.pow(solveTree(root.left, values), solveTree(root.right, values));
			default:
				return 0;
		
		}
		
	}

	private static void printTree(Operator tree) {
		
		if(tree == null) return;
		
		System.out.println(tree.operator);
		printTree(tree.left);
		printTree(tree.right);
		
	}

	private static Operator buildTree(LinkedList<Operator> operators) {
		
		Operator root = operators.pop();
		Operator buffer;
		
		while(!operators.isEmpty()) {
			
			buffer = operators.pop();
			
			root = placeNode(buffer, root);
			
		}
		
		return root;
	}

	private static Operator placeNode(Operator buffer, Operator root) {
		
		if(root == null) return buffer;
		if(buffer.pos < root.pos) root.left = placeNode(buffer, root.left);
		else root.right = placeNode(buffer, root.right);
		
		return root;
	}

	private static void buildLists(String mainExpression, LinkedList<Operator> operators, LinkedList<Value> values) {		

		LinkedList<Character> pile = new LinkedList<Character>();
		LinkedList<Character> var = new LinkedList<Character>();
		
		int length = mainExpression.length();
		char buffer;
		
		for(int i = 0; i < length; i++) {
			
			buffer = mainExpression.charAt(i);
			if(isOperator(buffer, i == 0 ? 0 : mainExpression.charAt(i - 1))) {
				
				operators.add(new Operator(buffer, pile.size(), i));
				values.add(new Value(Double.parseDouble(format(var.toString())), pile.size(), i - 1));
				var.clear();
				
			} else if(isOpening(buffer)) {
				
				pile.addFirst(buffer);
				
			} else if(isClosing(buffer)) {
				
				pile.pop();
				
			} else {
				
				var.add(buffer);
				
			}
			
		}		

		values.add(new Value(Double.parseDouble(format(var.toString())), pile.size(), length - 1));
		
	}
	
	private static boolean isOperator(char cur, char prev) {
		
		return cur == '*' || cur == '/' || cur == '%' || cur == '^'
				|| ((cur == '+' || cur == '-')
						&& (prev != 0 && !isOpening(prev)));
		
	}
	
	private static boolean isOpening(char cur) {
		
		return cur == '(' || cur == '[' || cur == '{';
		
	}
	
	private static boolean isClosing(char cur) {
		
		return cur == ')' || cur == ']' || cur == '}';
		
	}
	
	private static String format(String string) {
		
		return string.replace(",", "").replace("[", "").replace("]", "").replace(" ", "");
		
	}

}
