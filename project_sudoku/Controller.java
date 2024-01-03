package project_sudoku;

public class Controller {
	MySudoku model;
	View view;

	public Controller(MySudoku model) {
		this.model = model;
		view = new View(this);
	}

	public void makeNewGame() {
		view.init();
	}

	public int[][] run(int[][] matrix) {
		return model.run(matrix);
	}

}
