package com.lyn.designpattern.singleton;

import org.junit.Test;

public class EnumSingletonTest {

	@Test
	public void testGetRandomNumber() {
		
		int randomNumer = EnumSingleton.INSTANCE.getRandomNumber();
		assert randomNumer < 10 && randomNumer >= 0;
		
	}

}
