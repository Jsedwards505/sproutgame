package com.CSJE.Sprouts;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IsoCheckerTest {

	@Before
	public void setUp() throws Exception {
		
		String pos1 = "1,2,3,4;5;7/8,9,5,6;4,2;10,11,8/7";
		String pos2 = "7/10,11,8;9,8,5,6;2,4/7;5;1,2,3,4"; //iso to 1
		String pos3 = "7/10,11,8;9,8,5,6;2,4/7;5;3,2,1,4";
		String pos4 = "";
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

//1,2,3,4
//1,4,3,2