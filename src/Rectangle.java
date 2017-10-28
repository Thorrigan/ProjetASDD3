
public class Rectangle {
	private Point p1;
	private Point p2;
	private float Xmin;
	private float Xmax;
	private float Ymin;
	private float Ymax;
	
	public Rectangle(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
		if(p1.getX() < p2.getX()) {
			Xmin = p1.getX();
			Xmax = p2.getX();
		}else {
			Xmin = p2.getX();
			Xmax = p1.getX();
		}
		
		if(p1.getY() < p2.getY()) {
			Ymin = p1.getY();
			Ymax = p2.getY();
		}
		else{
			Ymin = p2.getY();
			Ymax = p1.getY();
		}
	}
	
	public boolean contient(Point p) {
		if(p.getX() >= Xmin && p.getX() <= Xmax && p.getY() >= Ymin && p.getY() <= Ymax) {
			return true;
		}
		return false;
	}
}
