package Tests;
import Maths.Droite;
import Maths.Forme;
import Maths.Point;

public class TestsUnitairesMaths {
	public static void menuprincipalMaths() {
		test();
	}
	
	private static void test() {
		System.out.println("--- Deux droites parallèles");
		Point p1 = new Point(3,1);
		Point p2 = new Point(4,2);
		Point p3 = new Point(1,0);
		Point p4 = new Point(2,1);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Droite d1 = new Droite(p1,p2);
		Droite d2 = new Droite(p3,p4);
		System.out.println("Nos droites: D1" + d1 + " D2" + d2);
		testFormes(d1,d2);
	}
	
	private static void testFormes(Forme f1, Forme f2) {
		if(f1.intersection(f2) && f2.intersection(f1)) {
			System.out.println("Les deux formes sont sécantes.");
			for(Point p : f1.PointsIntersection(f2)) {
				
			}
		}else if((f1.intersection(f2) && !f2.intersection(f1)) || (!f1.intersection(f2) && f2.intersection(f1))) {
			System.out.println("*-*-*-*-*   ERREUR   *-*-*-*-*");
		}else if(!f1.intersection(f2) && !f2.intersection(f1)){
			System.out.println("Les deux formes ne sont pas sécantes.");
		}else {
			System.out.println("*-*-*-*-*   ERREUR   *-*-*-*-*");
		}
	}
}
