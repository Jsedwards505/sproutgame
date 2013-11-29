package com.CSJE.graphObjects;

import java.util.Iterator;
import java.util.LinkedList;

public class Boundary implements Iterable<Dot> {
	private LinkedList<Dot> dots;
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
	public void addDot(Dot dot)
	{
		dots.add(dot);
	}
/*	public LinkedList<Dot> getDots()
	{
		return dots;
	}
	*/ 
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

}
