package project_sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gene {
	List<Integer> geneList = new ArrayList<Integer>();
	Random random = new Random();

	public List<Integer> init() {
		List<Integer> solutionPos = new ArrayList<Integer>();
		for (int i = 0; i < geneList.size(); i++) {
			if (geneList.get(i) == 0) {
				boolean add = false;
				while (!add) {
					int a = random.nextInt(1, 10);
					if (!geneList.contains(a)) {
						geneList.set(i, a);
						solutionPos.add(i);
						add = true;
					}
				}
			}
		}
		return solutionPos;
	}

	public List<Integer> getGeneList() {
		return geneList;
	}

	public void setGeneList(List<Integer> geneList) {
		this.geneList = geneList;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

}
