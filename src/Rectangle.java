

import java.util.ArrayList;

public class Rectangle implements Forme{
	private Point p1;
	private Point p2;
	
	public Rectangle(Point p1, Point p2) {
		if(p1.getX() < p2.getX()) {
			this.p1 = p1;
			this.p2 = p2;
		}else {
			this.p1 = p2;
			this.p2 = p1;
		}
	}
	
	public Rectangle[] division() {
		Rectangle[] rectangles = new Rectangle[4];
		rectangles[0] = new Rectangle(new Point(minX(), centre().getY()), new Point(centre().getX(), maxY()));
		rectangles[1] = new Rectangle(centre(), p2);
		rectangles[2] = new Rectangle(centre(), new Point(maxX(), minY()));
		rectangles[3] = new Rectangle(p1, centre());
		return rectangles;
	}
	
	// peut etre pas besoin de celle la
	public int nbIntersection(ArrayList<Triangle> tab) {
		int compteur = 0;
		for(Triangle t : tab) {
			if(PointsintersectionTriangle(t) != null) {
				compteur += PointsintersectionTriangle(t).size();
			}
		}
		return compteur;
	}
	
	public Point centre() {
		return (new Segment(p1,p2)).milieu();
	}
	
	private float minX() {
		return p1.getX();
	}
	
	private float maxX() {
		return p2.getX();
	}
	
	private float minY() {
		if(p1.getY() < p2.getY()) {
			return p1.getY();
		}
		return p2.getY();
	}
	
	private float maxY() {
		if(p1.getY() < p2.getY()) {
			return p2.getY();
		}
		return p1.getY();
	}
	
	
	public Segment[] transformationSegment() {
		Point p1p = new Point(p2.getX(), p1.getY());
		Point p2p = new Point(p1.getX(), p2.getY());
		Segment[] tab = new Segment[4];
		tab[0] = new Segment(p1, p2p);
		tab[1] = new Segment(p2p, p2);
		tab[2] = new Segment(p2, p1p);
		tab[3] = new Segment(p1p, p1);
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

	private boolean intersectionPolygone(Polygone pg1) {
		return pg1.intersection(this);
	}

	private boolean intersectionRectangle(Rectangle r1) {
		for(Segment seg : this.transformationSegment()) {
			for(Segment seg2 : r1.transformationSegment()) {
				if(seg.intersection(seg2)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean intersectionTriangle(Triangle t1) {
		for(Segment seg : this.transformationSegment()) {
			if(t1.intersection(seg)) {
				return true;
			}
		}
		return false;
	}

	private boolean intersectionSegment(Segment s1) {
		for(Segment seg : this.transformationSegment()) {
			if(seg.intersection(s1)) {
				return true;
			}
		}
		return false;
	}

	private boolean intersectionDroite(Droite d1) {
		for(Segment seg : this.transformationSegment()) {
			if(seg.intersection(d1)) {
				return true;
			}
		}
		return false;
	}

	public boolean contient(Point p) {
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
		ArrayList<Point> lstp = new ArrayList<Point>();
		if(!intersectionRectangle(r1)) {
			lstp.add(null);
			return lstp;
		}
		
		for(Segment seg : this.transformationSegment()) {
			for(Segment seg2 : r1.transformationSegment()) {
				if(seg.intersection(seg2) && !lstp.contains(seg.PointsIntersection(seg2).get(0))) {
					lstp.add(seg.PointsIntersection(seg2).get(0));
				}
			}
		}
		
		return lstp;
	}

	public String toString() {
		return "[" + p1 + ", " + p2 + "]";
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
			if(seg.intersection(s1)) {
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
			if(seg.intersection(d1)) {
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
