package com.CSJE.graphObjects;

public class Dot {
	private int dotID;
	public Dot(int id)
	{
		dotID = id;
	}
	
	public Dot(Dot d) {
		dotID = d.getID();
	}

	public int getID() 
	{
		return dotID;
	}

	
}
