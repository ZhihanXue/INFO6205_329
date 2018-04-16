package genetic;

import java.util.Random;

/**
 * @author guozhitang xuezhihan
 * @date 2018/04/08
 */

public class CoreControl {     // The class for the core control
	
	/**
	 * Main parameters
	 * @param A                the lower bound
	 * @param B                the upper bound
	 * @param interal          the interval
	 * @param problemThings[]  the number for the fish
	 * @param members[]        the member fish in the species group
	 * @param LENGTH           the length of the genetic code
	 * @param stop             the generation for stop
	 * @return  
	 */
	
	public static final double A = -1;// the lower bound
	public static final double B = 2;// the upper bound
	public static final double interal = B-A;// the interval
	public double[] problemThings = new double[SpeciesGroup.size];// the number for the fish
	public String[] members = new String[SpeciesGroup.size];
	
	public static final int LENGTH = 22;// the length of the genetic code, because it should hold 6 digits after the decimal thus it is 22-digit long
	public static final int MJ2 = 4194304;// 2^22 

	public static final int stop = 100;// the generation for stop
	
	/**
	 * Encoding
	 */
	public void encode(){
		int[] decimal = new int[problemThings.length];// Decimal type for members
		for(int i = 0; i < problemThings.length; i++){
			decimal[i] = (int)((problemThings[i] - A) / interal * (MJ2 - 1));// Transfer the double random number to encoding integer
		}
        
		for(int i = 0; i < decimal.length; i++){// Transfer the decimal to binary
			members[i] = Integer.toBinaryString(decimal[i]);
		}
		
		for(int i = 0; i < members.length; i++){
			if(members[i].length() < 22){
				while(members[i].length() < 22){
					members[i] = '0' + members[i];// if the genetic code is not full, use zero to fill up it
				}
			}
			else if(members[i].length() > 22){
				System.out.println("There is something wrong with the encoding.");
			}
			else{				
			}
		}	
	}
	
	/**
	 * Judge whether it is stopped
	 */
	boolean isEnded(){
		if(SpeciesGroup.generation < stop)
			return false;
		else
			return true;
	}

	/**
	 * Initialization
	 * @param args
	 */
	Random randomNumber=new Random();
	public void initialize(){
		for(int i = 0;i<SpeciesGroup.size;i++){
			problemThings[i] = A+randomNumber.nextDouble()*interal;
		}
		encode();
	}
	
	public String[] getMembers(){
		return members;
	}
}
