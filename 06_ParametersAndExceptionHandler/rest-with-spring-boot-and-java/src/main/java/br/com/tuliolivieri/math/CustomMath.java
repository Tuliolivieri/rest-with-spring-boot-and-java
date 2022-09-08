package br.com.tuliolivieri.math;

public class CustomMath {
	public static double sum(double numberOne,	double numberTwo) {
		return numberOne + numberTwo;
	}
	
	public static double subtraction(double numberOne, double numberTwo) {
		return numberOne -numberTwo;
	}

	public static double multiplication(double numberOne, double numberTwo) {
		return numberOne * numberTwo;
	}

	public static double division( double dividend, double divider) { 
		return dividend /divider;
	}
	
	public static double mean(double numberOne, double numberTwo) {
		
		return (numberOne +numberTwo) / 2;
	}

	public static double sqrt(double number) {
		return Math.sqrt(number);
	}
}
