import java.util.ArrayList;

public interface Forme {
	public boolean intersection(Forme f1);
	public ArrayList<Point> PointsIntersection(Forme f1);
	public boolean contient(Forme f1);
	public boolean contient(Point p);
}
