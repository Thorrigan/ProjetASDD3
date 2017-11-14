package main;
import Maths.Point;

public class Jeu {
	private Point balle;
	private int score;
	private Terrain map;
	
	public Jeu(String nomFichier) {
		
	}
	
	//A TESTER LUL
	public Point CalculePointAtterrissageBalle(Point cible) {
		float angle, b, moda, angler, distr;
		int signe; //0 negatif //1 positif
		
		angle = balle.angle(cible);
		signe = (int) Math.random(); //(int)(Math.random()*(max-min))+min
		System.out.println(signe);
		b = (float) (Math.random() *(40));
		moda = (float) (Math.random() *(40));
		if (signe == 1)
			angler = angle + moda;
		else
			angler = angle - moda;
		signe = (int) Math.random();
		if (signe == 1)
			distr = (1+(b/100)) * balle.distance(cible);
		else
			distr = (1-(b/100)) * balle.distance(cible);
		return balle.rotation(distr, angler);
	}
	
	public Point CalculePointDepartBalle(){
		return null;
	}
	
	public int CalculeScore() {
		return 0;
	}
}
