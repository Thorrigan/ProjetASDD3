import java.util.ArrayList;

public class Map {
	private ArrayList<Polygone> surfaces;
	private Point dep;
	private Point arv;
	
	public Map(ArrayList<Polygone> ls, Point depart, Point arrivee){
		this.surfaces = ls;
		this.dep = depart;
		this.arv = arrivee;
	}
	
	public ArrayList<Polygone> getSurfaces() {
		return surfaces;
	}

	public Point getDep() {
		return dep;
	}

	public Point getArv() {
		return arv;
	}

}
