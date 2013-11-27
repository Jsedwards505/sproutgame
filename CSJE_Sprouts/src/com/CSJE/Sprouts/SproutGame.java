package com.CSJE.Sprouts;

import java.util.LinkedList;

import com.CSJE.graphObjects.Boundary;
import com.CSJE.graphObjects.Dot;
import com.CSJE.graphObjects.Region;


public class SproutGame {
	
	private SproutGame parent;
	private LinkedList<SproutGame> children;
	
	private LinkedList<Region> regions;
	private String stateString;
	
	public SproutGame(String game, SproutGame parent)
	{
		this.parent = parent;
		stateString = game;
		buildState(game);		
		generateChildren();
	}

	private void generateChildren() {
		//This method will use the rules of the game to create possible children states
	
		//the following loops will attempt to wire every dot to every other dot in the same region.
		for(Region r:regions)
		{
			for(Boundary b1 : r.getBoundaries())
			{
				for(Dot d1 : b1.getDots())
				{
                  //the second 3 loops iterate over ever dot in the game space to select every partner
				  for(Boundary b2 : r.getBoundaries())
				  {
				    for(Dot d2 : b2.getDots())
					{
					  String childString = makeChildString(r,b1,d1,b2,d2);
					  //if this new gamestate is not an isomoprh, add it to children.
					  if(!isomorph(childString)) children.add(new SproutGame(childString,this));
       				}
     			}
		     }	
	      }
	 }
}
    //Chris: Implement this?
	private boolean isomorph(String childString) {
		//determine if things are isomorphs in some clever way?
		//access to children if needed (like to compare a new gamestate with an old one...)
		//if childstring in children, don't make it kind of thing.
		return false;
	}

	private String makeChildString(Region r1, Boundary b1, Dot d1, Boundary b2, Dot d2)
	{
		String childString = null;
		//magic. Build the child strings.
		if(b1==b2) //if connecting dots in the same boundary
		{
			
		}
		return childString;
	}

	//parses string input into objects
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

	public String toString()
	{
		return stateString;
		
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
		
		SproutGame game = new SproutGame(args[0],null);
	}
	
	//validates the input to the program (not done?)
	private static boolean validGame(String[] game)
	{
        if(game.length!=1) return false;		
		if(game[0]==null || game[0].isEmpty()) return false;
		return true;
	}

}
