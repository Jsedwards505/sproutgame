package com.CSJE.graphObjects;

import java.util.LinkedList;

public class Boundary {
	LinkedList<Dot> dots;
	
	public Boundary()
	{
		dots = new LinkedList<Dot>();
	}
	public void addDot(Dot dot)
	{
		dots.add(dot);
	}

}
