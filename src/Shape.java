import java.awt.Color;
import java.util.Random;

abstract class Shape {
	protected Color color;
	protected Block [] position1;
	protected Block [] position2;
	protected Block [] position3;
	protected Block [] position4;
	protected Block [] currentPosition;

	public Shape (Color shapeColor){
		color = shapeColor;
		initPositions();
		Random random = new Random();
		int position = random.nextInt(4) + 1;
		if (position == 1){currentPosition = position1;}
		else if (position == 2){currentPosition = position2;}
		else if (position == 3){currentPosition = position3;}
		else if (position == 4){currentPosition = position4;}
	}
	
	abstract void initPositions();
	
	public void rotateLeft(){
		if (currentPosition == position1){currentPosition = position2;}
		else if (currentPosition == position2){currentPosition = position3;}
		else if (currentPosition == position3){currentPosition = position4;}
		else if (currentPosition == position4){currentPosition = position1;}
	}
	
	public void rotateRight(){
		if (currentPosition == position1){currentPosition = position4;}
		else if (currentPosition == position2){currentPosition = position1;}
		else if (currentPosition == position3){currentPosition = position2;}
		else if (currentPosition == position4){currentPosition = position3;}
	}

	public Block[] getPosition1() {
		return position1;
	}

	public Block[] getPosition2() {
		return position2;
	}

	public Block[] getPosition3() {
		return position3;
	}

	public Block[] getPosition4() {
		return position4;
	}

	public Block[] getCurrentPosition() {
		return currentPosition;
	}

	public Color getColor(){
		return color;
	}
	
	public void updateX(int Xcoord){
		for (Block block : position1){
			block.setX(block.getX() + Xcoord);
		}
		
		for (Block block : position2){
			block.setX(block.getX() + Xcoord);
		}
		
		for (Block block : position3){
			block.setX(block.getX() + Xcoord);
		}
		
		for (Block block : position4){
			block.setX(block.getX() + Xcoord);
		}
	}
	
	public void updateY(int Ycoord){
		for (Block block : position1){
			block.setY(block.getY() + Ycoord);
		}
		
		for (Block block : position2){
			block.setY(block.getY() + Ycoord);
		}
		
		for (Block block : position3){
			block.setY(block.getY() + Ycoord);
		}
		
		for (Block block : position4){
			block.setY(block.getY() + Ycoord);
		}
	}
	
	public int maxY(){
		int maxY = 0;
    	for (Block block : currentPosition){
    		if(block.getY() > maxY){maxY = block.getY();}
    	}
    	return maxY;
	}
	
	public int maxX(){
		int maxX = 0;
    	for (Block block : currentPosition){
    		if(block.getX() > maxX){maxX = block.getX();}
    	}
    	return maxX;
	}
	
	public int minX(){
		int minX = 10000000;
    	for (Block block : currentPosition){
    		if(block.getX() < minX){minX = block.getX();}
    	}
    	return minX;
	}
}
