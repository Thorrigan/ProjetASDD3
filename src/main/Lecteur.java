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
		ArrayList<Trace> traces = new ArrayList<Trace>(); 
		Point depart = null;
		Point arrive = null;
		//si existe
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(f));
			String ligne;
			int compteur = 0;
			int nbsurfaces = 0;
			int nbtraces = 0;
			while((ligne = br.readLine()) != null){			
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
							Point p = new Point(X, Y);
							lp.add(p);
						}		
					}
					char type = ligne.charAt(ligne.length()-1);
					Polygone s = new Polygone(lp, type);
					map.add(s);
				}else if(compteur == nbsurfaces +1) {
					nbtraces = Integer.parseInt(ligne);
				}else if(compteur > nbsurfaces + 1 && compteur <= nbsurfaces + nbtraces + 1) {
					traces.add(Trace.lireTrace(ligne));	
					//compteur++;
				}else {
					System.out.println("Erreur dans le fichier de départ.");
				}
				compteur ++;
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(traces);
		return new Jeu(traces, map, min_x, max_x, min_y, max_y, N);
	}
}