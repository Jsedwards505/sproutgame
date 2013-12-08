package com.CSJE.graphObjects;

import java.util.Iterator;
import java.util.LinkedList;

public class Region implements Iterable<Boundary> {
	private LinkedList<Boundary> boundaries = new LinkedList<Boundary>();;
	
	public Region() 
	{
		boundaries = new LinkedList<Boundary>();
	}
	
	public Region(Region r)
	{
		for(Boundary b : r.getBoundaries())
		{
			Boundary b2 = new Boundary(b); 
			boundaries.add(b2);
		}
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
	@Override
	public String toString()
	{
		String str = null;
		for(Boundary b : boundaries)
		{
			if(str==null) str = b.toString();
			else str = str+";"+ b.toString();
		}
		return str;
	}
	
	public Boundary getBoundary(int index)
	   {
		   return boundaries.get(index);
	   }

	public int indexOf(Boundary b2) {
		return boundaries.indexOf(b2);
		
	}
	public void removeBoundary(Boundary b)
	{
		boundaries.remove(b);
	}
	   

}
