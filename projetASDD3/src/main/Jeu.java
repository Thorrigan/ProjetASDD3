package main;
import Maths.Point;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import Dessin.Fenetre;
import Maths.Droite;
import Maths.Point;
import Maths.Polygone;
import Maths.Triangle;

public class Jeu {
	private Point balle;
	private QuadTree map;
	private Point arrive;
	private int score;
	
	private int scoretrace;
	private int state;
	private int par;
	private int tour;
	private ArrayList<Polygone> lpoly;
	private ArrayList<Polygone> parpoly;

	
	/*STATE A CHOISIR
	0 default
	1 buff
	2 debuff sable
	3 
	*/
	
	public Jeu(Point depart, Point arrive, ArrayList<Polygone> lst, float min_x, float max_x, float min_y, float max_y, int N, ArrayList<Polygone> parpoly) {
		// Creation du QuadTree
		this.lpoly = lst;
		this.map = QuadTree.ConstructionQT(lpoly, 0, 10, 0, 10, N);
		map.afficher();
		// Definition point depart et arrivee
		this.balle = depart;
		this.arrive = arrive;
		this.parpoly = parpoly;
		this.state = 0;
		Point cible = new Point(0.0f,0.0f);
		
		//while (this.balle.equals(arrive)) {
			System.out.println("TESTUUU : ");
			/*cible = SaisiePoint();
			System.out.println("cible :" + cible);
			cible = this.CalculePointAtterrissageBalle(cible);
			System.out.println("cible post aterrisage5:" + cible);*/
		//}
	}
	
	public void JeuGraphique(){
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for(Polygone p : this.lpoly){
			triangles.addAll(p.triangulation());
		}
		JFrame fenetre = new Fenetre(triangles);		
	}
	
	public void JeuConsole(){
		
	}
	
	//A TESTER LUL
	public Point CalculePointAtterrissageBalle(Point cible) {
		float angle, b, moda, angler, distr;
		int signe; //0 negatif //1 positif
		
		angle = balle.angle(cible);
		signe = (int) Math.random(); //(int)(Math.random()*(max-min))+min
		System.out.println(signe);
		b = (float) (Math.random() *(40));
		moda = (float) (Math.random() *(40));
		System.out.println("moda " + moda + "   b : " + b);
		if (signe == 1)
			angler = angle + moda;
		else
			angler = angle - moda;
		signe = (int) Math.random();
		System.out.println("signe : " + signe + "   angle : " + angler);
		if (signe == 1)
			distr = (1+(b/100)) * balle.distance(cible);
		else if (state == 2)
			distr = ((1-(b/100)) * balle.distance(cible)) / 2;
		else
			distr = (1-(b/100)) * balle.distance(cible);
		this.scoretrace++;
		return balle.rotation(distr, angler);
	}
	
	
	
	public Point CalculePointDepartBalle(Point cible){
		if (map.recherche(cible) == null)
			return null;
		else {
			char type = map.recherche(cible).getType();
			if (cible.getX() > 10.0 || cible.getY() > 10.0 || type == 'S') {
				this.scoretrace++;
				return balle;
			}
			else if (type == 'J') {
				this.state = 2;
				return cible;
			}
			else if (type == 'B') {
				int i = 0;
				Droite d = new Droite(cible, arrive); //p1 cible, p2 arrivée
				ArrayList<Point> points;
				this.scoretrace++;
				//trouver le polygone qui contient le point
				
				
				// PAS BON DE CHERCHE DANS LA LISTE DES POLYGONES, UTILISER QUADTREE ?????
				while (lpoly.get(i).contient(cible) == false)
					i++;
				//obtention point intersection
				points = lpoly.get(i).PointsIntersection(d);
				
				
				
				
				Point min = new Point(1555.0f, 1555.0f);
				//calcul distance mini
				for (Point p : points) {
					if (cible.distance(p) < cible.distance(min))
						min = p;
				}
				this.state = 0;
				return min;
			}
			else if (arrive.distance(cible) <= 1.0) {
				this.state = 1;
				return cible;
			}
			else {
				this.state = 0;
				return cible;
			}
		}
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
				scanner.close();
				return new Point(x, y);
			}
			scanner.close();
			return null;
		}
		scanner.close();
		return null;
	}
	
	public int Calculescoretrace() {
		if (tour < par)
			return this.tour - this.scoretrace;
		else
			return par - this.scoretrace;
	}
	
	public int CalculeScore() {
		return score;
	}
}
