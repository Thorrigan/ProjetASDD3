import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
/**
 * <p>
 * Une classe représentant une forme géométrique de type "Polygone". 
 * Un polygone est composé de minimum 4 points reliés entre eux par des segments.
 * Elle est représentée par une liste de points qui forment les sommets du polygone.
 * On parle ici de polygone simple qui peuvent être concave ou convex.
 * </p>
 * @version 1.0
 * @author Matthias Goulley, Apollon Vieira
 * @see Forme
 */
public class Polygone implements Forme{
	private ArrayList<Point> lstp;
	private float minX;
	
	/**
	 * Constructeur d'un polygone à partir d'une liste de sommet/point
	 * Compléxité: O(nbp)
	 * @param lstp Une liste de point/sommet
	 */
	public Polygone(ArrayList<Point> lstp) {
		this.minX = 0.0f;
		for(Point p : lstp) {
			if(p.getX() < minX) {
				minX = p.getX();
			}
		}
		this.lstp = lstp;
	}
	
	/**
	 * 
	 */
	public Polygone() {
		this.minX = 0.0f;
		this.lstp = new ArrayList<Point>();
	}
	
	/**
	 * @param p
	 */
	public void ajouterPoint(Point p) {
		if(p.getX() < minX) {
			minX = p.getX();
		}
		this.lstp.add(p);
	}
	// O(n2)
	/**
	 * @return
	 */
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
				if(p != A && p != B && p != C && (!t.contient(p) || seg.contient(p))) {
					break;
				}else {
					Segment AD = new Segment(A,C);
					while(this.contient(AD.p2)) {
						System.out.println(AD + " " + AD.p2);
						AD = new Segment(A, AD.pointSuivant(AD.p2));
						try {
							TimeUnit.SECONDS.sleep(2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println(AD);

				}
			}
		}
		
		if(triangles.isEmpty()) {
			return null;
		}
		return triangles;
	}
	
	/**
	 * @return
	 */
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

	/* (non-Javadoc)
	 * @see Forme#intersection(Forme)
	 */
	public boolean intersection(Forme f1) {
		if(f1 instanceof Droite) {
			Droite d1 = (Droite) f1;
			for(Segment seg : this.transformationSegment()) {
				if(seg.intersection(d1)) {
					return true;
				}
			}
			return false;
		}else if(f1 instanceof Segment) {
			Segment s1 = (Segment) f1;
			for(Segment seg : this.transformationSegment()) {
				if(seg.intersection(s1)) {
					return true;
				}
			}
			return false;
		}else if(f1 instanceof Triangle) {
			Triangle t1 = (Triangle) f1;
			for(Segment seg : this.transformationSegment()) {
				if(seg.intersection(t1)) {
					return true;
				}
			}
			return false;
		}else if(f1 instanceof Rectangle) {
			Rectangle r1 = (Rectangle) f1;
			for(Segment seg : this.transformationSegment()) {
				for(Segment seg1 : r1.transformationSegment()) {
					seg.intersection(seg1);
				}
			}
			return false;
		}else if(f1 instanceof Polygone) {
			Polygone pg1 = (Polygone) f1;
			for(Segment seg : this.transformationSegment()) {
				for(Segment seg1 : pg1.transformationSegment()) {
					seg.intersection(seg1);
				}
			}
			return false;
		}		
		return false;
	}

	/* (non-Javadoc)
	 * @see Forme#PointsIntersection(Forme)
	 */
	public ArrayList<Point> PointsIntersection(Forme f1) {
		ArrayList<Point> pts = new ArrayList<Point>();
		if(f1 instanceof Droite) {
			Droite d1 = (Droite) f1;
			for(Segment seg : this.transformationSegment()) {
				if(seg.intersection(d1)) {
					pts.add(seg.PointsIntersection(d1).get(0));
				}
			}
		}else if(f1 instanceof Segment) {
			Segment s1 = (Segment) f1;
			for(Segment seg : this.transformationSegment()) {
				if(seg.intersection(s1)) {
					pts.add(seg.PointsIntersection(s1).get(0));
				}
			}
		}else if(f1 instanceof Triangle) {
			Triangle t1 = (Triangle) f1;
			for(Segment seg : this.transformationSegment()) {
				for(Segment seg1 : t1.transformationSegment()) {
					if(seg.intersection(seg1)) {
						pts.add(seg.PointsIntersection(seg1).get(0));
					}
				}
			}
		}else if(f1 instanceof Rectangle) {
			Rectangle r1 = (Rectangle) f1;
			for(Segment seg : this.transformationSegment()) {
				for(Segment seg1 : r1.transformationSegment()) {
					if(seg.intersection(seg1)) {
						pts.add(seg.PointsIntersection(seg1).get(0));
					}
				}
			}
		}else if(f1 instanceof Polygone) {
			Polygone pg1 = (Polygone) f1;
			for(Segment seg : this.transformationSegment()) {
				for(Segment seg1 : pg1.transformationSegment()) {
					if(seg.intersection(seg1)) {
						pts.add(seg.PointsIntersection(seg1).get(0));
					}
				}
			}
		}		
		return pts;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "[lstp=" + lstp + "]";
	}

	/* (non-Javadoc)
	 * @see Forme#contient(Forme)
	 */
	public boolean contient(Forme f1) {
		if(f1 instanceof Droite) {
			return false;
		}else if(f1 instanceof Segment) {
			Segment s1 = (Segment) f1;
			if(this.contient(s1.p1) && this.contient(s1.p2) && PointsIntersection(s1).size() == 0) {
				return true;
			}
			return false;
		}else if(f1 instanceof Triangle) {
			Triangle t1 = (Triangle) f1;
			if(this.contient(t1.p1) && this.contient(t1.p2) && this.contient(t1.p3) && PointsIntersection(t1).size() == 0) {
				return true;
			}
			return false;
		}else if(f1 instanceof Rectangle) {
			Rectangle r1 = (Rectangle) f1;
			if(PointsIntersection(r1).size() == 0) {
				return true;
			}
			return false;
		}else if(f1 instanceof Polygone) {
			Polygone pg1 = (Polygone) f1;
			if(PointsIntersection(pg1).size() == 0) {
				return true;
			}
			return false;
		}		
		return false;
	}
	
	// On prend un point dont on sait qu'il est dehors du polygone
	/* (non-Javadoc)
	 * @see Forme#contient(Point)
	 */
	public boolean contient(Point p) {
		Segment seg = new Segment(new Point(minX-1, 0), p);
		if(this.PointsIntersection(seg).isEmpty()) {
			System.out.println("Le polygone ne contient pas le point: " + p);
			return false;
		}
		System.out.println("Le polygone contient le point: " + p);
		return true;
	}

}
