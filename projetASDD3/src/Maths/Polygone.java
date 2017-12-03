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
	private char type;
	private float minX;
	
	/**
	 * Constructeur d'un polygone à partir d'une liste de sommet/point
	 * Compléxité: O(taille(lstp))
	 * @param lstp Une liste de point/sommet
	 * @param t Le type de la surface du polygone
	 */
	public Polygone(ArrayList<Point> lstp, char t) {
		this.minX = 0.0f;
		for(Point p : lstp) {
			if(p.getX() < minX) {
				minX = p.getX();
			}
		}
		this.type = t;
		this.lstp = lstp;
	}
	
	public Polygone(ArrayList<Point> lstp) {
		this.minX = 0.0f;
		for(Point p : lstp) {
			if(p.getX() < minX) {
				minX = p.getX();
			}
		}
		this.type = ' ';
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
	
	public char getType() {
		return type;
	}
	
	
	public ArrayList<Point> getLstp() {
		return lstp;
	}

	/**
	 * Réalise la découpe d'un polygone en plusieurs triangle non sécants
	 * Compléxité: O(n^2) Avec la méthode de l'oreille. 
	 * NB: Il existe d'autres méthodes plus complexes mais qui permettent une efficacité en O(nlog(n).
	 * @return La liste des triangles qui composent le polygone
	 */
	public ArrayList<Triangle> triangulation(){
		ArrayList<Point> lp = new ArrayList<Point>(this.lstp);
		return this.triangulation(lp, 0);
	}
	
	private ArrayList<Triangle> triangulation(ArrayList<Point> points, int index){
		if(index > points.size()-3) {
			index = 0;
		}
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();

		if(points.isEmpty()) {
			return triangles;
		}
		if(points.size() == 3) {
			// Si les trois points sont align�s
			Droite d1 = new Droite(points.get(0), points.get(1));
			if(d1.contient(points.get(2))) {
				//System.out.println("3 derniers points alignés DUH");
				return triangles;
			}
			triangles.add(new Triangle(points.get(0), points.get(1), points.get(2), this.type));
			return triangles;
		}
	
		Point A = points.get(0+index);
		Point B = points.get(1+index);
		Point C = points.get(2+index);
		Triangle t = new Triangle(A, B, C, this.type);
		Segment seg = new Segment(A, C);
		for(Point p : this.lstp){
			if(t.contient(p) && p != A && p != B && p != C){
				ArrayList<Triangle> resultat = new ArrayList<Triangle>();
				resultat.addAll(triangles);
				resultat.addAll(this.triangulation(points, index+1));
				return resultat;
			}
		}
		if(!this.intersection(seg) && this.contient(seg.milieu())) {
			triangles.add(t);
			points.remove(B);
		}
		else{
			index++;
		}
		
		// On retourne la liste des triangles actuels + la liste des triangles restants
		ArrayList<Triangle> resultat = new ArrayList<Triangle>();
		resultat.addAll(triangles);
		resultat.addAll(this.triangulation(points, index));
		return resultat;
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
	 * Compléxité: O(1)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "[Type= "+ type  +" lstp=" + lstp + "]";
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
			System.out.println(this.PointsIntersection(s1));
			if(this.PointsIntersection(s1).isEmpty() && this.contient(s1.p1) && this.contient(s1.p2)){
				return true;
			}
			return false;
		}else if(f1 instanceof Triangle) {
			Triangle t1 = (Triangle) f1;
			if(this.PointsIntersection(t1).isEmpty() && this.contient(t1.p1) && this.contient(t1.p2) && this.contient(t1.p3)) {
				return true;
			}
			return false;
		}else if(f1 instanceof Rectangle) {
			Rectangle r1 = (Rectangle) f1;
			//rectangles superpos�s impossible
			if(this.PointsIntersection(r1).isEmpty() && this.contient(r1.getP1()) && this.contient(r1.getP2())) {
				return true;
			}
			return false;
		}else if(f1 instanceof Polygone) {
			Polygone pg1 = (Polygone) f1;
			for(Point p : pg1.lstp) {
				if(!this.contient(p)) {
					return false;
				}
			}			
			for(Segment s : pg1.transformationSegment()) {
				if(!this.PointsIntersection(s).isEmpty()) {
					return false;
				}
			}
			return true;
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
			compteur += s.PointsIntersection(seg).size();
		}
		return (compteur%2 != 0);
	}
}
