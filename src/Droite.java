

import java.util.ArrayList;

public class Droite implements Forme{
	protected float a;
	protected float b;
	protected float c;
	protected Point p1;
	protected Point p2;
	
	
	public Droite (Point p1, Point p2) {
		float a = 0.0f;
		float b = 0.0f;
		float c = 0.0f;
		// Droite verticale
		if(p1.getX() == p2.getX()) {
			a = 1;
			b = 0;
			c = - p1.getX();
		}
		// Droite horizontale
		else if(p1.getY() == p2.getY()) {
			a = 0;
			b = 1;
			c = - p1.getY();
		}
		else {
			a = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());		
			/*if(p2.getX() < p1.getX()) {
				a *= -1;
			}*/
			b = -1;
			c = p1.getY() - a*p1.getX();
		}
		
		this.a = a;
		this.b = b;
		this.c = c;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point pointSuivant(Point p) {
		if(this.estOrdinaire()) {
			return new Point(p.getX()+1, p.getY()+(p2.getY()-p1.getY())/(p2.getX()-p1.getX()));
		}else if(this.estHorizontale()) {
			return new Point(p.getX()+1, p.getY());
		}else if(this.estVerticale()) {
			return new Point(p.getX(), p.getY()+1);
		}
		return null;
	}
	
	public boolean intersection(Forme f1) {
		if(f1 instanceof Droite) {
			return intersectionDroite((Droite) f1);
		}else if(f1 instanceof Segment) {
			return intersectionSegment((Segment) f1);
		}else if(f1 instanceof Triangle) {
			return intersectionTriangle((Triangle) f1);
		}else if(f1 instanceof Rectangle) {
			return intersectionRectangle((Rectangle) f1);
		}else if(f1 instanceof Polygone) {
			return intersectionPolygone((Polygone) f1);
		}		
		return false;
	}

	public boolean contient(Point p1) {
		return this.a*p1.getX() + this.b*p1.getY() + this.c == 0;
	}
	
	public int demiPlan(Point p) {
		if(contient(p)) {
			return 0;
		}else if(p.getY() > this.a*p.getX() + this.c) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	public String toString() {
		return "(" + a + "x + " + b + "y + " + c + " = 0)";
	}

	public ArrayList<Point> PointsIntersection(Forme f1) {
		if(f1 instanceof Droite) {
			return PointsintersectionDroite((Droite) f1);
		}else if(f1 instanceof Segment) {
			return PointsintersectionSegment((Segment) f1);
		}else if(f1 instanceof Triangle) {
			return PointsintersectionTriangle((Triangle) f1);
		}else if(f1 instanceof Rectangle) {
			return PointsintersectionRectangle((Rectangle) f1);
		}else if(f1 instanceof Polygone) {
			return PointsintersectionPolygone((Polygone) f1);
		}		
		return null;
	}
	
	private boolean intersectionDroite(Droite d1) {
		if((this.a*d1.b) - (d1.a*this.b) != 0.0f) {
			return true;
		}else if(this.a == 1 && d1.a== 1 && this.b == 0 && d1.b == 0) {
				return false;
		}else if(this.a == 0 && d1.a== 0 && this.b == 1 && d1.b == 1) {
			return false;
		}else if((this.a == 1 && this.b == 0) ||(d1.a == 1 && d1.b == 0)) {
			return true;
		}
		else if((this.a == 0 && this.b == 1) ||(d1.a == 0 && d1.b == 1)) {
			return true;
		}
		return false;
	}
	
	private boolean intersectionSegment(Segment s1) {
		return s1.intersection(this);
	}
	
	private boolean intersectionTriangle(Triangle t1) {
		return t1.intersection(this);
	}
	
	private boolean intersectionRectangle(Rectangle r1) {
		return r1.intersection(this);
	}
	
	private boolean intersectionPolygone(Polygone pg1) {
		return pg1.intersection(this);
	}
	
	private ArrayList<Point> PointsintersectionDroite(Droite d1) {
		ArrayList<Point> lstp = new ArrayList<Point>();
		if(!intersectionDroite(d1)) {
			lstp.add(null);
			return lstp;
		}
		
		float x = 0.0f;
		float y = 0.0f;
		
		if(this.estOrdinaire() && d1.estOrdinaire()) {
			x = ((this.c/this.b)-(d1.c/d1.b))/((d1.a/d1.b)-(this.a/this.b));
			y = ((-this.a*x)-this.c)/this.b;	
		}else if(this.estOrdinaire() && d1.estHorizontale()) {
			y = d1.p1.getY();
			x = ((-this.b*y) - this.c)/this.a;			
		}else if(this.estOrdinaire() && d1.estVerticale()) {
			x = d1.p1.getX();
			y = ((-this.a*x)- this.c)/this.b;
		}else if(d1.estOrdinaire() && this.estHorizontale()) {
			y = this.p1.getY();
			x = ((-d1.b*y) - d1.c)/d1.a;
		}else if(d1.estOrdinaire() && this.estVerticale()) {
			x = this.p1.getX();
			y = ((-d1.a*x)- d1.c)/d1.b;
		}else if(d1.estHorizontale() && this.estVerticale()) {
			x = this.p1.getX();
			y = d1.p1.getY();
		}else if(this.estHorizontale() && d1.estVerticale()) {
			x = d1.p1.getX();
			y = this.p1.getY();
		}
		
		lstp.add(new Point(x,y));
		return lstp;
	}
		
	private ArrayList<Point> PointsintersectionSegment(Segment s1) {
		return s1.PointsIntersection(this);
	}
	
	private ArrayList<Point> PointsintersectionTriangle(Triangle t1) {
		return t1.PointsIntersection(this);
	}
	
	private ArrayList<Point> PointsintersectionRectangle(Rectangle r1) {
		return r1.PointsIntersection(this);
	}
	
	private ArrayList<Point> PointsintersectionPolygone(Polygone pg1) {
		return pg1.PointsIntersection(this);
	}
	
	protected boolean estVerticale() {
		if(this.a == 1 && this.b == 0) {
			return true;
		}
		return false;
	}
	
	protected boolean estHorizontale() {
		if(this.a == 0 && this.b == 1) {
			return true;
		}
		return false;
	}
	
	protected boolean estOrdinaire() {
		if(this.b == -1) {
			return true;
		}
		return false;
	}

	public boolean contient(Forme f1) {
		if(f1 instanceof Droite) {
			return contientDroite((Droite) f1);
		}else if(f1 instanceof Segment) {
			return contientSegment((Segment) f1);
		}else if(f1 instanceof Triangle) {
			return contientTriangle((Triangle) f1);
		}else if(f1 instanceof Rectangle) {
			return contientRectangle((Rectangle) f1);
		}else if(f1 instanceof Polygone) {
			return contientPolygone((Polygone) f1);
		}		
		return false;
	}

	private boolean contientPolygone(Polygone pg1) {
		return false;
	}

	private boolean contientRectangle(Rectangle r1) {
		return false;
	}

	private boolean contientTriangle(Triangle t1) {
		return false;
	}

	private boolean contientSegment(Segment s1) {
		if(this.contient(s1.p1) && this.contient(s1.p2)) {
			return true;
		}
		return false;
	}

	
	private boolean contientDroite(Droite d1) {
		if(d1.a == this.a && d1.b == this.b && d1.c == this.c) {
			return true;
		}
		return false;
	}
}
