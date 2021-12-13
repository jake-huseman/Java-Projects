import java.util.ArrayList;

public class JustAnOrdinArrayDay
{
  /**
   * Private constructor disables instantiation.
   */
  private JustAnOrdinArrayDay() {}
  
  /**
   * Determines whether the sub-array beginning at position <code>start</code>
   * is a <em>palindrome</em>.
   * An array is a palindrome if the ordering of elements is the
   * same whether it is read left-to-right or right-to-left.  For example,
   * <ul>
   * <li>isPalindrome([1, 3, 7, 3, 1], 0) returns true
   * <li>isPalindrome([1, 3, 7, 3, 1], 1) returns false
   * <li>isPalindrome([5, 4, 1, 3, 3, 1], 2) returns true
   * <li>isPalindrome([1, 3, 7], 2) returns true
   * </ul>
   * If the given index <code>start</code> is out of range, the method returns true.
   * @param arr
   *   given array
   * @param start
   *   given starting position of subarray to check
   * @return
   *   true if arr[start] through arr[length - 1] is a palindrome
   */
  public static boolean isPalindrome(int[] arr, int start)
  {
	  int count = 0;
	  if(start >= arr.length - 1)
	  {
		  return true;
	  }
		for(int i = start; i < arr.length - 1; i++)
		{
			if(arr[i] == arr[(arr.length - count) - 1])
			{
				count++;
				continue;
			}
		}
		if(count == arr.length - count ||count == arr.length - 1)
		{
			return true;
		}
		else
		{
			return false;
		}
  }

  
  /**
   * Creates an array representing the count of how many values in a given data
   * set fall into each of a specified number of bins,
   * where a bin is a sub-range of values within a given minimum and maximum.  
   * (Such an array is also known as a histogram)
   * The size of each bin is always (max - min) / numBins, where
   * numBins is a parameter representing the number of bins.
   * A number x goes into one of the bins as follows:
   *
   * Bin 0: min <= x < min + binSize
   * Bin 1: min + binSize <= x < min + 2 * binSize
   * Bin 2: min + 2 * binSize <= x < min + 3 * binSize
   * ...
   * Bin n - 1: min + (n - 1) * binSize <= x < max
   * where n = numBins.
   * The method returns an array arr of length numBins such
   * that arr[i] contains the count of values from the data set
   * that are in bin i. Thus, "putting a number into a bin" just means
   * incrementing the count at the appropriate bin index. 
   * <p>
   *  		<em>Note:</em> The bin index for a value x can always be computed as
   *  (x - min) / binSize, truncated down to the next lowest integer, so no
   *  complicated conditional statements are needed to determine which bin
   *  <em>x</em> should go into!
   *  <p>
   *  It can be assumed that max &gt; min and numBins &gt; 0.  Values in 
   * the data that are less than min, or greater than or equal to max, are ignored.
   * <p>
   * <em>Example: </em> if <code>nums</code> is the array
   * [18.0, 15.0, 8.0, 16.0, 14.0, 19.0], then for the call
   * <pre>sortIntoBins(nums, 4, 2.0, 24.0);</pre>
   * binSize is (24.0 - 2.0)/4 = 5.5, so the bins are
   * <ul>
   * Bin 0: 2.0 <= x < 7.5
   * Bin 1: 7.5 <= x < 13.0
   * Bin 2: 13.0 <= x < 18.5
   * Bin 3: 18.5 <= x < 24.0
   * </ul>
   * In this case the method returns the array [0, 1, 4, 1].
   * @param data
   *   the data set
   * @param numBins
   *   number of bins
   * @param min
   *   lower bound (inclusive) for numbers to be included in the bins
   * @param max
   *   upper bound (exclusive) for numbers to be included in the bins
   * @return
   *   array whose <em>i</em>th cell contains the number of values in bin <em>i</em>
   *   how many numbers are in that data range given
   **/

  public static int[] sortIntoBins(double[] data, int numBins, double min, double max)
  {
      double binSize = (max - min) / numBins; // range increment value
      int[] arr = new int[numBins];
	  double range = min;

	  for(int i = 0; i < arr.length; i++)
	  {
		  for(int j = 0; j < data.length; j++)
		  {
			  if(data[j] >= range && data[j] < range + binSize) // determining if data[i] is in the current range.
			  {
				  arr[i] = arr[i] + 1;
			  }
		  }
		  range = range + binSize;
	  }
	  return arr;
  }
	public static void main(String[] args)
	{
		
	}
  
  /**
   * Shifts the elements of the given array to the right by the given amount. 
   * Vacated cells are filled with zeros. 
   * <em>Example:</em> if <code>arr</code> is [10, 20, 30. 40, 50, 60],
   * then after <code>shiftRight(arr, 4)</code>, <code>arr</code> contains
   * [0, 0, 0, 0, 10, 20].  If the shift amount is greater than or equal to 
   * the size of the array, the array would just be filled by all zeros.
   * If <code>amount</code> is negative, then the result is equivalent to the call
   * <code>shiftLeft(-amount)</code>.
   * @param arr
   *   given array to be modified
   * @param amount
   *   amount of shift
   */ 
  public static void shiftRight(int[] arr, int amount)
  {
	  if(amount > arr.length - 1)
	  {
		  for(int i = 0; i < arr.length - 1; i++)
		  {
			  arr[i] = 0;
		  }
	  }
	  
	  if(amount < 0)
	  {
		  shiftLeft(arr, - amount);
	  }
	  
	  outerLoop:
		  for(int b = arr.length - 1; b >= 0; b--)
		  {
			  if(b < amount)
			  {
				  for(int c = 0; c < amount; c++)
				  {
					  arr[c] = 0;
				  }
				  break outerLoop;
			  }
			  arr[b] = arr[b - amount];
		  }
		  
}
  
  /**
   * Shifts the elements of the given array to the left by the given amount. 
   * Vacated cells are filled with zeros. 
   * <em>Example:</em> if <code>arr</code> is [10, 20, 30. 40, 50, 60],
   * then after <code>shiftLeft(arr, 4)</code>, <code>arr</code> contains
   * [50, 60, 0, 0, 0, 0].  If the shift amount is greater than or equal to 
   * the size of the array, the array would just be filled by all zeros.
   * If <code>amount</code> is negative, then the result is equivalent to the call
   * <code>shiftRight(-amount)</code>.
   * @param arr
   *   given array to be modified
   * @param amount
   *   amount of shift
   */
  public static void shiftLeft(int[] arr, int amount)
  {  
	  if(amount >= arr.length - 1)
	  {
	  	  for(int i = 0; i < arr.length - 1; i++)
	  	  {
	  		  arr[i] = 0;
	  	  }
	  }

	  if(amount < 0)
	  {
	  	  shiftRight(arr, - amount);
	  }

	  outerLoop:
	  	  for(int b = 0; b <= arr.length; b++)
	  	  {
	  		  if(b >= amount * 2)
	  		  {
	  			  for(int c = arr.length - 1; c >= arr.length - amount; c--)
	  			  {
	  				  arr[c] = 0;
	  			  }
	  			  break outerLoop;
	  		  }
	  		  arr[b] = arr[b + amount];
	  	  }
	  	  
	 }
  
  /**
   * Cycles the elements of the given array by the given amount, rotating towards the
   * right if amount is positive and left if amount is negative. Values moved
   * off the end of the array are wrapped around. The given array is modified by 
   * this operation. <em>Example:</em> if <code>arr</code> is 
   * [10, 20, 30, 40, 50, 60],
   * then after <code>cycle(arr, 4)</code>, <code>arr</code> contains
   * [30, 40, 50, 60, 10, 20]. Note that the same result would be obtained
   * from <code>cycle(arr, 16)</code> or <code>cycle(arr, -2)</code>
   * @param arr
   *   given array to be modified
   * @param amount
   *   amount of rotation
   */
  public static void cycle(int[] arr, int amount)
  {
      int n = arr.length;
      int[] temp = new int[n];

      if (n < 0) {
           int index = 0;

           for (int i = 0; i < n; i++) {
                index = (i + (n - amount)) % n;
                temp[index] = arr[i];
           }

           for (int i = 0; i < n; i++) {
                System.out.print(temp[i] + " ");
           }
      } 
      else 
      {
           int index = 0;

           for (int i = 0; i < n; i++) {
                index = (i + (amount)) % n;
                temp[index] = arr[i];
           }
      }
      for(int i = 0; i < arr.length; i++)
      {
    	  arr[i] = temp[i];
      }
  }

  
  /**
   * Returns a new array with duplicate elements removed.  The relative ordering
   * of the first occurrence of each element is unchanged.  The given array
   * is not modified.  The method is case sensitive.
   * <p>
   * <em>Example: </em>Given the array ["Eel", "Aardvark", "Eel", "Dog", "Eel", "Aardvark", "Helicopter", "Dog"], 
   * returns the array ["Eel", "Aardvark", "Dog", "Helicopter"]. 
   * @param arr
   *   given array
   * @return
   *   new array with duplicates removed
   */
  public static String[] removeMultiples(String[] arr)
  {
	  int length = arr.length;
	  if(length==0 || length==1) 
		  return arr;
	  int i=1;
	  for(int j=1; j<length; j++){
	    if(!arr[j].equals(arr[j-1])){
	       arr[i]=arr[j];
	      i++;
	    }
	  }
	  if(i<length) arr[i]=null;
	    return arr;
	} 
  
  /**
   * Given an array, returns a new array consisting of the longest run of
   * consecutive nondecreasing values in the given array.  If there are multiple runs
   * of the same maximal length, the first such run is returned.
   * <em>Example:</em> Given the array
   * [5, 3, 3, 7, 10, 9, 11, 13, 10, 14, 16, 17], the method returns
   * the array [3, 3, 7, 10]
   * @param arr
   *   given array
   * @return
   *   array containing longest nondecreasing run in the given array
   */
  public static int[] longestAscending(int[] arr)
  {
	  {
		  int[] current = new int[arr.length];
		  int length = 1;
		  int length1 = 1;
		  int count = 1;

		  //setting runs into current
		  for (int i = 0; i <= arr.length; i++) 
		  {
			  if(i == 0)
			  {
				  if(arr[i] <= arr[i + 1])
				  {
					  current[i] = arr[i];
				  }
				  continue;
			  }
			  if(i == arr.length)
			  {
				  if(arr[i - 1] > current[i - 1])
				  {
					  current[i - 1] = arr[i - 1];
				  }
				  continue;
			  }
		      if (arr[i - 1] < arr[i]) 
		      {
		          current[i] = arr[i];
		      }
		      else
		      {
		    	  continue;
		      }
		  }
		  
		  //going through current to see which sub has the longest run
		  for(int i = 0; i < current.length; i++)
		  {  
			  if(i == 0)
			  {
				  if(current[i] < current[i + 1])
				  {
					  length++;
					  
				  }
				  continue;
			  }
			  if(current[i - 1] < current[i])
			  {
				  length++;
			  }
			  if(current[i] == 0)
			  {
				  count += length;
				  length1= length;
				  length = 0;
			  }
		  }
		  if(length <= length1)
		  {
			  int[] max = new int[length1];
			  for(int i = 0; i < length1; i++)
			  {
				  max[i] = current[i + count]; 
			  }
			  if(length1 == 1)
			  {
				  max[0] = arr[0];
			  }
			  return max;
		  }
		  else
		  {	  
			  int[] max = new int[length];
			  for(int i = 0; i < length; i++)
			  {
				  max[i] = current[i + count]; 
			  }
			  if(length == 1)
			  {
				  max[0] = arr[0];
			  }
			  return max;
		  }
	  }
  }
  
  /**
   * Makes a palindrome from a given array by appending the smallest possible number
   * of elements.  For example,
   * <ul>
   * <li>makeIntoPalindrome([1, 3, 7]) returns [1, 3, 7, 3, 1]
   * <li>makeIntoPalindrome([5, 4, 1, 3, 3, 1]) returns [5, 4, 1, 3, 3, 1, 4, 5]
   * <li>makeIntoPalindrome([1, 3, 3, 1]) returns [1, 3, 3, 1]
   * </ul>
   * @param arr
   *   an array
   * @return
   *   a new array that is a palindrome obtained by appending the fewest
   *   number of elements onto <code>arr</code>
   */
  public static int[] makeIntoPalindrome(int[] arr)
  {
	  ArrayList<Integer> list = new ArrayList<>();
	  
      for (int i = 0; i < arr.length; i++)
      {
    	  list.add(arr[i]);
      }
      int n = arr.length;
      
      for (int i = 0, j = n - 1; i <= j; i++) 
      {
    	  if (list.get(i) != list.get(j)) 
    	  {
             list.add(j + 1, list.get(i));
		  }
    	  else 
    	  {
    		  j--;
          }
      }
      arr = new int[list.size()];
      
      for (int i = 0; i < arr.length; i++)
      {
    	  arr[i] = list.get(i);
      }
      
      return arr;
  }
}
