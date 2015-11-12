import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener{
	private Color [][] board;
	private Shape currentShape;
	private Shape nextShape;
	private Timer timer;
	private boolean paused;
	private int linesRemoved;
	private Game window;
	private JLabel gameOver;
	public final int CELL_SIZE = Block.SIZE;
	public final int ROWS = 20;
	public final int COLUMNS = 15;
	public final int BOARD_WIDTH = COLUMNS * CELL_SIZE;
	public final int BOARD_HEIGHT = ROWS * CELL_SIZE;
	
	
	public Board(Game parent){
		window = parent;
		setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
		setBorder(BorderFactory.createLineBorder(Color.black));
		this.addKeyListener(this);
		timer = new Timer (400, new TimerListener()); 
		paused = false;
		gameOver = new JLabel("Game Over");
		linesRemoved = 0;
		createBoard();
		randomShape();
		newShape();
	}
	
	public void createBoard(){
		board = new Color [ROWS][COLUMNS];
		for(int i=0; i<ROWS; i++){
			for(int j=0; j<COLUMNS; j++){
				board[i][j] = Color.gray;
			}
		}
	} 
	
	public void randomShape(){
		Random random = new Random();
		int choice = random.nextInt(7);
		if (choice == 0){nextShape = new L_Shape();}
		else if (choice == 1){nextShape = new J_Shape();}
		else if (choice == 2){nextShape = new S_Shape();}
		else if (choice == 3){nextShape = new Z_Shape();}
		else if (choice == 4){nextShape = new T_Shape();}
		else if (choice == 5){nextShape = new I_Shape();}
		else if (choice == 6){nextShape = new O_Shape();}
	}
	
	public void newShape(){
		currentShape = nextShape;
		randomShape();		
		currentShape.updateX(COLUMNS/2*CELL_SIZE);
		timer.start();

	}
	
	@Override
	public void paint (Graphics g) {
		int x, y;
		super.paint(g);	
		for(int i=0; i<ROWS; i++){
			for(int j=0; j<COLUMNS; j++){
				if (board[i][j] != Color.gray){
					x = j * CELL_SIZE;
					y = i * CELL_SIZE;
					drawSquare(g, x, y, board[i][j]);
				}
			}
		}
		for(Block block : currentShape.getCurrentPosition()){
			x = block.getX();
			y = block.getY();
			drawSquare(g, x, y, currentShape.getColor());
		}	
		
	}
	
	public void drawSquare(Graphics g, int x, int y, Color color){		
		g.setColor(color);
		g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
		g.setColor(color.brighter());
	    g.drawLine(x, y + CELL_SIZE - 1, x, y);
	    g.drawLine(x, y, x + CELL_SIZE - 1, y);

	    g.setColor(color.darker());
	    g.drawLine(x + 1, y + CELL_SIZE - 1,
	                         x + CELL_SIZE - 1, y + CELL_SIZE - 1);
	    g.drawLine(x + CELL_SIZE - 1, y + CELL_SIZE - 1,
	                         x + CELL_SIZE - 1, y + 1);
			
	}
	
	public boolean Ycollision(){
		boolean collision = false;
		//adjustCoords();
		for(Block block : currentShape.getCurrentPosition()){
			int y = block.getY()/CELL_SIZE;
			int x = block.getX()/CELL_SIZE;
			if (board[y+1][x] != Color.gray){collision = true;}
		}
		return collision;
	}
	
	public boolean Xcollision (){
		boolean collision = false;
		//adjustCoords();
		if(currentShape.minX() == 0){collision = true;}
		else if(currentShape.maxX() + CELL_SIZE == BOARD_WIDTH){collision = true;}
		else {
			for(Block block : currentShape.getCurrentPosition()){
				int y = block.getY()/CELL_SIZE;
				int x = block.getX()/CELL_SIZE;
				if (board[y][x + 1] != Color.gray || board[y][x - 1] != Color.gray){collision = true;}
			}
		}
		return collision;
	}
	
	public void moveDown(){
		
    	if (currentShape.maxY() + CELL_SIZE == BOARD_HEIGHT){shapeDropped();removeLines();}
    	else if (Ycollision()){shapeDropped();removeLines();}
    	else {currentShape.updateY(CELL_SIZE);	}			
		repaint();
		
	}
	
	public void shapeDropped(){
		timer.stop();
		for(Block block: currentShape.getCurrentPosition()){
			int column = block.getX()/CELL_SIZE;
			int row = block.getY()/CELL_SIZE;
			board[row][column] = currentShape.getColor();
		}
		
		gameOver();
	}
	
	public void gameOver(){
		boolean over = false;
		for (int i=0; i<COLUMNS;i++){
			if (board[0][i] != Color.gray){over = true; break;}
		}
		if (over){add(gameOver);}
		else{newShape();}
	}
	
	public int fullLineAt(){
		//boolean lineIsFull = false;
		int fullCells = 0;
		int row = -1;
		for(int i=0; i<ROWS; i++){	
			fullCells = 0;
			for(int j=0; j<COLUMNS; j++){					
				if (board[i][j] != Color.gray){
					fullCells++;
				}
			if(fullCells == COLUMNS){return i;}
			}
		}
		
		return row;
	}
	
	public void updateBoard(int row){
		for(int i=row; i>0; i--){
			for(int j=0; j<COLUMNS; j++){				
				board[i][j] = board[i-1][j];
			}
		}
	}
	
	public void removeLines(){
		boolean allLinesRemoved = false;
		while(!allLinesRemoved){
			int fullLineAt = fullLineAt();
			if (fullLineAt == -1){allLinesRemoved = true;}
			else {
				updateBoard(fullLineAt);
				linesRemoved++;
				window.getPanel().getScore().setText("Score:   " + linesRemoved*10);
			}
		}
		repaint();
		
	}
	
	public int getLinesRemoved(){
		return linesRemoved;
	}
	
	public void adjustXCoords(){
		if (currentShape.maxX() > BOARD_WIDTH - CELL_SIZE){
			int blocksOutside = (currentShape.maxX() - (BOARD_WIDTH - CELL_SIZE))/CELL_SIZE;
			currentShape.updateX(-blocksOutside*CELL_SIZE);
		}
	}
	
	public void keyPressed(KeyEvent event) {
		   int keyCode = event.getKeyCode();
		   
		   if (keyCode == 'p' || keyCode == 'P') {
               pause();
               return;
           }
		    switch( keyCode ) { 
		        case KeyEvent.VK_UP:
		            currentShape.rotateLeft();
	        		adjustXCoords();
		            break;
		            
		        case KeyEvent.VK_DOWN:
		        	moveDown();			
		        	break;
		        	
		        case KeyEvent.VK_LEFT:
		        	if (Xcollision()){;}
		        	else {currentShape.updateX(-CELL_SIZE);}			            
		            break;
		            
		        case KeyEvent.VK_RIGHT :
		        	if (Xcollision()){;}
		        	else {currentShape.updateX(CELL_SIZE);}
		            break;
		    }
		    repaint();
		
	}
	
	private void pause()
    {
        paused = !paused;
        if (paused) {
            timer.stop();
     
        } else {
            timer.start();
        }
        //repaint();
    }
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			moveDown();
		
		}
	
	}
}