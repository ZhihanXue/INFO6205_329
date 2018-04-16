package genetic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author guozhitang xuezhihan
 * @date 2018/04/08
 */

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter pw1 = new PrintWriter(new File("../../"+File.separator+"averg.txt")) ;
		PrintWriter pw2 = new PrintWriter(new File("../../"+File.separator+"maxfit.txt")) ;
		
		CoreControl control = new CoreControl();
		control.initialize();        // Do the initialization
		
		SpeciesGroup speciesGroup = new SpeciesGroup(control.getMembers());
		System.out.println("The initial member of the species group: ");
		speciesGroup.print();       // Output the species group before doing the GA
		double theBest;
		
		speciesGroup.decode();
		System.out.println("The 0 Generation£º");
		theBest = speciesGroup.sufficiency();
		System.out.println("f = " + theBest); // Output the largest value of the fitness for each iteration
		System.out.println();
		
		double[] values = speciesGroup.getValues() ;

		pw1.println(values[0]) ;
		pw2.println(values[1]) ;
		pw1.flush() ;
		pw2.flush() ;

		int n = 1;
		do{
			speciesGroup.gambleWheel();        // Use Russian Roulette Selection method			          
			speciesGroup.intersect();          // Intersect
			speciesGroup.variation();          // Variation

			values = speciesGroup.getValues() ;

			pw1.println(values[0]) ;
			pw2.println(values[1]) ;
			pw1.flush() ;
			pw2.flush() ;
			
			speciesGroup.decode();
			System.out.println("The" + n + "Generation: ");
			theBest = speciesGroup.sufficiency();
			System.out.println("f = " + theBest); // Output the maximum fitness value of each iteration 
			System.out.println();

			SpeciesGroup.generation++;
			n++;

		}while(!control.isEnded());           // Do the iteration repeatedly when it does not reach the iteration limit
			
		System.out.println("The fianl member of the species group: "+ speciesGroup);
		speciesGroup.print();      // Output the species group after doing the GA
		speciesGroup.printr();
	}

}
