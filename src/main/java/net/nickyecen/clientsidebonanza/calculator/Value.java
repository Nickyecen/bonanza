package net.nickyecen.clientsidebonanza.calculator;

public class Value extends MathSymbol {
	
	double value;
	
	public Value(double value, int extraWeight, int pos) {
		
		this.value = value;
		this.pos = pos;
		this.weight = 3 + 4 * extraWeight;
		
	}

}
