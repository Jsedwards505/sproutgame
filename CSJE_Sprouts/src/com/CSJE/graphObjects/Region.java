package com.CSJE.graphObjects;

import java.util.LinkedList;

public class Region {
	private LinkedList<Boundary> boundaries;
	
	public Region() 
	{
		boundaries = new LinkedList<Boundary>();
	}
	
	public void addBoundary(Boundary bound)
	{
		boundaries.add(bound);
	}
	
	public LinkedList<Boundary> getBoundaries()
	{
		return boundaries;
	}

}
