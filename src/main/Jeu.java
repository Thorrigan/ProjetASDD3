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
import Maths.Segment;
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
	
	
	/*STATE A CHOISIR
	0 default
	1 buff
	2 debuff sable
	3 
	*/
	
	public Jeu(ArrayList<Trace> trace, ArrayList<Polygone> lst, float min_x, float max_x, float min_y, float max_y, int N) {
		this.balle = trace.get(0).getDepart();
		this.map = QuadTree.ConstructionQT(lst, 0, 10, 0, 10, N);
		//this.map.afficher();
		for(float i = 0.0f; i <= 10.0f; i+= 0.1f) {
			for(float j = 0.0f; j <= 10.0f; j+= 0.1f) {
				if(this.map.recherche(new Point(i,j)) == null) {
					System.out.println("ERREUR i: " + i + "  j: " + j);
				}
			}
		}
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
	
	public Trace getactTrace() {
		return this.traces.get(this.trouAct);
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
				Point cible = new Point(0.0f,0.0f);

				//Point dest = SaisiePoint();
				//System.out.println("On veut se diriger en: " + dest);
				// on traite le point et tout et tout...
				balle = SaisiePoint();
				
				System.out.println("TESTUUU : ");
				cible = SaisiePoint();
				System.out.println("cible :" + cible);
				//cible = this.CalculePointAtterrissageBalle(cible);
				System.out.println("cible post aterrisage5:" + cible);
				
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
		System.out.println(balle);
		System.out.println("On tire avec un angle de " + angle + " degres et une distance de " + distance);
		balle = CalculePointDepartBalle(CalculePointAtterrissageBalle(angle,distance));
		System.out.println("Balle après modification: " + balle);
		this.fenetre.repaint();
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
	
	public Point CalculePointAtterrissageBalle(float angle, float distance) {
		float[] pourcentage = {1,2,3,4,5,6,7,8,9,10,40};
		int rand = (int) (Math.random() *(pourcentage.length));
		float anglemodifie = pourcentage[rand];
		rand = (int) (Math.random() *(pourcentage.length));
		float distancemodifie = pourcentage[rand];
		float signe = (int) Math.random();
		if(signe == 0) {
			signe = 1;
		}else {
			signe = -1;
		}
		angle = (angle + (1+(anglemodifie/100))) * signe;
		signe = (int) Math.random();
		if(signe == 0) {
			signe = 1;
		}else {
			signe = -1;
		}
		distance = distance * (1+(signe * distancemodifie/100));
		System.out.println("angle mod : " + angle + "  distance mod : " + distance);
		Point pointintermediaire = new Point(balle.getX()+distance, balle.getY());
		return pointintermediaire.rotation(balle, angle);
	}
	
	private Polygone getGreen() {
		int polygoneID = this.getactTrace().getSurfaces().get(this.getactTrace().getSurfaces().size()-1);
		return this.lpoly.get(polygoneID);
	}
	
	
	
	public Point CalculePointDepartBalle(Point cible){
		// Si la balle attérit en dehors des limites ou dans une surface sapin
		if (map.recherche(cible) == null) {
			System.out.println("cible : " + cible);
			this.scoreact++;
			return balle; 
		}
		else {
			char type = map.recherche(cible).getType();
			if(type == 'S') {
				this.scoreact++;
				System.out.println("BLABLABLA");
				return balle; 
			}
			else if (type == 'J') {
				this.state = 2;
				return cible;
			}
			else if (type == 'B') {
				Segment seg = new Segment(balle, cible);
				for(Polygone p : this.lpoly) {
					if(p.contient(cible)) {
						cible = seg.PointsIntersection(p).get(0);
						break;
					}
				}
				this.state = 0;
				return CalculePointDepartBalle(cible);	
			}
			else if (this.ptArrive().distance(cible) <= 1.0 && this.getGreen().contient(cible)) {
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
