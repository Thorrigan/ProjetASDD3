
public class Jeu {
	private Point balle;
	private int score;
	private Map map;
	
	public Jeu(String nomFichier) {
		Lecteur l =  new Lecteur(nomFichier);
		Map map = l.read();
		Dessin dessin = new Dessin(map);
		Fenetre fen = new Fenetre("Jeu de Golf", 1200, 1000, dessin);
	}
}
