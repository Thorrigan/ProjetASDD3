package main;
import java.util.ArrayList;

import Maths.Point;
import Maths.Polygone;
import Maths.Rectangle;
import Maths.Triangle;

public class QuadTree {
	private Noeud racine;
	private int N; //nb max de triangle dans une région
	
	public QuadTree(float min_x,float max_x, float min_y, float max_y, int nbIntersec) {
		this.racine = new Noeud(new Rectangle(new Point(min_x, min_y), new Point(max_x, max_y)));
		this.N = nbIntersec;
	}
	
	public Rectangle region() {
		return racine.region;
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
		if(!racine.region.contient(t.getP1()) || !racine.region.contient(t.getP2()) || !racine.region.contient(t.getP3())) {
			System.out.println("On ne peut pas ins�rer le triangle car il n'est pas dans la region de d�part.");
			return;
		}
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
				System.out.print("f\n");
			}else if(n.estFeuille() && n.triangles == null) {
				for(int i = 0; i < hauteur; i++) {
					System.out.print("             ");
				}
				System.out.print(n.region.centre() + "F\n");
			}
			else {
				affichage(n.n1, hauteur +1);
				affichage(n.n2, hauteur +1);
				for(int i = 0; i < hauteur; i++) {
					System.out.print("             ");
				}
				System.out.print(n.region.centre() + "g\n");
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
	
	private void inserer(Noeud n, Triangle t) {
		if(n.estFeuille()) {
			if(n.triangles.size() < N) {
				n.triangles.add(t);
				return;
			}else if(n.triangles.size() == N) {
				Rectangle [] tab = n.region.division(); // On récupère les 4 sous-régions
				n.n1 = new Noeud(tab[0]);
				n.n2 = new Noeud(tab[1]);
				n.n3 = new Noeud(tab[2]);
				n.n4 = new Noeud(tab[3]);
				ArrayList<Triangle> listr = n.triangles; // On sauvegarde temporairement la liste des triangles
				n.triangles = new ArrayList<Triangle>(); 
				for(Triangle triangle : listr) {
					inserer(racine, triangle);
				}
				inserer(racine, t);
				return;
			}
			else {
				n.triangles.add(t);
				return;
			}
		}
		else {
			// On teste 
			if(n.n1.region.contient(t.getP1()) && n.n1.region.contient(t.getP2()) && n.n1.region.contient(t.getP3()) || ( n.n1.region.intersection(t))) {
				inserer(n.n1, t);
				return;
			}
			if(n.n2.region.contient(t.getP1()) && n.n2.region.contient(t.getP2()) && n.n2.region.contient(t.getP3()) || n.n2.region.intersection(t)) {
				inserer(n.n2, t);
				return;
			}
			if(n.n3.region.contient(t.getP1()) && n.n3.region.contient(t.getP2()) && n.n3.region.contient(t.getP3()) || n.n3.region.intersection(t)) {
				inserer(n.n3, t);
				return;
			}
			if(n.n4.region.contient(t.getP1()) && n.n4.region.contient(t.getP2()) && n.n4.region.contient(t.getP3()) || n.n4.region.intersection(t)) {
				inserer(n.n4, t);
				return;
			}
		}
	}
	
	public Triangle recherche(Point p) {
	//	System.out.println("Le point n'est pas dans la r�gion de d�part.");

		if(!racine.region.contient(p)) {
			return null;
		}	
		return recherche(racine, p);
	}
	
	private Triangle recherche(Noeud n, Point p) {
		if(n.estFeuille()) {
			//System.out.println(n.triangles);
			for(Triangle t : n.triangles) {
				if(t.contient(p)) {
					//System.out.println("charlieDELTA : " + n);
					return t;
				}
			}
		}
		else {
			//System.out.println("Pas charlie : " + n);
			Triangle t1 = recherche(n.n1, p);
			Triangle t2 = recherche(n.n2, p);
			Triangle t3 = recherche(n.n3, p);
			Triangle t4 = recherche(n.n4, p);
			if(t1 != null){
				return t1;
			}
			if(t2 != null){
				return t2;
			}
			if(t3 != null){
				return t3;
			}
			if(t4 != null){
				return t4;
			}
		}
		return null; // Si le point n'est pas dans un triangle
	}
	/*private Triangle recherche(Noeud n, Point p) {
		if(n.estFeuille()) {
			for(Triangle t : n.triangles) {
				if(t.contient(p)) {
					return t;
				}
			}
		}else if(n.n1.estFeuille() || n.n2.estFeuille() || n.n3.estFeuille() || n.n4.estFeuille()) {
			if(recherche(n.n1, p) != null && n.n1.estFeuille()) {
				return recherche(n.n1, p);
			}
			if(recherche(n.n2, p) != null && n.n2.estFeuille()) {
				return recherche(n.n2, p);
			}
			if(recherche(n.n3, p) != null && n.n3.estFeuille()) {
				return recherche(n.n3, p);
			}
			if(recherche(n.n4, p) != null && n.n4.estFeuille()) {
				return recherche(n.n4, p);
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
		*/
	
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
