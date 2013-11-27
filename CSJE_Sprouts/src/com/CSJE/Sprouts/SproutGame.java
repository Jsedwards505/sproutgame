package com.CSJE.Sprouts;

import java.util.LinkedList;

import com.CSJE.graphObjects.Boundary;
import com.CSJE.graphObjects.Dot;
import com.CSJE.graphObjects.Region;


public class SproutGame {
	
	SproutGame parent;
	LinkedList<SproutGame> children;
	
	LinkedList<Region> regions;
	String generatingString;
	
	public SproutGame(String game)
	{
		generatingString = game;
		buildState(game);		
	}

	private void buildState(String game) {
		
		Region activeRegion = new Region();
		Boundary activeBoundary = new Boundary();
		
		activeRegion.addBoundary(activeBoundary);
		regions.add(activeRegion);
		
		for(int i = 0; i<game.length(); i++)
		{
		  if(game.charAt(i)=='/')
		  {
			  activeRegion = new Region();
			  activeBoundary = new Boundary();
			  activeRegion.addBoundary(activeBoundary);
			  regions.add(activeRegion);
		  }
		  else if(game.charAt(i)==';')
		  {
			  activeBoundary = new Boundary();
			  activeRegion.addBoundary(activeBoundary);
		  }
		  else if(game.charAt(i)==','); //do nothing
		  else
		  {
			activeBoundary.addDot(new Dot(game.charAt(i)));
		  }
		   
		}
	}

	/**
	 * @param args - String defining a game state.
	 */
	public static void main(String[] args) {
		//This method will validate the args and build a new SproutsGame object
		//with those arguments.
		if(!validGame(args)) //if the input is invalid, blow up.
		{
			System.out.println("Invalid Input");
			return; 
		}
		
		SproutGame game = new SproutGame(args[0]);
	}
	
	//validates the input to the program (not done?)
	private static boolean validGame(String[] game)
	{
        if(game.length!=1) return false;		
		if(game[0]==null || game[0].isEmpty()) return false;
		return true;
	}

}
