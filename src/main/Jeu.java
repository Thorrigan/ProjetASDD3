package main;
import Maths.Point;;
import java.util.ArrayList;

import Maths.Droite;
import Maths.Point;
import Maths.Polygone;
import Maths.Triangle;
import Tests.TestsUnitairesMaths;

public class Jeu {
	private Point balle;
	private int score;
	private int scoretrace;
	private QuadTree map;
	private Point arrive;
	private int state;
	private int par;
	private int tour;
	private ArrayList<Polygone> lpoly;
	
	public Jeu(String nomFichier) {
		this.score += this.scoretrace;
	}
	
	/*STATE A CHOISIR
	0 default
	1 buff
	2 debuff sable
	3 
	*/
	
	
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
		else if (state == 2)
			distr = ((1-(b/100)) * balle.distance(cible)) / 2;
		else
			distr = (1-(b/100)) * balle.distance(cible);
		this.scoretrace++;
		return balle.rotation(distr, angler);
	}
	
	
	
	public Point CalculePointDepartBalle(Point cible){
		if (map.recherche(cible) == null)
			return null;
		else {
			char type = map.recherche(cible).getType();
			if (cible.getX() > 10.0 || cible.getY() > 10.0 || type == 'S') {
				this.scoretrace++;
				return balle;
			}
			else if (type == 'J') {
				this.state = 2;
				return cible;
			}
			else if (type == 'B') {
				int i = 0;
				Droite d = new Droite(cible, arrive); //p1 cible, p2 arrivée
				ArrayList<Point> points;
				this.scoretrace++;
				//trouver le polygone qui contient le point
				while (lpoly.get(i).contient(cible) == false)
					i++;
				//obtention point intersection
				points = lpoly.get(i).PointsIntersection(d);
				Point min = new Point(1555.0f, 1555.0f);
				//calcul distance mini
				for (Point p : points) {
					if (cible.distance(p) < cible.distance(min))
						min = p;
				}
				return min;
			}
			else if (arrive.distance(cible) <= 1.0) {
				this.state = 1;
				return cible;
			}
			else
				return cible;
		}
	}
	
	public int Calculescoretrace() {
		if (tour < par)
			return this.tour - this.scoretrace;
		else
			return par - this.scoretrace;
	}
	
	public int CalculeScore() {
		return score;
	}
}
