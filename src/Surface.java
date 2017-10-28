import java.util.ArrayList;


public class Surface {
	private ArrayList<Point> listp = new ArrayList<Point>();
	private char type;
	
	public Surface(ArrayList<Point> points, char type){
		this.listp = points;
		this.type = type;
	}

	public ArrayList<Point> getListp() {
		return listp;
	}
	
	public ArrayList<Triangle> TriangulationTerrain(){
		return null;
		
	}

	public char getType() {
		return type;
	}

	public String toString() {
		return "Surface [listp=" + listp + ", type=" + type + "]";
	}
	
	
}
