package net.nickyecen.clientsidebonanza.calculator;

public class Operator extends MathSymbol {
	
	char operator;
	
	Operator left;
	Operator right;
	
	public Operator(char operator, int extraWeight, int pos) {
		
		this.operator = operator;
		this.pos = pos;

		switch(operator) {
		
			case '+':
			case '-':
				this.weight = extraWeight * 4;
				break;
			
			case '*':
			case '/':
			case '%':
				this.weight = 1 + extraWeight * 4;
				break;
			
			case '^':
				this.weight = 2 + extraWeight * 4;
				break;
		
		}
		
		left = null;
		right = null;
		
	}

}
