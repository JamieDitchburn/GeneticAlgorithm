package test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.Assert.*;

import org.junit.Test;

import main.DNA;

public class DNATest {
	private static final int targetLength = 54;
	private DNA dna = new DNA(targetLength);
	private DNA dna2 = new DNA(targetLength);
	private DNA child;
	
	@Test public void
	should_fill_genes_with_suitable_characters() {
		for (char gene : dna.getGenes()) {
			assertThat(gene + "", matchesPattern("[a-zA-Z .]"));
		}
	}
	
	@Test public void
	should_calculate_normalized_fitness() {
		dna.calcFitness("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .");
		
		assertThat(dna.getFitness(), allOf(greaterThanOrEqualTo(0d), lessThanOrEqualTo(1d)));
	}

	@Test public void
	should_reproduce_with_another_dna_object() {
		child = dna.reproduceWith(dna2, 0d);
		for (int i = 0; i < child.getGenes().size(); i++) {
			char childGene = child.getGene(i);
			char dna1Gene = dna.getGene(i);
			char dna2Gene = dna2.getGene(i);
			if (i % 2 == 0) {
				assertTrue(childGene == dna1Gene);
			} else {
				assertTrue(childGene == dna2Gene);
			}
		}
	}
	
}
