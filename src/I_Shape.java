import java.awt.Color;

public class I_Shape extends Shape{

	public I_Shape () {
		super(Color.pink);		
	}
	
	public void initPositions(){
		position1 = new Block [] {new Block(0, 0), new Block(0, Block.SIZE), 
				new Block(0, 2*Block.SIZE), new Block(0, 3*Block.SIZE)};
		position3 = new Block [] {new Block(0, 0), new Block(0, Block.SIZE), 
				new Block(0, 2*Block.SIZE), new Block(0, 3*Block.SIZE)};
		position2 = new Block [] {new Block(0, 0), new Block(Block.SIZE, 0), 
				new Block(2*Block.SIZE, 0), new Block(3*Block.SIZE, 0)};
		position4 = new Block [] {new Block(0, 0), new Block(Block.SIZE, 0), 
				new Block(2*Block.SIZE, 0), new Block(3*Block.SIZE, 0)};
	}
}

