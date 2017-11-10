import java.util.ArrayList;

public class Polygone implements Forme{
	private ArrayList<Point> lstp;
	
	public Polygone(ArrayList<Point> lstp) {
		this.lstp = lstp;
	}
	
	public Polygone() {
		this.lstp = new ArrayList<Point>();
	}
	
	public void ajouterPoint(Point p) {
		this.lstp.add(p);
	}
	
	public ArrayList<Triangle> triangulation(){
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for(int i = 0; i < this.lstp.size(); i++) {
			Point A;
			Point B;
			Point C;
			if(i == lstp.size() - 2) {
				A = lstp.get(i);
				B = lstp.get(i+1);
				C = lstp.get(0);
			}else if(i == lstp.size() - 1) {
				A = lstp.get(i);
				B = lstp.get(0);
				C = lstp.get(1);
			}else {
				A = lstp.get(i);
				B = lstp.get(i+1);
				C = lstp.get(i+2);
			}
			
			Triangle t = new Triangle(A, B, C);
			Segment seg = new Segment(A, C);
			for(Point p : this.lstp) {
				if(p != lstp.get(i) && p != lstp.get(i+1) && p != lstp.get(0) && (!t.contient(p) || seg.contient(p))) {
					break;
				}else {
					
				}
			}
			
			// TODO finir
		}
		
		if(triangles.isEmpty()) {
			return null;
		}
		return triangles;
	}
	
	public ArrayList<Segment> transformationSegment(){
		ArrayList<Segment> lsts = new ArrayList<Segment>();
		for(int i = 0; i < lstp.size(); i++) {
			if(i == lstp.size() - 1) {
				lsts.add(new Segment(lstp.get(i), lstp.get(0)));
			}else {
				lsts.add(new Segment(lstp.get(i), lstp.get(i+1)));
			}
		}
		
		return lsts;
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
		for(Segment seg : this.transformationSegment()) {
			for(Segment seg1 : pg1.transformationSegment()) {
				seg.intersection(seg1);
			}
		}
		return false;
	}

	private boolean intersectionRectangle(Rectangle r1) {
		for(Segment seg : this.transformationSegment()) {
			for(Segment seg1 : r1.transformationSegment()) {
				seg.intersection(seg1);
			}
		}
		return false;
	}

	private boolean intersectionTriangle(Triangle t1) {
		for(Segment seg : this.transformationSegment()) {
			if(seg.intersection(t1)) {
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
		ArrayList<Point> pts = new ArrayList<Point>();
		for(Segment seg : this.transformationSegment()) {
			for(Segment seg1 : pg1.transformationSegment()) {
				if(seg.intersection(seg1)) {
					pts.add(seg.PointsIntersection(seg1).get(0));
				}
			}
		}
		if(pts.isEmpty()) {
			return null;
		}
		return pts;
	}

	private ArrayList<Point> PointsintersectionRectangle(Rectangle r1) {
		ArrayList<Point> pts = new ArrayList<Point>();
		for(Segment seg : this.transformationSegment()) {
			for(Segment seg1 : r1.transformationSegment()) {
				if(seg.intersection(seg1)) {
					pts.add(seg.PointsIntersection(seg1).get(0));
				}
			}
		}
		if(pts.isEmpty()) {
			return null;
		}
		return pts;
	}

	private ArrayList<Point> PointsintersectionTriangle(Triangle t1) {
		ArrayList<Point> pts = new ArrayList<Point>();
		for(Segment seg : this.transformationSegment()) {
			for(Segment seg1 : t1.transformationSegment()) {
				if(seg.intersection(seg1)) {
					pts.add(seg.PointsIntersection(seg1).get(0));
				}
			}
		}
		if(pts.isEmpty()) {
			return null;
		}
		return pts;
	}

	private ArrayList<Point> PointsintersectionSegment(Segment s1) {
		ArrayList<Point> pts = new ArrayList<Point>();
		for(Segment seg : this.transformationSegment()) {
			if(seg.intersection(s1)) {
				pts.add(seg.PointsIntersection(s1).get(0));
			}
		}
		if(pts.isEmpty()) {
			return null;
		}
		return pts;
	}

	private ArrayList<Point> PointsintersectionDroite(Droite d1) {
		ArrayList<Point> pts = new ArrayList<Point>();
		for(Segment seg : this.transformationSegment()) {
			if(seg.intersection(d1)) {
				pts.add(seg.PointsIntersection(d1).get(0));
			}
		}
		if(pts.isEmpty()) {
			return null;
		}
		return pts;
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
		if(PointsIntersection(pg1).size() == 0) {
			return true;
		}
		return false;
	}

	public String toString() {
		return "[lstp=" + lstp + "]";
	}

	private boolean contientRectangle(Rectangle r1) {
		if(PointsIntersection(r1).size() == 0) {
			return true;
		}
		return false;
	}

	private boolean contientTriangle(Triangle t1) {
		if(this.contient(t1.p1) && this.contient(t1.p2) && this.contient(t1.p3) && PointsIntersection(t1).size() == 0) {
			return true;
		}
		return false;
	}

	private boolean contientSegment(Segment s1) {
		if(this.contient(s1.p1) && this.contient(s1.p2) && PointsIntersection(s1).size() == 0) {
			return true;
		}
		return false;
	}

	private boolean contientDroite(Droite f1) {
		return false;
	}

	public boolean contient(Point p) {
		for(Triangle t : this.triangulation()) {
			if(t.contient(p)) {
				return true;
			}
		}
		return false;
	}

}
