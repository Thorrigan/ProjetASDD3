package Maths;
import java.util.ArrayList;
/**
 * <p>
 * Une classe représentant une forme géométrique de type "Triangle".
 * Elle est représentée par les 3 points qui forment les sommets du triangle.
 * </p>
 * @version 1.0
 * @author Matthias Goulley, Apollon Vieira
 * @see Forme
 */
public class Triangle implements Forme{
	// Les trois sommets
	protected Point p1;
	protected Point p2;
	protected Point p3;
	private char type;

	/**
	 * Le constructeur d'un triangle
	 * Compléxité: O(1)
	 * @param p1 Le premier sommet
	 * @param p2 Le deuxième sommet
	 * @param p3 Le troisième sommet
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	/**
	 * Le constructeur d'un triangle
	 * Compléxité: O(1)
	 * @param p1 Le premier sommet
	 * @param p2 Le deuxième sommet
	 * @param p3 Le troisième sommet
	 * @parem t	Le type de la surface du triangle
	 */
	public Triangle(Point p1, Point p2, Point p3, char t) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.type = t;
	}
	
	/**
	 * Transforme un triangle (3 points) en 3 segments
	 * Compléxité: O(1)
	 * @return Les trois segments représentants le triangle.
	 */
	public Segment[] transformationSegment() {
		Segment[] tab = new Segment[3];
		tab[0] = new Segment(p1, p2);
		tab[1] = new Segment(p2, p3);
		tab[2] = new Segment(p3, p1);
		return tab;
		
	}
	
	/* (non-Javadoc)
	 * Compléxité: O(1)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "[" + p1 + ", " + p2 + ", " + p3 + "]";
	}

	/* (non-Javadoc)
	 * Compléxité: O(nbs) en meilleur cas, O(nbs^nbs) en pire. 
	 * Comme un triangle est toujours constitué de 3 segments on à alors en pire cas O(9) -> O(1)
	 * Sinon, voir la compléxité de la classe de la forme.
	 * @see Forme#intersection(Forme)
	 */
	public boolean intersection(Forme f1) {
		if(f1 instanceof Droite) {
			Droite d1 = (Droite) f1;
			int compteur = 0;
			for(Segment seg : this.transformationSegment()) {
				if(seg.intersection(d1)) {
					compteur++;
				}
			}
			return compteur != 0;
		}else if(f1 instanceof Segment) {
			Segment s1 = (Segment) f1;
			int compteur = 0;
			for(Segment seg : this.transformationSegment()) {
				if(seg.intersection(s1)) {
					compteur++;
				}
			}
			return compteur != 0;
		}else if(f1 instanceof Triangle) {
			Triangle t1 = (Triangle) f1;
			int compteur = 0;
			for(Segment seg : this.transformationSegment()) {
				for(Segment seg2 : t1.transformationSegment()) {
					if(seg.intersection(seg2)) {
						compteur++;
					}
				}
			}
			return compteur != 0;
		}else {
			return f1.intersection(this);
		}
	}

	/* (non-Javadoc)
	 * Compléxité: O(nbs) donc comme on est dans un triangle -> O(3) -> O(1)
	 * @see Forme#contient(Point)
	 */
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

	/* (non-Javadoc)
	 * Compléxité: O(nbs) en meilleur cas, O(nbs^nbs) en pire cas
	 * Comme un triangle est toujours constitué de 3 segments on à alors en pire cas O(9) -> O(1)
	 * Sinon, voir la compléxité de la classe de la forme.
	 * @see Forme#PointsIntersection(Forme)
	 */
	public ArrayList<Point> PointsIntersection(Forme f1) {
		if(f1 instanceof Droite) {
			Droite d1 = (Droite) f1;
			ArrayList<Point> lstp = new ArrayList<Point>();
			if(!intersection(d1)) {
				return lstp;
			}
			
			for(Segment seg : this.transformationSegment()) {
				if(seg.intersection(d1) && !lstp.contains(seg.PointsIntersection(d1).get(0))) {
					lstp.add(seg.PointsIntersection(d1).get(0));
				}
			}
			
			return lstp;
		}else if(f1 instanceof Segment) {
			Segment s1 = (Segment) f1;
			ArrayList<Point> lstp = new ArrayList<Point>();
			if(!intersection(s1)) {
				return lstp;
			}
			
			for(Segment seg : this.transformationSegment()) {
				if(seg.intersection(s1) && !lstp.contains(seg.PointsIntersection(s1).get(0))) {
					lstp.add(seg.PointsIntersection(s1).get(0));
				}
			}
			
			return lstp;
		}else if(f1 instanceof Triangle) {
			Triangle t1 = (Triangle) f1;
			ArrayList<Point> lstp = new ArrayList<Point>();
			if(!intersection(t1)) {
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
		}else {
			return f1.PointsIntersection(this);
		}
	}
	
	/* (non-Javadoc)
	 * Compléxité: O(1) en meilleur cas, O(nbs) en fonction de la taille du polygone en pire cas
	 * @see Forme#contient(Forme)
	 */
	public boolean contient(Forme f1) {
		if(f1 instanceof Droite) {
			return false;
		}else if(f1 instanceof Segment) {
			Segment s1 = (Segment) f1;
			if(this.contient(s1.p1) && this.contient(s1.p2)) {
				return true;
			}
			return false;
		}else if(f1 instanceof Triangle) {
			Triangle t1 = (Triangle) f1;
			if(this.contient(t1.p1) && this.contient(t1.p2) && this.contient(t1.p3)) {
				return true;
			}
			return false;
		}else if(f1 instanceof Rectangle) {
			Rectangle r1 = (Rectangle) f1;
			int compteur = 0;
			for(Segment seg : r1.transformationSegment()) {
				if(!this.contient(seg)) {
					compteur++;
				}
			}
			return compteur == 0;
		}else if(f1 instanceof Polygone) {
			Polygone pg1 = (Polygone) f1;
			int compteur = 0;
			for(Segment seg : pg1.transformationSegment()) {
				if(!this.contient(seg)) {
					compteur++;
				}
			}
			return compteur == 0;
		}		
		return false;
	}
	
	/**
	 * 
	 * @return type du triangle
	 */
	public char getType() {
		return type;
	}
}
