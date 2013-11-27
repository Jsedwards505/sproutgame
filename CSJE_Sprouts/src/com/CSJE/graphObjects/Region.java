package com.CSJE.graphObjects;

import java.util.LinkedList;

public class Region {
	LinkedList<Boundary> boundaries;
	
	public Region() 
	{
		boundaries = new LinkedList<Boundary>();
	}
	
	public void addBoundary(Boundary bound)
	{
		boundaries.add(bound);
	}

}
