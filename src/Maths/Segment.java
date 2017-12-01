package Maths;
import java.util.ArrayList;
/**
 * <p>
 * Une classe représentant une forme géométrique de type "Segment".
 * Elle est représentée par les 2 points qui forment l'extrémité du segment.
 * </p>
 * @version 1.0
 * @author Matthias Goulley, Apollon Vieira
 * @see Forme
 */
public class Segment implements Forme{
	protected Point p1;
	protected Point p2;
	
	/**
	 * Le constructeur d'un segment. 
	 * NB: Peut importe l'ordre des points donné en paramètre, 
	 * on sauvegardera toujours le point avec la plus petite valeure de X en premier, 
	 * pour simplifier certains calculs par la suite.
	 * Compléxité: O(1)
	 * @param p1 Le premier point représentant le segment
	 * @param p2 Le deuxième point représentant le segment
	 */
	public Segment(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * Recherche le milieu d'un segment.
	 * Compléxité O(1)
	 * @return Le point du milieu du segment
	 */
	public Point milieu() {
		return new Point((p1.getX()+p2.getX())/2, (p1.getY()+p2.getY())/2);
	}
	
	/**
	 * Cherche si un point p est contenu dans le rectangle 
	 * NB: si un point est sur la droite du segment et qu'il est dans le rectangle du segment,
	 * alors il est forcément sur le segment.
	 * Compléxité: O(1)
	 * @param p le point que l'on veut localiser
	 * @return Vrai ssi le point est dans le rectangle,sinon faux
	 */
	private boolean estDansRectangle(Point p) {
		Rectangle r = new Rectangle(this.p1, this.p2);
		return r.contient(p);
	}
	
	/**
	 * Permet de transformer un Segment en Droite.
	 * Compléxité: O(1)
	 * @return La droite représentée par le segment
	 */
	public Droite transformationDroite() {
		return new Droite(p1, p2);
	}
	
	/* (non-Javadoc)
	 * Compléxité: O(1)
	 * @see Forme#intersection(Forme)
	 */
	public boolean intersection(Forme f1) {
		if(f1 instanceof Droite) {
			Droite d1 = (Droite) f1;
			if(this.transformationDroite().intersection(d1) && this.estDansRectangle(this.transformationDroite().PointsIntersection(d1).get(0))) {
				return true;
			}
			return false;
		}else if(f1 instanceof Segment) {
			Segment s1 = (Segment) f1;
			// SI les deux segment ont au moins un point en commun
			if(this.p1.equals(s1.p1) || this.p2.equals(s1.p2) || this.p1.equals(s1.p2) || this.p2.equals(s1.p1)) {
				return false;
			}
			if(this.contient(s1)) {				
				return false;
			}
			if(this.transformationDroite().intersection(s1.transformationDroite())) {
				if(this.estDansRectangle(this.PointsIntersection(s1.transformationDroite()).get(0)) && s1.estDansRectangle(s1.PointsIntersection(this.transformationDroite()).get(0))){
					//parfois l�
					return true;
				}
				return false;
			}
			return false;
		}else {
			//parfois l�
			return f1.intersection(this);
		}
	}


	/* (non-Javadoc)
	 * Compléxité: O(1)
	 * @see Forme#contient(Point)
	 */
	public boolean contient(Point p1) {
		if(estDansRectangle(p1) && this.transformationDroite().contient(p1)) {
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * Compléxité: O(1)
	 * @see Forme#PointsIntersection(Forme)
	 */
	public ArrayList<Point> PointsIntersection(Forme f1) {
		ArrayList<Point> lstp = new ArrayList<Point>();
		if(f1 instanceof Droite) {
			Droite d1 = (Droite) f1;
			if(!this.transformationDroite().intersection(d1)) {
				return lstp;
			}
			lstp.add(this.transformationDroite().PointsIntersection(d1).get(0));
		}else if(f1 instanceof Segment) {
			Segment s1 = (Segment) f1;
			if(this.contient(s1)) {
				//never here
				return lstp; //ici yavait null
			}
			if(!this.intersection(s1)) {
				//never here
				return lstp;
			}	
			lstp.add(this.transformationDroite().PointsIntersection(s1.transformationDroite()).get(0));
			System.out.println("LSTP STP AFFICHE QQCH : " + lstp);
		}else {
			return f1.PointsIntersection(this);
		}		
		return lstp;
	}

	/* (non-Javadoc)
	 * Compléxité: O(1)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(" + p1 + ", " + p2 + ")";
	}
	
	/* (non-Javadoc)
	 * Compléxité: O(1)
	 * @see Forme#contient(Forme)
	 */
	public boolean contient(Forme f1) {
		if(f1 instanceof Droite) {
			Droite d1 = (Droite) f1;
			return this.transformationDroite().contient(d1);
		}else if(f1 instanceof Segment) {
			Segment s1 = (Segment) f1;
			if(this.contient(s1.p1) && this.contient(s1.p2)){
				return true;
			}
			return false;
		}else {
			return false;
		}
	}
}
