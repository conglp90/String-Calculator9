import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testNull() {
		assertTrue(Calculator.add(null) == 0);
	}

	@Test
	public void testBlankValue() {
		assertTrue(Calculator.add("") == 0);
	}

//	//
	@Test
	public void testWithOneValue() {
		assertTrue(Calculator.add("1") == 1);
		assertTrue(Calculator.add("111") == 111);
	}
//
//	//
	@Test
	public void testUnknownNumberOfValue() {
		assertTrue(Calculator.add("1,2,3,4") == 10);
		assertTrue(Calculator.add("1,2,3,4,5") == 15);
	}
////
////	//
	@Test
	public void testWithNewLine() {
		assertTrue(Calculator.add("1\n2,3,4") == 10);
		assertTrue(Calculator.add("1\n2,3\n4") == 10);
	}
//
////	//
	@Test
	public void testWithChangeDelimiter() {
		assertTrue(Calculator.add("//;\n1\n2;3,4") == 10);
		assertTrue(Calculator.add("//^\n1\n2^3,4") == 10);
		assertTrue(Calculator.add("//[;]\n1\n2;3,4") == 10);
		assertTrue(Calculator.add("//[aaa]\n1\n2aaa3,4") == 10);
	}
//	//
	@Test
	public void testWithNegativeNumber() {
		Exception e = null;
		// ////////////////////////////
		try {
			Calculator.add("-100,-2000,-3,-7");
		} catch (Exception e1) {
			// 
			e = e1;
			System.out.println(e.getMessage());
		}
		assertTrue(e.getMessage().equals("negatives not allowed: -100, -2000, -3, -7"));
		////////////////////////////////
		try {
			Calculator.add("//[a*3a]\n1a*3a-2a*3a3,-7,8,9,70");
		} catch (Exception e1) {
			// TODO: handle exception
			e = e1;
			System.out.println(e.getMessage());
		}
		assertTrue(e.getMessage().equals("negatives not allowed: -2, -7"));
	}
//
	@Test
	public void testWithNumberLagerThan1000() {
		assertTrue(Calculator.add("1,2,3,1004") == 6);
	}
////
	@Test
	public void testWithMultiDelimiter() {
		assertTrue(Calculator.add("//[aaa][bbb][ccc]\n1aaa2bbb3ccc4") == 10);
		assertTrue(Calculator.add("//[a38*][b3*][3c3]\n13c32a38*3b3*4") == 10);
		assertTrue(Calculator.add("//[55erffdsfdsfa(*&%$$$]],]]]]]]]]][s5dfsadf%&^%&][fsget654752574552687$$%$^%@#%&$]\n155erffdsfdsfa(*&%$$$]],]]]]]]]]100s5dfsadf%&^%&900")==1001);
		
	}
}
