package main;
import Maths.Point;

public class Jeu {
	private Point balle;
	private int score;
	private Terrain map;
	
	public Jeu(String nomFichier) {
		Lecteur l =  new Lecteur(nomFichier);
		Map map = l.read();
		Dessin dessin = new Dessin(map);
		Fenetre fen = new Fenetre("Jeu de Golf", 1200, 1000, dessin);
	}
	
	public Point CalculePointAtterrissageBalle() {
		return null;
	}
	
	public Point CalculePointDepartBalle(){
		return null;
	}
	
	public int CalculeScore() {
		return 0;
	}
}
