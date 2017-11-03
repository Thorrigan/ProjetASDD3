

import java.util.ArrayList;

public class Triangle implements Forme{
	protected Point p1;
	protected Point p2;
	protected Point p3;
	
	public Triangle(Point p1, Point p2, Point p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	public Segment[] transformationSegment() {
		Segment[] tab = new Segment[3];
		tab[0] = new Segment(p1, p2);
		tab[1] = new Segment(p2, p3);
		tab[2] = new Segment(p3, p1);
		return tab;
		
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

	public String toString() {
		return "[" + p1 + ", " + p2 + ", " + p3 + "]";
	}

	private boolean intersectionPolygone(Polygone pg1) {
		return pg1.intersection(this);
	}

	private boolean intersectionRectangle(Rectangle r1) {
		return r1.intersection(this);
	}

	private boolean intersectionTriangle(Triangle t1) {
		int compteur = 0;
		for(Segment seg : this.transformationSegment()) {
			for(Segment seg2 : t1.transformationSegment()) {
				if(seg.intersection(seg2)) {
					compteur++;
				}
			}
		}
		return compteur != 0;
	}

	private boolean intersectionSegment(Segment s1) {
		int compteur = 0;
		for(Segment seg : this.transformationSegment()) {
			if(seg.intersection(s1)) {
				compteur++;
			}
		}
		return compteur != 0;
	}

	private boolean intersectionDroite(Droite d1) {
		int compteur = 0;
		for(Segment seg : this.transformationSegment()) {
			if(seg.intersection(d1)) {
				compteur++;
			}
		}
		return compteur != 0;
	}

	public boolean contient(Point p) {
		for(Segment s : this.transformationSegment()) {
			if(s.contient(p)) {
				return true;
			}
		}
		
		float ABC = Math.abs (p1.getX() * (p2.getY() - p3.getY()) + p2.getX() * (p3.getY() - p1.getY()) + p3.getX() * (p1.getY() - p2.getY()));
		float ABP = Math.abs (p1.getX() * (p2.getY() - p.getY()) + p2.getX() * (p.getY() - p1.getY()) + p.getX() * (p1.getY() - p2.getY()));
		float APC = Math.abs (p1.getX() * (p.getY() - p3.getY()) + p.getX() * (p3.getY() - p1.getY()) + p3.getX() * (p1.getY() - p.getY()));
		float PBC = Math.abs (p.getX() * (p2.getY() - p3.getY()) + p2.getX() * (p3.getY() - p.getY()) + p3.getX() * (p.getY() - p2.getY()));
		return (ABP + APC + PBC) == ABC;
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
		ArrayList<Point> lstp = new ArrayList<Point>();
		return lstp;
	}

	private ArrayList<Point> PointsintersectionRectangle(Rectangle r1) {
		ArrayList<Point> lstp = new ArrayList<Point>();
		return lstp;
	}

	private ArrayList<Point> PointsintersectionTriangle(Triangle t1) {
		ArrayList<Point> lstp = new ArrayList<Point>();
		if(!intersectionTriangle(t1)) {
			lstp.add(null);
			return lstp;
		}
		
		for(Segment seg : this.transformationSegment()) {
			for(Segment seg2 : t1.transformationSegment()) {
				if(seg.intersection(seg2) && !lstp.contains(seg.PointsIntersection(seg2).get(0))) {
					lstp.add(seg.PointsIntersection(seg2).get(0));
				}
			}
		}
		
		return lstp;
	}

	private ArrayList<Point> PointsintersectionSegment(Segment s1) {
		ArrayList<Point> lstp = new ArrayList<Point>();
		if(!intersectionSegment(s1)) {
			lstp.add(null);
			return lstp;
		}
		
		for(Segment seg : this.transformationSegment()) {
			if(seg.intersection(s1) && !lstp.contains(seg.PointsIntersection(s1).get(0))) {
				lstp.add(seg.PointsIntersection(s1).get(0));
			}
		}
		
		return lstp;
	}

	private ArrayList<Point> PointsintersectionDroite(Droite d1) {
		ArrayList<Point> lstp = new ArrayList<Point>();
		if(!intersectionDroite(d1)) {
			lstp.add(null);
			return lstp;
		}
		
		for(Segment seg : this.transformationSegment()) {
			if(seg.intersection(d1) && !lstp.contains(seg.PointsIntersection(d1).get(0))) {
				lstp.add(seg.PointsIntersection(d1).get(0));
			}
		}
		
		return lstp;
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
		int compteur = 0;
		for(Segment seg : pg1.transformationSegment()) {
			if(!this.contient(seg)) {
				compteur++;
			}
		}
		return compteur == 0;
	}

	private boolean contientRectangle(Rectangle r1) {
		int compteur = 0;
		for(Segment seg : r1.transformationSegment()) {
			if(!this.contient(seg)) {
				compteur++;
			}
		}
		return compteur == 0;
	}

	private boolean contientTriangle(Triangle t1) {
		if(this.contient(t1.p1) && this.contient(t1.p2) && this.contient(t1.p3)) {
			return true;
		}
		return false;
	}

	private boolean contientSegment(Segment s1) {
		if(this.contient(s1.p1) && this.contient(s1.p2)) {
			return true;
		}
		return false;
	}

	private boolean contientDroite(Droite d1) {
		return false;
	}
}
