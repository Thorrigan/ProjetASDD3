import java.util.ArrayList;

public class QuadTree {
	private Noeud racine;
	
	private class Noeud{
		Point p;
		Noeud n1,n2,n3,n4;
		ArrayList<Triangle> faces;
		
		Noeud(Point p, ArrayList<Triangle> faces){
			this.p = p;
			this.faces = faces;
		}
	}
	
	public void inserer(Point p, ArrayList<Triangle> liste_faces) {
		racine = inserer(racine, p, liste_faces);
	}
	
	public Noeud inserer(Noeud n, Point p, ArrayList<Triangle> liste_faces) {
		float X = n.p.getX();
		float Y = n.p.getY();
		float x = p.getX();
		float y = p.getY();
		
		if(n == null) {
			return new Noeud(p, liste_faces);
		}
		else if(x < X && y < Y) {
			
		}
		else if(x < X && y > Y) {
			
		}
		else if(x > X && y < Y) {
			
		}
		else if(x > X && y > Y) {
	
		}
		return n;
	}
	
	public QuadTree ConstructionQT() {
		return null;
		
	}
}
