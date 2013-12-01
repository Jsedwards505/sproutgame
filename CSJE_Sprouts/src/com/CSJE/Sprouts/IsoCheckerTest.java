package com.CSJE.Sprouts;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IsoCheckerTest {
	
	String pos1 = "1,2,3,4;5;7/8,9,5,6;4,2;10,11,8/7";
	String pos2 = "7/10,11,8;9,8,5,6;2,4/7;5;1,2,3,4"; //iso to 1
	String pos3 = "7/10,11,8;9,8,5,6;2,4/7;5;3,2,1,4"; //iso to 1
	String pos4 = "7/10,11,8;9,8,5,6;2,4/7;5;3,2,1,7"; //non-iso

	@Before
	public void setUp() throws Exception {
		

	}

	@Test
	public void test() {
		assertTrue(IsoChecker.check(pos1, pos1));
		
		assertTrue(IsoChecker.check(pos1, pos2));
		
		assertTrue(IsoChecker.check(pos1, pos3));
		
		assertTrue(IsoChecker.check(pos3, pos2));
		
		assertFalse(IsoChecker.check(pos1, pos4));
	}

}