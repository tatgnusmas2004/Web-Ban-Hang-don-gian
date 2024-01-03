package project_sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Individual implements Comparable<Individual>, Cloneable {
	private List<Gene> genes;
	private int fitness;
	private List<String> solutions;
	static Random random = new Random();

	public Individual(List<Gene> problemGene) {
		this.genes = copyOf(problemGene);
		solutions = new ArrayList<String>();
	}

	public Individual() {
		// TODO Auto-generated constructor stub
	}

	public void initialize() {
		for (int i = 0; i < genes.size(); i++) {
			List<Integer> col = genes.get(i).init();
			if (!col.isEmpty()) {
				for (Integer integer : col) {
					solutions.add(String.valueOf(i) + String.valueOf(integer));
				}
			}
		}
//		System.out.println(solutions);
	}

	public void fitnessCal() {
		int fitness = 0;
		for (int i = 0; i < genes.size(); i++) {
			List<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < genes.size(); j++) {
				List<Integer> col = new ArrayList<Integer>();
				if (row.contains(genes.get(i).getGeneList().get(j))) {
					fitness++;
				} else {
					row.add(genes.get(i).getGeneList().get(j));
				}
				if (col.contains(genes.get(j).getGeneList().get(i))) {
					fitness++;
				} else {
					col.add(genes.get(j).getGeneList().get(i));
				}
			}
		}

		for (int i = 0; i < genes.size(); i += Math.sqrt(genes.size())) {
			for (int j = 0; j < genes.size(); j += Math.sqrt(genes.size())) {
				List<Integer> block = new ArrayList<Integer>();
				for (int a = i; a < i + Math.sqrt(genes.size()); a++) {
					for (int b = j; b < j + Math.sqrt(genes.size()); b++) {
						if (block.contains(genes.get(a).getGeneList().get(b))) {
							fitness++;
						} else {
							block.add(genes.get(a).getGeneList().get(b));
						}
					}
				}
			}
		}
		this.setFitness(fitness);
	}

	public List<Gene> getGenes() {
		return this.genes;
	}

	private void setGenes(List<Gene> genes) {
		this.genes = genes;

	}

	public int getFitness() {
		return this.fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;

	}

	public List<String> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<String> solutions) {
		this.solutions = solutions;
	}

	public List<Gene> copyOf(List<Gene> genes) {
		List<Gene> res = new ArrayList<Gene>();
		for (Gene gene : genes) {
			List<Integer> g = new ArrayList<Integer>();
			for (Integer i : gene.getGeneList()) {
				g.add(i);
			}
			Gene gen = new Gene();
			gen.setGeneList(g);
			res.add(gen);
		}
		return res;

	}

	public int compareTo(Individual o) {
		// TODO Auto-generated method stub
		return this.getFitness() - o.getFitness();

	}
	public static void main(String[] args) {
		
	}
}
