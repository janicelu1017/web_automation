package tests;

import org.testng.annotations.Test;

public class Sort {
	// write a program in any language that finds the biggest number in an array and prints it out
	@Test
	public void f() {
		int[] anArray = { 100, 200, 300, 400, 500, 600, 700, 900, 800, 1000 };

		int temp = 0;

		for (int i = 0; i < anArray.length - 1; i++) {
			for (int j = i + 1; j < anArray.length; j++) {
				if (anArray[i] < anArray[j]) {
					temp = anArray[j];
				} else {
					temp = anArray[i];
				}

			}
		}
		
		System.out.println("the biggest number in an array is: " + temp);

	}
}
