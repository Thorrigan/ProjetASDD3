package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Maths.Point;
import Maths.Polygone;


public class lecteur {
	private ArrayList<Polygone> map = new ArrayList<Polygone>();
	
	public lecteur(String nomFichier){
		File f = new File(nomFichier);
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
		
	}

	public ArrayList<Polygone> getMap() {
		return map;
	}

}