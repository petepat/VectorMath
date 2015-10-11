package VectorMath;

import java.util.Vector;


//—писок клеток дл€ разреженных матриц в пор€дке возрастани€ по месту
public class CellList {
	Vector<Cell> cells; 
	public CellList(Vector<Cell> cells){
		this.cells = cells;
	}
	
	//ƒобавление клетки в нужное место (если уже есть така€, то значени€ складываютс€!!!)
	public void Add(Cell c){
		Cell temp;
		temp=c.clone();
		if(cells.size()==0){
			cells.add(temp);
		}else{
		int k = pos(c.getx(), c.gety());
		if(k==-1){
			cells.insertElementAt(temp, 0);
		}else{
		if((cells.get(k).getx()==c.getx())&&(cells.get(k).gety()==c.gety())){
			cells.get(k).setValue(cells.get(k).getValue()+c.getValue());
		}else{
			cells.insertElementAt(temp, k+1);
		}
		}
		}
	}
	
	//ƒобавление клетки последней (экономит врем€, если € уверен, что она должна идти последней)
	public void AddLast(Cell c){
		Cell temp;
		temp=c.clone();
		cells.add(temp);
	}
	
	//»щем, есть ли клетка с заданной позицией. ≈сли есть, выводим ее номер. ≈сли нет, выводим последнюю, котора€ по позиции перед ней. 
	public int pos(int i, int j){
		if(cells.size()==0){
			return -1;
		}else{
		Cell temp;
		int str = -1;
		int fin = cells.size();
		while(fin > str){
			int k = (str + fin)/2;
			temp = cells.get(k);
			if((temp.getx()==i)&&(temp.gety()==j)){
				return k;
			}else{
				if((temp.getx()>i)||((temp.getx()==i)&&(temp.gety()>j))){
					if(fin>k){
						fin = k;
					}else{
						return str;
					}
				}else{
					if(k>str){
						str = k;
					}else{
						return k;
					}
				}
			}
		}
		return str;
		}
	}
	
	//ѕросто ищем клетку с заданной позицией
	public int find(int i, int j){
		if(cells.size()==0){
			return -1;
		}else{
		int k = pos(i, j);
		if(k==-1){
			return -1;
		}else{
		if((cells.get(k).getx()==i)&&(cells.get(k).gety()==j)){
			return k;
		}else{
			return -1;
		}
		}
		}
	}
	
	//–азмер
	public int Size(){
		return cells.size();
	}
	
	//ѕолучение клетки по номеру
	public Cell getCell(int k){
		Cell temp;
		temp = cells.get(k).clone();
		return temp;
	}
	
	// лонирование списка
	public CellList clone(){
		Vector<Cell> Ncells = (Vector<Cell>) cells.clone();
		CellList ncells = new CellList(Ncells);
		return ncells;
	}
	
	//—ли€ние двух списков клеток
	public CellList Merge(CellList cells2){
		Cell temp;
		Vector<Cell> merg = new Vector<Cell>(); 
		int i=0;
		int j=0;
		if(cells.size()==0){
			return cells2.clone();
		}
		if(cells2.Size()==0){
			CellList C = this.clone();
			return C;
		}
		while((i<cells.size())||(j<cells2.Size())){
			if((i<cells.size())&&(j<cells2.Size())){
			if((cells.get(i).getx()==cells2.getCell(j).getx())&&(cells.get(i).gety()==cells2.getCell(j).gety())){
				temp = new Cell(cells.get(i).getx(), cells.get(i).gety(), cells.get(i).getValue()+cells2.getCell(j).getValue());
				merg.add(temp);
				i+=1;
				j+=1;
			}else{
				if((cells.get(i).getx()>cells2.getCell(j).getx())||((cells.get(i).gety()>cells2.getCell(j).gety())&&(cells.get(i).getx()==cells2.getCell(j).getx()))){
					temp = cells2.getCell(j).clone();
					merg.add(temp);
					j+=1;
				}else{
					temp = cells.get(i).clone();
					merg.add(temp);
					i+=1;
				}
			}
			}else{
				if(i>=cells.size()){
						temp = cells2.getCell(j).clone();
						merg.add(temp);
						j+=1;
				}else{
						temp = cells.get(i).clone();
						merg.add(temp);
						i+=1;
				}
			}
		}
		CellList C = new CellList(merg);
		return C;
	}
}
