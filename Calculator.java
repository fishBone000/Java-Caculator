/**
Brief: This is a class for calculating.
@author FishBone000
@version alpha 0.7
Date: 2018/7/18
**/

import java.util.Scanner;
import java.lang.Math;
public class Calculator{
	
	static char [] operator = {'^', '*', '/', '+', '-'};
	
	// SUMMARY
	// Gets start&end index of a basic expression.
	// expression: The expression where the operator is included in;
	// oprtIndex: Index of operator in expression;
	// length: Length of the operator string;
	// SUMMARY
	public static int [] getIndex(String expression, int oprtIndex, int length){
		/*char crChar;
		int [] result = new int[2];
		if(expression.charAt(oprtIndex - 1) == ')' & length == 1) { // Condition must be checked while adding new operator
			result[0] = expression.substring(0, oprtIndex).lastIndexOf('(') + 1;
		}else{
			result[0] = oprtIndex - 1;
			crChar = expression.charAt(result[0]);
			while(crChar >= 48 & crChar <= 57| crChar == '.') {
				if(result[0] == 0)
					break;
				result[0] --;
				crChar = expression.charAt(result[0]);
			}
			if(result[0] != 0)
				result[0]++;
			else if(result[0] == 1)
				if(expression.charAt(result[0] - 1) == '_')
					result[0] --;
		}
		
		if(expression.charAt(oprtIndex + length) == '('){
			result[1] = expression.substring(oprtIndex + length + 1).indexOf(')');
			return result;
		}else{
			result[1] = oprtIndex + length;
			int expressionLength = expression.length();
			crChar = expression.charAt(result[1]);
			while(crChar >= 48 & crChar <= 57 | crChar == '.') {
				result[1] ++;
				if(result[1] >= expressionLength)
					break;
				crChar = expression.charAt(result[1]);
			}
			return result;
			*/
		
		int [] result = new int[2];
		int cursor = oprtIndex - 1;
		loop:do {
			char character = expression.charAt(cursor); // run it every time when using cursur.
			if((character >= 48 & character <= 57) | character == '.') {
				if(cursor > 0) {
					character = expression.charAt(cursor - 1);
					switch(character) {
					case '(' :
						break loop;
					case '-' :
						if(cursor - 2 == 0) break loop;
						// continue programming here
					}
				} else if(cursor == 0) break;
			}
		}while(cursor >= 0);
	}
	// SUMMARY
	// Calculates a simple expression includes a single operator.
	// expression: The target expression.
	// oprtIndex: The index of the operator.
	// length: Length of the operator.
	// SUMMARY
	public static double basicCalculate(String expression, int oprtIndex, int length){
		double result;
		double [] number = new double[2];
		number[0] = 0;
		int [] index = new int[2];
		String operator = expression.substring(oprtIndex, oprtIndex + length);
		index = getIndex(expression, oprtIndex, length);
		
		if(length == 1) {
			number[0] = Double.parseDouble(expression.substring(index[0], oprtIndex).replace('_', '-'));			
		}
		number[1] = Double.parseDouble(expression.substring(oprtIndex + length, index[1]).replace('_', '-'));
		// MUST be checked while adding a new operator
		switch(operator) {
		case "+" :
			result = number[0] + number[1];
			break;
		case "-" :
			result = number[0] - number[1];
			break;
		case "*" : 
			result = number[0] * number[1];
			break;
		case "/" : 
			result = number[0] / number[1];
			break;
		case "^" :
			result = Math.pow(number[0], number[1]);
			break;
		case "sin" :
			result = Math.sin(number[1]);
			break;
		case "cos" :
			result = Math.cos(number[1]);
			break;
		case "tan" :
			result = Math.tan(number[1]);
			break;
		default :
			System.out.println("Faltal error, operator not recognized!");
			return 0;
		}
		return result;
	}
	
	public static int minIndex(int a, int b) {
		if(a == -1 | b == -1)
			return Math.max(a, b);
		else
			return Math.min(a,  b);
	}
	
	public static double solve(String expression) {
		double result = 0;
		StringBuffer resultBuffer = new StringBuffer(expression);
		int [] target = new int[3]; 
		// target[]: Includes indexes of a basic expression which will be calculated,
		// and the index of the operator in the origin expression.
		do {
			String name = "";
			target[2] = resultBuffer.indexOf("sin");
			if(target[2] != -1)
				name = "sin";
			else {
				target[2] = resultBuffer.indexOf("cos");
				if(target[2] != -1)
					name = "cos";
				else {
					target[2] = resultBuffer.indexOf("tan");
					if(target[2] != -1)
						name = "tan";
				}
			}
			
			if(target[2] != -1) {
				int [] temp = new int[2];
				
				target[2] = resultBuffer.indexOf(name);
				temp = getIndex(resultBuffer.toString(), target[2], 3);
				target[0] = temp[0];
				target[1] = temp[1];
				// Improvement required: less codes(flex with getIndex())
				
				resultBuffer.replace(target[0], target[1], String.valueOf(basicCalculate(resultBuffer.toString(), target[2], 3)).replace('-', '_'));
			}
		} while(target[2] != -1);
		
		target[2] = resultBuffer.indexOf("^");
		while(target[2] != -1) {
			int [] temp = getIndex(resultBuffer.toString(), target[2], 1);
			target[0] = temp[0];
			target[1] = temp[1];
			resultBuffer.replace(target[0], target[1], String.valueOf(basicCalculate(resultBuffer.toString(), target[2], 1)).replace('-', '_'));
			target[2] = resultBuffer.indexOf("^");
		}
		
		target[2] = minIndex(resultBuffer.indexOf("*"), resultBuffer.indexOf("/"));
		while(target[2] != -1) {
			int [] temp = getIndex(resultBuffer.toString(), target[2], 1);
			target[0] = temp[0];
			target[1] = temp[1];
			resultBuffer.replace(target[0], target[1], String.valueOf(basicCalculate(resultBuffer.toString(), target[2], 1)).replace('-', '_'));
			target[2] = minIndex(resultBuffer.indexOf("*"), resultBuffer.indexOf("/"));
		} // Improvement required: run getIndex for only 1 time instead of 2 times (basicCalculate included)
		
		target[2] = minIndex(resultBuffer.indexOf("+"), resultBuffer.indexOf("-"));
		while(target[2] != -1) {
			int [] temp = getIndex(resultBuffer.toString(), target[2], 1);
			target[0] = temp[0];
			target[1] = temp[1];
			resultBuffer.replace(target[0], target[1], String.valueOf(basicCalculate(resultBuffer.toString(), target[2], 1)).replace('-', '_'));
			target[2] = minIndex(resultBuffer.indexOf("+"), resultBuffer.indexOf("-"));
		}
		
		result = Double.parseDouble(resultBuffer.toString().replace('_', '-'));
		return result;
	}
	
	public static double calculate(String expression) {
		if(expression.indexOf("[") != -1) {
			expression.replaceAll("[", "(");
			expression.replaceAll("]", ")");
		}
		if(expression.indexOf("{") != -1) {
			expression.replaceAll("{", "(");
			expression.replaceAll("}", ")");
		}
		StringBuffer expressionBuffer = new StringBuffer(expression);
		String targetExpression = expression;
		
		int [] targetIndex = {expressionBuffer.indexOf("("), 0}; // Condition of while loop
		while(targetIndex[0] != -1) {
			targetIndex[0] = expressionBuffer.indexOf("(_");
			// bug might appear in this while loop
			label:while(targetIndex[0] != -1) { // Finds (*Negative number*)
				targetExpression = expressionBuffer.substring(targetIndex[0]);
				targetIndex[1] = expressionBuffer.substring(targetIndex[0]).indexOf(")");
				if(targetExpression.substring(1).indexOf("(") > targetIndex[1]){
					String [] operators = {"+", "-", "*", "/", "^", "sin", "cos", "tan"};
					// Check required while adding new operators.
					targetExpression = expressionBuffer.substring(targetIndex[0], targetIndex[1]);
					for(int i = 0; i < operators.length; i ++) {
						if(targetExpression.indexOf(operators[i]) != -1) {
							targetIndex[0] = expressionBuffer.substring(targetIndex[0] + 1).indexOf("(_");
							continue label;
						}	
					}	
					expressionBuffer.deleteCharAt(targetIndex[1]);
					expressionBuffer.deleteCharAt(targetIndex[0]);
				} else {
					targetIndex[0] = expressionBuffer.substring(targetIndex[0] + 1).indexOf("(_");
					continue;
				}
			
				targetExpression = expressionBuffer.toString();
				targetIndex[0] = expressionBuffer.indexOf("("); // Condition of while loop
				targetIndex[1] = targetExpression.length();
				
				while(targetIndex[0] != -1) {
					targetIndex[1] = expressionBuffer.substring(targetIndex[0]).indexOf(")");
					targetExpression = expressionBuffer.substring(targetIndex[0] + 1, targetIndex[0]);
					if(targetExpression.indexOf("(") == -1) {
						expressionBuffer.replace(targetIndex[0], targetIndex[1] + 1, String.valueOf(solve(targetExpression)));
						// WARNING! Not available for high double value, use BigDecimal instead; Check other places while fixing it.
					} else {
						targetIndex[0] = targetIndex[0] + targetExpression.indexOf("(") + 1;
					}
				}
			}
			
			targetIndex[0] = expressionBuffer.indexOf("(");
		}
		return solve(expressionBuffer.toString());
	}
	
	public static void main(String [] args){
		Scanner scanner = new Scanner(System.in);
		String expression = scanner.nextLine();
		System.out.println(calculate(expression));
		scanner.close();
	}
}