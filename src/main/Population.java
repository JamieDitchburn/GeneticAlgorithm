package main;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Population {

	private String target;
	private double mutationRate;
	private int maxPopulation;
	private static List<DNA> populationList;

	
	public Population(String target, double mutationRate, int maxPopulation) {
		this.target = target;
		this.mutationRate = mutationRate;
		this.maxPopulation = maxPopulation;
		populationList = new LinkedList<>();
	}
	
	public void initialise() {
		// Populate with DNA objects
		for(int i = 0; i < maxPopulation; i++) {
			populationList.add(i, new DNA(target.length()));
		}
	}
	
	public List<DNA> buildMatingPool(int maxPopulation, DNA previousBest) {
		List<DNA> matingPool = new ArrayList<>();
		for (int i = 0; i < maxPopulation - 1; i++) {
			DNA dna = populationList.get(i);
			matingPool.add(dna);
		}
		matingPool.add(previousBest);
		return matingPool;
	}

	public List<DNA> getPopulationList() {
		return populationList;
	}

	public void calcFitness() {
		for (DNA dna : populationList) {
			dna.calcFitness(target);
		}
	}

	public void addToPopulation(DNA child) {
		populationList.add(child);
	}

	public DNA getCurrentBest() {
		int best = 0;
		for (int i = 0; i < populationList.size(); i++) {
			if (populationList.get(i).getFitness() > populationList.get(best).getFitness()) best = i;
		}
		
		return populationList.get(best);
	}
	
}
