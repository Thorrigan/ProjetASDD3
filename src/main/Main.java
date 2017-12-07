package main;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

import Dessin.Fenetre;
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
		// Initialisation du programme avec les valeurs par d�fault
		// ou les valeurs entree en ligne de commande
		String nomFichier = "";
		int N = 3;
		float min_X = 0;
		float min_Y = 0;
		float max_X = 10;
		float max_Y = 10;
		if(args.length == 0) {
			nomFichier = "map.txt";
			N = 3;
		}
		else if(args.length == 1) {
			nomFichier = args[0];
		}
		else if(args.length == 2){
			nomFichier = args[0];
			N = Integer.parseInt(args[1]);
		}else if(args.length == 6) {
			nomFichier = args[0];
			N = Integer.parseInt(args[1]);
			min_X = Float.parseFloat(args[2]);
			min_Y = Float.parseFloat(args[3]);
			max_X = Float.parseFloat(args[4]);
			max_Y = Float.parseFloat(args[5]);
		}else {
			System.out.println("Erreur dans la lecture de la commande d'execution.");
			System.exit(1);
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
		
		
		Lecteur lec = new Lecteur(nomFichier);
		Jeu jeu = lec.creationJeu(min_X, max_X, min_Y, max_Y, N);
		jeu.JeuGraphique();
	}
}
