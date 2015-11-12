import java.awt.Color;

public class J_Shape extends Shape{

	public J_Shape () {
		super(Color.blue);
		
	}
	
	public void initPositions(){
		position1 = new Block [] {new Block(Block.SIZE, 0), new Block(Block.SIZE, Block.SIZE), 
				new Block(0, 2*Block.SIZE), new Block(Block.SIZE, 2*Block.SIZE)};
		position2 = new Block [] {new Block(0, 0), new Block(Block.SIZE, 0), 
				new Block(2*Block.SIZE, 0), new Block(2*Block.SIZE, Block.SIZE)};
		position3 = new Block [] {new Block(0, 0), new Block(0, Block.SIZE), 
				new Block(0, 2*Block.SIZE), new Block(Block.SIZE, 0)};
		position4 = new Block [] {new Block(0, 0), new Block(0, Block.SIZE), 
				new Block(Block.SIZE, Block.SIZE), new Block(2*Block.SIZE, Block.SIZE)};
	}
	
}
