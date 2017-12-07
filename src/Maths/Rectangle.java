package Maths;
import java.util.ArrayList;
/**
 * <p>
 * Une classe représentant une forme géométrique de type "Rectangle".
 * Elle est représentée par 2 points seulement car les deux autres points du rectangles sont calculables.
 * </p>
 * @version 1.0
 * @author Matthias Goulley, Apollon Vieira
 * @see Forme
 */
public class Rectangle implements Forme{
	private Point p1;
	private Point p2;
	
	/**
	 * Constructeur d'un rectangle à l'aide de deux points
	 * Compléxité: O(1)
	 * @param p1 Le premier point du rectangle
	 * @param p2 Le deuxième point du rectangle (diagonale avec le premier point)
	 */
	public Rectangle(Point p1, Point p2) {
		if(p1.getX() < p2.getX()) {
			this.p1 = p1;
			this.p2 = p2;
		}else {
			this.p1 = p2;
			this.p2 = p1;
		}
	}
	/**
	 * Divise un rectangle en 4 sous rectangles.
	 * Compléxité: O(1)
	 * @return Les 4 sous rectangles de ce rectangle
	 */
	public Rectangle[] division() {
		Rectangle[] rectangles = new Rectangle[4];
		rectangles[0] = new Rectangle(new Point(minX(), centre().getY()), new Point(centre().getX(), maxY()));
		rectangles[1] = new Rectangle(centre(), new Point(maxX(), maxY()));
		rectangles[2] = new Rectangle(centre(), new Point(maxX(), minY()));
		rectangles[3] = new Rectangle(new Point(minX(), minY()), centre());
		return rectangles;
	}
	
	/**
	 * Compte le nombre d'intersection avec une liste de triangle
	 * Compléxité: O(nbt)
	 * @param tab la liste des triangles.
	 * @return le nombre d'intersection entre les triangle et le rectangle
	 */
	public int nbIntersection(ArrayList<Triangle> tab) {
		int compteur = 0;
		for(Triangle t : tab) {
			compteur += PointsIntersection(t).size();
		}
		return compteur;
	}

	
	/**
	 * Localise le point au centre du rectangle.
	 * Compléxité: O(1)
	 * @return Le point situé au centredu rectangle
	 */
	public Point centre() {
		return (new Segment(p1,p2)).milieu();
	}
	
	//EVENTUELLEMENT A OPTIMISER
	/**
	 * @return La valeur de X minimum dans le rectangle
	 */
	public float minX() {
		if(p1.getX() <= p2.getX()) {
			return p1.getX();
		}
		return p2.getX();
	}
	
	/**
	 * @return La valeur de X maximum dans le rectangle
	 */
	public float maxX() {
		if(p1.getX() < p2.getX()) {
			return p2.getX();
		}
		return p1.getX();
	}
	
	/**
	 * @return La valeur de Y minimum dans le rectangle
	 */
	public float minY() {
		if(p1.getY() <= p2.getY()) {
			return p1.getY();
		}
		return p2.getY();
	}
	
	/**
	 * @return La valeur de Y maximum dans le rectangle
	 */
	public float maxY() {
		if(p1.getY() < p2.getY()) {
			return p2.getY();
		}
		return p1.getY();
	}
	
	
	/**
	 * Permet de "découper" un rectangle en 4 segments qui le composent
	 * Compléxité: O(1)
	 * @return les 4 segments du rectangle
	 */
	public Segment[] transformationSegment() {
		Segment[] tab = new Segment[4];
		tab[0] = new Segment(new Point(minX(), minY()), new Point(minX(),maxY()));
		tab[1] = new Segment(new Point(minX(),maxY()), new Point(maxX(),maxY()));
		tab[2] = new Segment(new Point(maxX(),maxY()), new Point(maxX(),minY()));
		tab[3] = new Segment(new Point(maxX(), minY()), new Point(minX(),minY()));
		return tab;
		
	}
	
	/* (non-Javadoc)
	 * Compléxité: en meilleur cas O(nbs) dans le rectangle -> O(4) -> O(1), 
	 * en pire cas O(4^4) -> O(256) -> O(1),
	 * ou si f1 est un polygone, voir la classe polygone
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
			for(Segment seg : this.transformationSegment()){
				for(Segment seg2 : t1.transformationSegment()){
					if(seg.intersection(seg2)){
						Point p = seg.PointsIntersection(seg2).get(0);
						if(p.equals(seg2.p1) ||p.equals(seg2.p2) ) {
							continue;
						}
						return true;
					}
				}
			}
			//System.out.println("PAS D'intersection entre la region: "+ toString() + " et le triangle " + t1);
			return false;
		}else if(f1 instanceof Rectangle) {
			Rectangle r1 = (Rectangle) f1;
			for(Segment seg : this.transformationSegment()) {
				for(Segment seg2 : r1.transformationSegment()) {
					if(seg.intersection(seg2)) {
						return true;
					}
				}
			}
			return false;
		}else {
			return f1.intersection(this);
		}
	}

	/* (non-Javadoc)
	 * Compléxité: O(1)
	 * @see Forme#contient(Point)
	 */
	public boolean contient(Point p) {
		if(p == null) {
			return false;
		}
		
		if(p.getX()>= minX() && p.getX() <= maxX() && p.getY() >= minY() && p.getY() <= maxY()){
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * Compléxité: O(1)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "[" + p1 + ", " + p2 + "]";
	}

	/* (non-Javadoc)
	 * Compléxité: en meilleur cas O(nbs) du rectangle -> O(4) -> O(1),
	 * en pire cas O(4^4) -> O(256) -> O(1),
	 * Sinon si f1 est un polgone, voir la classe polygone.
	 * @see Forme#PointsIntersection(Forme)
	 */
	public ArrayList<Point> PointsIntersection(Forme f1) {
		ArrayList<Point> lstp = new ArrayList<Point>();
		if(f1 instanceof Droite) {
			Droite d1 = (Droite) f1;		
			for(Segment seg : this.transformationSegment()) {
				if(seg.intersection(d1)) {
					lstp.add(seg.PointsIntersection(d1).get(0));
				}
			}
		}else if(f1 instanceof Segment) {
			Segment s1 = (Segment) f1;
			for(Segment seg : this.transformationSegment()) {
				if(seg.intersection(s1)) {
					lstp.add(seg.PointsIntersection(s1).get(0));
				}
			}
		}else if(f1 instanceof Triangle) {
			Triangle t1 = (Triangle) f1;
			for(Segment seg : this.transformationSegment()) {
				for(Segment seg2 : t1.transformationSegment()) {
					if(seg.intersection(seg2)) { //eventuellement  && !lstp.contains(seg.PointsIntersection(seg2).get(0))
						lstp.add(seg.PointsIntersection(seg2).get(0));
					}
				}
			}
		}else if(f1 instanceof Rectangle) {
			Rectangle r1 = (Rectangle) f1;
			for(Segment seg : this.transformationSegment()) {
				for(Segment seg2 : r1.transformationSegment()) {
					if(seg.intersection(seg2)) { // same
						lstp.add(seg.PointsIntersection(seg2).get(0));
					}
				}
			}
		}else {
			return f1.PointsIntersection(this);
		}
		return lstp;
	}
	
	public Point getP1() {
		return p1;
	}
	public Point getP2() {
		return p2;
	}
	/* (non-Javadoc)
	 * Compléxité: O(1) en meilleur cas, O(1) en pire cas ou si f1 est un polygone voir la classe polygone
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
}
