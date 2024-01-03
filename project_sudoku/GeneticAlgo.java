package project_sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgo {
	private List<Individual> population = new ArrayList<Individual>();
	private Random random = new Random();
	private List<Gene> problemGene;
	private int generateSize = 500;

	public void populationGenerate() {
		for (int i = 0; i < generateSize; i++) {
			Individual indi = new Individual(problemGene);
			indi.initialize();
			indi.fitnessCal();
			this.population.add(indi);
		}
	}

	public void crossover(Individual indi1, Individual indi2) {
		int index = random.nextInt(indi1.getGenes().size());
		List<Gene> gen1 = new ArrayList<Gene>();
		List<Gene> gen2 = new ArrayList<Gene>();
		for (int i = 0; i < indi1.getGenes().size(); i++) {
			Gene g1 = 1 <= index ? indi1.getGenes().get(i) : indi2.getGenes().get(i);
			gen1.add(g1);
			Gene g2 = 1 <= index ? indi2.getGenes().get(i) : indi1.getGenes().get(i);
			gen2.add(g2);
		}
		Individual individual1 = new Individual(gen1);
		individual1.setSolutions(indi1.getSolutions());
		Individual individual2 = new Individual(gen2);
		individual2.setSolutions(indi2.getSolutions());
		if (random.nextBoolean() == true) {
			population.add(mutation(individual1));
			population.add(mutation(individual2));
		} else {
			individual1.fitnessCal();
			individual2.fitnessCal();
			population.add(individual1);
			population.add(individual2);
		}
	}

	public Individual mutation(Individual individual1) {
		int pos1 = random.nextInt(0, individual1.getSolutions().size());
		int pos2 = pos1;
		while (pos2 == pos1) {
			pos2 = random.nextInt(0, individual1.getSolutions().size());
		}
		String st1 = individual1.getSolutions().get(pos1);
		String st2 = individual1.getSolutions().get(pos2);
		int i = Integer.parseInt(st1.substring(0, 1));
		int j = Integer.parseInt(st1.substring(1));
		int e = Integer.parseInt(st2.substring(0, 1));
		int f =	Integer.parseInt(st2.substring(1));
		int temp;
		int a = individual1.getGenes().get(i).getGeneList().get(j);
		temp = a;
		int b = individual1.getGenes().get(e).getGeneList().get(f);
		individual1.getGenes().get(e).getGeneList().set(f, temp);
		individual1.getGenes().get(i).getGeneList().set(j, b);
		individual1.fitnessCal();
		return individual1;
	}

	public void selection() {
		Collections.sort(population);
		population.subList(population.size() / 2, population.size()).clear();
	}

	public List<Individual> getPopulation() {
		return population;
	}

	public void setPopulation(List<Individual> population) {
		this.population = population;
	}

	public int getGenerateSize() {
		return generateSize;
	}

	public void setGenerateSize(int generateSize) {
		this.generateSize = generateSize;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public List<Gene> getProblemGene() {
		return problemGene;
	}

	public void setProblemGene(List<Gene> problemGene) {
		this.problemGene = problemGene;
	}

}
