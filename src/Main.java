import java.util.ArrayList;


public class Main {
	public static void main(String[] args) {
		String nomFichier = "map.txt";
		if(args.length != 0) {
			nomFichier = args[0];
		}
		Polygone pg = new Polygone();
		pg.ajouterPoint(new Point(1.0f,1.0f));
		pg.ajouterPoint(new Point(1.0f,3.0f));
		pg.ajouterPoint(new Point(3.0f,3.0f));
		pg.ajouterPoint(new Point(4.0f,4.0f));
		pg.ajouterPoint(new Point(5.0f,4.0f));
		pg.ajouterPoint(new Point(5.0f,2.0f));
		pg.ajouterPoint(new Point(6.0f,1.0f));
		pg.ajouterPoint(new Point(5.0f,0.0f));
		pg.ajouterPoint(new Point(2.0f,1.0f));
		ArrayList<Triangle> tt = pg.triangulation();
		for(Triangle t : tt) {
			System.out.println(t);
		}
		
		//Jeu jeu = new Jeu(nomFichier);
		//TestsUnitaires test = new TestsUnitaires();
		//testsMaths
	}
	
	private static void testsMaths() {
		TestsDroites();
		TestsSegments();
		TestsTriangles();
		TestsRectangles();
		TestsPolygones();
	}
	
	private static void TestsRectangles() {
		System.out.println("//////// TESTS SUR LES RECTANGLES ////////");
		testRectangle1();
		testRectangle2();
		testRectangle3();
		testRectangle4();
		testRectangle5();
		testRectangle6();
		System.out.println("//////// FIN DES TESTS SUR LES RECTANGLES ////////");	
	}
	
	private static void TestsPolygones() {
		System.out.println("//////// TESTS SUR LES POLYGONES ////////");
		testPolygone1();
		testPolygone2();
		testPolygone3();
		testPolygone4();
		testPolygone5();
		System.out.println("//////// FIN DES TESTS SUR LES POLYGONES ////////");	
	}

	private static void TestsTriangles() {
		System.out.println("//////// TESTS SUR LES TRIANGLES ////////");
		testTriangle1();
		testTriangle2();
		testTriangle3();
		testTriangle4();
		testTriangle5();
		testTriangle6();
		testTriangle7();
		System.out.println("//////// FIN DES TESTS SUR LES TRIANGLES ////////");
	}
	
	private static void TestsSegments() {
		System.out.println("//////// TESTS SUR LES SEGMENTS ////////");
		testSegment1();
		testSegment2();
		testSegment3();
		testSegment4();
		testSegment5();
		System.out.println("//////// FIN DES TESTS SUR LES SEGMENTS ////////");
	}
	
	private static void TestsDroites() {
		System.out.println("//////// TESTS SUR LES DROITES ////////");
		testDroite1();
		testDroite2();
		testDroite3();
		testDroite4();
		testDroite5();
		testDroite6();
		testDroite7();
		System.out.println("//////// FIN DES TESTS SUR LES DROITES ////////");
	}
	
	
	private static void  testDroite1() {
		System.out.println("--- Deux droites parallèles");
		Point p1 = new Point(3,1);
		Point p2 = new Point(4,2);
		Point p3 = new Point(1,0);
		Point p4 = new Point(2,1);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Droite d1 = new Droite(p1,p2);
		Droite d2 = new Droite(p3,p4);
		System.out.println("Nos droites: D1" + d1 + " D2" + d2);
		Point p = d1.PointsIntersection(d2).get(0);
		System.out.println("Le point d'intersection  des droites: " + ((p != null) ? p.toString() : "n'existe pas"));
		System.out.println();
	}
	
	private static void  testDroite2() {
		System.out.println("--- Deux droites sécantes");
		Point p1 = new Point(3,1);
		Point p2 = new Point(4,2);
		Point p3 = new Point(-2,2);
		Point p4 = new Point(2,-4);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Droite d1 = new Droite(p1,p2);
		Droite d2 = new Droite(p3,p4);
		System.out.println("Nos droites: D1" + d1 + " D2" + d2);
		Point p = d1.PointsIntersection(d2).get(0);
		System.out.println("Le point d'intersection  des droites: " + ((p != null) ? p.toString() : "n'existe pas"));
		System.out.println();
	}
	
	private static void  testDroite3() {
		System.out.println("--- Deux droites sécantes dont une horizontale");
		Point p1 = new Point(2,1);
		Point p2 = new Point(3,1);
		Point p3 = new Point(0,0);
		Point p4 = new Point(2,8);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Droite d1 = new Droite(p1,p2);
		Droite d2 = new Droite(p3,p4);
		System.out.println("Nos droites: D1" + d1 + " D2" + d2);
		Point p = d1.PointsIntersection(d2).get(0);
		System.out.println("Le point d'intersection  des droites: " + ((p != null) ? p.toString() : "n'existe pas"));
		System.out.println();
	}
	
	private static void  testDroite4() {
		System.out.println("--- Deux droites sécantes dont une verticale");
		Point p1 = new Point(1,2);
		Point p2 = new Point(1,3);
		Point p3 = new Point(0,0);
		Point p4 = new Point(2,8);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Droite d1 = new Droite(p1,p2);
		Droite d2 = new Droite(p3,p4);
		System.out.println("Nos droites: D1" + d1 + " D2" + d2);
		Point p = d1.PointsIntersection(d2).get(0);
		System.out.println("Le point d'intersection  des droites: " + ((p != null) ? p.toString() : "n'existe pas"));
		System.out.println();
	}
	
	private static void  testDroite5() {
		System.out.println("--- Deux droites verticales");
		Point p1 = new Point(1,2);
		Point p2 = new Point(1,3);
		Point p3 = new Point(3,0);
		Point p4 = new Point(3,8);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Droite d1 = new Droite(p1,p2);
		Droite d2 = new Droite(p3,p4);
		System.out.println("Nos droites: D1" + d1 + " D2" + d2);
		Point p = d1.PointsIntersection(d2).get(0);
		System.out.println("Le point d'intersection  des droites: " + ((p != null) ? p.toString() : "n'existe pas"));
		System.out.println();
	}
	
	private static void  testDroite6() {
		System.out.println("--- Deux droites horizontale");
		Point p1 = new Point(2,1);
		Point p2 = new Point(3,1);
		Point p3 = new Point(0,3);
		Point p4 = new Point(8,3);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Droite d1 = new Droite(p1,p2);
		Droite d2 = new Droite(p3,p4);
		System.out.println("Nos droites: D1" + d1 + " D2" + d2);
		Point p = d1.PointsIntersection(d2).get(0);
		System.out.println("Le point d'intersection  des droites: " + ((p != null) ? p.toString() : "n'existe pas"));
		System.out.println();
	}
	
	private static void  testDroite7() {
		System.out.println("--- Une droite verticale et une droite horizontale");
		Point p1 = new Point(3,1);
		Point p2 = new Point(4,1);
		Point p3 = new Point(-2,2);
		Point p4 = new Point(-2,3);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Droite d1 = new Droite(p1,p2);
		Droite d2 = new Droite(p3,p4);
		System.out.println("Nos droites: D1" + d1 + " D2" + d2);
		Point p = d1.PointsIntersection(d2).get(0);
		System.out.println("Le point d'intersection  des droites: " + ((p != null) ? p.toString() : "n'existe pas"));
		System.out.println();
	}
	
	
	private static void  testSegment1() {
		System.out.println("--- Deux segments sécants");
		Point p1 = new Point(-4,2);
		Point p2 = new Point(4,1);
		Point p3 = new Point(-4,0);
		Point p4 = new Point(1,4);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p3, p4);
		System.out.println("Nos segments: S1" + s1 + " S2" + s2);
		Point p = s1.PointsIntersection(s2).get(0);
		System.out.println("Le point d'intersection  des segments: " + ((p != null) ? p.toString() : "n'existe pas"));
		System.out.println();
	}
	
	private static void  testSegment2() {
		System.out.println("--- Deux segments parallèles");
		Point p1 = new Point(-3,0);
		Point p2 = new Point(-1,2);
		Point p3 = new Point(-1,-2);
		Point p4 = new Point(1,0);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p3, p4);
		System.out.println("Nos segments: S1" + s1 + " S2" + s2);
		Point p = s1.PointsIntersection(s2).get(0);
		System.out.println("Le point d'intersection  des segments: " + ((p != null) ? p.toString() : "n'existe pas"));
		System.out.println();
	}
	
	private static void  testSegment3() {
		System.out.println("--- Deux segments non sécants dont les droites sont sécantes");
		Point p1 = new Point(-3,0);
		Point p2 = new Point(-1,2);
		Point p3 = new Point(-1,0);
		Point p4 = new Point(-1,-2);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p3, p4);
		System.out.println("Nos segments: S1" + s1 + " S2" + s2);
		Point p = s1.PointsIntersection(s2).get(0);
		System.out.println("Le point d'intersection  des segments: " + ((p != null) ? p.toString() : "n'existe pas"));
		System.out.println();
	}
	
	private static void  testSegment4() {
		System.out.println("--- Deux segments sécants avec 1 point en commun");
		Point p1 = new Point(-3,0);
		Point p2 = new Point(-1,2);
		Point p3 = new Point(-3,0);
		Point p4 = new Point(-1,-2);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p3, p4);
		System.out.println("Nos segments: S1" + s1 + " S2" + s2);
		Point p = s1.PointsIntersection(s2).get(0);
		System.out.println("Le point d'intersection  des segments: " + ((p != null) ? p.toString() : "n'existe pas"));
		System.out.println();
	}
	
	private static void  testSegment5() {
		System.out.println("--- Deux segments sécants dont un point est sur le deuxième segment");
		Point p1 = new Point(1,1);
		Point p2 = new Point(4,1);
		Point p3 = new Point(3,1);
		Point p4 = new Point(3,3);
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p3, p4);
		System.out.println("Nos segments: S1" + s1 + " S2" + s2);
		Point p = s1.PointsIntersection(s2).get(0);
		System.out.println("Le point d'intersection  des segments: " + ((p != null) ? p.toString() : "n'existe pas"));
		System.out.println();
	}
	
	
	private static void  testTriangle1() {
		System.out.println("--- Deux Triangles distincts");
		Point p1 = new Point(0,1);
		Point p2 = new Point(0,0);
		Point p3 = new Point(1,0);
		
		Point p4 = new Point(2,2);
		Point p5 = new Point(3,2);
		Point p6 = new Point(3,1);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4 + ", E" + p5 + ", F" + p6);
		Triangle t1 = new Triangle(p1, p2, p3);
		Triangle t2 = new Triangle(p4, p5, p6);
		System.out.println("Nos triangles: T1" + t1 + " T2" + t2);
		ArrayList<Point> tabp = t1.PointsIntersection(t2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les triangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux triangles.");
		}
		System.out.println();
	}
	
	private static void  testTriangle2() {
		System.out.println("--- Deux Triangles avec un point en commun");
		Point p1 = new Point(0,1);
		Point p2 = new Point(0,0);
		Point p3 = new Point(1,0);
		
		Point p4 = new Point(0,1);
		Point p5 = new Point(0,2);
		Point p6 = new Point(-1,2);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4 + ", E" + p5 + ", F" + p6);
		Triangle t1 = new Triangle(p1, p2, p3);
		Triangle t2 = new Triangle(p4, p5, p6);
		System.out.println("Nos triangles: T1" + t1 + " T2" + t2);
		ArrayList<Point> tabp = t1.PointsIntersection(t2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les triangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux triangles.");
		}
		System.out.println();
	}
	
	private static void  testTriangle3() {
		System.out.println("--- Deux Triangles avec deux points en commun");
		Point p1 = new Point(0,1);
		Point p2 = new Point(0,0);
		Point p3 = new Point(1,0);
		
		Point p4 = new Point(0,0);
		Point p5 = new Point(0,1);
		Point p6 = new Point(-1,0);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4 + ", E" + p5 + ", F" + p6);
		Triangle t1 = new Triangle(p1, p2, p3);
		Triangle t2 = new Triangle(p4, p5, p6);
		System.out.println("Nos triangles: T1" + t1 + " T2" + t2);
		ArrayList<Point> tabp = t1.PointsIntersection(t2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les triangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux triangles.");
		}
		System.out.println();
	}
	
	private static void  testTriangle4() {
		System.out.println("--- Deux Triangles sécant en 1 points");
		Point p1 = new Point(1,1);
		Point p2 = new Point(1,2);
		Point p3 = new Point(3,2);
		
		Point p4 = new Point(1,4);
		Point p5 = new Point(2,2);
		Point p6 = new Point(3,4);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4 + ", E" + p5 + ", F" + p6);
		Triangle t1 = new Triangle(p1, p2, p3);
		Triangle t2 = new Triangle(p4, p5, p6);
		System.out.println("Nos triangles: T1" + t1 + " T2" + t2);
		ArrayList<Point> tabp = t1.PointsIntersection(t2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les triangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux triangles.");
		}
		System.out.println();
	}
	
	private static void  testTriangle5() {
		System.out.println("--- Deux Triangles sécant en 2 points");
		Point p1 = new Point(1,1);
		Point p2 = new Point(1,2);
		Point p3 = new Point(3,2);
		
		Point p4 = new Point(1.5f,1.5f);
		Point p5 = new Point(4,1);
		Point p6 = new Point(3,3);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4 + ", E" + p5 + ", F" + p6);
		Triangle t1 = new Triangle(p1, p2, p3);
		Triangle t2 = new Triangle(p4, p5, p6);
		System.out.println("Nos triangles: T1" + t1 + " T2" + t2);
		ArrayList<Point> tabp = t1.PointsIntersection(t2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les triangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux triangles.");
		}
		System.out.println();
	}
	
	private static void  testTriangle6() {
		System.out.println("--- Deux Triangles sécant en 4 points");
		Point p1 = new Point(1,1);
		Point p2 = new Point(1,2);
		Point p3 = new Point(3,2);
		
		Point p4 = new Point(0.5f,2);
		Point p5 = new Point(2,1);
		Point p6 = new Point(2,3);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4 + ", E" + p5 + ", F" + p6);
		Triangle t1 = new Triangle(p1, p2, p3);
		Triangle t2 = new Triangle(p4, p5, p6);
		System.out.println("Nos triangles: T1" + t1 + " T2" + t2);
		ArrayList<Point> tabp = t1.PointsIntersection(t2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les triangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux triangles.");
		}
		System.out.println();
	}
	
	private static void  testTriangle7() {
		System.out.println("--- Un triangle dans un triangle");
		Point p1 = new Point(1,1);
		Point p2 = new Point(3,1);
		Point p3 = new Point(2,3);
		
		Point p4 = new Point(-1,0);
		Point p5 = new Point(2,4);
		Point p6 = new Point(5,0);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4 + ", E" + p5 + ", F" + p6);
		Triangle t1 = new Triangle(p1, p2, p3);
		Triangle t2 = new Triangle(p4, p5, p6);
		System.out.println("Nos triangles: T1" + t1 + " T2" + t2);
		ArrayList<Point> tabp = t1.PointsIntersection(t2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les triangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux triangles.");
		}
		System.out.println();
	}
	
	private static void  testRectangle1() {
		System.out.println("--- Deux rectangles non sécants");
		Point p1 = new Point(1,1);
		Point p2 = new Point(4,3);
		
		Point p3 = new Point(-1,1);		
		Point p4 = new Point(-3,3);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Rectangle r1 = new Rectangle(p1, p2);
		Rectangle r2 = new Rectangle(p3, p4);
		System.out.println("Nos Rectangles: T1" + r1 + " T2" + r2);
		ArrayList<Point> tabp = r1.PointsIntersection(r2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les rectangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux rectangles.");
		}
		System.out.println();
	}
	private static void  testRectangle2() {
		System.out.println("--- Deux rectangles sécants");
		Point p1 = new Point(1,1);
		Point p2 = new Point(4,3);
		
		Point p3 = new Point(2,2);		
		Point p4 = new Point(-1,4);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Rectangle r1 = new Rectangle(p1, p2);
		Rectangle r2 = new Rectangle(p3, p4);
		System.out.println("Nos Rectangles: T1" + r1 + " T2" + r2);
		ArrayList<Point> tabp = r1.PointsIntersection(r2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les rectangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux rectangles.");
		}
		System.out.println();
	}	
	private static void  testRectangle3() {
		System.out.println("--- Deux rectangles sécants en un point");
		Point p1 = new Point(1,1);
		Point p2 = new Point(4,3);
		
		Point p3 = new Point(-1,3);		
		Point p4 = new Point(1,4);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Rectangle r1 = new Rectangle(p1, p2);
		Rectangle r2 = new Rectangle(p3, p4);
		System.out.println("Nos Rectangles: T1" + r1 + " T2" + r2);
		ArrayList<Point> tabp = r1.PointsIntersection(r2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les rectangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux rectangles.");
		}
		System.out.println();
	}	
	private static void  testRectangle4() {
		System.out.println("--- Deux rectangles avec deux points en communs");
		Point p1 = new Point(1,1);
		Point p2 = new Point(4,3);
		
		Point p3 = new Point(1,3);		
		Point p4 = new Point(-2,1);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Rectangle r1 = new Rectangle(p1, p2);
		Rectangle r2 = new Rectangle(p3, p4);
		System.out.println("Nos Rectangles: T1" + r1 + " T2" + r2);
		ArrayList<Point> tabp = r1.PointsIntersection(r2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les rectangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux rectangles.");
		}
		System.out.println();
	}	
	private static void  testRectangle5() {
		System.out.println("--- Deux rectangles avec deux points en communs");
		Point p1 = new Point(1,1);
		Point p2 = new Point(4,3);
		
		Point p3 = new Point(2,3);		
		Point p4 = new Point(-2,1);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Rectangle r1 = new Rectangle(p1, p2);
		Rectangle r2 = new Rectangle(p3, p4);
		System.out.println("Nos Rectangles: T1" + r1 + " T2" + r2);
		ArrayList<Point> tabp = r1.PointsIntersection(r2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les rectangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux rectangles.");
		}
		System.out.println();
	}	
	private static void  testRectangle6() {
		System.out.println("--- Un rectangle dans un rectangle");
		Point p1 = new Point(0,1);
		Point p2 = new Point(4,4);
		
		Point p3 = new Point(1,2);		
		Point p4 = new Point(3,3);
		
		System.out.println("Nos points: A" + p1 + ", B" + p2 + ", C" + p3 + ", D" + p4);
		Rectangle r1 = new Rectangle(p1, p2);
		Rectangle r2 = new Rectangle(p3, p4);
		System.out.println("Nos Rectangles: T1" + r1 + " T2" + r2);
		ArrayList<Point> tabp = r1.PointsIntersection(r2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les rectangles: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux rectangles.");
		}
		System.out.println();
	}

	private static void  testPolygone1() {
		System.out.println("--- Deux polygones non sécants");
		ArrayList<Point> tab1 = new ArrayList<Point>();
		tab1.add(new Point(1,1));
		tab1.add(new Point(3,1));
		tab1.add(new Point(2,3));
		tab1.add(new Point(0,3));
		ArrayList<Point> tab2 = new ArrayList<Point>();
		tab2.add(new Point(-1,0));
		tab2.add(new Point(-1,1));
		tab2.add(new Point(-2,2));
		tab2.add(new Point(-3,1));
		
		Polygone p1 = new Polygone(tab1);
		Polygone p2 = new Polygone(tab2);
		System.out.println("Nos Polygones: P1" + p1 + " P2" + p2);
		ArrayList<Point> tabp = p1.PointsIntersection(p2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les polygones: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux polygones.");
		}
		System.out.println();
	}
	private static void  testPolygone2() {
		System.out.println("--- Deux polygones sécants");
		ArrayList<Point> tab1 = new ArrayList<Point>();
		tab1.add(new Point(1,1));
		tab1.add(new Point(3,1));
		tab1.add(new Point(2,3));
		tab1.add(new Point(0,3));
		ArrayList<Point> tab2 = new ArrayList<Point>();
		tab2.add(new Point(1,2));
		tab2.add(new Point(1,4));
		tab2.add(new Point(-2,4));
		tab2.add(new Point(-1,2));
		
		Polygone p1 = new Polygone(tab1);
		Polygone p2 = new Polygone(tab2);
		System.out.println("Nos Polygones: P1" + p1 + " P2" + p2);
		ArrayList<Point> tabp = p1.PointsIntersection(p2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les polygones: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux polygones.");
		}
		System.out.println();
	}
	private static void  testPolygone3() {
		System.out.println("--- Deux polygones avec deux points en commun");
		ArrayList<Point> tab1 = new ArrayList<Point>();
		tab1.add(new Point(1,1));
		tab1.add(new Point(3,1));
		tab1.add(new Point(2,3));
		tab1.add(new Point(0,3));
		ArrayList<Point> tab2 = new ArrayList<Point>();
		tab2.add(new Point(1,1));
		tab2.add(new Point(0,3));
		tab2.add(new Point(-1,3));
		tab2.add(new Point(-1,1));
		
		Polygone p1 = new Polygone(tab1);
		Polygone p2 = new Polygone(tab2);
		System.out.println("Nos Polygones: P1" + p1 + " P2" + p2);
		ArrayList<Point> tabp = p1.PointsIntersection(p2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les polygones: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux polygones.");
		}
		System.out.println();
	}
	private static void  testPolygone4() {
		System.out.println("--- Un polygone dans un polygone");
		ArrayList<Point> tab1 = new ArrayList<Point>();
		tab1.add(new Point(-2,0));
		tab1.add(new Point(-1,2));
		tab1.add(new Point(-2,4));
		tab1.add(new Point(-4,4));
		tab1.add(new Point(-5,2));
		tab1.add(new Point(-3,1));
		ArrayList<Point> tab2 = new ArrayList<Point>();
		tab2.add(new Point(-2,1));
		tab2.add(new Point(-2,3));
		tab2.add(new Point(-4,3));
		tab2.add(new Point(-4,2));
		
		
		Polygone p1 = new Polygone(tab1);
		Polygone p2 = new Polygone(tab2);
		System.out.println("Nos Polygones: P1" + p1 + " P2" + p2);
		ArrayList<Point> tabp = p1.PointsIntersection(p2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les polygones: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux polygones.");
		}
		System.out.println();
	}
	private static void  testPolygone5() {
		System.out.println("--- Deux polygones polygones de 10 points chacuns et concave");
		ArrayList<Point> tab1 = new ArrayList<Point>();
		tab1.add(new Point(-1,0));
		tab1.add(new Point(-1,2));
		tab1.add(new Point(2,3));
		tab1.add(new Point(2,2));
		tab1.add(new Point(3,1));
		tab1.add(new Point(4,2));
		tab1.add(new Point(5,2));
		tab1.add(new Point(5,-1));
		tab1.add(new Point(4,-2));
		tab1.add(new Point(1,-2));
		
		ArrayList<Point> tab2 = new ArrayList<Point>();
		tab2.add(new Point(-1,-1));
		tab2.add(new Point(2,-1));
		tab2.add(new Point(3,-3));
		tab2.add(new Point(5,-2));
		tab2.add(new Point(4,-1));
		tab2.add(new Point(6,3));
		tab2.add(new Point(4,3));
		tab2.add(new Point(4,1));
		tab2.add(new Point(1,2));
		tab2.add(new Point(-2,2));		
		
		Polygone p1 = new Polygone(tab1);
		Polygone p2 = new Polygone(tab2);
		System.out.println("Nos Polygones: P1" + p1 + " P2" + p2);
		ArrayList<Point> tabp = p1.PointsIntersection(p2);
		if(tabp.get(0) != null) {
			String s = "Les points d'intersections entre les polygones: ";
			for(Point p : tabp) {
				s += p + " ";
			}
			System.out.println(s);
		}else {
			System.out.println("Il n'existe pas de point d'intersection entre les deux polygones.");
		}
		System.out.println();
	}
}
