package main;
import Tests.TestsUnitairesMaths;

/**
 * Compléxité: Dans ce programme, nous noterons: 
 * 1 : une compléxité constante
 * n : le nombre d'itération dans le cas général
 * log(n) : une compléxité logarithmique 
 * nbp : le nombre de points 
 * nbd : le nombre de droites
 * nbs : le nombre de segments
 * nbt : le nombre de triangles
 * nbpg : le nombre de polygones
 * @author matt
 *
 */
public class Main {
	public static void main(String[] args) {
		String nomFichier = "map.txt";
		if(args.length != 0) {
			nomFichier = args[0];
		}
		
		TestsUnitairesMaths.menuprincipalMaths();
		
		/*
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
		
		Triangle t = new Triangle(new Point(1.0f,1.0f), new Point(2.0f,1.0f), new Point(2.0f,2.0f));
		
		System.out.println(pg.PointsIntersection(t));
		ArrayList<Triangle> tt = pg.triangulation();
		for(Triangle t : tt) {
			System.out.println(t);
		}
		
		Jeu jeu = new Jeu(nomFichier);
		TestsUnitaires test = new TestsUnitaires();
		testsMaths();*/
	}
}
