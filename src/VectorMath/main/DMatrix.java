package VectorMath;

import java.util.Vector;

public class DMatrix implements Matrix {
	int[][] Mat;
	int sizex;
	int sizey;
	MatrixCreator Overseer = new MatrixCreator();
	DMatrix(int[][] Mat, int sizex, int sizey){
		this.Mat = Mat;
		this.sizex = sizex;
		this.sizey = sizey;
	}

	public Matrix mult(int k) {
		int[][] NMat = new int[sizex][sizey];
		for(int i =0; i<sizex; i++){
			for(int j =0; j<sizey; j++){
				NMat[i][j] = k*Mat[i][j];
			}
		}
		DMatrix NDMatrix = new DMatrix(NMat, sizex, sizey);
		return NDMatrix;
	}
	public Matrix Msum(Matrix S){
		if((S.getSizex()!=sizex)||(S.getSizey()!=sizey)){
			System.out.println("WRONG!");
			return Overseer.CreateDZero(sizex, sizey);
		}else{
		int[][] NMat = new int[sizex][sizey];
		for(int i =0; i<sizex; i++){
			for(int j =0; j<sizey; j++){
				NMat[i][j] = Mat[i][j]+S.getValue(i, j);
			}
		}
		DMatrix NDMatrix = new DMatrix(NMat, sizex, sizey);
		return NDMatrix;
		}
	}
	public Matrix Mmult(Matrix M){
		if(M.getSizex()!=sizey){
			System.out.println("WRONG!");
			return Overseer.CreateDZero(sizex, sizey);
		}else{
		int[][] NMat = new int[sizex][M.getSizey()];
		for(int i=0; i<sizex; i++){
			for(int j=0; j<M.getSizey(); j++){
				for(int k=0; k<sizey; k++){
				NMat[i][j] += Mat[i][k]*M.getValue(k, j);
				}
			}
		}
		DMatrix NDMatrix = new DMatrix(NMat, sizex, M.getSizey());
		return NDMatrix;
		}
	}
	public int getValue(int n, int m) {
		return Mat[n][m];
	}
	public int getSizex(){
		return sizex;
	}
	public int getSizey(){
		return sizey;
	}
	public CellList Cells(){
		Cell temp;
		Vector<Cell> c = new Vector<Cell>();
		CellList cells = new CellList(c);
		for(int i =0; i<sizex; i++){
			for(int j=0; j<sizey; j++){
				if(Mat[i][j]!=0){
					temp = new Cell(i, j, Mat[i][j]);
					cells.AddLast(temp);
				}
			}
		}
		return cells;
	}
}
