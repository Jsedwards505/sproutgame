package com.CSJE.graphObjects;

import java.util.Iterator;
import java.util.LinkedList;

public class Boundary implements Iterable<Dot> {
	private LinkedList<Dot> dots = new LinkedList<Dot>();
	private boolean shell;
	
	public boolean isShell() {
		return shell;
	}
	public void setShell(boolean shell) {
		this.shell = shell;
	}
	public Boundary()
	{
		dots = new LinkedList<Dot>();
	}
	public Boundary(Boundary b) {
		for(Dot d : b.dots)
		{
			Dot d2 = new Dot(d);
			dots.add(d2);
		}
	}
	public void addDot(Dot dot)
	{
		dots.add(dot);
	
	}
	public int indexOf(Dot dot)
	{
		return dots.indexOf(dot);
	}

	@Override
	public Iterator<Dot> iterator() {
		return dots.iterator();
	}
	public int getLength()
	{
		return dots.size();
	}
	
	public void insert(int index, Dot dot)
	{
		dots.add(index,dot);
	}
	public Dot get(int index)
	{
		return dots.get(index);
	}
	@Override
    public String toString()
	{
		String str=null;
		for(Dot d : dots)
		{
			if(str==null) str = Integer.toString(d.getID());
			else str = str +"-"+d.getID();
		}
		
		return str;
	}
	public int size() {
	return dots.size();
	}

}
