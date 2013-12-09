package com.CSJE.Datastructures;

import java.util.HashMap;

public class Collector<T> {
	
	HashMap<T,Integer> collection = new HashMap<T,Integer>();
	
	public int getCount(T o)
	{
		if(!collection.containsKey(o)) return 0;
		return collection.get(o).intValue();
	}
	public void add(T o)
	{
		if(!collection.containsKey(o)) collection.put(o, new Integer(1));
		else collection.put(o, collection.get(o).intValue()+1);
	}
	public boolean contains(T o) {
		
		return collection.containsKey(o);
	}
}
