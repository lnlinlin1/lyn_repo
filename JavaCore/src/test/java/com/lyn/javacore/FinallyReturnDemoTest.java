package com.lyn.javacore;

import org.junit.BeforeClass;
import org.junit.Test;

public class FinallyReturnDemoTest {
	
	private static FinallyReturnDemo demo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		demo = new FinallyReturnDemo();
	}

	@Test
	public final void testReturnMethod() {
		assert demo.returnMethod() == 1;
	}

	@Test
	public final void testReturnMethodWithException() {
		assert demo.returnMethodWithException() == 2;
	}

	@Test
	public final void testReturnMethodWithTryCatch() {
		assert demo.returnMethodWithTryCatch() == -3;
	}

	@Test
	public final void testReturnMethodWithTryCatchFinally() {
		assert demo.returnMethodWithTryCatchFinally() == 44;
	}

}
