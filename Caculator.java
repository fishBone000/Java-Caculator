/**
* Program Caculator by FishBone000
* Registered value:
* Scanner sum: input sum
* String sc1: scanning character
* int sc2: index of scanning position
* String sums: string type of sum
* int mem: Temp memory
* int num1: a number waiting for be caculated
* double result: result of 
* char scc: Scanning character
*/
import java.util.Scanner;
public class Caculator{
	static String sc1, str;
	static char scc = '0';
	static boolean [] Debug = {true, true, true, true, true, true};
	//                 toDouble^ Caculate num1^ num2^ main^ SymScan
	public static String sums;
	static int mem, sc2 = 0;
	static double result;
	static double toDouble(String tra1, int a, int b){
		if(Debug[0] == true) System.out.println("toDouble:Start, toDouble(" + tra1 + ", " + a + ", " + b + ")");
		if(b == tra1.length())
			str = tra1.substring(a);
		else
			str = tra1.substring(a, b);
		result = 0;
		sc1 = "0";
		sc2 = tra1.indexOf('.');
		if(sc2 == -1){
			if(Debug[0] == true) System.out.println("toDouble():Dot not found.");
			for(int i = str.length() - 1; i>=0; i--){
				if(Debug[0] == true) System.out.println("toDouble():Current char at " + i + " is " + ((int)str.charAt(i) - 48) + ", result = " + result + ", result += " + ((int)str.charAt(i) - 48) * Math.pow(10, str.length() - i - 1));
				if(str.charAt(0) == '-')
					if(i == 0)
						break;
				result += ((int)str.charAt(i) - 48) * Math.pow(10, str.length() - i - 1);
			}
			if(str.charAt(0) == '-')
				result -= 2*result;
			return result;
		}else{
			if(Debug[0] == true) System.out.println("toDouble():Dot found at " +sc2);
			for(int i = str.length() - 1; i>=0 & i!=sc2; i--){
				if(Debug[0] == true) System.out.println("toDouble():" + i + ", result = " + result + ", result += " + ((int)str.charAt(i) - 48) * Math.pow(10, sc2 - i));
				result += ((int)str.charAt(i) - 48) * Math.pow(10, sc2 - i);
			}
			for(int i = sc2 - 1; i>=0; i--){
				if(Debug[0] == true) System.out.println("toDouble():" + i + ", result = " + result + ", result += " + ((int)str.charAt(i) - 48) * Math.pow(10, sc2 - 1 - i));
								if(str.charAt(0) == '-')
					if(i == 0)
						break;
				result += ((int)str.charAt(i) - 48) * Math.pow(10, sc2 - 1 - i);
			}
						if(str.charAt(0) == '-')
				result -= 2*result;
			if(Debug[0] == true)
				System.out.println("toDouble(): result=" + result);
			return result;
		}
	}
	static double Caculate(){
		double result = 0;
		switch(sums.charAt(mem)){
			case '+':
				result =  num1() + num2();
				break;
			case '-':
				result =  num1() - num2();
				break;
			case '*':
				result =  num1() * num2();
				break;
			case '/':
				result =  num1() / num2();
				break;
			default:
				System.out.println("Unknow character: " + sums.charAt(mem));
		}
		return result;
	}
	static double num1(){
		sc1 = "0";
		int a = 0, num1S = 0, num1E = mem;
		double result = 0;
		/*
		if(sums.substring(a, b).indexOf('+') != -1)
			result = sums.substring(a, b).indexOf('+');
		else if(sums.substring(a, b).indexOf('-') != -1){
			if(sums.charAt(sums.substring(a, b).indexOf('-')) == '(' | sums.substring(a, b).indexOf('-') == 0)
				
		}else if(sums.substring(a, b).indexOf('*') != -1)
			result = sums.substring(a, b).indexOf('*');
		else if(sums.substring(a, b).indexOf('/') != -1)
			result = sums.substring(a, b).indexOf('/');
		if(result == 0 | sums.charAt(result - 1) == '('){
			a = result + 1;
		}
		*/
		/*
		for(int i = mem - 1; (!(sc1.equals("+")|sc1.equals("-")|sc1.equals("*")|sc1.equals("/"))) & i != -1; i--){
			sc1 = sums.substring(i, i + 1);
			a = i;
		}
		*/
		for(int i = mem - 1; sums.charAt(i) != '+'&sums.charAt(i) != '-'&sums.charAt(i) != '*'&sums.charAt(i) != '/'&sums.charAt(i) != '('; i--){
			num1S = i;
			if(Debug[2] == true) System.out.println("num1():Scanning char " + sums.charAt(i) + " at " + i);
			if(i == 0) break;
		}
		if(num1S > 0){
			if(sums.charAt(num1S - 1) == '-'){
				num1S--;
				if(Debug[2] == true) System.out.println("num1():num1S set to:" + num1S);
			}else if(Debug[2] == true) System.out.println("num1():num1S set to:" + num1S);
		}else if(Debug[2] == true) System.out.println("num1():num1S set to:" + num1S);
		if(sums.charAt(mem - 1) == ')')
			num1E = mem - 1;
		result = toDouble(sums.substring(num1S, num1E), 0, num1E - num1S);
		if(Debug[2] == true) System.out.println("num1(): num1()=" + result);
		return result;
	}
	static double num2(){
		int num2S, num2E = 0;  //These values store the index of num2()'s first and the last character
		sc1 = "0";
		boolean negative = false;
		if(sums.charAt(mem + 1) == '('){
			num2S = mem + 2;
			if(Debug[3] == true) System.out.println("num2():num2S set to " + num2S);
			if(sums.charAt(mem + 2) == '-'){
				if(Debug[3] ==  true) System.out.println("num2():Symbol \"-\" found at:" + (mem + 2));
				negative = true;
				num2S = mem + 3;
				if(Debug[3] == true) System.out.println("num2():num2S set to " + num2S);
			}
		}else{
			num2S = mem + 1;
			if(Debug[3] == true) System.out.println("num2():num2S set to " + num2S);
			}
		for(int i = num2S; (!(sc1.equals("+")|sc1.equals("-")|sc1.equals("*")|sc1.equals("/")|sc1.equals(")"))) & i != sums.length(); i++){
			sc1 = sums.substring(i, i + 1);
			num2E = i;
			if(Debug[3] == true)
				System.out.println("num2():" + i + ", " + sc1);
		}
		if(sums.charAt(num2E) == ')')
			num2E--;
		if(Debug[3] == true) System.out.println("num2():num2E set to " + num2E);
		result = toDouble(sums.substring(num2S, num2E + 1), 0, sums.substring(num2S, num2E + 1).length());
		if(negative)
			result -= 2 * result;
		if(Debug[3] == true) System.out.println("num2(): num2()=" + result);
		return result;
	}
	static int SymScan(int a, int b){
		int result = -1;
		boolean loop;
		do{
			loop = false;
			if(Debug[5] == true) System.out.println("SymScan():Start scanning from " + a + " to " + b);
			if(sums.substring(a, b).indexOf('+') != -1)
				result = sums.substring(a, b).indexOf('+');
			else if(sums.substring(a, b).indexOf('-') != -1)
				result = sums.substring(a, b).indexOf('-');
			else if(sums.substring(a, b).indexOf('*') != -1)
				result = sums.substring(a, b).indexOf('*');
			else if(sums.substring(a, b).indexOf('/') != -1)
				result = sums.substring(a, b).indexOf('/');
			if(result == 0){
				a = result + 1;
				loop = true;
				if(Debug[5] == true) System.out.println("SymScan():Negative found at " + result + ", loop");
			}else if(sums.charAt(result - 1) == '('){
				a = result + 1;
				loop = true;
				if(Debug[5] == true) System.out.println("SymScan():Negative found at " + result + ", loop");
			}
		}while(loop);
		result += a;
		if(Debug[5] == true) System.out.println("SymScan():Symbol found at " + result);
		return result;
	}
	public static void main(String[] args){
		System.out.println("Welcome to use Caculator v0.3 by FishBone000.");
		System.out.println("Type sum down below.");
		Scanner sum = new Scanner(System.in);
		sums = sum.nextLine();
		while(true){
			// Debug Settings Start
			while(sums.indexOf("debug") == 0){
				System.out.print("Current debug mode is: ");
				for(boolean i: Debug)
					System.out.print(i + " ");
				System.out.print("\n" +         "                  toDouble^ Caculate num1 num2 main"
				+ "\n" + "Type 1 or 0 to modify, or empty to skip." + "\n");
				sums = sum.nextLine();
				if(sums.length() != 0){
					for(int i = 0; i <= Debug.length - 1; i++){
						if(sums.charAt(i) - 48 == 1)
							Debug[i] = true;
						else
							Debug[i] = false;
					}
				}
				sums = sum.nextLine();
			}
			// Debug Settings End
			sc1 = "0";
			sc2 = 0;
			mem = sc2 = SymScan(0, sums.length());
			if(Debug[4] == true)
				System.out.println("main:" + num1() + ", " + num2());
			System.out.println(Caculate());
			sums = sum.nextLine();
		}
	}
}