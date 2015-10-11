package VectorMath;


//ячейка матрицы
public class Cell {
	private int x;
	private int y;
	private int value;
	public Cell(int x, int y, int Value){
		this.x = x;
		this.y = y;
		this.value = Value;
	}
	public int getValue(){
		return value;
	}
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	public void setValue(int NewValue){
		value = NewValue;
	}
	public Cell clone(){
		Cell c = new Cell(x, y, value);
		return c;
	}
}
