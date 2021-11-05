package edu.iastate.cs228.hw2;

import java.io.File;

/**
 *  
 * @author Jacob Huseman
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 
import java.util.Arrays;


public class CompareSorters 
{

	  private static Scanner input = new Scanner(System.in);
	  private static Random rand = new Random();
	  private static int numPts = 0;
	  private static File f = null;
	  private static String fileName = "";
	  private static int order;


	  /**
	   * Repeatedly take integer sequences either randomly generated or read from files. Use them as coordinates to
	   * construct points.  Scan these points with respect to their median coordinate point four times, each time using a
	   * different sorting algorithm.
	   *
	   * @param args
	   **/
	  public static void main(String[] args) throws FileNotFoundException 
	  {
	    System.out.println("Performances of Four Sorting Algorithms in Point Scanning\n");
	    System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
	    PointScanner[] scanners = new PointScanner[4];
	    int trial = 1;
	    int selection;

	    while(true) 
	    {
	      System.out.print("Trial " + trial + ": ");
	      selection = input.nextInt();
	      
	      if(selection < 1 || selection > 3) 
	      {
	        continue;
	      } 
	      else if(selection == 3) 
	      {
	        System.exit(0);
	      } 
	      else if(selection == 1) 
	      {
	        System.out.print("Enter number of random points: ");
	        numPts = input.nextInt();
	        Point[] pts = generateRandomPoints(numPts, rand);
	        scanners[0] = new PointScanner(Arrays.copyOf(pts, pts.length), Algorithm.InsertionSort);
	        scanners[1] = new PointScanner(Arrays.copyOf(pts, pts.length), Algorithm.MergeSort);
	        scanners[2] = new PointScanner(Arrays.copyOf(pts, pts.length), Algorithm.QuickSort);
	        scanners[3] = new PointScanner(Arrays.copyOf(pts, pts.length), Algorithm.SelectionSort);
	      } 
	      else 
	      { // selection == 2
	        System.out.println("Points from a file");
	        System.out.print("File name: ");
	        String fileName = input.next();
	        scanners[0] = new PointScanner(fileName, Algorithm.InsertionSort);
	        scanners[1] = new PointScanner(fileName, Algorithm.MergeSort);
	        scanners[2] = new PointScanner(fileName, Algorithm.QuickSort);
	        scanners[3] = new PointScanner(fileName, Algorithm.SelectionSort);
	      }
	      System.out.println("\nalgorithm\t\tsize\t\ttime (ns)");
	      System.out.println("---------------------------------------");
	      for(PointScanner scanner : scanners) 
	      {
	        scanner.scan();
	        System.out.println(scanner.stats());
	        scanner.writeMCPToFile();
	      }
	      System.out.println("---------------------------------------\n");
	      trial++;
	    }
	  }

	  // For each input of points, do the following.
	  //
	  //     a) Initialize the array scanners[].
	  //
	  //     b) Iterate through the array scanners[], and have every scanner call the scan()
	  //        method in the PointScanner class.
	  //
	  //     c) After all four scans are done for the input, print out the statistics table from
	  //		  section 2.
	  //
	  // A sample scenario is given in Section 2 of the project description.


	  /**
	   * This method generates a given number of random points. The coordinates of these points are pseudo-random numbers
	   * within the range [-50,50] [-50,50]. Please refer to Section 3 on how such points can be generated.
	   * <p>
	   * Ought to be private. Made public for testing.
	   *
	   * @param numPts number of points
	   * @param rand   Random object to allow seeding of the random number generator
	   * @throws IllegalArgumentException if numPts < 1
	   */
	  private static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException 
	  {
	    if(numPts < 1) 
	    {
	      throw new IllegalArgumentException("numPoints < 1");
	    }

	    Point[] points = new Point[numPts];

	    for(int i = 0; i < numPts; i++) 
	    {
	      points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
	    }

	    return points;
	  }

	}
