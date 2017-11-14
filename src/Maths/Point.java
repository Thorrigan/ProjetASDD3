package Maths;


public class Point implements Comparable<Point>{
	private float x;
	private float y;
	
	public Point(float x, float y){
		if(x == -0.0f) {
			x = 0.0f;
		}
		if(y == -0.0f) {
			y = 0.0f;
		}
		this.x = x;
		this.y = y;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}



	public float getX(){
		return this.x;
	}
	public float getY(){
		return this.y;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public int compareTo(Point p) {
		if(y < p.y) {
			return -1;
		}
		if(y > p.y) {
			return 1;
		}
		if(x < p.x) {
			return -1;
		}
		if(x > p.x) {
			return 1;
		}
		return 0;
	}
}
