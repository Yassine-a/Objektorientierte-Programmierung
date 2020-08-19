package Praktikum13;

public class Main {

	public static void main(String[] agrs)
	{
		Intergesicht[] obj = new Intergesicht[4];
		
		Point p1 = new Point(1,2);
		Point p2 = new Point(2,3);
		Point p3 = new Point(3,4);
		
		Rechteck rex1 = new Rechteck(p1,p2);
		Rechteck rex2 = new Rechteck(p2,p3);
		
		
		KreisAgg kex1 = new KreisAgg(p1,2);
		KreisAgg kex2 = new KreisAgg(p2,1);
		
		obj[0] = rex1;
		obj[1] = rex2;
		obj[2] = kex1;
		obj[3] = kex2;
		
		double wholeSurfaceArea = 0;
		
		for(int i = 0; i<4;i++)
		{
			wholeSurfaceArea += obj[i].getSurfaceArea();
		}
		
		System.out.println("Gesamtflächeninhalt = "+wholeSurfaceArea);
		
	}
}
