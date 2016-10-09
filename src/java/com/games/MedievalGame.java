/**
 * 
 */
package com.games;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author attilio
 *
 */
public class MedievalGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 1) {
			File file = new File(args[0]);
			try {
				Scanner sc = new Scanner(file);
				sc.useDelimiter("\\n");
				int numberOfTestCases = sc.nextInt();		
				// no validation on the input data
				MedievalGame game = new Answer2().new MedievalGame();
				for (int currentTestCase = 1; currentTestCase <= numberOfTestCases; ++currentTestCase) {
					String firstLine = sc.next();
					String secondLine = sc.next();
					String[] firstLineSplitted = firstLine.split("\\s+");
					String[] secondLineSplitted = secondLine.split("\\s+");
					int maxCannonBallsFiredWithoutPermutations = game.printMaxCannonBallsFiredWithoutPermutations(currentTestCase, firstLineSplitted[0],
							firstLineSplitted[1], secondLineSplitted);
					int maxCannonBallsFiredWithPermutations = game.printMaxCannonBallsFiredWithPermutations(currentTestCase, firstLineSplitted[0],
							secondLineSplitted);
					if (maxCannonBallsFiredWithoutPermutations != 
							maxCannonBallsFiredWithPermutations)
						System.out.println("ERROR IN THE ALGORITHM");
					else	
						System.out.println("ALGORITHM WELL DONE");
				}	
				sc.close();
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else System.out.println("Usage: java answer2.class fullpath_filename");

	}

	private boolean[] cannonsArray;
	private TreeMap<Integer, Integer> solutions = new TreeMap<>();
	// StdOut 
	private final PrintStream STDOUT = System.out;

	// Print the maximum number of cannonballs that can be fired after Y assassinations
	// Given Y number of firers to be assassinated 
	// Given X starting cannons/firers
	// Given the positions of the firers to be assassinated in ascending order {Zi}
	// After mathematical calculation, the maximum number of cannonballs can be calculated
	// by the following formula:
	// Y*(X-1) - SUM(1 to Y-1){Zi}
	// Y = numberOfFirersAssassinated
	// X = numberOfCannons
	// Zi = indexOfFirersAssassinated
	public int printMaxCannonBallsFiredWithoutPermutations(int currentTestCase,
			String cannons, 
			String firersAssassinated, 
			String[] indexesOfFirersAssassinated) {
		int numberOfCannons = convertToInt(cannons);
		int numberOfFirersAssassinated = convertToInt(firersAssassinated);
		// the second line of the input is separated by a WhiteSpace 
		int sumIndexesOfFirersAssassinated = sum(indexesOfFirersAssassinated);

		int maxNumCannonBalls = numberOfFirersAssassinated * (numberOfCannons -1) - sumIndexesOfFirersAssassinated; 
		STDOUT.printf("Case %d: %d\n", currentTestCase, maxNumCannonBalls);
		return maxNumCannonBalls;
	}

	public int printMaxCannonBallsFiredWithPermutations(int currentTestCase,
			String cannons, 
			String[] indexesOfFirersAssassinated) {
		int numberOfCannons = convertToInt(cannons);

		Integer[] arrayFirersAssassinated = convertArrayFirersAssassinated(indexesOfFirersAssassinated);
		cannonsArray = new boolean[numberOfCannons];
		permute(Arrays.asList(arrayFirersAssassinated), 0);
		int maxNumCannonBallsFired = solutions.descendingKeySet().first();
		STDOUT.println("The maximum number of cannon balls fired with permutations is: " + maxNumCannonBallsFired);
		return maxNumCannonBallsFired;
	}

	private Integer[] convertArrayFirersAssassinated(String[] indexesOfFirersAssassinated){
		Integer[] arrayFirersAssassinated = new Integer[indexesOfFirersAssassinated.length];
		for (int i =0; i < arrayFirersAssassinated.length; ++i) arrayFirersAssassinated[i] = convertToInt(indexesOfFirersAssassinated[i]);
		return arrayFirersAssassinated;
	}

	private void resetCannons() {
		for (int i = 0; i < cannonsArray.length; ++i) cannonsArray[i]=false;
	}

	private void permute(java.util.List<Integer> arr, int k){
		for(int i = k; i < arr.size(); i++){
			java.util.Collections.swap(arr, i, k);
			permute(arr, k+1);
			java.util.Collections.swap(arr, k, i);
		}

		if (k == arr.size() -1){
			resetCannons();
			//int max = 0;
			int counter = 0;
			for (Integer idxCannons : arr) { 
				int idx = idxCannons - 1;
				cannonsArray[idx] = true;
				int q = idx-1; 

				while (q > -1 && !cannonsArray[q]) {
					counter++;
					--q;
				}
				q = idx+1;
				while (q < cannonsArray.length && !cannonsArray[q]) {
					counter++;
					++q;
				}
			}
			solutions.put(counter, counter);
			//STDOUT.println(Arrays.toString(arr.toArray()));
		}
	}

	// It is enough simple integer value for this function because 
	// 1 <= X <= 1000
	// 1 <= Y <= 10
	private int sum(String[] indexesOfFirersAssassinated) {
		int sum = 0;
		for (int i = 0; i < indexesOfFirersAssassinated.length - 1; ++i) {
			sum += convertToInt(indexesOfFirersAssassinated[i]);
		}
		return sum;
	}

	private int convertToInt(String number) {
		return Integer.parseInt(number);
	}

}
}
