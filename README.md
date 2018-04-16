# INFO6205_329
The final project of Info-6205-03 Program Structure and Algorithms
Team members: Zhihan Xue NUID:001839227 Guozhi Tang NUID: 001824925
Problem
We created a genetic algorithm to measure the depth of a lake. The problem poses the situation: There is a lake which is too deep and the depth of it cannot be measured by the limited tools with us. However, we have a kind of fish which has greater survivability in deeper waters. Therefore, we can estimate the maximum depth of the water through the average of the depth that fish live after breeding this fish for many generations.

Main //Class for the Genetic Algorithms

    CoreControl control = new CoreControl();

    control.initialize();        // Do the initialization

    speciesGroup.print();       // Output the species group before doingb the GA

    theBest = speciesGroup.sufficiency(); // Output the corresponding x of the maximum fitness of each iteration

    speciesGroup.gambleWheel();        // Use Russian Roulette Selection method
		          
    speciesGroup.intersect();          // Intersect

    speciesGroup.variation();          // Variation

CoreControl      // Class for the core control

    * 主要参数
    	 * @param A                the lower bound
    	 * @param B                the upper bound
    	 * @param interal          the interval 
    	 * @param problemThings[]  the number of fish
    	 * @param members[]        the member fish in the species group
    	 * @param LENGTH           the length of the genetic code
    	 * @param stop             the generation for stop
    	 
    	 The main methods in this class are for the initialization of the genetic algorithms, encoding and judging whether generation should be stopped.
   

SpeciesGroup // Class for realization of the genetic algorithms
