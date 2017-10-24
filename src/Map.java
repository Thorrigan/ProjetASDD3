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
	
}
