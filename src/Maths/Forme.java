package Maths;
import java.util.ArrayList;
/**
 * <p>
 * Une interface qui représente toutes les formes géométrique possibles. 
 * NB: On pourrait rajouter des cercles, des lignes courbes, etc... Qui hériteraient aussi de cette interface
 * </p>
 * @version 1.0
 * @author Matthias Goulley, Apollon Vieira
 * @see Droite
 * @see Segment
 * @see Triangle
 * @see Rectangle
 * @see Polygone
 */
public interface Forme {
	
	/**
	 * Permet d'identifier si deux formes sont sécantes ou non.
	 * Compléxité: voir chaque fonction dans sa classe.
	 * @param f1 La Forme sont on veut tester l'intersection
	 * @return Vrai ssi il existe une intersection entre les deux formes, sinon faux.
	 * @see Droite#intersection(Forme)
	 * @see Segment#intersection(Forme)
	 * @see Triangle#intersection(Forme)
	 * @see Rectangle#intersection(Forme)
	 * @see Polygone#intersection(Forme)
	 */
	public boolean intersection(Forme f1);
	
	/**
	 * Permet d'avoir une liste des points d'intersections entre deux formes géométrique.
	 * Compléxité: voir chaque fonction dans sa classe.
	 * @param f1 La forme d'on on veut obtenir les points d'intersections
	 * @return La liste des points d'intersection. Si il n'y a aucun point d'intersection, retourne une liste vide.
	 * @see Droite#PointsIntersection(Forme)
	 * @see Segment#PointsIntersection(Forme)
	 * @see Triangle#PointsIntersection(Forme)
	 * @see Rectangle#PointsIntersection(Forme)
	 * @see Polygone#PointsIntersection(Forme)
	 */
	public ArrayList<Point> PointsIntersection(Forme f1);
	
	/**
	 * Permet d'identifier si une forme est dans une autre.
	 * Compléxité: voir chaque fonction dans sa classe.
	 * @param f1 La forme dont on veut voir si elle est contenu
	 * @return Vrai si f1 est dans la forme, sinon faux.
	 * @see Droite#contient(Forme)
	 * @see Segment#contient(Forme)
	 * @see Triangle#contient(Forme)
	 * @see Rectangle#contient(Forme)
	 * @see Polygone#contient(Forme)
	 */
	public boolean contient(Forme f1);
	
	/** 
	 * Permet d'identifier si un point est à l'intérieur, sur les bords ou sur un sommet de la forme.
	 * Compléxité: voir chaque fonction dans sa classe.
	 * @param p Le point que l'on veut localiser
	 * @return Vrai si le point est à l'intérieur, sur les bords ou sur un sommet de la forme, sinon faux.
	 * @see Droite#contient(Point)
	 * @see Segment#contient(Point)
	 * @see Triangle#contient(Point)
	 * @see Rectangle#contient(Point)
	 * @see Polygone#contient(Point)
	 */
	public boolean contient(Point p);
}
