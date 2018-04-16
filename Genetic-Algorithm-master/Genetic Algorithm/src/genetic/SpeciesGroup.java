package genetic;

import java.util.Arrays;
import java.util.Random;

/**
 * @author guozhitang xuezhihan
 * @date 2018/04/08
 */

public class SpeciesGroup {
	
	public  SpeciesGroup() {
		
	}
	
	/**
	 * @param size             The size for the species group
	 * @param member[]         The member for the species group
	 * @param fitness[]        The fitness for the members
	 * @param generation       The exact generation number
	 * @param VProbability     The probability for the variation
	 * @param IProbability     The probability for the intersect
	 * @param lengthOfCode     The length of the genetic code
	 * @param problemThings[]  The number for the fish
	 * @return  
	 */
	
	
	/**
	 * Main property
	 */
	static int size = 50; // The size for the species group
	String[] member = new  String[SpeciesGroup.size]; // The member for the species group
	double[] fitness = new  double[SpeciesGroup.size]; // The fitness for the members
	
	static int generation = 0; // Generation
	double VProbability = 0.01; // The probability of variation
	double IProbability = 0.25; // The probability of intersect
	static int lengthOfCode = 22; // The length of the genetic code
	
	Random randomNumber=new Random();
	public double[] problemThings = new double[SpeciesGroup.size]; // the number for the fish
	
	/**
	 * @param firstGeneration
	 */
	SpeciesGroup(String[] firstGeneration){
		for(int i = 0;i<size;i++){
			member[i] = firstGeneration[i];
		}
		decode(); // Decoding
	}
	
	/**
	 * Encoding
	 */
	public void encode(){
		int[] decimal = new int[problemThings.length]; // Decimal type for members
		for(int i = 0;i < problemThings.length;i++){
			decimal[i] = (int)((problemThings[i]-CoreControl.A)/CoreControl.interal*(CoreControl.MJ2-1)); // Transfer the double random number to encoding integer
		}
        
		for(int i = 0;i < decimal.length;i++){ // Transfer the decimal to binary
			member[i] = Integer.toBinaryString(decimal[i]);
		}
		
		for(int i = 0;i < member.length;i++){
			if(member[i].length()<lengthOfCode){
				while(member[i].length()<lengthOfCode){
					member[i] = '0'+member[i]; // if the genetic code is not full, use zero to fill up it
				}
			}
			else if(member[i].length()>22){
				System.out.println("There is something wrong with the encoding.");
			}
			else{			
			}
		}	
	}
	
	/**
	 * The first output the average fitness, the second output the maximum fitness
	 * @return
	 */
	public double[] getValues(){
		double[] values = new double[2] ;
		
		double sum = 0 ;
		for(int i = 0 ; i < fitness.length ; i ++){
			sum += fitness[i] ;
		}
		
		values[0] = sum / fitness.length ;
		values[1] = sufficiency() ;
		
		return values ;
	}
	
	
	/**
	 * Decoding
	 */
	public void decode(){
		int k; // Decimal type for members
		for(int i = 0; i<member.length;i++){
			k = Integer.parseInt(member[i], 2);
			problemThings[i] = CoreControl.A + CoreControl.interal/(CoreControl.MJ2-1)*k;
		}		
	}
	
	/**
	 * Variation 
	 */
	public void variation(){
		for(int i=0;i<size;i++){
			if(randomNumber.nextDouble()<=VProbability){

				int changePlace=randomNumber.nextInt(22); // Make a random digit negation
				StringBuffer sb=new StringBuffer(member[i]);
				
				if(sb.charAt(changePlace)=='0'){     
					sb.setCharAt(changePlace, '1');			// Negation 						
				}
				else if(sb.charAt(changePlace)=='1'){		
					sb.setCharAt(changePlace, '0');         // Negation
				}
				else{
					System.out.println("There is something wrong!");
				}
				member[i]=sb.toString();
			}
			else{				
			}
		}
	}
	
	/**
	 * Intersect
	 */
	public void intersect(){
		for(int i = 0;i <= size/2;i++){
			if(Math.random() <= IProbability){
				int one = randomNumber.nextInt(size); // Take one individual randomly
				int theOther = randomNumber.nextInt(size); // Take another individual randomly
				int intersection = randomNumber.nextInt(lengthOfCode); // Randomly get the intersect point
							
				String str_one = member[one].substring(intersection);
				String str_theOther = member[theOther].substring(intersection);
				
				StringBuffer sb = new StringBuffer(member[one]); // Exchange the first individual
				sb.replace(intersection, lengthOfCode,str_theOther);
				member[one] = sb.toString();
				
			    sb = new StringBuffer(member[theOther]); // Exchange the second individual
				sb.replace(intersection, lengthOfCode,str_one);
				member[theOther] = sb.toString();

 			}
			else{				
			}
		}
	}
	
	/**
	 * Russian roulette method
	 */
	public void gambleWheel_1()
	{ 
	   double sum=0;
	   for (int i = 0; i <size; i++) {
		   sum=fitness[i]+sum;
	   }

	   double[] p = new double[SpeciesGroup.size]; // The probability for the fitness
	   for (int i = 0; i < size; i++) {
		   p[i]=fitness[i]/sum;
	   }
	   double[] q = new double[SpeciesGroup.size];
	   for (int i = 0; i < size; i++) {
		   for (int j = 0; j < i+1; j++) {	     
			  q[i]+=p[j];
	       }
	   }
	   double[] ran=new double[50];
	   String[] tempPop=new String[50];
	   for (int i = 0; i < ran.length; i++) {	    
		   ran[i]=randomNumber.nextDouble();
	   }
	   for (int i = 0; i < ran.length; i++) {	    
		   int k = 0;	  
		   for (int j = 0; j < q.length; j++) {	    
			   if(ran[i]<q[j]){	     
				   k=j;	      
				   break;	    
			   }	     
			   else continue;	    
		   }	    
		   tempPop[i]=member[k];	  
	   }
	   for (int i = 0; i < tempPop.length; i++) {	    
		   member[i]=tempPop[i];
	   }
	}
	
	/**
	 * Russian roulette selection
	 */
	public void gambleWheel(){	
		double sum=0; // The sum of the fitness
		for(int i=0;i<SpeciesGroup.size;i++){
			sum+=fitness[i];
		}
		
		double[] fProbability = new double[SpeciesGroup.size]; // The probability of the fitness
		for(int i=0;i<SpeciesGroup.size;i++){
			fProbability[i] = fitness[i]/sum;
		}

		double[] wheelBorder = new double[SpeciesGroup.size+1]; // The bound of the gamblewheel
		wheelBorder[0] = 0;
		for(int i = 0;i<SpeciesGroup.size;i++){
			for(int j = 0;j<=i;j++){
				wheelBorder[i+1] +=   fProbability[j];
			}
		}
		
		double pointer = 0;
		String[]tempMember = new  String[SpeciesGroup.size]; // The temporary members for the next generation
		for(int i = 0;i<SpeciesGroup.size;i++){                   			
			pointer = randomNumber.nextDouble();			 
			for(int j=0;j<wheelBorder.length-1;j++){					 
				if(pointer>=wheelBorder[j]&&pointer<wheelBorder[j+1]){ // Make sure the area					 
					tempMember[i] = member[j];           // The results are stored temporarily					 
					break;				 
				}				 
				else {				 
				}			 			
			}		
		}
		for(int i = 0;i<SpeciesGroup.size;i++){ // Transfer the temporary members to the real members	
			member[i] = tempMember[i];
		}	
	}
	
	/**
	 * Return the maximum fitness
	 * f == x * sin(10 * pi * x) + 1
	 */
	public double sufficiency(){

		int max = 0;
		for(int i = 0;i<SpeciesGroup.size;i++){
			fitness[i] = problemThings[i] * Math.sin(Math.PI * 10 * problemThings[i]) + 1;
			if(fitness[max]<fitness[i]){
				max = i;
			}
		}
		System.out.println("x=£º" + problemThings[max]); // Output the corresponding x of the maximum fitness of each iteration
		return fitness[max];
	}
	
	/**
	 * The next generation
	 */
	public void nextGeneration(){	
 
		gambleWheel();      // Selection
		
		intersect();   		// Intersect
		variation();   		// Variation
		SpeciesGroup.generation++;         // Do the iteration to the next generation
	}
	
	/**
	 * Output the members for species group
	 */
	public void print(){
		for(int i=0;i< SpeciesGroup.size;i++){
			System.out.println(member[i]);
		}
	}
	
	public void printr() {
        double s = 0;
        double c = 0;
		for(int i=0;i< SpeciesGroup.size;i++){
			double f = fitness[i];
			s = s + f;
		}
		c = s/(SpeciesGroup.size);
		System.out.println("Average_depth = "+ c);
	}
}
