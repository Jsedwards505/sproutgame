package com.CSJE.Sprouts;

import java.util.HashSet;
import java.util.LinkedList;

import com.CSJE.Datastructures.Collector;
import com.CSJE.graphObjects.Boundary;
import com.CSJE.graphObjects.Dot;
import com.CSJE.graphObjects.Region;

public class SproutGame {

	private SproutGame parent;
	private LinkedList<SproutGame> children = new LinkedList<SproutGame>();

	private LinkedList<Region> regions = new LinkedList<Region>();
	private String stateString;
	private Collector<Dot> dotOccurances = new Collector<Dot>();
	private HashSet<Dot> freeDots = new HashSet<Dot>();
	private int dotCount = 0;

	public SproutGame(String game, SproutGame parent) {
		this.parent = parent;
		stateString = game;
		buildState(game);
		// generateChildren();
	}

	private void generateChildren() {
		// This method will use the rules of the game to create possible
		// children states

		// the following loops will attempt to wire every dot to every other dot
		// in the same region.
		for (Region r : regions) {
			for (Boundary b1 : r.getBoundaries()) {
				for (Dot d1 : b1) {
					// the second 3 loops iterate over ever dot in the game
					// space to select every partner
					for (Boundary b2 : r.getBoundaries()) {
						for (Dot d2 : b2) {
							
							if(freeDots.contains(d1)&&freeDots.contains(d2))
							{
							String childString = makeChildString(r, b1, d1, b2,
									d2);
							// if this new gamestate is not an isomoprh, add it
							// to children.
							if (!isomorph(childString))
								children.add(new SproutGame(childString, this));
							}
						}
					}
				}
			}
		}
	}

	// Justin: WIP - do not use yet.
	private boolean isomorph(String childString) {
		// determine if things are isomorphs in some clever way.
		
		return IsoChecker.check(stateString, childString);
	}

	/*
	 * Hi Justin! If possible: When producing the String, try and order things
	 * with the largest collection first. For example:
	 * 1,2,3,4,5;1,3,4;22,17,9/4,7,12,33,12;17,10/17,16 is preferrable order
	 * over 17,16/17,10;4,7,12,33,12/1,2,3,4,5;1,3,4;22,17,9 This will make
	 * isomorphism checks go faster. Thanks!
	 */
	private String makeChildString(Region r1, Boundary b1, Dot d1, Boundary b2,
			Dot d2) {
		LinkedList<Region> graph = new LinkedList<Region>(regions);
		String childString = null;
		
		if (b1 == b2) // if connecting dots in the same boundary
		{
			Region newR = new Region();
			Boundary newB = new Boundary();
			newB.setShell(true);
			copyFromTo(b1,d1,d2,newB);

			//more stuff need clarification

		} else // connecting dots in another boundary.
		{
			Dot mid = new Dot(dotCount++);

		}
		return childString;
	}


	private void copyFromTo(Boundary b1, Dot d1, Dot d2, Boundary newB) {
		boolean copy = false;
		for(Dot d : b1)
		{
		  if(d==d1) copy=true;
		  if(copy) newB.addDot(new Dot(d.getID()));  
		  if(d==d2) return;
		}
		
	}

	private void insertAfter(Boundary b, Dot insert, Dot after) {
		for (int i = 0; i < b.getLength(); i++) {
			if (b.get(i) == after) {
				b.insert(i, insert);
			}

		}
	}

	// parses string input into objects
	private void buildState(String game) {

		Region activeRegion = new Region();
		Boundary activeBoundary = new Boundary();

		activeRegion.addBoundary(activeBoundary);
		regions.add(activeRegion);

		for (int i = 0; i < game.length(); i++) {
			if (game.charAt(i) == '/') {
				activeRegion = new Region();
				activeBoundary = new Boundary();
				activeRegion.addBoundary(activeBoundary);
				regions.add(activeRegion);
			} else if (game.charAt(i) == ';') {
				activeBoundary = new Boundary();
				activeRegion.addBoundary(activeBoundary);
			} else if (game.charAt(i) == '-')
				; // do nothing
			else {
				Dot d = new Dot(dotCount++);
				dotOccurances.add(d); // add new dot to tally
				// add it to freedots
				if (dotOccurances.getCount(d) < 3)
					freeDots.add(d);
				else
					freeDots.remove(d); // unless it's full.
				activeBoundary.addDot(d);
			}
		}
	}

	public String toString() {
		return stateString;

	}

	/**
	 * @param args
	 *            - String defining a game state.
	 * @throws CloneNotSupportedException
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		// This method will validate the args and build a new SproutsGame object
		// with those arguments.
		if (!validGame(args)) // if the input is invalid, blow up.
		{
			System.out.println("Invalid Input");
			// return;
		}

		SproutGame game = new SproutGame("A,B;C,D/E,F", null); // (args[0],null);
		
		
	}
	public LinkedList<Region> getRegions()
	{
		return regions;
	}

	// validates the input to the program (not done?)
	private static boolean validGame(String[] game) {
		if (game.length != 1)
			return false;
		if (game[0] == null || game[0].isEmpty())
			return false;
		return true;
	}
}