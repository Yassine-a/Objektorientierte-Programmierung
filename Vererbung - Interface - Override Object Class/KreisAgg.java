package Praktikum13;

public class KreisAgg implements Intergesicht {
		Point p;
		double r;
		KreisAgg(){}
		KreisAgg(Point p, double r){this.p = p; this.r = r;}
		KreisAgg(KreisAgg k){this.p.x = k.p.x; this.p.y = k.p.y; this.r = k.r;}
		
		
		void setKreis(double r) {this.r = r;}
		double getKreis() { return this.r;}
		
		void setLocation(Point p) {this.p.x = p.x; this.p.y = p.y;}
		void setLocation(int x,int y) {this.p.x = x; this.p.y = p.y;}
		
		Point getLocation() {return this.p;}
		
		public double getSurfaceArea() {return Math.PI * r * r;}
		boolean equals(KreisAgg k) {return (this.p.x == k.p.x && this.p.y == k.p.y && this.r == k.r);}
		public String toString() {return p.toString()+" Radius = "+ r + "Flächeninhalt = "+ this.getSurfaceArea(); }
		public KreisAgg clone() { return new KreisAgg(this.p,r);	}
}
