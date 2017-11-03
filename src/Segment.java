

import java.util.ArrayList;

public class Segment implements Forme{
	protected Point p1;
	protected Point p2;
	
	public Segment(Point p1, Point p2) {
		if(p1.getX() < p2.getX()) {
			this.p1 = p1;
			this.p2 = p2;
		}else {
			this.p1 = p2;
			this.p2 = p1;
		}
	}
	
	private boolean estDansRectangle(Point p) {
		if(p == null) {
			return false;
		}
		if(this.p1.getY() < this.p2.getY() && this.p1.getX() <= p.getX() &&  this.p2.getX() >= p.getX() && p.getY() <= this.p2.getY() && p.getY() >= this.p1.getY()) {
			return true;
		}else if(this.p1.getY() > this.p2.getY() && this.p1.getX() <= p.getX() && p.getY() >= this.p2.getY() && p.getY() <= this.p1.getY() && p.getX() <= this.p2.getX()) {
			return true;
		}else if(this.p1.getY() == this.p2.getY() && this.p1.getX() <= p.getX() && p.getX() <= this.p2.getX() && p.getY() == this.p1.getY()) {
			return true;
		}
		return false;
	}
	
	public Droite transformationDroite() {
		return new Droite(p1, p2);
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
	
	private boolean intersectionPolygone(Polygone pg1) {
		return pg1.intersection(this);
	}

	private boolean intersectionRectangle(Rectangle r1) {
		return r1.intersection(this);
	}

	private boolean intersectionTriangle(Triangle t1) {
		return t1.intersection(this);
	}

	private boolean intersectionSegment(Segment s1) {
		if(this.intersectionDroite(s1.transformationDroite()) && s1.intersectionDroite(this.transformationDroite())) {
			return true;
		}
		return false;
	}

	private boolean intersectionDroite(Droite d1) {
		if(this.transformationDroite().intersection(d1) && this.estDansRectangle(this.transformationDroite().PointsIntersection(d1).get(0))) {
			return true;
		}
		return false;
	}

	public boolean contient(Point p1) {
		if(estDansRectangle(p1) && this.transformationDroite().contient(p1)) {
			return true;
		}
		return false;
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

	private ArrayList<Point> PointsintersectionPolygone(Polygone pg1) {
		return pg1.PointsIntersection(this);
	}

	private ArrayList<Point> PointsintersectionRectangle(Rectangle r1) {
		return r1.PointsIntersection(this);
	}

	private ArrayList<Point> PointsintersectionTriangle(Triangle t1) {
		return t1.PointsIntersection(this);
	}

	private ArrayList<Point> PointsintersectionSegment(Segment s1) {
		ArrayList<Point> lstp = new ArrayList<Point>();
		if(!intersectionSegment(s1)) {
			lstp.add(null);
			return lstp;
		}
		
		lstp.add(this.transformationDroite().PointsIntersection(s1.transformationDroite()).get(0));
		return lstp;
	}

	private ArrayList<Point> PointsintersectionDroite(Droite d1) {
		ArrayList<Point> lstp = new ArrayList<Point>();
		if(!this.intersectionDroite(d1)) {
			lstp.add(null);
			return lstp;
		}
		lstp.add(this.transformationDroite().PointsIntersection(d1).get(0));
		return lstp;
	}

	public String toString() {
		return "(" + p1 + ", " + p2 + ")";
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
		if((this.p1 == s1.p1 && this.p2 == s1.p2) || (this.p1 == s1.p2 && this.p2 == s1.p1)) {
			return true;
		}
		return false;
	}

	private boolean contientDroite(Droite d1) {
		return this.transformationDroite().contient(d1);
	}
}
