package VectorMath;

import java.util.Vector;

public class SMatrix implements Matrix {
	CellList Mat;
	int sizex;
	int sizey;
	MatrixCreator Overseer = new MatrixCreator();
	SMatrix(CellList Mat, int sizex, int sizey){
		this.Mat = Mat.clone();
		this.sizex = sizex;
		this.sizey = sizey;
	}
	
	public Matrix mult(int k) {
		Cell temp;
		Vector<Cell> cells = new Vector<Cell>();
		CellList NMat = new CellList(cells);
		for(int i=0; i<Mat.Size(); i++){
			temp = Mat.getCell(i);
			temp.setValue(temp.getValue()*k);
			NMat.AddLast(temp);
		}
		SMatrix NSMatrix = new SMatrix(NMat, sizex, sizey);
		return NSMatrix;
	}

	public int getValue(int n, int m) {
		int k = Mat.find(n, m);
		if(k==-1){
			return 0;
		}else{
		return Mat.getCell(k).getValue();
		}
	}

	public Matrix Msum(Matrix S) {
		if((S.getSizex()!=sizex)||(S.getSizey()!=sizey)){
			System.out.println("WRONG!");
			return Overseer.CreateSZero(sizex, sizey);
		}else{
		CellList NMat = Mat.Merge(S.Cells());	
		SMatrix NSMatrix = new SMatrix(NMat, sizex, sizey);
		return NSMatrix;
		}
	}

	public Matrix Mmult(Matrix M) {
		if(M.getSizex()!=sizey){
			System.out.println("WRONG!");
			return Overseer.CreateSZero(sizex, sizey);
		}else{
		Cell temp; 
		CellList ncells = Overseer.CreateZeroCellList();
		CellList mcells = M.Cells();
		for(int i=0; i<Mat.Size(); i++){
			for(int j=0; j<mcells.Size(); j++){
				if(Mat.getCell(i).gety()==mcells.getCell(j).getx()){
					temp = new Cell(Mat.getCell(i).getx(), mcells.getCell(j).gety(), Mat.getCell(i).getValue()*mcells.getCell(j).getValue());
					ncells.Add(temp);
				}
			}
		}
		SMatrix NMat = new SMatrix(ncells, sizex, M.getSizey());
		return NMat;
		}
	}

	public int getSizex(){
		return sizex;
	}
	public int getSizey(){
		return sizey;
	}
	public CellList Cells(){
		return Mat;
	}
}
