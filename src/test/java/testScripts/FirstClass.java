package testScripts;

import org.testng.annotations.Test;

public class FirstClass {
	
	@Test(groups ="smoke")
	public void TC1() {
		System.out.println("1st test using mvn framework");
	}
	
	@Test
	public void TC2() {
		System.out.println("2nd test using mvn framework");
	}

}
