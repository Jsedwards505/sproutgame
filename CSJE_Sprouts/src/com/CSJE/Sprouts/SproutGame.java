package com.CSJE.Sprouts;

import java.util.LinkedList;

import com.CSJE.graphObjects.Region;


public class SproutGame {
	
	LinkedList<Region> regions;
	
	public SproutGame(String game)
	{
		
		
		
	}

	/**
	 * @param args - String defining a game state.
	 */
	public static void main(String[] args) {
		//This method will parse the args and build a new SproutsGame object
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
