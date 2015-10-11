package VectorMath;

public interface Matrix {
	Matrix mult(int k); //��������� �� �����
	public int getValue(int n, int m); //��������� �������� ������
	Matrix Msum(Matrix S); //����� ������
	Matrix Mmult(Matrix M); //��������� ������
	int getSizex(); //������ �� x
	int getSizey(); //������ �� y
	CellList Cells(); //��������� ������� � ���� ������ �� �����
}
