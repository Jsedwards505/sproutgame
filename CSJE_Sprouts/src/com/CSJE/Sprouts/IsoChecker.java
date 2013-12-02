package com.CSJE.Sprouts;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Responsible for checking the isomorphic properties of two game states in
 * Sprouts
 * 
 * @author Chris
 * 
 */
public class IsoChecker {

	/**
	 * This method will accept two game state String representations and
	 * determine whether they are isomorphically equivalent to each other
	 * 
	 * @param parent
	 *            - parent Game State string
	 * @param child
	 *            - child game state string
	 * @return true if child is isomorphically equivalent to the parent. False,
	 *         if not
	 */
	public static boolean check(String parent, String child) {

		String pr[] = parent.split("/");
		String cr[] = child.split("/");
		boolean matchFound = false;

		// if # of Regions don't match, not an isomorph
		if (pr.length != cr.length)
			return false;

		for (int i = 0; i < pr.length; i++) {
			for (int j = 0; j < cr.length; j++) {
				if (!matchFound)
					if (pr[i].length() == cr[j].length())
						if (regionMatch(pr[i], cr[j]))
							matchFound = true;
			}
			if (!matchFound)
				return false;
			else
				matchFound = false;
		}

		return true;
	}

	private static boolean regionMatch(String pr, String cr) {
		String pb[] = pr.split(";");
		String cb[] = cr.split(";");
		boolean matchFound = false;

		if (pb.length != cb.length)
			return false;

		for (int i = 0; i < pb.length; i++) {
			for (int j = 0; j < cb.length; j++) {
				if (!matchFound)
					if (pb[i].length() == cb[j].length())
						if (boundaryMatch(pb[i], cb[j]))
							matchFound = true;
			}
			if (!matchFound)
				return false;
			else
				matchFound = false;
		}
		return true;
	}

	private static boolean boundaryMatch(String pb, String cb) {
		String pn[] = pb.split(",");
		String cn[] = cb.split(",");
		boolean matchFound = false;
		String shiftedParent[] = pn.clone();
		String shiftedChild[] = new String[cn.length];

		if (pn.length != cn.length)
			return false;
		
		if (pn.length == 1){
			if(pn[0].equals(cn[0])) return true;
			else return false;
		}

		// first, we need to shift the order of the child node array to start
		// with the same node number as the parent. There could be up to three
		// possible states, so we need to try all three. And we need to try this
		// for every

		for (int p = 1; p < pn.length; p++) {
			int leadCharCount = 0;
			String leadChar = shiftedParent[0];
			for (String c : shiftedParent)
				if (c.equals(leadChar))
					leadCharCount++;

			for (int i = 0; i < leadCharCount; i++) {
				int j = i;
				while (!cn[j].equals(leadChar)) {
					j++;
					if (j == cn.length)
						break;
				}
				int sci = 0;
				for (int k = j; k < cn.length; k++)
					shiftedChild[sci++] = cn[k];
				if (j != 0) {
					for (int k = 0; k < j; k++)
						shiftedChild[sci++] = cn[k];
				}
				if (nodeMatch(copyOf(shiftedParent), copyOf(shiftedChild)))
					return true;
			}
			// shift the parent
			int pci = 0;
			for (int i = p; i < pn.length; i++) {
				shiftedParent[pci++] = pn[i];
			}
			for (int k = 0; k < p; k++) {
				shiftedParent[pci++] = pn[k];
			}

		}

		return false;
	}

	private static boolean nodeMatch(String[] pn, String[] cn) {
		ArrayList<String> pnl = new ArrayList<String>();
		ArrayList<String> cnl = new ArrayList<String>();

		for (int i = 0; i < pn.length; i++) {
			if (!pn[i].equals(cn[i])) {
				pnl.add(pn[i]);
				cnl.add(cn[i]);
			}
		}
		if (pnl.size() == pn.length)
			return false;
		if (pnl.size() == 0)
			return true;

		String source = pnl.get(0);
		String target = cnl.get(0);
		int targetCount = 0;

		for(int i = 0; i < pnl.size(); i++){
			if(pnl.get(i).equals(source))
					pnl.set(i, target);
			else if (pnl.get(i).equals(target)){
				pnl.set(i, source);
				targetCount++;
			}
		}
		
		if (targetCount == 0)
			return false;

		String[] np = new String[pnl.size()];
		String[] nc = new String[cnl.size()];
		for (int i = 0; i < pnl.size(); i++) {
			np[i] = pnl.get(i);
			nc[i] = cnl.get(i);
		}
		return nodeMatch(np, nc);
	}

	private static String[] copyOf(String[] s) {
		String[] copy = new String[s.length];

		for (int i = 0; i < s.length; i++)
			copy[i] = new String(s[i]);

		return copy;
	}

}
