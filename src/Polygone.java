import java.util.ArrayList;

public class Polygone implements Forme{
	private ArrayList<Point> lstp;
	
	public Polygone(ArrayList<Point> lstp) {
		this.lstp = lstp;
	}
	
	public Polygone() {
		this.lstp = new ArrayList<Point>();
	}
	
	public void ajouterPoint(Point p) {
		this.lstp.add(p);
	}
	
	public ArrayList<Segment> transformationSegment(){
		return null;
		
	}

	public boolean intersection(Forme f1) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Point> PointsIntersection(Forme f1) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean contient(Forme f1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean contient(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

}
