/**
* Programe Caculator by FishBone000
* Registered value:
* Scanner sum: input sum
* String sc1: scanning character
* int sc2: index of scanning position
* String sums: string type of sum
* int mem: Temp memory
* int num1: a number waiting for be caculated
* double result: result of 
*/
import java.util.Scanner;
public class Caculator{
	static String sc1, str;
	public static String sums;
	static int mem, sc2 = 0;
	static double result;
	static double toInt(String tra1, int a, int b){
		if(b == tra1.length())
			str = tra1.substring(a);
		else
			str = tra1.substring(a, b);
		result = 0;
		sc1 = "0";
		sc2 = tra1.indexOf('.');
		if(sc2 == -1){
			for(int i = str.length() - 1; i>=0; i--){
				result += ((int)str.charAt(i) - 48) * Math.pow(10, str.length() - i - 1);
			}
			return result;
		}else{
			for(int i = str.length() - 1; i>=0 & i!=sc2; i--){
				result += ((int)str.charAt(i) - 48) * Math.pow(10, sc2 - i);
			}
			for(int i = sc2 - 1; i>=0; i--){
				result += ((int)str.charAt(i) - 48) * Math.pow(10, sc2 - 1 - i);
			}
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
		int a = 0;
		for(int i = mem - 1; (!(sc1.equals("+")|sc1.equals("-")|sc1.equals("*")|sc1.equals("/"))) & i != -1; i--){
			sc1 = sums.substring(i, i + 1);
			a = i;
		}
		return toInt(sums.substring(a, mem), 0, sums.substring(a, mem).length());
	}
	static double num2(){
		sc1 = "0";
		int a = 0;
		for(int i = mem + 1; (!(sc1.equals("+")|sc1.equals("-")|sc1.equals("*")|sc1.equals("/"))) & i != sums.length(); i++){
			sc1 = sums.substring(i, i + 1);
			a = i;
			System.out.println("56:" + i + ", " + sc1);
		}
		return toInt(sums.substring(mem + 1, a + 1), 0, sums.substring(mem + 1, a + 1).length());
	}
   public static void main(String[] args){
	   System.out.println("Welcome to use Caculator v0.2 by FishBone000.");
	   System.out.println("Type sum down below.");
	   Scanner sum = new Scanner(System.in);
	   sums = sum.nextLine();
	   sc1 = sums.substring(sc2, sc2 + 1);
	   while(!(sc1.equals("+") | sc1.equals("-") | sc1.equals("*") | sc1.equals("/"))){
		   sc2++;
		   sc1 = sums.substring(sc2, sc2 + 1);
	   }
	   mem = sc2;
	   System.out.println(num1() + ", " + num2());
	   System.out.println(Caculate());
   }
}