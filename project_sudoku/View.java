package project_sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame implements ActionListener, KeyListener {
	private Controller controller;
	Container container;
	JButton newGame_bt, run_btn;
	JLabel time_lb;
	JButton cells[][] = new JButton[9][9];
	int matrix[][] = new int[9][9];
	JButton selected = null;
	Color selectedColor = new Color(153, 204, 255);

	public View(Controller sudokuController) {
		super("My Sudoku");
		controller = sudokuController;
	}

	public void init() {
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout(10, 10));
		container.add(createMatrix(), BorderLayout.CENTER);
		container.add(createOption(), BorderLayout.EAST);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		this.container = container;
	}

	private JPanel createOption() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 400));
		panel.setLayout(new GridLayout(3, 1, 10, 10));
		newGame_bt = new JButton("New Game");
		newGame_bt.addActionListener(this);
		newGame_bt.setActionCommand("NewGame");
		run_btn = new JButton("Run");
		run_btn.addActionListener(this);
		run_btn.setActionCommand("Run");
		time_lb = new JLabel("00 sec");

		JPanel option = new JPanel();
		option.setLayout(new GridLayout(5, 1, 10, 10));
		option.add(newGame_bt);
		option.add(run_btn);
		option.add(time_lb);
		panel.add(option);
		return panel;
	}

	private JPanel createMatrix() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(600, 400));
		panel.setLayout(new GridLayout(9, 9));
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				cells[i][j] = new JButton();
				cells[i][j].addActionListener(this);
				cells[i][j].addKeyListener(this);
				cells[i][j].setActionCommand("Cell");
				cells[i][j].setBackground(Color.white);
				cells[i][j].setFont(new Font("UTM Micra", 1, 30));
				cells[i][j].setForeground(Color.black);
				panel.add(cells[i][j]);
			}
		for (int i = 0; i < 9; i += 3)
			for (int j = 0; j < 9; j += 3) {
				cells[i][j].setBorder(BorderFactory.createMatteBorder(3, 3, 1, 1, Color.black));
				cells[i][j + 2].setBorder(BorderFactory.createMatteBorder(3, 1, 1, 3, Color.black));
				cells[i + 2][j + 2].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
				cells[i + 2][j].setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, Color.black));
				cells[i][j + 1].setBorder(BorderFactory.createMatteBorder(3, 1, 1, 1, Color.black));
				cells[i + 1][j + 2].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.black));
				cells[i + 2][j + 1].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.black));
				cells[i + 1][j].setBorder(BorderFactory.createMatteBorder(1, 3, 1, 1, Color.black));
				cells[i + 1][j + 1].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
			}
		return panel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() >= '0' & e.getKeyChar() <= '9' & selected != null) {
			selected.setText(e.getKeyChar() + "");
			selected.setForeground(Color.black);
		}
	}

	public void newGame() {
		Controller con = new Controller(new MySudoku());
		this.dispose();
		con.makeNewGame();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE & !selected.getForeground().equals(Color.black)) {
			selected.setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("NewGame")) {
			newGame();
		}
		if (e.getActionCommand().equals("Run")) {
			run();
		}
		if (e.getActionCommand().equals("Cell")) {
			JButton cell = (JButton) e.getSource();
			selected = cell;
			cell.setBackground(selectedColor);
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[0].length; j++) {
					if (!cells[i][j].equals(selected)) {
						cells[i][j].setBackground(Color.white);
						validate();
					}

				}
			}
		}
	}

	private void run() {
		System.currentTimeMillis();
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				System.out.println(Integer.parseInt(cells[i][j].getText()));
				matrix[i][j] = Integer.parseInt(cells[i][j].getText());
			}
		}
		int [][] res = controller.run(this.matrix);
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[0].length; j++) {
				cells[i][j].setText(res[i][j]+"");
				validate();
			}
		}
		long time = System.currentTimeMillis()/1000;
		this.time_lb.setText(time+" sec");
	}

	// lÃ m má»›i láº¡i bÃ n game
	public void resetBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				cells[i][j].setText("");
				matrix[i][j] = 0;
				cells[i][j].setFocusable(true);
				cells[i][j].setForeground(Color.white);
			}
		}
	}


	public static void main(String[] args) {
		MySudoku game = new MySudoku();
		Controller con = new Controller(game);
		View view = new View(con);
		con.makeNewGame();
	}

}
