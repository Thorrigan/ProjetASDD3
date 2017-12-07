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
	
	public Point rotation(Point cible, float angle){
		float X,Y, xnew, ynew;
		X = this.x - cible.getX();
		Y = this.y - cible.getY();
		xnew = cible.getX() + (float) ((Math.cos(Math.toRadians(angle))* X) - Math.sin(Math.toRadians(angle))* Y);
		ynew = cible.getY() + (float) ((Math.sin(Math.toRadians(angle))* X) + Math.cos(Math.toRadians(angle))* Y);
		return new Point(xnew,ynew);
	}
	
	public void rotationv(Point centre, float angle, float dist){
		float x,y,a,b,c, A,B,C, delta, xnew, ynew;
	
		//rotation
		/*x = this.x - centre.getX();
		y = this.y - centre.getY();
		this.x = centre.getX() + (float) ((Math.cos(Math.toRadians(angle))* x) - Math.sin(Math.toRadians(angle))* y);
		this.y = centre.getY() + (float) ((Math.sin(Math.toRadians(angle))* x) + Math.cos(Math.toRadians(angle))* y);*/
		x = 0;
		y = 0;
		x = centre.getX() - this.x;
		y = centre.getY() - this.y;
		xnew = this.x + (float) ((Math.cos(Math.toRadians(angle))* x) - Math.sin(Math.toRadians(angle))* y);
		ynew = this.y + (float) ((Math.sin(Math.toRadians(angle))* x) + Math.cos(Math.toRadians(angle))* y);
		
		Point p = new Point(xnew, ynew);
		Droite d = new Droite(this, p);

		/*//point intersection droite cercle
		a = -d.getA();
		b = d.getB();
		c = -d.getC();
		System.out.println(d);
		A = 1 + (a*a) / (b*b);
		B = -2*this.getX() - (2 * a * c * this.getY()) / b;
		C = this.getX() * this.getX() + this.getY() * this.getY() + (2*a*c) / b - (dist * dist) + (c/b) * (c/b); 
		System.out.println("A : " + A + " B : " + B + " C : " + C);
		delta = (B * B) - (4 * A * C);
		System.out.println("delta" + delta);
		if (delta > 0) {
			float xp1 = -B + (float) (Math.sqrt(delta) / (2*A));
			float yp1 = (a*xp1 + c) / b;
			float xp2 = -B - (float) (Math.sqrt(delta) / (2*A));
			float yp2 = (a*xp2 + c) / b;
			
			Point p1 = new Point(xp1, yp1);
			Point p2 = new Point(xp2, yp2);
			if (this.angle(p1) == angle)
				System.out.println(p1.distance(this));
			else if (this.angle(p2) == angle)
				System.out.println(p2.distance(this));
		}
		else if(delta == 0) {
			float xp1 = -B + 2 / A;
			float yp1 = (a*xp1 + c) / b;
			Point p1 = new Point(xp1, yp1);
			System.out.println(p1.distance(this));
		}*/
			
	}
	
	public float angle(Point p1){
		/*if(this.equals(p1)) {
			return 0;
		}
		else if(p1.getY() == this.y && p1.getX() > this.x)
			return 0;
		else if(p1.getY() == this.y && p1.getX() < this.x)
			return 180;
		else if(p1.getX() == this.x && p1.getY() > this.y)
			return 90;
		else if(p1.getX() == this.x && p1.getY() < this.y)
			return 270;
		else {
			Point p2 = new Point(p1.getX(), this.y);
			if(p1.getX() > this.x && p1.getY() > this.y) {
				return (float) Math.toDegrees(Math.asin(this.distance(p2)/this.distance(p1)));
			}
			else if(p1.getX() > this.x && p1.getY() < this.y) {
				return 90.0f + (float) Math.toDegrees(Math.asin(this.distance(p2)/this.distance(p1)));
			}
			else if(p1.getX() > this.x && p1.getY() < this.y) {
				return 180.0f + (float) Math.toDegrees(Math.asin(this.distance(p2)/this.distance(p1)));
			}
			else {
				return 270.0f + (float) Math.toDegrees(Math.asin(this.distance(p2)/this.distance(p1)));
			}
		}*/
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
