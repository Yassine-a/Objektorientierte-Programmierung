package Praktikum13;

public class Rechteck implements Intergesicht {

	Point p1 = new Point();
	Point p2 = new Point();
	
	Rechteck(){}
	
	Rechteck(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	Rechteck(Rechteck rechteck)
	{
		this.p1.x = rechteck.p1.x;
		this.p2.x = rechteck.p2.x;
		this.p1.y = rechteck.p1.y;
		this.p2.y = rechteck.p2.y; }
	
	void setPoint1(int x, int y)	{p1.x = x; p1.y = y;}
	void setPoint2(int x, int y)	{ p2.x = x; p2.y = y;	}
	
	void setPoint1(Point p) {this.p1.x = p.x; this.p1.y = p.y;}
	void setPoint2(Point p) {this.p2.x = p.x; this.p2.y = p.y;}
	
	Point getPoint1() {return p1.clone();}
	Point getPoint2() {return p2.clone();}
	
	public double getSurfaceArea() {return Math.abs(p1.x-p2.x)*Math.abs(p2.y-p1.y);}
	
	boolean equals(Rechteck r) {return (this.p1.x == r.p1.x && this.p1.y == r.p1.y && this.p2.x == r.p2.x && this.p2.y == r.p2.y);}
	
	public String toString() {return  "x1 = " + p1.x + ", y1 = "+ p1.y + "x2 = " + p2.x + ", y2 = "+ p2.y + "Flächeninhalt = "+ this.getSurfaceArea(); }
	
	public Rechteck clone() { return new Rechteck(this.p1,this.p2);	}
	
	}
		