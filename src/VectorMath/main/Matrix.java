package VectorMath;

public interface Matrix {
	Matrix mult(int k); //умножение на число
	public int getValue(int n, int m); //получение значения ячейки
	Matrix Msum(Matrix S); //сумма матриц
	Matrix Mmult(Matrix M); //умножение матриц
	int getSizex(); //размер по x
	int getSizey(); //размер по y
	CellList Cells(); //получение матрицы в виде списка ее ячеек
}
