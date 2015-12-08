package com.itguru.FirstTask.introduction;

public class MathUtils {
	/**
	 * Returns the greatest common divider of given two numbers
	 * 
	 * @param firstNumber
	 *            - positive number
	 * @param secondNumber
	 *            - positive number
	 * @return greatest common divider of two numbers
	 */
	public int getGreatestCommonDivider(int firstNumber, int secondNumber) {
		if (secondNumber == 0) return firstNumber;
		return getGreatestCommonDivider(secondNumber, firstNumber % secondNumber);
	}

	/**
	 * Returns sum of digits of the given number
	 * 
	 * @param number
	 *            - positive number
	 * @return the sum of digits of the given number
	 */
	public int getSumOfDigits(int number) {
		int sum = 0;
		while (number != 0) {
			int digit = number % 10;
			sum += digit;
			number /= 10;
		}
		return sum;
	}

	/**
	 * Checks if the given number is prime or not
	 * 
	 * @param number
	 * @return true - if number is prime, if not return false
	 */
	public boolean isPrime(int number) {
		if (number <= 1) return false;
		for (int i = 2; i <= number / 2; i++)
            if (number % i == 0) return false;
        return true;
	}

	/**
	 * Returns sum of row: 1! - 2! + 3! - 4! + 5! - ... + n!
	 * 
	 * @param n
	 *            - positive number
	 */
	public int getSumOfRow(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if (i % 2 == 0) sum -= factorial(i);
			else sum += factorial(i);
		}
		return sum;
	}
	
	public int factorial(int n) {
		if (n == 0 || n == 1) return 1;
		return n * factorial(n - 1);
	}

	/**
	 * Returns Fibonacci series of a specified length
	 * 
	 * @param length
	 *            - the length of the Fibonacci series
	 * @return array filled with Fibonacci series
	 */
	public int[] getFibonacciSeries(int length) {
		int[] series = new int[length];
		for (int i = 1; i <= length; i++)
			series[i - 1] = getFibonachiNumber(i);
		return series;
	}
	
	public int getFibonachiNumber(int n) {
		if (n == 1 || n == 2) return 1;
		return getFibonachiNumber(n - 1) + getFibonachiNumber(n - 2);
	}

	/**
	 * Returns array with prime numbers
	 * 
	 * @param length
	 *            - the length of prime numbers series
	 * @return array filled with prime numbers
	 */
	public int[] getPrimeSeries(int length) {
		int[] numbers = new int[length];
		int countPrime = 0;
		int number = 1;
		int index = 0;
		while (countPrime < length) {
			if (isPrime(number)) {
				numbers[index++] = number;
				countPrime++;
			}
			number++;
		}
		return numbers;
	}
}