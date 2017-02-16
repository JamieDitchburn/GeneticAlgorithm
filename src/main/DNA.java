package main;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DNA {

	private int targetLength;
	private double fitness;
	private List<Character> genes;
	public static Random rand = new Random();

	public DNA(int targetLength) {
		this.targetLength = targetLength;
		
		init();
	}

	private void init() {
		genes = new LinkedList<>();
		
		// Populate with genes
		for (int i = 0; i < targetLength; i++) {
			genes.add(i, randChar());
		}
	}
	
	private char randChar() {
		int c = rand.nextInt(54) + 63;
		if (c > 90) c += 6;				// 97-122 = capital letters
		else if (c == 63) c = 32;		// 32 = space
		else if (c == 64) c = 46;		// 46 = period
		return (char) c;		// Return ASCII 65-90 & 97-122 & 32 & 46
	}
	
	public void calcFitness(String target) {
		char[] targetChars = target.toCharArray();
		int fitnessCalc = 0;
		
		for (int i = 0; i < target.length(); i++) {
			if (targetChars[i] == genes.get(i)) fitnessCalc++;
		}
		
		fitness = ((double)fitnessCalc / targetLength);		// Normalize fitness
	}
	
	public DNA reproduceWith(DNA parentB, double mutationRate) {
		DNA newDNA = new DNA(targetLength);
		for (int i = 0; i < targetLength; i++) {
			if (rand.nextDouble() > mutationRate) {
				if (i % 2 == 0) {
					newDNA.setGene(i, getGene(i));
				} else {
					newDNA.setGene(i, parentB.getGene(i));
				}
			} else {
				newDNA.setGene(i, randChar());
			}
		}
		return newDNA;
	}
	
	public List<Character> getGenes() {
		return genes;
	}
	
	public Character getGene(int i) {
		return genes.get(i);
	}
	
	public double getFitness() {
		return fitness;
	}
	
	public void setGene(int i, Character c) {
		genes.set(i, c);
	}

	public String toString() {
		StringBuilder genesString = new StringBuilder();
		for (Character character : genes) {
			genesString.append(character);
		}
		return genesString.toString();
	}
	
}
