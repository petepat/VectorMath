package VectorMath;

import java.util.Random;
import java.util.Vector;

public class MatrixCreator {
	Random rand;
	
	//создание нулевой плотной матрицы
	public Matrix CreateDZero(int sizex, int sizey){
		int[][] Z = new int[sizex][sizey];
		for(int i=0; i<sizex; i++){
			for(int j =0; j<sizey; j++){
				Z[i][j] = 0;
			}
		}
		DMatrix Zero = new DMatrix(Z, sizex, sizey);
		return Zero;
	}
	
	//создание "случайной" плотной матрицы с заданным начальным числом 
	public Matrix GenerateDMatrix(int sizex, int sizey, long seed){
		rand = new Random(seed);
		int[][] Mat = new int[sizex][sizey];
		for(int i=0; i<sizex; i++){
			for(int j =0; j<sizey; j++){
				Mat[i][j] = rand.nextInt(100000);
			}
		}
		DMatrix NMat = new DMatrix(Mat, sizex, sizey);
		return NMat;
	}
	
	//создание реально случайной матрицы
	public Matrix GenerateRDMatrix(int sizex, int sizey){
		rand = new Random();
		int[][] Mat = new int[sizex][sizey];
		for(int i=0; i<sizex; i++){
			for(int j =0; j<sizey; j++){
				Mat[i][j] = rand.nextInt(100000);
			}
		}
		DMatrix NMat = new DMatrix(Mat, sizex, sizey);
		return NMat;
	}
	
	//создание нулевой разреженной матрицы
	public Matrix CreateSZero(int sizex, int sizey){
		Vector<Cell> cells = new Vector<Cell>();
		CellList Z = new CellList(cells);
		SMatrix Zero = new SMatrix(Z, sizex, sizey);
		return Zero;
	}
	
	//создание нулевого списка ячеек
	public CellList CreateZeroCellList(){
		Vector<Cell> cells = new Vector<Cell>();
		CellList Z = new CellList(cells);
		return Z;
	}
	
	//создание случайного списка ячеек
		public CellList CreateRCellList(int length, int sizex, int sizey){
			rand = new Random();
			Cell temp;
			Vector<Cell> c = new Vector<Cell>();
			CellList cells = new CellList(c);
			for(int i=0; i<length; i++){
				temp = new Cell(rand.nextInt(sizex), rand.nextInt(sizey), rand.nextInt(100000));
				cells.Add(temp);
			}
			return cells;
		}
		//создание случайной разреженной матрицы <5000 ячеек
		public Matrix CreateSMatrix(int sizex, int sizey){
			rand = new Random();
			CellList cells = this.CreateRCellList(rand.nextInt(5000), sizex, sizey);
			SMatrix Zero = new SMatrix(cells, sizex, sizey);
			return Zero;
		}
		//создание случайной клетки
				public Cell CreateRCell(int sizex, int sizey){
					rand = new Random();
					Cell temp;
					temp = new Cell(rand.nextInt(sizex), rand.nextInt(sizey), rand.nextInt(100000));
					return temp;
				}
		public Matrix CreateRMatrix(int sizex, int sizey){
			rand = new Random();
			int type = rand.nextInt(1);
			if(type==1){
				return this.GenerateRDMatrix(sizex, sizey);
			}else{
				return this.CreateSMatrix(sizex, sizey);
			}
		}
}
