public class Droite {
	Point p1;
	Point p2;
	CoefficientsDroite coefs;
	
	public Droite (Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.coefs = CoefficientsDroite.CalculCoefficientsDroite(p1, p2);
	}
	
	public boolean intersectionDroite(Droite d1, Droite d2) {
		if(d1.coefs.a != d2.coefs.a) {
			return true;
		}
		return false;
	}
	
	public Point pointIntersectionDroites(Droite d1, Droite d2) {
		return p1;
		
	}
	
	public boolean estSurDroite(Droite d, Point p) {
		return d.coefs.estSurDroite(p);
	}
	
	public int demiPlan(Droite d, Point p) {
		if(d.estSurDroite(d, p)) {
			return 0;
		}else if(p.getY() > d.coefs.a*p.getX() + d.coefs.c) {
			return 1;
		}
		else {
			return -1;
		}
	}
}
