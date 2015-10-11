package VectorMath;

public class LazyMatrixMultiple implements Matrix {
	Matrix A;
	Matrix B;
	int sizex;
	int sizey;
	int length;
	MatrixCreator Overseer = new MatrixCreator();
	LazyMatrixMultiple(Matrix A, Matrix B){
		if(B.getSizex()!=A.getSizey()){
			System.out.println("WRONG!");
			this.A = Overseer.CreateDZero(A.getSizex(), A.getSizey());
			this.B = Overseer.CreateDZero(B.getSizex(), B.getSizey());
		}else{
			this.A = A;
			this.B = B;
			this.sizex = A.getSizex();
			this.sizey = B.getSizey();
			this.length = A.getSizey();
		}
	}
	
	public Matrix mult(int k) {
		Matrix NMat = new LazyMatrixMultiple(A.mult(k), B);
		return NMat;
	}

	public int getValue(int n, int m) {
		int s = 0;
		for(int i=0; i<length; i++){
			s+= A.getValue(n, i)*B.getValue(i, m);
		}
		return s;
	}

	public Matrix Msum(Matrix S) {
		Matrix NMat = new LazyMatrixSum(this, S);
		return NMat;
	}

	public Matrix Mmult(Matrix M) {
		Matrix NMat = new LazyMatrixMultiple(this, M);
		return NMat;
	}

	public int getSizex(){
		return sizex;
	}
	public int getSizey(){
		return sizey;
	}
	public CellList Cells(){
		return A.Mmult(B).Cells();
	}
}
