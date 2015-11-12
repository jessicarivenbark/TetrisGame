import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game extends JFrame{
	private Board board;
	private InfoPanel panel;
	
	public Game(){
		setTitle("Tetris");
		setLayout(new BorderLayout());
		board = new Board(this);
		panel = new InfoPanel(this);
		add(board, BorderLayout.CENTER);
		add(panel, BorderLayout.EAST);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

		board.setFocusable(true);
		board.requestFocusInWindow();
		setVisible(true);
	}

	public static void main(String[] args) {
		Game window = new Game();

	}
	
	public Board getBoard(){
		return board;
	}
	
	public InfoPanel getPanel(){
		return panel;
	}

}
