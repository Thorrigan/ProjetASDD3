import java.util.ArrayList;
import java.util.Scanner;

public class TestsUnitaires {
	
	public TestsUnitaires() {
		MenuTestUnitaire();
	}
	
	public void testPolygone(ArrayList<Point> polygone1, ArrayList<Point> polygone2) {			
		Polygone p1 = new Polygone(polygone1);
		Polygone p2 = new Polygone(polygone2);
		System.out.println("Nos Polygones: P1" + p1 + " P2" + p2);
		ArrayList<Point> tabp = p1.PointsIntersection(p2);
		if(tabp != null && tabp.size() == 1) {
			System.out.println("Il existe un point d'intersection entre les deux polygones: " + tabp.get(0));
		}else if(tabp != null && tabp.size() > 1){
			String s = "Il existe plusieurs points d'intersections entre les deux polygones: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux polygones.");
		}
		System.out.println();
	}
	
	public void MenuTestUnitaire() {
		System.out.println("Veuillez entrer votre choix:  (-1 pour revenir en arrière)");
		System.out.println("-1 Test Sur les Droites");
		System.out.println("-2 Test Sur les Segments");
		System.out.println("-3 Test Sur les Triangles");
		System.out.println("-4 Test Sur les Rectangles");
		System.out.println("-5 Test Sur les Polygones");
		System.out.println("-6 Test Sur les intersections de Formes");
		
		int saisie = Selection();
		while(saisie >= 1  && saisie <= 6) {
			if(saisie == 1) {
				
			}else if(saisie == 2) {
				
			}else if(saisie == 3) {
				
			}else if(saisie == 4) {
				
			}else if(saisie == 5) {
				
			}else if(saisie == 6) {
				testPolygone(SaisieListePoints(), SaisieListePoints());
			}
			saisie = Selection();
		}
	}
	
	private int Selection() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}
	
	private ArrayList<Point> SaisieListePoints() {
		ArrayList<Point> lstp = new ArrayList<Point>();
		System.out.println("Veuillez entrer votre choix:");
		Point p = SaisiePoint();
		while(p != null) {
			lstp.add(p);
			p = SaisiePoint();
		}
		
		if(lstp.isEmpty()) {
			return null;
		}
		return lstp;
	}
	
	private Point SaisiePoint() {
		System.out.println("Saisissez un point : (Entrer n pour annuler)");
		Scanner scanner = new Scanner(System.in);
		System.out.print("X: ");
		if(scanner.hasNextFloat()) {
			float x = scanner.nextFloat();
			System.out.print("Y: ");
			if(scanner.hasNextFloat()) {
				float y = scanner.nextFloat();
				return new Point(x, y);
			}
			return null;
		}
		return null;
	}
}