import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
	private JLabel nextShape;
	private JLabel score;
	private JButton pause;
	
	public InfoPanel(Game window){
		setPreferredSize(new Dimension(150, 300));
		setLayout(new BorderLayout());
		nextShape = new JLabel("Next Shape:");
		score = new JLabel("Score:  0");
		pause = new JButton("Pause/Play");
		add(nextShape, BorderLayout.NORTH);
		add(score, BorderLayout.CENTER);
		add(pause, BorderLayout.SOUTH);
	}
	
	public JLabel getScore(){
		return score;
	}
}
