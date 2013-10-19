package reprotool.dmodel.extensions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;

/**
 * Based on the Combinadic Algorithm explained by James McCaffrey in the MSDN
 * article titled: "Generating the mth Lexicographical Element of a Mathematical
 * Combination" http://msdn.microsoft.com/en-us/library/aa289166(VS.71).aspx
 * 
 * @author Ahmed Abdelkader Licensed under Creative Commons Attribution 3.0
 *         http://creativecommons.org/licenses/by/3.0/us/
 *         
 * @author Viliam Simko :	reimplemented using BigIntegers, added randomCombinations() which
 * 							generates a random subset of all combinations C(n,k)
 */
final public class Combinations {

	/**
	 * Returns the m-th lexicographic element of combination C(n,k)
	 */
	final public static int[] element(final int n, final int k, final BigInteger m) {
		final int[] ans = new int[k];

		int a = n;
		int b = k;
		BigInteger x = choose(n, k).subtract(m).subtract(BigInteger.ONE); // x is the "dual" of m

		for (int i = 0; i < k; ++i) {
			a = largestV(a, b, x); // largest value v, where v < a and vCb < x
			x = x.subtract(choose(a, b));
			b = b - 1;
			ans[i] = (n - 1) - a;
		}

		return ans;
	}
	
	/**
	 * Useful for small values of m.
	 */
	final private static int[] element(final int n, final int k, final int m) {
		return element(n, k, BigInteger.valueOf(m));
	}

	/**
	 * Returns the largest value v where v < a and Choose(v,b) <= x
	 */
	private static int largestV(final int a, final int b, final BigInteger x) {
		int v = a - 1;

		while (choose(v, b).compareTo(x) > 0)
			--v;

		return v;
	}

	/**
	 * Returns nCk - watch out for overflows
	 */
	final public static BigInteger choose(final int n, final int k) {
		if (n < 0 || k < 0)
			return BigInteger.valueOf(-1);
		if (n < k)
			return BigInteger.ZERO;
		if (n == k || k == 0)
			return BigInteger.ONE;

		final BigInteger delta;
		final int iMax;

		if (k < n - k) {
			delta = BigInteger.valueOf(n - k);
			iMax = k;
		} else {
			delta = BigInteger.valueOf(k);
			iMax = n - k;
		}

		BigInteger ans = delta.add( BigInteger.ONE );

		for (int i = 2; i <= iMax; ++i) {
			final BigInteger ibig = BigInteger.valueOf(i);
			ans = ans.multiply( delta.add(ibig) ).divide(ibig);
		}

		return ans;
	}
	
	final private static Random rnd = new Random();
	final private static BigInteger rndBigInt(final BigInteger max) {
	    do {
	        final BigInteger i = new BigInteger(max.bitLength(), rnd);
	        if (i.compareTo(max) <= 0)
	            return i;
	    } while (true);
	}
	
	// only for debugging purposes
	private static long numCollisions = 0;
	final public long getNumCollisions() {
		return numCollisions;
	}
	
	private static int ESTIMATED_MAX_COLLISIONS_FACTOR = 5;
	
	/**
	 * @author Viliam Simko
	 */
	final public static int[][] randomCombinations(final int n, final int k, int howmany) {
		
		final BigInteger total = choose(n, k);
		final BigInteger totalMinus1 = total.subtract(BigInteger.ONE);
		if(total.compareTo(BigInteger.valueOf(howmany)) < 0)
			howmany = total.intValue();
		
		final int[][] buf = new int[howmany][k];
		int bufidx = 0;

		// las vegas algorithm
		final Set<String> seen = new HashSet<>();
		numCollisions = 0; // for debugging
		final long maxCollisions = howmany * ESTIMATED_MAX_COLLISIONS_FACTOR; // estimated worst case
		while(bufidx < howmany) {
			final int[] subset = element(n, k, rndBigInt(totalMinus1));
			final String subsethash = Arrays.toString(subset);
			if(seen.add(subsethash)) {
				buf[bufidx] = subset;
				bufidx++;
			} else {
				numCollisions++;
				// this prevents infinite loops if the las vegas approach fails
				if(numCollisions > maxCollisions) {
					throw new RuntimeException("Our las vegas algorithm exceeded the estimated worst case scenario where maxCollisions=" + maxCollisions);
				}
			}
		}
		return buf;
	}
	
	/**
	 * @author Viliam Simko
	 */
	final public static List<List<Integer>> randomCombinationsAsList(final int n, final int k, final int howmany) {
		final List<List<Integer>> result = new ArrayList<>();
		
		for(final int[] subset : randomCombinations(n, k, howmany)) {
			final List<Integer> subsetAsList = new ArrayList<>();
			for (final int item : subset) {
				subsetAsList.add(item);
			}
			result.add(subsetAsList);
		}
		
		return result;
	}
	
	final public static <T> List<List<T>> generateCombin(final List<T> allItems, final int k) {
		final int n = allItems.size();
		final int total = choose(n, k).intValue();
		
		final List<List<T>> result = new ArrayList<>();
		
		// systematically generate all combinations COMBIN(N,K)
		for(int m=0; m<total; ++m) {
			final int[] combidx = element(n, k, m); // a single combination of indexes
			
			final List<T> combitem = new ArrayList<>(k);

			for(int i=0; i<k; ++i) {
				combitem.add( allItems.get(combidx[i]) );
			}
			
			result.add(combitem);
		}
		return result;
	}
	
	final public static <T> Iterable<Pair<T, T>> generateCombinPairs(final Iterable<T> allItems) {
		final List<List<T>> combin = generateCombin( IterableExtensions.toList(allItems), 2);
		final List<Pair<T,T>> result = new ArrayList<>();
		
		for (final List<T> pair : combin) {
			result.add(Pair.of(pair.get(0), pair.get(1)));
		}
		return result;
	}
	
	final public static <T> List<List<T>> generateVariations(final List<T> allItems, final int k) {
		List<List<T>> result = new ArrayList<>();
		result.add(new ArrayList<T>());

		for(int i=0; i<k; ++i) {
			final List<List<T>> tmp = new ArrayList<>(k);
			for (final List<T> list : result) {
				for (T item : allItems) {
					final List<T> copyOfList = new ArrayList<>(list);
					copyOfList.add(item);
					tmp.add(copyOfList);
				}
			}
			result = tmp;
		}
		
		return result;
	}
	
	final public static <T> Iterable<Pair<T, T>> generateVariationsPairs(final Iterable<T> allItems) {
		final List<List<T>> variations = generateVariations( IterableExtensions.toList(allItems), 2);
		final List<Pair<T,T>> result = new ArrayList<>();
		
		for (final List<T> pair : variations) {
			result.add(Pair.of(pair.get(0), pair.get(1)));
		}
		return result;
	}
}