package main;
import java.util.ArrayList;

import Maths.Droite;
import Maths.Point;
import Maths.Polygone;
import Maths.Triangle;
import Tests.TestsUnitairesMaths;

/**
 * Compl�xit�: Dans ce programme, nous noterons: 
 * 1 : une compl�xit� constante
 * n : le nombre d'it�ration dans le cas g�n�ral
 * log(n) : une compl�xit� logarithmique 
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
		System.out.println(nomFichier);
		lecteur lec = new lecteur(nomFichier);
		ArrayList<Polygone> listp = lec.getMap();
		
		for(Polygone p : listp){
			//Polygone p = listp.get(11);
			//System.out.println(p.transformationSegment());
			ArrayList<Triangle> tt = p.triangulation();
			for(Triangle t : tt) {
				System.out.println(t);
			}
		}
		
		System.out.println(listp.get(0).getType());
		
		/*Segment s1 = new Segment(new Point(1.0f, 1.0f), new Point(11.0f,11.0f));
		Segment s2 = new Segment(new Point(3.0f, 3.0f), new Point(4.0f,4.0f));
		//System.out.println(s1.contient(s2));
		//System.out.println(new Point(1.0f, 1.0f).equals(new Point(1.0f, 1.1f)));
		
		
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
		
		//System.out.println(pg.contient(new Point(1.0f, 2.0f)));		
		//Triangle t = new Triangle(new Point(1.0f,1.0f), new Point(2.0f,1.0f), new Point(2.0f,2.0f));		
		//System.out.println(pg.PointsIntersection(t));
		ArrayList<Triangle> tt = pg.triangulation();
		for(Triangle t : tt) {
			System.out.println(t);
		}
		*/
		
		
		/*Point p1 = new Point(1.0f, 1.0f);
		Point p2 = new Point(1.0f, 2.0f);
		float angle = 90f;
		System.out.println(angle);
		Point p3 = p1.rotation(1f, angle);
		System.out.println(p3);*/
		//TestsUnitairesMaths.menuprincipalMaths();
		
		//Droite d = new Droite(new Point(1.0f,0.0f), new Point(2.0f,2.0f));
		//System.out.println(d + " " + d.pointenX(0.5f));
		
		/*Polygone pg = new Polygone();
		pg.ajouterPoint(new Point(1.0f,1.0f));
		pg.ajouterPoint(new Point(1.0f,3.0f));
		pg.ajouterPoint(new Point(3.0f,3.0f));
		pg.ajouterPoint(new Point(4.0f,4.0f));
		pg.ajouterPoint(new Point(5.0f,4.0f));
		pg.ajouterPoint(new Point(5.0f,2.0f));
		pg.ajouterPoint(new Point(6.0f,1.0f));
		pg.ajouterPoint(new Point(5.0f,0.0f));
		pg.ajouterPoint(new Point(2.0f,1.0f));
		
		//System.out.println(pg.contient(new Point(1.0f, 2.0f)));		
		//Triangle t = new Triangle(new Point(1.0f,1.0f), new Point(2.0f,1.0f), new Point(2.0f,2.0f));		
		//System.out.println(pg.PointsIntersection(t));
		ArrayList<Triangle> tt = pg.triangulation();
		/*for(Triangle t : tt) {
			System.out.println(t);
		}*/
		
		//Jeu jeu = new Jeu(nomFichier);
	}
}
