package Maths;
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
	 * Compléxité: O(taille(lstp))
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
	 * Constructeur d'un polygone vide.
	 * Compléxité: O(1)
	 */
	public Polygone() {
		this.minX = 0.0f;
		this.lstp = new ArrayList<Point>();
	}
	
	/**
	 * Ajout d'un point dans un polygone.
	 * Le point sera toujours en dernière position.
	 * Compléxité: O(1)
	 * @param p Le sommet à ajouter au polygone.
	 */
	public void ajouterPoint(Point p) {
		if(p.getX() < minX) {
			minX = p.getX();
		}
		this.lstp.add(p);
	}
	
	/**
	 * Réalise la découpe d'un polygone en plusieurs triangle non sécants
	 * Compléxité: O(n^2) Avec la méthode de l'oreille. 
	 * NB: Il existe d'autres méthodes plus complexes mais qui permettent une efficacité en O(nlog(n).
	 * @return La liste des triangles qui composent le polygone
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
			Point D = seg.transformationDroite().pointenX(11.0f);
			Segment AD = new Segment(A, D);
			System.out.println(AD + " " + C);
			
			for(Point p : this.lstp) {
				if(p != A && p != B && p != C && (!t.contient(p) || seg.contient(p))) {
					break;
				}else {
					
				}
			}
		}
		
		if(triangles.isEmpty()) {
			return null;
		}
		return triangles;
	}
	
	private int intersectionPropre(Segment seg) {
		if(!seg.PointsIntersection(this).isEmpty()) {
			int compteur = 0;
			// Si le point d'intersection est � l'int�rieur du segment ouvert
			if(seg.PointsIntersection(this).get(0) != seg.p1 || seg.PointsIntersection(this).get(0) != seg.p2) {
				
			}
			// Si le sommet est � l'int�rieur du segment ouvert
			
			// Si le segment AB est � l'int�rieur du segment ouvert
		}
		return 0;
	}
	
	/**
	 * Relie tous les sommets du polygone par des segments.
	 * Compléxité: O(nbp) du polygone.
	 * @return La liste des segments qui composent le polygone
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
	 * Compléxité: O(nbs) -> O(nbp) le nombre de points qui composent le polygone en meilleur cas. 
	 * Sinon O(nbp * nbp(f1)) en pire cas.
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
					if(seg.intersection(seg1)) {
						return true;
					}
				}
			}
			return false;
		}else if(f1 instanceof Polygone) {
			Polygone pg1 = (Polygone) f1;
			for(Segment seg : this.transformationSegment()) {
				for(Segment seg1 : pg1.transformationSegment()) {
					if(seg.intersection(seg1)) {
						return true;
					}
				}
			}
			return false;
		}		
		return false;
	}

	/* (non-Javadoc)
	 * Compléxité: O(nbs) -> O(nbp) le nombre de points qui composent le polygone en meilleur cas.
	 * Sinon O(nbp * nbp(f1)) en pire cas.
	 * @see Forme#PointsIntersection(Forme)
	 */
	public ArrayList<Point> PointsIntersection(Forme f1) {
		ArrayList<Point> pts = new ArrayList<Point>();
		if(f1 instanceof Droite) {
			Droite d1 = (Droite) f1;
			for(Segment seg : this.transformationSegment()) {
				if(seg.intersection(d1) && pts.contains(seg.PointsIntersection(d1).get(0))) {
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
					if(seg.intersection(seg1) && !pts.contains(seg.PointsIntersection(seg1).get(0))) {
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
	 * Compléxité: O(1)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "[lstp=" + lstp + "]";
	}

	/* (non-Javadoc)
	 * Compléxité: O(1) en meilleur cas, O(nbp * nbp(f1)) en pire cas.
	 * @see Forme#contient(Forme)
	 */
	public boolean contient(Forme f1) {
		if(f1 instanceof Droite) {
			return false;
		}else if(f1 instanceof Segment) {
			Segment s1 = (Segment) f1;
			if(PointsIntersection(s1).size() == 0) {
				return true;
			}
			return false;
		}else if(f1 instanceof Triangle) {
			Triangle t1 = (Triangle) f1;
			System.out.println("Triangle: " + t1);
			System.out.println(this.PointsIntersection(t1));
			if(PointsIntersection(t1).isEmpty()) {
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
		// Si le point est l'un des sommets
		if(this.lstp.contains(p)) {
			return true;
		}
		// Si le point est sur l'une des arr�tes
		for(Segment s : this.transformationSegment()) {
			if(s.contient(p)) {
				return true;
			}
		}
		// Si le point n'est ni un sommet et n'est pas sur une arr�te
		Segment seg = new Segment(p, new Point(11.0f, 0.0f));
		int compteur = 0;
		for(Segment s : this.transformationSegment()) {
			if(!s.PointsIntersection(seg).isEmpty()) {
				Point p1 = s.PointsIntersection(seg).get(0);
				if(s.p1 != p1 && s.p2 != p1) {
					compteur++;
				}
			}
		}
		return (compteur%2 != 0);
	}
}
