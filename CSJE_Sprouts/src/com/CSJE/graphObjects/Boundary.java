package com.CSJE.graphObjects;

import java.util.LinkedList;

public class Boundary {
	private LinkedList<Dot> dots;
	
	public Boundary()
	{
		dots = new LinkedList<Dot>();
	}
	public void addDot(Dot dot)
	{
		dots.add(dot);
	}
	public LinkedList<Dot> getDots()
	{
		return dots;
	}

}
