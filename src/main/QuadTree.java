package main;
import java.util.ArrayList;

import Maths.Point;
import Maths.Polygone;
import Maths.Rectangle;
import Maths.Triangle;

public class QuadTree {
	private Noeud racine;
	private int N; //nb max de triangle dans une région
	private static int erreur;
	
	public QuadTree(float min_x,float max_x, float min_y, float max_y, int nbIntersec) {
		racine = new Noeud(new Rectangle(new Point(min_x, min_y), new Point(max_x, max_y)));
		N = nbIntersec;
		erreur = 0;
	}
	
	public static int getErreur() {
		return erreur;
	}

	private class Noeud{
		Noeud n1,n2,n3,n4;
		ArrayList<Triangle> triangles;
		Rectangle region;
		
		public String toString() {
			if(estFeuille()){
				return "[triangles=" + triangles + ", region=" + region + "]";
			}else{
				return "[region=" + region + "]";
			}
		}

		public Noeud(Rectangle r){
			this.region = r;
			this.n1 = null;
			this.n2 = null;
			this.n3 = null;
			this.n4 = null;
			this.triangles = new ArrayList<Triangle>();
		}
		
		boolean estFeuille() {
			if(n1 == null && n2 == null && n3 == null && n4 == null) {
				return true;
			}
			return false;
		}
	}
	
	public void inserer(Triangle t) {
		System.out.println("Debut insertion du triangle: " + t);
		inserer(racine, t);
	}
	
	public int hauteur() {
		return hauteur(racine);
	}
	
	private int hauteur(Noeud n) {
		if(n == null) {
			return 0;
		}
		return 1 + Math.max(Math.max(hauteur(n.n1), hauteur(n.n2)), Math.max(hauteur(n.n3), hauteur(n.n4)));
	}
	
	public void afficher() {
		System.out.println("Affichage de l'arbre :");
		affichage(racine, 0);
	}
	
	private void affichage(Noeud n, int hauteur) {
		if(n != null) {
			if(n.estFeuille() && n.triangles != null) {
				for(int i = 0; i < hauteur; i++) {
					System.out.print("             ");
				}
				System.out.print(n.region.centre() + " ");
				for(Triangle t : n.triangles) {
					System.out.print(t);
				}
				System.out.print("\n");
			}else if(n.estFeuille() && n.triangles == null) {
				for(int i = 0; i < hauteur; i++) {
					System.out.print("             ");
				}
				System.out.print(n.region.centre() + "\n");
			}
			else {
				affichage(n.n1, hauteur +1);
				affichage(n.n2, hauteur +1);
				for(int i = 0; i < hauteur; i++) {
					System.out.print("             ");
				}
				System.out.print(n.region.centre() + "\n");
				affichage(n.n3, hauteur +1);
				affichage(n.n4, hauteur +1);
			}
		}else if(hauteur < this.hauteur()){
			affichage(null, hauteur +1);
			affichage(null, hauteur +1);
			for(int i = 0; i < hauteur; i++) {
				System.out.print("             ");
			}
			System.out.print(".............\n");
			affichage(null, hauteur +1);
			affichage(null, hauteur +1);
		}
	}
	
	private Noeud inserer(Noeud n, Triangle t) {
		//System.out.println("On passe par le noeud: " + n);
		if(n.estFeuille()) {
			//System.out.println("feuille");
			if(n.triangles.size() < N) {
				n.triangles.add(t);
				return n;
			}else if(n.triangles.size() == N) {
				Rectangle [] tab = n.region.division(); // On récupère les 4 sous-régions
				//System.out.println(" triangle size  ! " + n.triangles.size() + " aaaaaaaaaaaaaaa");
				//System.out.println("triangles : " + n.triangles);
				n.n1 = new Noeud(tab[0]);
				n.n2 = new Noeud(tab[1]);
				n.n3 = new Noeud(tab[2]);
				n.n4 = new Noeud(tab[3]);
				ArrayList<Triangle> listr = n.triangles; // On sauvegarde temporairement la liste des triangles
				n.triangles = new ArrayList<Triangle>(); 
				for(Triangle triangle : listr) {
					inserer(racine, triangle);
				}
				return inserer(racine, t);
			}
			else {
				//System.out.println("WTF");
				n.triangles.add(t);
				return n;
			}
		}
		else {
			// On teste 
			//System.out.println("NOEUD");
			//System.out.println("intersection "  + n.n1.region.intersection(t));
			if(n.n1.region.contient(t.getP1()) && n.n1.region.contient(t.getP2()) && n.n1.region.contient(t.getP3()) || ( n.n1.region.intersection(t))) {
				return inserer(n.n1, t);
			}
			else if(n.n2.region.contient(t.getP1()) && n.n2.region.contient(t.getP2()) && n.n2.region.contient(t.getP3()) || n.n2.region.intersection(t)) {
				return inserer(n.n2, t);
			}
			else if(n.n3.region.contient(t.getP1()) && n.n3.region.contient(t.getP2()) && n.n3.region.contient(t.getP3()) || n.n3.region.intersection(t)) {
				return inserer(n.n3, t);
			}
			else if(n.n4.region.contient(t.getP1()) && n.n4.region.contient(t.getP2()) && n.n4.region.contient(t.getP3()) || n.n4.region.intersection(t)) {
				return inserer(n.n4, t);
			}
			else{
				System.out.println("ERREURRRRRRRRRRRRRRRRRRRRRRRRRR");
				System.out.println("\n\n\n");
				System.out.println("R�gions : " + n.region);
				this.erreur++;
				System.out.println(t);
				return null;
			}
		}
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
	
	
	public static  QuadTree ConstructionQT(ArrayList<Polygone> plg, float min_x,float max_x, float min_y, float max_y, int nbIntersec) {
		ArrayList<Triangle> lst = new ArrayList<Triangle>();
		QuadTree QT = new QuadTree( min_x, max_x, min_y, max_y, nbIntersec);
		for(Polygone p : plg) {
			lst.addAll(p.triangulation());
		}
		for(Triangle t : lst) {
			QT.inserer(t);
		}
		return QT;
		
	}
}
