package Maths;


public class Point implements Comparable<Point>{
	private float x;
	private float y;
	
	public Point(float x, float y){
		x = Math.round(x*100.0f)/100.0f;
		y = Math.round(y*100.0f)/100.0f;
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
		if(Float.floatToIntBits(x) == Float.floatToIntBits(other.x) &&  Float.floatToIntBits(y) == Float.floatToIntBits(other.y)) {
			return true;
		}
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
	
	public float distance(Point p1){
		return (float) Math.sqrt(Math.pow(Math.abs(p1.getX()-this.x), 2) + Math.pow(Math.abs(p1.getY()-this.y), 2));
	}
	
	public float distanceOrigine(){
		return (float) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}
	
	public Point rotation(float dist, float angle){
		return new Point((float)(((Math.cos(Math.toRadians(angle))* this.x) - Math.sin(Math.toRadians(angle))* this.y) * dist),(float) (((Math.sin(Math.toRadians(angle))* this.x) + Math.cos(Math.toRadians(angle))* this.y)) * dist);
	}
	
	public void rotationv(float dist, float angle){
		this.x = (float) ((Math.cos(angle)* this.x) - Math.sin(angle)* this.y);
		this.y = (float) ((Math.sin(angle)* this.x) + Math.cos(angle)* this.y);
	}
	
	public float angle(Point p1){
		if (p1.getX() == this.x)
			return 90;
		else if (p1.getY() == this.y)
			return 0;
		else {
			Point p2 = new Point(p1.getX(), this.y);
			return (float) Math.toDegrees(Math.asin(this.distance(p2)/this.distance(p1)));
		}
	}
}
