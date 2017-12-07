package Maths;
import java.util.ArrayList;
/**
 * <p>
 * Une classe représentant une forme géométrique de type "Droite". Elle passe par les deux points qui forment la droite.
 * Elle est aussi représentée par les 3 variables a,b,c de l'équation cartésienne ax+by+c=0 représentant cette même droite
 * </p>
 * @version 1.0
 * @author Matthias Goulley, Apollon Vieira
 * @see Forme
 */
public class Droite implements Forme{
	// Variables Equation Cartésienne
	protected float a;
	protected float b;
	protected float c;
	// Les deux points "directeurs" de la droite
	protected Point p1;
	protected Point p2;
	
	/**
	 * Constructeur de la classe. NB: L'ordre des points en paramètre n'importe pas.
	 * Compléxité: O(1)
	 * @param p1 Le premier point directeur de la droite
	 * @param p2 Le deuxieme point directeur de la droite
	 */
	public Droite (Point p1, Point p2) {
		float a = 0.0f;
		float b = 0.0f;
		float c = 0.0f;
		// Droite verticale
		if(p1.getX() == p2.getX()) {
			a = 1;
			b = 0;
			c = - p1.getX();
		}
		// Droite horizontale
		else if(p1.getY() == p2.getY()) {
			a = 0;
			b = 1;
			c = - p1.getY();
		}
		//Droite ordinaire
		else {
			a = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());		
			b = -1;
			c = p1.getY() - a*p1.getX();
		}
		// On affecte les bonnes valeurs aux bonnes variables.
		this.a = a;
		this.b = b;
		this.c = c;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/* (non-Javadoc)
	 * Compléxité: O(1)
	 * @see Forme#contient(Point)
	 */
	public boolean contient(Point p1) {
		return this.a*p1.getX() + this.b*p1.getY() + this.c == 0;
	}
	
	public Point pointenX(float x) {
		if(this.estOrdinaire() && p1.getX() < p2.getX()) {
			return new Point(x, (this.a*x)+c);
		}else if(this.estOrdinaire() && p1.getX() > p2.getX()){
			return new Point(-1, (-this.a)+c);
		}else if(this.estHorizontale() && p1.getX() < p2.getX()) {
			return new Point(x, this.p1.getY());
		}else if(this.estHorizontale() && p1.getX() > p2.getX()) {
			return new Point(-1, this.p1.getY());
		}else if(this.estVerticale() && p1.getY() > p2.getY()) {
			return new Point(p1.getX(), -1);
		}else if(this.estVerticale() && p1.getY() < p2.getY()) {
			return new Point(p1.getX(), x);
		}
		return null;
	}
	
	/**
	 * Permet de localiser un point par rapport à une droite.
	 * Compléxité: O(1)
	 * @param p Le point que l'on veut localiser
	 * @return Le demi-Plan auquel appartient le point p. NB: Renvoi 0 si le point est sur la droite.
	 */
	public int demiPlan(Point p) {
		if(contient(p)) {
			return 0;
		}else if(p.getY() > this.a*p.getX() + this.c) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	/* (non-Javadoc)
	 * Compléxité: O(1)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(" + a + "x + " + b + "y + " + c + " = 0)";
	}
	
	/* (non-Javadoc)
	 * Compléxité: O(1)
	 * @see Forme#intersection(Forme)
	 */
	public boolean intersection(Forme f1) {
		if(f1 instanceof Droite) {
			Droite d1 = (Droite) f1;
			if((this.a*d1.b) - (d1.a*this.b) != 0.0f) {
				return true;
			}else if(this.a == 1 && d1.a== 1 && this.b == 0 && d1.b == 0) {
				return false;
			}else if(this.a == 0 && d1.a== 0 && this.b == 1 && d1.b == 1) {
				return false;
			}else if((this.a == 1 && this.b == 0) ||(d1.a == 1 && d1.b == 0)) {
				return true;
			}
			else if((this.a == 0 && this.b == 1) ||(d1.a == 0 && d1.b == 1)) {
				return true;
			}
			return false;
		}else{
			return f1.intersection(this);
		}		
	}

	/* (non-Javadoc)
	 * Compléxité: O(1) si f1 est une droite. Sinon voir la définition de la fonction dans la classe de f1.
	 * NB: Retourne 1 ou 0 point
	 * @see Forme#PointsIntersection(Forme)
	 */
	public ArrayList<Point> PointsIntersection(Forme f1) {
		// On regarde quel est le type de f1
		if(f1 instanceof Droite) {
			Droite d1 = (Droite) f1; // on récupère notre droite à partir de la forme
			ArrayList<Point> lstp = new ArrayList<Point>();
			// On test si il y a bien une intersection
			if(!intersection(d1)) {
				return lstp;
			}
			
			float x = 0.0f;
			float y = 0.0f;
			//
			// On fait affronter les différents types possibles de droites
			// Il est possible de réaliser un cas général :         
			//         (x4-x3)(y1-y3) + (y4-y3)(x3-x1) 
			//g : p = ------------------------------- 
			//         (y4-y3)(x2-x1) - (x4-x3)(y2-y1)
			// Mais notre méthode réalise moins de calculs en une seule fois. Mais plusieurs petits calculs
			// Les chances d'erreurs d'approximations sur les nombres flotants sont alors moindres.
			//
			if(this.estOrdinaire() && d1.estOrdinaire()) {
				x = ((this.c/this.b)-(d1.c/d1.b))/((d1.a/d1.b)-(this.a/this.b));
				y = ((-this.a*x)-this.c)/this.b;	
			}else if(this.estOrdinaire() && d1.estHorizontale()) {
				y = d1.p1.getY();
				x = ((-this.b*y) - this.c)/this.a;			
			}else if(this.estOrdinaire() && d1.estVerticale()) {
				x = d1.p1.getX();
				y = ((-this.a*x)- this.c)/this.b;
			}else if(d1.estOrdinaire() && this.estHorizontale()) {
				y = this.p1.getY();
				x = ((-d1.b*y) - d1.c)/d1.a;
			}else if(d1.estOrdinaire() && this.estVerticale()) {
				x = this.p1.getX();
				y = ((-d1.a*x)- d1.c)/d1.b;
			}else if(d1.estHorizontale() && this.estVerticale()) {
				x = this.p1.getX();
				y = d1.p1.getY();
			}else if(this.estHorizontale() && d1.estVerticale()) {
				x = d1.p1.getX();
				y = this.p1.getY();
			}
			lstp.add(new Point(x,y));
			return lstp;
		}else {
			return f1.PointsIntersection(this);
		}
	}
	
	/**
	 * Compléxité: O(1)
	 * @return Vrai ssi la droite est verticale, sinon Faux
	 */
	protected boolean estVerticale() {
		if(this.a == 1 && this.b == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Compléxité: O(1)
	 * @return Vrai ssi la droite est horizontale, sinon Faux
	 */
	protected boolean estHorizontale() {
		if(this.a == 0 && this.b == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Compléxité: O(1)
	 * @return Vrai ssi la droite n'est ni horizontale, ni verticale, sinon Faux
	 */
	protected boolean estOrdinaire() {
		if(this.b == -1) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * Compléxité: O(1)
	 * @see Forme#contient(Forme)
	 */
	public boolean contient(Forme f1) {
		if(f1 instanceof Droite) {
			Droite d1 = (Droite) f1;
			if(d1.a == this.a && d1.b == this.b && d1.c == this.c) {
				return true;
			}
			return false;
		}else if(f1 instanceof Segment) {
			Segment s1 = (Segment) f1;
			if(this.contient(s1.p1) && this.contient(s1.p2)) {
				return true;
			}
			return false;
		}		
		return false;
	}
}
