package calculator;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);		
			String mainExpression = scan.nextLine();			
		scan.close();
		
		System.out.println(Evaluator.evaluate(mainExpression));

	}

}
