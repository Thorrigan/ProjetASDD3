package main;
import Maths.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import Dessin.Fenetre;
import Dessin.Fond;
import Maths.Droite;
import Maths.Point;
import Maths.Polygone;
import Maths.Rectangle;
import Maths.Triangle;

public class Jeu {
	private Point balle;
	private QuadTree map;
	private ArrayList<Polygone> lpoly;	
	private ArrayList<Trace> traces;
	private int trouAct;
	private int scoretot;
	private int scoreact;
	private int state;
	private boolean fin;
	private Fenetre fenetre;
	
	/*
	 * private Point arrive;
	private int score;
	
	private int scoretrace;
	private int state;
	private int par;
	private int tour;
	 */
	
	
	/*STATE A CHOISIR
	0 default
	1 buff
	2 debuff sable
	3 
	*/
	
	public Jeu(ArrayList<Trace> trace, ArrayList<Polygone> lst, float min_x, float max_x, float min_y, float max_y, int N) {
		this.balle = trace.get(0).getDepart();
		this.map = QuadTree.ConstructionQT(lst, 0, 10, 0, 10, N);
		this.lpoly = lst;
		this.traces = trace;
		this.trouAct = 0;
		this.scoretot = 0;
		this.scoreact = 0;
		this.state = 0;
		this.fin = false;
	}
	
	public int parAct() {
		return traces.get(trouAct).getPar();
	}
	
	public Point ballePosition() {
		return balle;
	}
	
	public int scoretotal() {
		return this.scoretot;
	}
	
	public int scoreactuel() {
		return this.scoreact;
	}
	
	public int partotal() {
		int compteur = 0;
		for(Trace t : this.traces) {
			compteur += t.getPar();
		}
		return compteur;
	}
	
	public int trouActuel() {
		return this.trouAct;
	}
	
	public Point ptDepart() {
		return this.traces.get(trouAct).getDepart();
	}
	
	public Point ptArrive() {
		return this.traces.get(trouAct).getArrivee();
	}
	
	public void JeuGraphique(){
		// Initialisation de la fenetre graphique
		fenetre = new Fenetre(this);		
	}
	
	public void JeuConsole(){
		for(Trace t : this.traces) {
			float aprox = 0.5f;
			Rectangle region = new Rectangle(new Point(t.getArrivee().getX()-aprox, t.getArrivee().getY()-aprox), 
											 new Point(t.getArrivee().getX()+aprox, t.getArrivee().getY()+aprox));
			System.out.println(region);
			while(!region.contient(balle)) {
				System.out.println("La balle se situe en: " + balle);
				//Point dest = SaisiePoint();
				//System.out.println("On veut se diriger en: " + dest);
				// on traite le point et tout et tout...
				balle = SaisiePoint();
				
				
				
			}
			System.out.println("Vous avez fini ce trace.");
			System.out.println("Vous avez obtenu le score:  " + this.scoreact + " / " + this.parAct());
			this.trouAct++;
		}
		System.out.println("Vous venez de finir le jeu.");
		System.out.println("Vous avez obtenu le score:  " + this.scoretot + " / " + this.partotal());
	}
	
	private Point SaisiePoint() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("X: ");
			float x = Float.parseFloat(input.readLine());
			System.out.print("Y: ");
			float y = Float.parseFloat(input.readLine());
			return new Point(x,y);
		} catch (NumberFormatException e) {
			System.out.print("Erreur dans la saisie du point.");
			return null;
		} catch (IOException e) {
			System.out.print("Erreur dans la saisie du point.");
			return null;
		}
	}
	
	public void JouerCoup(Point dest) {
		System.out.println("On tire vers le point " + dest);
		this.balle = dest;
		this.fenetre.repaint();
		this.scoreact++;
		
		
		if(fin == true) {
			System.out.println("Vous avez gagné !");
			System.exit(0);
		}
	}
	
	public void JouerCoup(float angle, float distance) {
		System.out.println("On tire avec un angle de " + angle + " degres et une distance de " + distance);
	}
	
	public ArrayList<Polygone> getPolygones(){
		return this.lpoly;
	}
	
	public ArrayList<Triangle> getTriangles(){
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for(Polygone p : this.lpoly) {
			triangles.addAll(p.triangulation());
		}
		return triangles;
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
		if (signe == 1)
			angler = angle + moda;
		else
			angler = angle - moda;
		signe = (int) Math.random();
		if (signe == 1)
			distr = (1+(b/100)) * balle.distance(cible);
		else if (state == 2)
			distr = ((1-(b/100)) * balle.distance(cible)) / 2;
		else
			distr = (1-(b/100)) * balle.distance(cible);
		this.scoreact++;
		return balle.rotation(distr, angler);
	}
	
	
	
	public Point CalculePointDepartBalle(Point cible){
		if (map.recherche(cible) == null)
			return null;
		else {
			char type = map.recherche(cible).getType();
			if (cible.getX() > 10.0 || cible.getY() > 10.0 || type == 'S') {
				this.scoreact++;
				return balle;
			}
			else if (type == 'J') {
				this.state = 2;
				return cible;
			}
			else if (type == 'B') {
				int i = 0;
				Droite d = new Droite(cible, this.ptArrive()); //p1 cible, p2 arrivée
				ArrayList<Point> points;
				this.scoreact++;
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
			else if (this.ptArrive().distance(cible) <= 1.0) {
				this.state = 1;
				return cible;
			}
			else {
				this.state = 0;
				return cible;
			}
		}
	}
}
