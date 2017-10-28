import java.util.ArrayList;

public class Map {
	private ArrayList<Surface> surfaces;
	private Point dep;
	private Point arv;
	
	public Map(ArrayList<Surface> ls, Point depart, Point arrivee){
		this.surfaces = ls;
		this.dep = depart;
		this.arv = arrivee;
	}
	
	public ArrayList<Surface> getSurfaces() {
		return surfaces;
	}

	public Point getDep() {
		return dep;
	}

	public Point getArv() {
		return arv;
	}

}
