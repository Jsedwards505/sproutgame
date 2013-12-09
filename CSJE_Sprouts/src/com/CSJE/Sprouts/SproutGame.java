package com.CSJE.Sprouts;

import java.util.ArrayList;
import java.util.HashMap;
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
	private static ArrayList<String> uniqueStates = new ArrayList<String>();
	private Collector<Integer> dotOccurances = new Collector<Integer>();
	private HashSet<Integer> freeDots = new HashSet<Integer>();
	private int dotCount = 0;
	private static int nodeCount = 0;

	public SproutGame(String game) throws Exception {
		nodeCount++;
		this.parent = null;
		stateString = game;
		buildState(game);
	    generateChildren();
	}
	public SproutGame(LinkedList<Region>regions, SproutGame parent) throws Exception
	{
		nodeCount++;
		this.parent = parent;
		this.regions = regions;
		stateString = regions.toString();
		populateStructures();
		generateChildren();	
	}
	private void populateStructures() {
		for(Region r : regions)
		{
			for(Boundary b : r)
			{
				for(Dot d : b)
				{
					if(!dotOccurances.contains(d.getID()))dotCount++;
					dotOccurances.add(d.getID());
					if(dotOccurances.getCount(d.getID())>2) freeDots.remove(d.getID());
					else freeDots.add(d.getID());
				}
			}
		}	
	}
	
	


	private void generateChildren() throws Exception {
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
							if (freeDots.contains(d1.getID()) && freeDots.contains(d2.getID())) {
								LinkedList<Region> childState = null;
								if(b1==b2)
									{
									if(d1==d2 && dotOccurances.getCount(d1.getID())>1); //do nothing
									else
									{
									ArrayList<ArrayList<Boundary>> iboundaries = getIncludableBoundaryList(r, b1);
//									System.out.println("Includeable Boundaries: " + iboundaries.size());
									for(ArrayList<Boundary> bs: iboundaries)
									{
									if(d1==d2)childState=joinSameDot(r,b1,d1,bs);
									else childState = joinSameBoundary(r, b1, d1,d2,bs);
									}
									}
									}
								else
								{
									childState = joinUniqueBoundary(r,b1,b2,d1,d2);
								}								
								if (childState!=null)//&&isomorph(childState.toString()) == false )
									children.add(new SproutGame(childState,
											this));
								}
						}
					}
				}
			}
		}
	}

	private LinkedList<Region> joinSameDot(Region r, Boundary b, Dot d,
			ArrayList<Boundary> bs) throws Exception {
		LinkedList<Region> graph = new LinkedList<Region>(regions);
		graph.remove(r);
		//System.out.println("Joisdfn " + d.getID() + " to " + d.getID());
	
			Region outR = new Region(r); //duplicate parent region
			Region inR = new Region();
			//System.out.println("GameState:"+r.toString());

			 int bindex, dindex;
			    bindex = r.indexOf(b);	            
	            dindex = b.indexOf(d);
			
	        //if null blow up
            if(b==null||d==null) throw new Exception();
                        
             
            //find the same dots and boundary
             b  = outR.getBoundary(bindex);
             d = b.get(dindex);
            
            
 			
          
 			//generate the inner boundary.
            Boundary innerBoundary = new Boundary();
            innerBoundary.setShell(true);
            innerBoundary.addDot(new Dot(d.getID())); //add i
            innerBoundary.addDot(new Dot(dotCount)); //add k
            inR.addBoundary(innerBoundary);
            
            //generate outer boundary
           Boundary out = new Boundary(b);
           outR.removeBoundary(b); //remove duplicate boundary.
                             
           if(b.getLength()==1)
           {
        	   out.addDot(new Dot(dotCount));
           }
           else
           {
           
           //add b through i.
           for(int j = 0; j<=dindex; j++)
           {
        	   out.addDot(new Dot(b.get(j).getID())); 
           }
           out.addDot(new Dot(dotCount)); // add k
           //add d through b
           for(int j=dindex; j<b.getLength(); j++) 
           {
        	   out.addDot(new Dot(b.get(j).getID())); 
           }
           }
           
           outR.addBoundary(out);

            for(Boundary ib : bs)
            {
            	//System.out.println("iboundary: " + ib.toString());
            	if(ib!=null)inR.addBoundary(ib);
            }
            
            graph.add(inR);
          	graph.add(outR);
//          	System.out.println(inR.toString());
//          	System.out.println(outR.toString());
//          	System.out.println("child to produce/Regions: " + graph.toString()+"/"+graph.size());
//            
 
			return graph;
	}
	private LinkedList<Region> joinUniqueBoundary(Region r, Boundary b1,
			Boundary b2, Dot d1,Dot d2) throws Exception {
LinkedList<Region> graph = new LinkedList<Region>(regions);
graph.remove(r);
//System.out.println("Unique Join " + d1.getID() + " to " + d2.getID());

	Region newR = new Region(r); //duplicate parent region
//	System.out.println("GameState:"+r.toString());
	 
	//get dot and boundary indicies
	 int d2index,d1index,b1index,b2index;
	 d1index = b1.indexOf(d1);   
	 d2index = b2.indexOf(d2);
	 b1index = r.indexOf(b1);
	 b2index= r.indexOf(b2);
	 
	 //point b1,b2,d1,d2 at duplicate
	 b1 = newR.getBoundary(b1index);
	 b2 = newR.getBoundary(b2index);
	 d1 = b1.get(d1index);
	 d2 = b2.get(d2index);
	 
	 if(b1==null ||b2==null || d1==null || d2 == null) throw new Exception();    //if null blow up
	 
	 Boundary newB = new Boundary();
	 
	 //add start b1 though i
	 for(int i = 0; i<d1index+1; i++)
	 {
		 newB.addDot(b1.get(i));
	 }
	 newB.addDot(new Dot(dotCount+1));	 //add k

     //add j through end b2
     for(int i = d2index; i<b2.size(); i++)
     {
    	 newB.addDot(b2.get(i));	 
     }
//	 add b2 through j
     for(int i = 0; i<d2index+1; i++)
     {
    	 newB.addDot(b2.get(i));
     }
     newB.addDot(new Dot(dotCount+1));	 //add k
     for(int i=d1index; i<b1.size(); i++)
     {
    	 newB.addDot(b1.get(i));
     }
      newR.removeBoundary(b1);
      newR.removeBoundary(b2);
      newR.addBoundary(newB);
      graph.add(newR);
	    
		
return graph;
	}
	// Justin: WIP - do not use yet.
	private boolean isomorph(String childString) {

		// if a match is found, it's an isomorph to something we already have
		for (String s : uniqueStates) {
			if (IsoChecker.check(s, childString) == true)
				return true;
		}

		// otherwise it is unique
		uniqueStates.add(childString);
		return false;
	}

	/*
	 * Hi Justin! If possible: When producing the String, try and order things
	 * with the largest collection first. For example:
	 * 1,2,3,4,5;1,3,4;22,17,9/4,7,12,33,12;17,10/17,16 is preferrable order
	 * over 17,16/17,10;4,7,12,33,12/1,2,3,4,5;1,3,4;22,17,9 This will make
	 * isomorphism checks go faster. Thanks!
	 */
	private LinkedList<Region> joinSameBoundary(Region r, Boundary b, Dot d1,Dot d2,ArrayList<Boundary> iboundaries) throws Exception {
		LinkedList<Region> graph = new LinkedList<Region>(regions);
		graph.remove(r);
//		System.out.println("Join " + d1.getID() + " to " + d2.getID());
	        
		    Region outR = new Region(r);
			Region inR = new Region(); //duplicate parent region

//			System.out.println("GameState:"+r.toString());
			
			
			
			 int bindex,d2index,d1index;
			    bindex = r.indexOf(b);
	            d2index = b.indexOf(d2);
	            d1index = b.indexOf(d1);
			
	        //if null blow up
            if(b==null||d1==null||d2==null) throw new Exception();
                        
            //if d1 further in list than d2 swap them.
            if(d1index>d2index)
            {
            	//
            	int swap;
            	swap = d1index;
            	d1index = d2index;
            	d2index = swap ;
            	
            	Dot dSwap;
            	dSwap = d1;
            	d1 = d2;
            	d2 = dSwap;
            }
            
            //find the same dots and boundary
             b  = outR.getBoundary(bindex);
             d1 = b.get(d1index);
             d2 = b.get(d2index);
            
 			outR.removeBoundary(b); //remove duplicate boundary.
            //generate the inner boundary.
            Boundary innerBoundary = new Boundary();
            innerBoundary.setShell(true);
            innerBoundary.addDot(d1); //add i
            innerBoundary.addDot(new Dot(dotCount)); //add k
            innerBoundary.addDot(d2); //add j
          
            //copy from j through i
            for(int i=d2index-1;i>d1index;i--)
            {
            	innerBoundary.addDot(b.get(i));
            }
            inR.addBoundary(innerBoundary);
            
           
            //generate outer boundary
           Boundary out = new Boundary();
           out.addDot(new Dot(d1.getID())); //add i
           out.addDot(new Dot(dotCount)); // add k
           for(int j = d2index; j<b.getLength(); j++)
           {
        	   out.addDot(new Dot(b.get(j).getID())); //add j-end
           }
           outR.addBoundary(out);

            for(Boundary ib : iboundaries)
            {
//            	System.out.println("iboundary: " + ib.toString());
            	if(ib!=null)inR.addBoundary(ib);
            }
            
            graph.add(inR);
          	graph.add(outR);
//          	System.out.println(inR.toString());
//          	System.out.println(outR.toString());
//          	System.out.println("child to produce/Regions: " + graph.toString()+"/"+graph.size());
            
 
			return graph;
	}

	// parses string input into objects
	private void buildState(String game) {

		Region activeRegion = new Region();
		Boundary activeBoundary = new Boundary();
		HashMap<Character,Integer> dotMap = new HashMap<Character,Integer>();
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
				if(dotMap.containsKey(game.charAt(i)))
				{
					int id = dotMap.get(game.charAt(i));
				
					dotOccurances.add(id);
					activeBoundary.addDot(new Dot(id));
					if (dotOccurances.getCount(id) > 2) freeDots.remove(id);
				}
				else
				{
        		Dot d = new Dot(dotCount++);
        		dotMap.put(game.charAt(i), d.getID());
				dotOccurances.add(d.getID());
				freeDots.add(d.getID());
				activeBoundary.addDot(d);
			}
		}
		}
	}

	public boolean equals(SproutGame game)
	{
		return game.toString().equals(this.toString());
		
	}
	
	public boolean isWin()
	{
		if(children.isEmpty()) return false; //if a leaf return lose
		else
		{
			boolean winning = true;
			//if any child is a loss we are a win.
			for(SproutGame s : children)
			{
				winning = s.isWin()&winning;
			}
			if(!winning) return true; //was any child a loss?
			return false; //we are a lose.
		}
		
	}

	public LinkedList<Region> getRegions() {
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
	@Override
	public String toString()
	{
		String str = null;
		for(Region r : regions)
		{
			if(str==null)str=r.toString();
			else str = str +"/"+r.toString();
		}
		return str;
	}
	private ArrayList<ArrayList<Boundary>> getIncludableBoundaryList(Region r, Boundary b){
		  
		  final int maxBoundaries = 100; //We are only including up to 100 boundaries for efficiency
		  ArrayList<ArrayList<Boundary>> masterList = new ArrayList<ArrayList<Boundary>>();
		  ArrayList<Boundary> currentList = null;
		  
		  
		  //determine how many boundary lists we need
		  LinkedList<Boundary> bList = r.getBoundaries();
		  int count = bList.size() -1;
		  
		  if(count > 0){//If there are actually includable boundaries
		   int boundaryListCount = (int) Math.pow(2, count);
		   for(int i = 1; i <= boundaryListCount; i++){//for all boundary lists we need to create
		    currentList = new ArrayList<Boundary>();
		    int bitPattern = i;
		    for(int j = 0; j < count; j++){//And for all includable boundaries to add to each list
		     if((bitPattern & 0x1) == 1){//we're gonna do a bit check to see whether we are including
		      if(bList.get(j) != b)
		       currentList.add(bList.get(j));
		     }
		     bitPattern = bitPattern >> 1;
		    }
		    masterList.add(currentList);
		   }
		  }
		  masterList.add(new ArrayList<Boundary>()); // add the null case.
		  return masterList;
		 }
	/**
	 * @param args - String defining a game state.
	 * @throws Exception 
	 * 
	 */
	public static void main(String[] args) throws Exception {
		// This method will validate the args and build a new SproutsGame object
		// with those arguments.
		if (!validGame(args)) // if the input is invalid, blow up.
		{
			System.out.println("Invalid Input");
			return;
		}
		SproutGame game = new SproutGame(args[0]);
		System.out.println("Number of nodes is:" + nodeCount + "  is winnable?  " + game.isWin());
	}
}
