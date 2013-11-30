package com.CSJE.Sprouts;

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
		String shiftedChild[] = new String[cn.length];

		if (pn.length != cn.length)
			return false;

		// first, we need to shift the order of the child node array to start
		// with the same node number as the parent. There could be up to three
		// possible states, so we need to try all three
		int leadCharCount = 0;
		String leadChar = pn[0];
		for (String c : pn)
			if (c.equals(leadChar))
				leadCharCount++;

		for (int i = 0; i < leadCharCount; i++) {
			int j = i;
			while (!cn[j++].equals(leadChar)) {
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
			if (nodeMatch(pn, shiftedChild))
				return true;
		}

		return false;
	}

	private static boolean nodeMatch(String[] pn, String[] cn) {

		return false;
	}

}