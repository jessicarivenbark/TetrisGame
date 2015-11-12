import java.awt.Color;

public class T_Shape extends Shape{

	public T_Shape () {
		super(Color.cyan);
		
	}
	
	public void initPositions(){
		position1 = new Block [] {new Block(0, 0), new Block(Block.SIZE, 0), 
				new Block(Block.SIZE, Block.SIZE), new Block(2*Block.SIZE, 0)};
		position2 = new Block [] {new Block(0, 0), new Block(0, Block.SIZE), 
				new Block(0, 2*Block.SIZE), new Block(Block.SIZE, Block.SIZE)};
		position3 = new Block [] { new Block(0, Block.SIZE), new Block(Block.SIZE, 0), 
				new Block(Block.SIZE, Block.SIZE), new Block(2*Block.SIZE, Block.SIZE)};
		position4 = new Block [] {new Block(0, Block.SIZE), new Block(Block.SIZE, 0),  
				new Block(Block.SIZE, Block.SIZE), new Block(Block.SIZE, 2*Block.SIZE)};
	}
	
}
