package main;
import java.util.ArrayList;

import Maths.Point;
import Maths.Rectangle;
import Maths.Triangle;

public class QuadTree {
	private Noeud racine;
	private int N; //nb max de triangle dans une région
	
	public QuadTree(float min_x,float max_x, float min_y, float max_y, int nbIntersec) {
		racine = new Noeud(new Rectangle(new Point(min_x, min_y), new Point(max_x, max_y)));
		N = nbIntersec;
	}
	
	private class Noeud{
		Noeud n1,n2,n3,n4;
		ArrayList<Triangle> triangles;
		Rectangle region;
		
		Noeud(Rectangle r){
			this.region = r;
			this.n1 = null;
			this.n2 = null;
			this.n3 = null;
			this.n4 = null;
			this.triangles = null;
		}
		
		boolean estFeuille() {
			if(n1 == null && n2 == null && n3 == null && n4 == null) {
				return true;
			}
			return false;
		}
	}
	
	public void inserer(Triangle t) {
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
		if(n.estFeuille()) {
			if(n.triangles == null) {
				n.triangles = new ArrayList<Triangle>();
				n.triangles.add(t);
			}else if(n.triangles.size() == N) {
				Rectangle [] tab = n.region.division(); // On récupère les 4 sous-régions
				n.n1 = new Noeud(tab[0]);
				n.n2 = new Noeud(tab[1]);
				n.n3 = new Noeud(tab[2]);
				n.n4 = new Noeud(tab[3]);
				ArrayList<Triangle> listr = n.triangles; // On sauvegarde temporairement la liste des triangles
				n.triangles = new ArrayList<Triangle>(); // 
				for(Triangle triangle : listr) {
					inserer(n, triangle);
				}
				inserer(n, t);
			}else {
				n.triangles.add(t);
			}
		}else {
			if(n.n1.region.intersection(t) || n.n1.region.contient(t)) {
				inserer(n.n1, t);
			}else if(n.n2.region.intersection(t) || n.n2.region.contient(t)) {
				inserer(n.n2, t);
			}else if(n.n3.region.intersection(t) || n.n3.region.contient(t)) {
				inserer(n.n3, t);
			}else if(n.n4.region.intersection(t) || n.n4.region.contient(t)) {
				inserer(n.n4, t);
			}
		}
		return null;
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
	
	
	public QuadTree ConstructionQT() {
		return null;
		
	}
}
