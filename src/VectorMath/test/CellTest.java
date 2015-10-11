package VectorMath;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;


//тестирование работы списка клеток
public class CellTest {
	Random rand;
	MatrixCreator Queen = new MatrixCreator();
	
	
	//тестирование функции, которая ищет позицию клетки
	@Test
	public void Postest() {
		CellList TestList;
		Cell temp;
		rand = new Random();
		for(int i=0; i<5; i++){
			int length = rand.nextInt(5000)+1;
			TestList = Queen.CreateRCellList(length, 10000, 10000);
			temp = Queen.CreateRCell(10000, 10000);
			TestList.Add(temp);
			int k = TestList.Size()-1;
			while((TestList.getCell(k).getx()>temp.getx())||(TestList.getCell(k).gety()>temp.gety())){
				k-=1;
			}
			assertTrue("wrong position", TestList.find(temp.getx(), temp.gety()) == k);
		}
	}
	
	//тестирование функций добавления клеток
	@Test
	public void Addtest() {
		CellList TestList;
		Cell temp;
		rand = new Random();
		for(int i=0; i<5; i++){
			//тестирование добавления одной клетки
			TestList = Queen.CreateZeroCellList();
			temp = Queen.CreateRCell(10000, 10000);
			TestList.Add(temp);
			assertTrue("You can't add even one cell!", TestList.getCell(0).getx()==temp.getx());		
			assertTrue("You can't add even one cell!", TestList.getCell(0).gety()==temp.gety());	
			assertTrue("You can't add even one cell!", TestList.getCell(0).getValue()==temp.getValue());	
		}
		for(int i=0; i<5; i++){
			//тестирование создания списка
			int length = rand.nextInt(5000)+1;
			TestList = Queen.CreateRCellList(length, 10000, 10000);
			length = TestList.Size();
			//System.out.println(length);
			for(int j=0; j<length-1; j++){
				//System.out.println(TestList.getCell(j).getx());
				//System.out.println(TestList.getCell(j).gety());
				//System.out.println(TestList.getCell(j+1).getx());
				//System.out.println(TestList.getCell(j+1).gety());
			assertTrue("You adding don't sort them right!", (TestList.getCell(j+1).getx()>TestList.getCell(j).getx())||((TestList.getCell(j+1).getx()==TestList.getCell(j).getx())&&(TestList.getCell(j+1).gety()>=TestList.getCell(j).gety())));
			}
		}
	}
	
	
	//тестирование слияния списков
	@Test
	public void Mergetest() {
		CellList TestList1;
		CellList TestList2;
		CellList TestList3;
		CellList TestList4;
		rand = new Random();
		for(int i=0; i<5; i++){
			//тестирование с добавлением 3 клеток вручную
			Cell temp;
			TestList1 = Queen.CreateZeroCellList();
			TestList2 = Queen.CreateZeroCellList();
			TestList3 = Queen.CreateZeroCellList();
			temp = new Cell(15, 3, 1111);
			TestList1.Add(temp);
			TestList3.Add(temp);
			temp = new Cell(15, 3, 1010);
			TestList2.Add(temp);
			TestList3.Add(temp);
			temp = new Cell(16, 3, 10);
			TestList2.Add(temp);
			TestList3.Add(temp);
			assertTrue("WTF?", TestList1.Merge(TestList2).getCell(0).getValue()==TestList3.getCell(0).getValue());
		}
		for(int i=0; i<5; i++){
			//тестирование слияния двух сгенерированных списков
			int length1 = rand.nextInt(5000)+1;
			int length2 = rand.nextInt(5000)+1;
			TestList1 = Queen.CreateRCellList(length1, 10000, 10000);
			TestList2 = Queen.CreateRCellList(length2, 10000, 10000);
			TestList3 = TestList1.clone();
			TestList4 = TestList2.clone();
			length1 = TestList1.Size();
			length2 = TestList2.Size();
			//System.out.println(length1);
			//System.out.println(length2);
			for(int j=0; j<length2; j++){
				TestList3.Add(TestList4.getCell(j));
			}
			CellList TestListN = TestList1.Merge(TestList2);
			assertTrue("You merge don't work!", TestList3.Size()==TestListN.Size());
			int length = TestList3.Size();
			for(int j=0; j<length; j++){
			assertTrue("You merge don't work!", TestList3.getCell(j).getx()==TestListN.getCell(j).getx());
			assertTrue("You merge don't work!", TestList3.getCell(j).gety()==TestListN.getCell(j).gety());
			assertTrue("You merge don't work!", TestList3.getCell(j).getValue()==TestListN.getCell(j).getValue());
			}
		}
	}

}
