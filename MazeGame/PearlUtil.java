package mini2;
import static mini2.State.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Utility class containing the key algorithms for moves in the
 * a yet-to-be-determined game.
 */
public class PearlUtil
{
  private PearlUtil()
  {
    // disable instantiation
  }

  /**
   * Replaces all PEARL states with EMPTY state between indices 
   * start and end, inclusive.
   * @param states
   *   any array of States
   * @param start
   *   starting index, inclusive
   * @param end
   *   ending index, inclusive
   */
  public static void collectPearls(State[] states, int start, int end)
  {
      
      for(int i = start;i <= end;i++) {
          if(states[i] == PEARL) {
              states[i] = EMPTY;
          }
      }
  }
  

  /**
   * Returns the index of the rightmost movable block that is at or
   * to the left of the given index <code>start</code>.  Returns -1 if
   * there is no movable block at <code>start</code> or to the left.
   * @param states
   *   array of State objects
   * @param start
   *   starting index for searching
   * @return
   *   index of first movable block encountered when searching towards
   *   the left, starting from the given starting index; returns -1 if there
   *   is no movable block found
   */
  public static int findRightmostMovableBlock(State[] states, int start)
  {
      int result = -1;
      for(int i = 0; i<=start ;i++) {
          if(states[i] == MOVABLE_POS || states[i] == MOVABLE_NEG) {
              result = i;
          }
      }
    return result;
  }
  
  
  /**
   * Creates a state array from a string description, using the character
   * representations defined by <code>State.getValue</code>. (For invalid 
   * characters, the corresponding State will be null, as determined by
   * <code>State.getValue</code>.)
   * Spaces in the given string are ignored; that is, the length of the returned 
   * array is the number of non-space characters in the given string.
   * @param text
   *   given string
   * @return
   *   State array constructed from the string 
   */
  public static State[] createFromString(String text)
  {
    ArrayList<State> temp = new ArrayList<State>(); 
    
    for(int i = 0; i<text.length();i++) {
        if(text.charAt(i) != ' ') {
            temp.add(State.getValue(text.charAt(i)));
        }
    }
    return temp.toArray(new State[] {});
  }
  
  /**
   * Determines whether the given state sequence is valid for the moveBlocks 
   * method.  A state sequence is valid for moveBlocks if
   * <ul>
   *   <li>its length is at least 2, and
   *   <li>the first state is EMPTY, OPEN_GATE, or PORTAL, and
   *   <li>it contains exactly one <em>boundary state</em>, which is the last element
   *   of the array
   * </ul>
   * Boundary states are defined by the method <code>State.isBoundary</code>, and
   * are defined differently based on whether there is any movable block in the array.
   * @param states
   *   any array of States
   * @return
   *   true if the array is a valid state sequence, false otherwise
   */
  public static boolean isValidForMoveBlocks(State[] states)
  {
      int start = 0;
    boolean flag = false;
    if(states.length<2) {
        return false;
    }
    if(states[0] != EMPTY && states[0] != OPEN_GATE && states[0] != PORTAL){
        return false;// TODO
    }
    for(int i=0;i<states.length;i++) {
        if(states[i] == MOVABLE_POS || states[i] == MOVABLE_NEG) {
            flag = true;
            start = i;
            break;
        }
    }
    
    for(int i= 0;i<states.length;i++) {
        if(i > start) {
            if(i!= states.length-1 && State.isBoundary(states[i], flag)) {
                return false;
            }
            
            if(i == states.length-1 && State.isBoundary(states[i], flag)) {
                return true;
            }
        }
    }
        
    return false;
  }
  
  /**
   * Updates the given state sequence to be consistent with shifting the
   * "player" to the right as far as possible.  The starting position of the player
   * is always index 0.  The state sequence is assumed to be <em>valid
   * for movePlayer</em>, which means that the sequence could have been
   * obtained by applying moveBlocks to a sequence that was valid for
   * moveBlocks.  That is, the validity condition is the same as for moveBlocks,
   * except that
   * <ul>
   *   <li>all movable blocks, if any, are as far to the right as possible, and
   *   <li>the last element may be OPEN_GATE or PORTAL, even if there are
   *   no movable blocks
   * </ul>
   * <p>
   * The player's new index, returned by the method, will be one of the following:
   * <ul>
   * <li>if the array contains any movable blocks, the new index is just before the 
   *     first movable block in the array;
   * <li>otherwise, if the very last element of the array is SPIKES_ALL, the new index is
   * the last position in the array;
   * <li>otherwise, the new index is the next-to-last position of the array.
   * </ul>
   * Note the last state of the array is always treated as a boundary for the 
   * player, even if it is OPEN_GATE or PORTAL.
   * All pearls in the sequence are changed to EMPTY and any open gates passed
   * by the player are changed to CLOSED_GATE by this method.  (If the player's new index 
   * is on an open gate, its state remains OPEN_GATE.)
   * @param states
   *   a valid state sequence
   * @return
   *   the player's new index
   */
  public static int movePlayer(State[] states) 
  {
      int index = 0;
      for(int i = 0; i< states.length;i++) {
          if(State.isBoundary(states[i], false) == true) {
              index = i - 1;
          }
          if(states[i] == SPIKES_ALL) {
              index = i;
          }
          if(states[i]== MOVABLE_NEG || states[i] == MOVABLE_POS) {
              index = i;
              return index -1;
          }
          if(states[i] == PORTAL) {
              index = i-1;
          }
          if(states[i] == OPEN_GATE) {
              index = i-1;
          }
      }
      PearlUtil.collectPearls(states, 0, index);
      for(int i =0; i<index; i++) {
          if(states[i] == OPEN_GATE && i>0) {
              states[i] = CLOSED_GATE;
          }
      }
      if(states[states.length-1] == SPIKES_ALL) {
          index = states.length-1;
      }


    return index;

  }

  /**
   * Updates the given state sequence to be consistent with shifting all movable
   * blocks as far to the right as possible, replacing their previous positions
   * with EMPTY. Adjacent movable blocks
   * with opposite parity are "merged" from the right and removed. The
   * given array is assumed to be <em>valid for moveBlocks</em> in the sense of
   * the method <code>validForMoveBlocks</code>. If a movable block moves over a pearl 
   * (whether or not the block is subsequently removed
   * due to merging with an adjacent block) then the pearl is also replaced with EMPTY.
   * <p>
   * <em>Note that merging is logically done from the right.</em>  
   * For example, given a cell sequence represented by ".+-+#", the resulting cell sequence 
   * would be "...+#", where indices 2 and 3 as move to index 3 and disappear
   * and position 1 is moved to index 3.
   * @param states
   *   a valid state sequence
   */
  public static void moveBlocks(State[] states) 
  {
      ArrayList<State> temp = new ArrayList<State>();
      
      boolean flag = false;
      for(int i=0; i<states.length-1; i++) {
          if(states[i] == MOVABLE_POS || states[i] == MOVABLE_NEG) {
              flag = true;
              temp.add(states[i]);
              states[i] = EMPTY;
          }
          else {
              if(flag)states[i] = EMPTY;
          }
          
      }
      State[] tmp = temp.toArray(new State[] {});
      int index = 0;
      while(index < tmp.length-1) {
          if((tmp[index] == MOVABLE_NEG && tmp[index+1] == MOVABLE_POS) || tmp[index] == MOVABLE_POS && tmp[index+1] == MOVABLE_NEG) {
              tmp[index] = EMPTY;
              tmp[index + 1] = EMPTY;
              index += 2;
          }
          else index ++;
      }
      temp.clear();
      for(State s : tmp) {
          if(s != EMPTY) {
              temp.add(s);
          }
      }  
      
      for(int i = 0;i<temp.size();i++) {
          states[states.length-1-(temp.size()-i)] = temp.get(i);
      }
  }
}