package Maths;

/**
 * <p>
 *	Une classe représentant un point cartésien avec des coordonnée réelles: Float 
 * </p>
 * @version 2.0
 * @author Matthias Goulley, Apollon Vieira
 *
 */
public class Point implements Comparable<Point>{
	private float x;
	private float y;
	
	/**
	 * Constructeur d'un point
	 * @param x Coordonnée en X abscisse
	 * @param y Coordonnée en Y ordonnée
	 */
	public Point(float x, float y){
		x = Math.round(x*100.0f)/100.0f;
		y = Math.round(y*100.0f)/100.0f;
		if(x == -0.0f) {
			x = 0.0f;
		}
		if(y == -0.0f) {
			y = 0.0f;
		}
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Test si deux points sont équivalent
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if(Float.floatToIntBits(x) == Float.floatToIntBits(other.x) &&  Float.floatToIntBits(y) == Float.floatToIntBits(other.y)) {
			return true;
		}
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}


	/**
	 * Getter de la valeur en X.
	 * @return La valeur en X du point
	 */
	public float getX(){
		return this.x;
	}
	
	/**
	 * Getter de la valeur en Y.
	 * @return La valeur en Y du point
	 */
	public float getY(){
		return this.y;
	}
	
	/**
	 * Permet de définir l'affichage d'un point dans la console.
	 */
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * Compare deux points ensemble (voir qui est le plus petit ou le plus grand)
	 */
	public int compareTo(Point p) {
		if(y < p.y) {
			return -1;
		}
		if(y > p.y) {
			return 1;
		}
		if(x < p.x) {
			return -1;
		}
		if(x > p.x) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * Donne la distance entre deux points
	 * @param p1	Le point d'on on veut connaitre la distance
	 * @return La distance entre les deux points
	 */
	public float distance(Point p1){
		return (float) Math.sqrt(Math.pow(Math.abs(p1.getX()-this.x), 2) + Math.pow(Math.abs(p1.getY()-this.y), 2));
	}
	
	/**
	 * Réalise la rotation d'un point par rapport à un autre, avec un angle donné.
	 * @param cible Le point de rotation
	 * @param angle L'angle de la rotation
	 * @return Le point correspondant à la rotation
	 */
	public Point rotation(Point cible, float angle){
		float X,Y, xnew, ynew;
		X = this.x - cible.getX();
		Y = this.y - cible.getY();
		xnew = cible.getX() + (float) ((Math.cos(Math.toRadians(angle))* X) - Math.sin(Math.toRadians(angle))* Y);
		ynew = cible.getY() + (float) ((Math.sin(Math.toRadians(angle))* X) + Math.cos(Math.toRadians(angle))* Y);
		return new Point(xnew,ynew);
	}
	
	
	/**
	 * Calcul l'angle entre deux points
	 * @param p1 Le point dont on veut connaitre l'orientation
	 * @return L'angle entre les deux points par rapport
	 */
	public float angle(Point p1){
		if(this.equals(p1)) {
			return 0;
		}
		else if(p1.getY() == this.y && p1.getX() > this.x)
			return 0;
		else if(p1.getY() == this.y && p1.getX() < this.x)
			return 180;
		else if(p1.getX() == this.x && p1.getY() > this.y)
			return 90;
		else if(p1.getX() == this.x && p1.getY() < this.y)
			return 270;
		else {
			Point p2 = new Point(p1.getX(), this.y);
			if(p1.getX() > this.x && p1.getY() > this.y) {
				return (float) Math.toDegrees(Math.asin(this.distance(p2)/this.distance(p1)));
			}
			else if(p1.getX() > this.x && p1.getY() < this.y) {
				return 270.0f + (float) Math.toDegrees(Math.asin(this.distance(p2)/this.distance(p1)));
			}
			else if(p1.getX() > this.x && p1.getY() > this.y) {
				return 180.0f + (float) Math.toDegrees(Math.asin(this.distance(p2)/this.distance(p1)));
			}
			else {
				return 90.0f + (float) Math.toDegrees(Math.asin(this.distance(p2)/this.distance(p1)));
			}
		}
	}
}
