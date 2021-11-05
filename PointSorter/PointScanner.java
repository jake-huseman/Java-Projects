package edu.iastate.cs228.hw2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Jacob Huseman
 *
 */



/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner 
{

	  private Point[] points;

	  private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of
	  // the x coordinates and y coordinates of those points in the array points[].
	  private Algorithm sortingAlgorithm;

	  private String stats = "";

	  protected long scanTime;         // execution time in nanoseconds.

	  private String fileName;

	  AbstractSorter aSorter = null;

	  /**
	   * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy the points into
	   * the array points[].
	   *
	   * @param pts input array of points
	   * @throws IllegalArgumentException if pts == null or pts.length == 0.
	   */
	  public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException 
	  {
	    this.points = pts;
	    this.sortingAlgorithm = algo;
	  }


	  /**
	   * This constructor reads points from a file.
	   *
	   * @param inputFileName
	   * @throws FileNotFoundException
	   * @throws InputMismatchException if the input file contains an odd number of integers
	   */
	  protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException 
	  {
	    sortingAlgorithm = algo;
	    fileName = inputFileName;
	    ArrayList<Point> temp = new ArrayList<>();
	    File f = new File(inputFileName);
	    Scanner scan = new Scanner(f);

	    while(scan.hasNextInt()) 
	    {
	      try 
	      {
	        temp.add(new Point(scan.nextInt(), scan.nextInt()));
	      } 
	      catch(NullPointerException e) 
	      {
	        throw new InputMismatchException("input from file incorrect");
	      }
	    }
	    points = new Point[temp.size()];
	    this.points = temp.toArray(points);
	    scan.close();
	  }


	  /**
	   * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:
	   * <p>
	   * a) Sort points[] by the x-coordinate to get the median x-coordinate. b) Sort points[] again by the y-coordinate to
	   * get the median y-coordinate. c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.
	   * <p>
	   * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter, or
	   * QuickSorter to carry out sorting.
	   *
	   * @param algo
	   * @return
	   */
	  public void scan() 
	  {
	    if(sortingAlgorithm == Algorithm.InsertionSort) 
	    {
	      this.aSorter = new InsertionSorter(points);
	    }
	    if(sortingAlgorithm == Algorithm.MergeSort) 
	    {
	      this.aSorter = new MergeSorter(points);
	    }
	    if(sortingAlgorithm == Algorithm.QuickSort) 
	    {
	      this.aSorter = new QuickSorter(points);
	    }
	    if(sortingAlgorithm == Algorithm.SelectionSort) 
	    {
	      this.aSorter = new SelectionSorter(points);
	    }
	    int medianCoordinatePointX = Integer.MIN_VALUE;
	    int medianCoordinatePointY = Integer.MIN_VALUE;

	    long start = System.nanoTime();
	    for(int i = 0; i < 2; i++) // two rounds of sorting
	    {
	      aSorter.setComparator(i); // a
	      aSorter.sort(); // b
	      if (i == 0) 
	      {
	        medianCoordinatePointX = aSorter.getMedian().getX();
	      } 
	      else 
	      {
	        medianCoordinatePointY = aSorter.getMedian().getY();
	      }
	    }

	    medianCoordinatePoint = new Point(medianCoordinatePointX, medianCoordinatePointY);
	    long end = System.nanoTime();
	    scanTime = end - start; // e - sum up the time spent

	    // create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two
	    // rounds of sorting, have aSorter do the following:
	    //
	    //     a) call setComparator() with an argument 0 or 1.
	    //
	    //     b) call sort().
	    //
	    //     c) use a new Point object to store the coordinates of the medianCoordinatePoint
	    //
	    //     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
	    //
	    //     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime.

	  }


	  /**
	   * Outputs performance statistics in the format:
	   * <p>
	   * <sorting algorithm> <size>  <time>
	   * <p>
	   * For instance,
	   * <p>
	   * selection sort   1000	  9200867
	   * <p>
	   * Use the spacing in the sample run in Section 2 of the project description.
	   */
	  public String stats() 
	  {
	    String size = String.valueOf(points.length);
	    return sortingAlgorithm + "\t\t" + size + "\t\t" + scanTime;
	  }


	  /**
	   * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed
	   * on the same line with exactly one blank space in between.
	   */
	  @Override
	  public String toString() 
	  {
	    return "MCP: (" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")";
	  }


	  /**
	   * This method, called after scanning, writes point data into a file by outputFileName. The format of data in the file
	   * is the same as printed out from toString().  The file can help you verify the full correctness of a sorting result
	   * and debug the underlying algorithm.
	   *
	   * @throws FileNotFoundException
	   */
	  public void writeMCPToFile() throws FileNotFoundException 
	  {
	    fileName = aSorter.algorithm.name() + ".txt";
	    try 
	    {
	      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	      for(Point point : points) 
	      {
	        writer.write(point.getX() + ", " + point.getY() + "\n");
	      }
	      writer.close();
	    } 
	    catch (IOException e)
	    {
	      System.out.println("Could not write to file");
	      e.printStackTrace();
	    }
	  }
	}
