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
	
	public ArrayList<Triangle> triangulation(){
		return null;
	}
	
	public ArrayList<Segment> transformationSegment(){
		ArrayList<Segment> lsts = new ArrayList<Segment>();
		for(int i = 0; i < lstp.size(); i++) {
			if(i == lstp.size() - 1) {
				lsts.add(new Segment(lstp.get(i), lstp.get(0)));
			}else {
				lsts.add(new Segment(lstp.get(i), lstp.get(i+1)));
			}
		}
		
		return lsts;
	}

	public boolean intersection(Forme f1) {
		if(f1 instanceof Droite) {
			return intersectionDroite((Droite) f1);
		}else if(f1 instanceof Segment) {
			return intersectionSegment((Segment) f1);
		}else if(f1 instanceof Triangle) {
			return intersectionTriangle((Triangle) f1);
		}else if(f1 instanceof Rectangle) {
			return intersectionRectangle((Rectangle) f1);
		}else if(f1 instanceof Polygone) {
			return intersectionPolygone((Polygone) f1);
		}		
		return false;
	}

	private boolean intersectionPolygone(Polygone f1) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean intersectionRectangle(Rectangle f1) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean intersectionTriangle(Triangle f1) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean intersectionSegment(Segment f1) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean intersectionDroite(Droite f1) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Point> PointsIntersection(Forme f1) {
		if(f1 instanceof Droite) {
			return PointsintersectionDroite((Droite) f1);
		}else if(f1 instanceof Segment) {
			return PointsintersectionSegment((Segment) f1);
		}else if(f1 instanceof Triangle) {
			return PointsintersectionTriangle((Triangle) f1);
		}else if(f1 instanceof Rectangle) {
			return PointsintersectionRectangle((Rectangle) f1);
		}else if(f1 instanceof Polygone) {
			return PointsintersectionPolygone((Polygone) f1);
		}		
		return null;
	}

	private ArrayList<Point> PointsintersectionPolygone(Polygone f1) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<Point> PointsintersectionRectangle(Rectangle f1) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<Point> PointsintersectionTriangle(Triangle f1) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<Point> PointsintersectionSegment(Segment f1) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<Point> PointsintersectionDroite(Droite f1) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean contient(Forme f1) {
		if(f1 instanceof Droite) {
			return contientDroite((Droite) f1);
		}else if(f1 instanceof Segment) {
			return contientSegment((Segment) f1);
		}else if(f1 instanceof Triangle) {
			return contientTriangle((Triangle) f1);
		}else if(f1 instanceof Rectangle) {
			return contientRectangle((Rectangle) f1);
		}else if(f1 instanceof Polygone) {
			return contientPolygone((Polygone) f1);
		}		
		return false;
	}

	private boolean contientPolygone(Polygone pg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "[lstp=" + lstp + "]";
	}

	private boolean contientRectangle(Rectangle r1) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean contientTriangle(Triangle t1) {
		if(this.contient(t1.p1) && this.contient(t1.p2) && this.contient(t1.p3) && PointsIntersection(t1).size() == 0) {
			return true;
		}
		return false;
	}

	private boolean contientSegment(Segment s1) {
		if(this.contient(s1.p1) && this.contient(s1.p2) && PointsIntersection(s1).size() == 0) {
			return true;
		}
		return false;
	}

	private boolean contientDroite(Droite f1) {
		return false;
	}

	public boolean contient(Point p) {
		for(Triangle t : this.triangulation()) {
			if(t.contient(p)) {
				return true;
			}
		}
		return false;
	}

}
