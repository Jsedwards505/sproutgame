package com.CSJE.graphObjects;

import java.util.Iterator;
import java.util.LinkedList;

public class Region implements Iterable<Boundary> {
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

	@Override
	public Iterator iterator() {
		return boundaries.iterator();
	}

}
