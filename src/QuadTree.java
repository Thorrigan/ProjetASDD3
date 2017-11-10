import java.util.ArrayList;

public class QuadTree {
	private Noeud racine;
	private int N; //nb max de triangle dans une région
	
	public QuadTree(float min_x,float max_x, float min_y, float max_y, int nbIntersec) {
		racine = new Noeud(new Rectangle(new Point(min_x, min_y), new Point(max_x, max_y)));
		N = nbIntersec;
	}
	
	private class Noeud{
		Noeud n1,n2,n3,n4;
		ArrayList<Triangle> triangles;
		Rectangle region;
		
		Noeud(Rectangle r){
			this.region = r;
		}
		
		boolean estFeuille() {
			if(triangles.isEmpty()) {
				return false;
			}
			return true;
		}
	}
	
	public void inserer(Triangle t) {
		inserer(racine, t);
	}
	
	private Noeud inserer(Noeud n, Triangle t) {
		return null;
	}
	
	public Triangle recherche(Point p) {
		return recherche(racine, p);
	}
	
	private Triangle recherche(Noeud n, Point p) {
		if(n.estFeuille()) {
			for(Triangle t : n.triangles) {
				if(t.contient(p)) {
					return t;
				}
			}
		}else {
			float x = n.region.centre().getX();
			float y = n.region.centre().getY();
			if(p.getX() <= x && p.getY() >= y) {
				return recherche(n.n1, p);
			}else if(p.getX() > x && p.getY() >= y) {
				return recherche(n.n2, p);
			}else if(p.getX() >= x && p.getY() < y) {
				return recherche(n.n3, p);
			}else if(p.getX() < x && p.getY() < y) {
				return recherche(n.n4, p);
			}
		}
		return null; // Si le point n'est pas dans un triangle
	}
	
	
	public QuadTree ConstructionQT() {
		return null;
		
	}
}
