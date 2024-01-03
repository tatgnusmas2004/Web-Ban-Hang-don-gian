package project_sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MySudoku {
	private GeneticAlgo geneticAlgo;
	private View view;
	private int[][] problemGene;
	private int interationCount = 0;
	private int maxInterator = 1000;
	private Individual best;

	public MySudoku() {
		this.geneticAlgo = new GeneticAlgo();
//		this.view = new View();
		this.best = new Individual();
		best.setFitness(1000);
//		run();
	}

	public void loadProblem(int[][] list) {
		List<Gene> genes = new ArrayList<Gene>();
		for (int i = 0; i < 9; i++) {
			Gene gen = new Gene();
			List<Integer> gene = new ArrayList<Integer>();
			gen.setGeneList(gene);
			for (int j = 0; j < 9; j++) {
				gene.add(list[i][j]);
			}
			genes.add(gen);
		}
		this.geneticAlgo.setProblemGene(genes);
	}

	public int[][] run(int[][] list) {
		loadProblem(list);
		this.geneticAlgo.populationGenerate();
		while (this.best.getFitness() != 0 && interationCount < maxInterator) {
			this.interationCount++;
			for (int i = 0; i < this.geneticAlgo.getGenerateSize(); i += 2) {
				this.geneticAlgo.crossover(geneticAlgo.getPopulation().get(i), geneticAlgo.getPopulation().get(i + 1));
			}
			this.geneticAlgo.selection();
			this.best = geneticAlgo.getPopulation().get(0);
			System.out.println( "iteration: " + interationCount + ", fitness: " + best.getFitness()+"\n current: ");
			for (Gene g : best.getGenes()) {
				System.out.println(g.getGeneList());
			}
		}
		return toTwoDArray(best);
	}
	
	public int[][] toTwoDArray(Individual indi){
		int[][] res = new int[indi.getGenes().size()][indi.getGenes().size()];
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res.length; j++) {
				res[i][j] = indi.getGenes().get(i).getGeneList().get(j);
			}
		}
		return res;
	}

	public static void main(String[] args) {
//		MySudoku sudoku = new MySudoku(new int[][] { 
//			{ 0, 3, 4, 6, 7, 8, 9, 1, 2 }, 
//			{ 6, 7, 2, 1, 9, 5, 3, 4, 8 },
//			{ 1, 9, 8, 3, 4, 2, 5, 6, 7 }, 
//			{ 8, 5, 9, 7, 6, 0, 4, 2, 0 }, 
//			{ 4, 2, 6, 8, 5, 0, 7, 9, 1 },
//			{ 0, 1, 3, 0, 2, 4, 8, 0, 6 }, 
//			{ 9, 6, 1, 5, 3, 7, 2, 8, 4 }, 
//			{ 2, 8, 7, 4, 1, 0, 6, 3, 5 },
//			{ 0, 4, 5, 2, 0, 0, 1, 7, 9 }});
		MySudoku sudoku = new MySudoku();
		sudoku.run(new int[][] { 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }});
	}
}
