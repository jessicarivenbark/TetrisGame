
public class Block {
	private int x;
	private int y;
	public final static int SIZE = 15;
	
	public Block (int Xcoord, int Ycoord){
		x = Xcoord;
		y = Ycoord;
	}

	public int getX() {
		return x;
	}

	public void setX(int Xcoord) {
		x = Xcoord;
	}

	public int getY() {
		return y;
	}

	public void setY(int Ycoord) {
		y = Ycoord;
	}
	
}
