package edu.iastate.cs228.hw2;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 *  
 * @author Jacob Huseman
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter 
{

	  // Other private instance variables if needed
	  private int n = points.length;
	  private int m = (n / 2);

	  /**
	   * Constructor takes an array of points.  It invokes the superclass constructor, and also set the instance variables
	   * algorithm in the superclass.
	   *
	   * @param pts input array of integers
	   */
	  public MergeSorter(Point[] pts) 
	  {
	    super(pts);
	    algorithm = Algorithm.MergeSort;
	  }


	  /**
	   * Perform mergesort on the array points[] of the parent class AbstractSorter.
	   */
	  @Override
	  public void sort() 
	  {
	    mergeSortRec(points);
	  }


	  /**
	   * This is a recursive method that carries out mergesort on an array pts[] of points. One way is to make copies of the
	   * two halves of pts[], recursively call mergeSort on them, and merge the two sorted subarrays into pts[].
	   *
	   * @param pts point array
	   */
	  private void mergeSortRec(Point[] pts) 
	  {
	    int len = pts.length;
	    if(len <= 1) 
	    {
	      return;
	    }
	    int mid = len / 2;
	    Point[] subL = new Point[mid];
	    Point[] subR = new Point[len - mid];
	    int i = 0;
	    // copy half elements into subL
	    while(i < mid) 
	    {
	      subL[i] = pts[i];
	      i++;
	    }
	    // copy remaining elements into subR
	    while(i < len) 
	    {
	      subR[i - mid] = pts[i];
	      i++;
	    }

	    mergeSortRec(subL); // recursive call on subL
	    mergeSortRec(subR); // recursive call on subR

	    int x = 0;
	    int y = 0;
	    i = 0;
	    // subL and subR are individually sorted
	    // merge subL and subR to get a sorted array
	    while(x < mid && y < (len - mid))
	    {
	      if(subL[x].compareTo(subR[y]) <= 0) 
	      {
	        pts[i] = subL[x];
	        x++;
	      } 
	      else 
	      {
	        pts[i] = subR[y];
	        y++;
	      }
	      i++;
	    }
	    while(x < mid) 
	    {
	      pts[i] = subL[x];
	      x++;
	      i++;
	    }
	    while(y < (len - mid))
	    {
	      pts[i] = subR[y];
	      y++;
	      i++;
	    }
	  }
	}
