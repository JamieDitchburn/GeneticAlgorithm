package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * Genetic Algorithm
 * 
 * Step 1: Initialise
 * 	Create a population of N elements, each with randomly generated DNA
 * 
 * Step 2: Selection
 * 	Evaluate the fitness of each element of the population and build a mating pool
 * 
 * Step 3: Reproduction
 * 	3.1. Pick two parents with probability according to relative fitness
 * 	3.2. Crossover - Create a child by combining th DNA of these two parents
 * 	3.3. Mutation - Mutate the child's DNA based on a given probability 
 * 	3.4. Add the new child to a new population
 * 	3.5. Repeat N times.
 * 
 * Step 4: Replace the old population with the new population and return to step 2.
 *
 */

public class PhraseFinder {
	
	// Algorithm variables
	private static final String target = "To be or not to be.";
	private static final double mutationRate = 0.03;
	private static final int maxPopulation = 200;
	private static Population population;
	private static List<DNA> matingPool = new ArrayList<>();
	private static DNA currentBest = new DNA(target.length());
	private static Random rand = new Random();
	private static int mutations = 0;
	

	public static void main(String[] args) {
		// Step 1:
		initialise();
		
		// Step 2:
		population.calcFitness();
		matingPool = population.buildMatingPool(maxPopulation, currentBest);
		
		// Step 3 and Step 4:
		while (!currentBest.toString().equals(target)) {
			mutations++;
			population = createNewPopulation();
			population.calcFitness();
			matingPool = population.buildMatingPool(maxPopulation, currentBest);
			currentBest = population.getCurrentBest();
			System.out.println(currentBest.toString() + ", Score = " + population.getCurrentBest().getFitness() + ",  matingPool.size() = " + matingPool.size());
		}
		
		System.out.println(mutations + " mutations.");
	}

	private static void initialise() {
		population = new Population(target, mutationRate, maxPopulation);
		population.initialise();
	}
	
	private static Population createNewPopulation() {
		Population newPopulation = new Population(target, mutationRate, maxPopulation);

		// Step 3
		for (int i = 0; i < maxPopulation; i++) {
			DNA parentA = pickRandomParent();
			DNA parentB = pickRandomParent();
			DNA child = parentA.reproduceWith(parentB, mutationRate);
			newPopulation.addToPopulation(child);
		}
		return newPopulation;
	}

	private static DNA pickRandomParent() {
//		return matingPool.get(rand.nextInt(matingPool.size()));
		int i = rand.nextInt(matingPool.size());
		return matingPool.get(i);
	}
	
}
