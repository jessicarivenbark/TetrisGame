import java.awt.Color;

public class S_Shape extends Shape{

	public S_Shape () {
		super(Color.yellow);
		
	}
	
	public void initPositions(){
		position1 = new Block [] {new Block(0, Block.SIZE), new Block(Block.SIZE, Block.SIZE), 
				new Block(Block.SIZE, 0), new Block(2*Block.SIZE, 0)};
		position3 = new Block [] {new Block(0, Block.SIZE), new Block(Block.SIZE, Block.SIZE), 
				new Block(Block.SIZE, 0), new Block(2*Block.SIZE, 0)};
		position2 = new Block [] {new Block(0, 0), new Block(0, Block.SIZE), 
				new Block(Block.SIZE, Block.SIZE), new Block(Block.SIZE, 2*Block.SIZE)};
		position4 = new Block [] {new Block(0, 0), new Block(0, Block.SIZE), 
				new Block(Block.SIZE, Block.SIZE), new Block(Block.SIZE, 2*Block.SIZE)};
		
	}
}

