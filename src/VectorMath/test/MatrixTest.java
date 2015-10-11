package VectorMath;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

//тестирование матриц
public class MatrixTest {
	Random rand;
	MatrixCreator Queen = new MatrixCreator();
	
	//тестирование операций суммы
	@Test
	public void Sumtest() {
		for(int i=0; i<5; i++){
			Matrix A = Queen.CreateRMatrix(100, 100);
			Matrix B = Queen.CreateRMatrix(100, 100);
			Matrix S = A.Msum(B);
			for(int j=0; j<100; j++){
				for(int k=0; k<100; k++){
					assertTrue("Too lazy for summing!", S.getValue(j, k)==A.getValue(j, k)+B.getValue(j, k));
				}
			}
			}
		for(int i=0; i<5; i++){
		Matrix A = Queen.GenerateRDMatrix(1000, 1000);
		Matrix B = Queen.GenerateRDMatrix(1000, 1000);
		Matrix S = new LazyMatrixSum(A, B);
		for(int j=0; j<1000; j++){
			for(int k=0; k<1000; k++){
				assertTrue("Too lazy for summing!", S.getValue(j, k)==A.getValue(j, k)+B.getValue(j, k));
			}
		}
		}
		for(int i=0; i<5; i++){
			Matrix A = Queen.GenerateRDMatrix(1000, 1000);
			Matrix B = Queen.GenerateRDMatrix(1000, 1000);
			Matrix C = Queen.GenerateRDMatrix(1000, 1000);
			Matrix S = new LazyMatrixSum(A, B);
			Matrix SS = S.Msum(C);
			for(int j=0; j<1000; j++){
				for(int k=0; k<1000; k++){
					assertTrue("Too lazy for summing!", SS.getValue(j, k)==A.getValue(j, k)+B.getValue(j, k)+C.getValue(j, k));
				}
			}
			}
		for(int i=0; i<5; i++){
			Matrix A = Queen.CreateSMatrix(100, 100);
			Matrix B = Queen.CreateSMatrix(100, 100);
			Matrix S = new LazyMatrixSum(A, B);
			for(int j=0; j<100; j++){
				for(int k=0; k<100; k++){
					assertTrue("Too lazy for summing!", S.getValue(j, k)==A.getValue(j, k)+B.getValue(j, k));
				}
			}
			}
		for(int i=0; i<5; i++){
			Matrix A = Queen.CreateSMatrix(100, 100);
			Matrix B = Queen.GenerateRDMatrix(100, 100);
			Matrix S = new LazyMatrixSum(A, B);
			for(int j=0; j<100; j++){
				for(int k=0; k<100; k++){
					assertTrue("Too lazy for summing!", S.getValue(j, k)==A.getValue(j, k)+B.getValue(j, k));
				}
			}
			}
	}
	
	//тестирование операций умножения
	@Test
	public void Multtest() {
		for(int i=0; i<5; i++){
			rand = new Random();
			Matrix A = Queen.GenerateRDMatrix(150, 200);
			int m = rand.nextInt(17);
			Matrix S = A.mult(m);
			for(int j=0; j<150; j++){
				for(int k=0; k<200; k++){
					assertTrue("Too lazy for multiplying!", S.getValue(j, k)==m*A.getValue(j, k));
				}
			}
			}
		for(int i=0; i<5; i++){
			rand = new Random();
			Matrix A = Queen.CreateSMatrix(150, 200);
			int m = rand.nextInt(17);
			Matrix S = A.mult(m);
			for(int j=0; j<150; j++){
				for(int k=0; k<200; k++){
					assertTrue("Too lazy for multiplying!", S.getValue(j, k)==m*A.getValue(j, k));
				}
			}
			}
		for(int i=0; i<5; i++){
			Matrix A = Queen.GenerateRDMatrix(30, 20);
			Matrix B = Queen.GenerateRDMatrix(20, 15);
			Matrix S = new LazyMatrixMultiple(A, B);
			for(int j=0; j<30; j++){
				for(int k=0; k<15; k++){
					int val=0;
					for(int l=0; l<20; l++){
						val+=A.getValue(j, l)*B.getValue(l, k);
					}
					assertTrue("Too lazy for multiplying!", S.getValue(j, k)==val);
				}
			}
			}
		for(int i=0; i<5; i++){
			Matrix A = Queen.CreateRMatrix(30, 20);
			Matrix B = Queen.CreateRMatrix(20, 15);
			Matrix S = A.Mmult(B);
			for(int j=0; j<30; j++){
				for(int k=0; k<15; k++){
					int val=0;
					for(int l=0; l<20; l++){
						val+=A.getValue(j, l)*B.getValue(l, k);
					}
					assertTrue("Too lazy for multiplying!", S.getValue(j, k)==val);
				}
			}
			}
		for(int i=0; i<5; i++){
			Matrix A = Queen.GenerateRDMatrix(30, 20);
			Matrix B = Queen.GenerateRDMatrix(20, 15);
			Matrix C = Queen.GenerateRDMatrix(15, 10);
			Matrix S = new LazyMatrixMultiple(A, B);
			Matrix SS = S.Mmult(C);
			for(int j=0; j<30; j++){
				for(int k=0; k<10; k++){
					int val=0;
					for(int l=0; l<20; l++){
						for(int m=0; m<15;m++){
							val+=A.getValue(j, l)*B.getValue(l, m)*C.getValue(m, k);
						}
					}
					assertTrue("Too lazy for multiplying!", SS.getValue(j, k)==val);
				}
			}
			}
		for(int i=0; i<5; i++){
			Matrix A = Queen.GenerateRDMatrix(30, 20);
			Matrix B = Queen.CreateSMatrix(20, 15);
			Matrix S = new LazyMatrixMultiple(A, B);
			for(int j=0; j<30; j++){
				for(int k=0; k<15; k++){
					int val=0;
					for(int l=0; l<20; l++){
						val+=A.getValue(j, l)*B.getValue(l, k);
					}
					assertTrue("Too lazy for multiplying!", S.getValue(j, k)==val);
				}
			}
			}
	}
	
	//тестирование дистибутивности
	@Test
	public void Distribtest(){
		for(int i=0; i<10; i++){
			Matrix A = Queen.CreateRMatrix(20, 20);
			Matrix B = Queen.CreateRMatrix(20, 20);
			Matrix D = Queen.CreateRMatrix(20, 20);
			Matrix S = A.Msum(B).Mmult(D);
			Matrix R = A.Mmult(D).Msum(B.Mmult(D));
			for(int j=0; j<20; j++){
				for(int k=0; k<20; k++){
					assertTrue("Too lazy for summing!", S.getValue(j, k)==R.getValue(j, k));
				}
			}
			}
		for(int i=0; i<10; i++){
			Matrix A = Queen.CreateRMatrix(20, 20);
			Matrix B = Queen.CreateRMatrix(20, 20);
			Matrix D = Queen.CreateRMatrix(20, 20);
			Matrix S = A.Mmult(B.Msum(D));
			Matrix R = A.Mmult(B).Msum(A.Mmult(D));
			for(int j=0; j<20; j++){
				for(int k=0; k<20; k++){
					assertTrue("Too lazy for summing!", S.getValue(j, k)==R.getValue(j, k));
				}
			}
			}
	}

}
