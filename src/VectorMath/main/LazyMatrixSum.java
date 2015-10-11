package VectorMath;

import java.util.Vector;

public class LazyMatrixSum implements Matrix {
	Matrix A;
	Matrix B;
	int sizex;
	int sizey;
	MatrixCreator Overseer = new MatrixCreator();
	LazyMatrixSum(Matrix A, Matrix B){
		if((A.getSizex()!=B.getSizex())||(A.getSizey()!=B.getSizey())){
			System.out.println("WRONG!");
			this.A = Overseer.CreateDZero(A.getSizex(), A.getSizey());
			this.B = Overseer.CreateDZero(B.getSizex(), B.getSizey());
		}else{
			this.A = A;
			this.B = B;
			this.sizex = A.getSizex();
			this.sizey = A.getSizey();
		}
	}
	public Matrix mult(int k) {
		Matrix NMat = new LazyMatrixSum(A.mult(k), B.mult(k));
		return NMat;
	}
	public Matrix Msum(Matrix S){
		Matrix NMat = new LazyMatrixSum(this, S);
		return NMat;
	}
	public Matrix Mmult(Matrix M){
		if(M.getSizex()!=sizey){
			System.out.println("WRONG!");
			return Overseer.CreateDZero(sizex, sizey);
		}else{
		Matrix NMat = new LazyMatrixSum(A.Mmult(M), B.Mmult(M));
		return NMat;
		}
	}
	public int getValue(int n, int m) {
		return A.getValue(n, m) + B.getValue(n, m);
	}
	public int getSizex(){
		return sizex;
	}
	public int getSizey(){
		return sizey;
	}
	public CellList Cells(){
		return A.Msum(B).Cells();
	}
}
