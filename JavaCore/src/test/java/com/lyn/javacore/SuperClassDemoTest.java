package com.lyn.javacore;


import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @BeforeClass annotated method should be static method. run one time for whole test case suite
 * @Before annotated method should be a instance method. run one time for each test case in the suite
 * @author lenovo
 *
 */
public class SuperClassDemoTest {

	private static SuperClassDemo demo = null;
	
	@BeforeClass
	public static void init(){
		System.out.println("!!!");
		demo = new SuperClassDemo();
	}
	
//	private SuperClassDemo demo = null;
//	@Before
//	public void init(){
//		demo = new SuperClassDemo();
//	}
	
	@Test
	public void testGetClassName() {
		assert "com.lyn.javacore.SuperClassDemo".equals(demo.getClassName());
	}

	@Test
	public void testGetSuperClassName() {
		assert "java.util.Date".equals(demo.getSuperClassName());
	}

}
