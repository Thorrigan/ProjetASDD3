package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Maths.Point;
import Maths.Polygone;


public class Lecteur {
	String nomFichier = "";
	
	public Lecteur(String nomFichier){
		this.nomFichier = nomFichier;	
	}

	public Jeu creationJeu(float min_x, float max_x, float min_y, float max_y, int N) {
		File f = new File(nomFichier);
		ArrayList<Polygone> map = new ArrayList<Polygone>();
		ArrayList<Polygone> par = new ArrayList<Polygone>();
		Point depart = null;
		Point arrive = null;
		//si existe
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(f));
			String ligne;
			int compteur = 0;
			int nbsurfaces = 0;
			while((ligne = br.readLine()) != null){
				System.out.println(ligne);				
				if(compteur == 0){
					nbsurfaces = Integer.parseInt(ligne);
				}
				else if(compteur <= nbsurfaces){
					ArrayList<Point> lp = new ArrayList<Point>();
					for(int i = 0; i < ligne.length()-1; i++){
						if(ligne.charAt(i) == '('){
							i++;
							float X = 0.0f;
							float Y = 0.0f;
							String s = "";
							while(ligne.charAt(i) != ','){
								s += ligne.charAt(i);
								i++;
							}
							X = Float.parseFloat(s);
							i++;
							
							s = "";
							while(ligne.charAt(i) != ')'){
								s += ligne.charAt(i);
								i++;
							}
							Y = Float.parseFloat(s);
							//System.out.println("X: " + X + " Y: " + Y);
							Point p = new Point(X, Y);
							lp.add(p);
						}		
					}
					char type = ligne.charAt(ligne.length()-1);
					//System.out.println("Type: " + type);
					Polygone s = new Polygone(lp, type);
					map.add(s);
				}
				if (compteur == nbsurfaces + 2) {
					int i = 0;
					String s = "";
					float X = 0.0f;
					float Y = 0.0f;
					//passage du par
					while (ligne.charAt(i) != '(') {
						while (ligne.charAt(i) != ',') {
							s += ligne.charAt(i);
							i++;
						}
						i++;
						int tracenb = Integer.parseInt(s);
						System.out.println("tracenb :" + tracenb);
						par.add(map.get(tracenb - 1));
						s = "";
					}
					i++;
					//passage point départ
					while(ligne.charAt(i) != ','){
						s += ligne.charAt(i);
						i++;
					}
					X = Float.parseFloat(s);
					i++;
					s = "";
					while(ligne.charAt(i) != ')'){
						s += ligne.charAt(i);
						i++;
					}
					Y = Float.parseFloat(s);
					depart = new Point(X, Y);
					System.out.println("départ : " + depart);
					i = i + 3; //fermante virgule ouvrante 
					s = "";
					while(ligne.charAt(i) != ','){
						s += ligne.charAt(i);
						i++;
					}
					i++;
					X = Float.parseFloat(s);
					s = "";
					while(ligne.charAt(i) != ')'){
						s += ligne.charAt(i);
						i++;
					}
					Y = Float.parseFloat(s);
					arrive = new Point(X, Y);
					System.out.println("arrivee : " + arrive);
				}
				compteur ++;
			}
			
			for(Polygone sf : map){
				System.out.println(sf.toString());
			}
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("par : " + par);
		return new Jeu(depart, arrive, map, min_x, max_x, min_y, max_y, N, par);
	}
}