package Praktikum13;

public class Point {
	int x = 0;
	int y = 0;
	
	
	Point(){}
	Point(Point p){				this.x = p.x;	this.y = p.y; }
	Point(int x,int y){		this.x = x;		this.y = y;}

	Point getLocation() {Point p = new Point(); p.x = this.x; p.y = this.y; return p;}
	void setLocation(Point p) {this.x = p.x; this.y = p.y;}
	void setLocation(int x, int y) {this.x = x; this.y = y;}
	
	void move(int dx, int dy) {this.x += dx; this.y += dy;}
	boolean equals(Point p) {return (this.x ==p.x&& this.y == p.y);}
	
	public String toString() {return "x= " + x + ", y = "+y ; }
	
	public Point clone() {
		return new Point(this);
	}
	
}

	