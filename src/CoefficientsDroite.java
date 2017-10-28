
public class CoefficientsDroite {
	float a;
	float b;
	float c;
	
	public CoefficientsDroite(float a, float b, float c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public static CoefficientsDroite CalculCoefficientsDroite(Point p1, Point p2) {
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
		else {
			a = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());		
			if(p2.getX() < p1.getX()) {
				a *= -1;
			}
			b = -1;
			c = p1.getY() - a*p1.getY();
		}
		
		return new CoefficientsDroite(a ,b, c);
	}
	
	public boolean estSurDroite(Point p) {
		if((this.a*p.getX() + this.b*p.getY() + this.c) == 0) {
			return true;
		}
		return false;
	}
}
