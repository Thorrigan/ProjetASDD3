package main;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

import Dessin.Fenetre;
import Maths.Point;
import Maths.Polygone;
import Maths.Triangle;

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
		// Initialisation du programme avec les valeurs par défault
		// ou les valeurs entree en ligne de commande
		String nomFichier = "map.txt";
		int N = 3;
		if(args.length >= 1) {
			nomFichier = args[0];
		}
		if(args.length >= 2){
			N = Integer.parseInt(args[1]);
		}
		/*
		// Variables pour le menu principal du programme
		int select = 0;
		
		// Programme principal
		System.out.println("Bienvenu sur notre jeu de Golf.");
		Scanner scanner = new Scanner(System.in);
		while(select !=1 || select !=2){
			System.out.println("Menu Principal :");
			System.out.println("- Tapez 1 pour un affichage graphique");
			System.out.println("- Tapez 2 pour un affichage console");
			System.out.println("- Tapez 0 pour quitter le programme");
			select = scanner.nextInt();
			if(select == 1){
				Jeu jeu = new Jeu(nomFichier, N);
				jeu.JeuGraphique();
				System.out.println("Fin du Jeu, retour au menu principal.");
				select = 0;
			}else if(select == 2){
				Jeu jeu = new Jeu(nomFichier, N);
				jeu.JeuConsole();
				System.out.println("Fin du Jeu, retour au menu principal.");
				select = 0;
			}
			else if(select == 0){
				Jeu jeu = new Jeu(nomFichier, N);
				jeu.JeuConsole();
				System.out.println("Fin du Jeu, retour au menu principal.");
				select = 0;
			}
		}
		scanner.close();*/
		//test();
		
		Jeu jeu = new Jeu(nomFichier, N);
		ArrayList<Polygone> lpoly = jeu.getLpoly();
		JFrame fenetre = new Fenetre(lpoly);
	}
	
	private static void test(){
		QuadTree qt = new QuadTree(0,10,0,10,3);
		qt.inserer(new Triangle(new Point(5.5f, 10.0f), new Point(10.0f,5.0f), new Point(10.0f,10.0f)));
		qt.afficher();
	}
}
