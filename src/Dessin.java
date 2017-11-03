import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class Dessin extends Canvas{
	private Map m;
	
	public Dessin(Map map) {
		this.m = map;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		float scalaire = 80.0f;
		/*Graphics2D g2 = (Graphics2D) g;
		AffineTransform olg = g2.getTransform();
		g2.translate(0, getHeight() -1);
		g2.scale(1, -1);
		ArrayList<Polygone> surfaces = m.getSurfaces();
		for(Polygone s : surfaces) {
			Path2D path = new Path2D.Float();
			ArrayList<Point> lstp = s.getPoints();
			path.moveTo(lstp.get(0).getX()*scalaire, lstp.get(0).getY()*scalaire);
			for(int i = 1; i < lstp.size(); i++) {
				path.lineTo(lstp.get(i).getX()*scalaire, lstp.get(i).getY()*scalaire);
			}
			path.closePath();
			g2.setColor(getColor(s.getType()));
			g2.fill(path);
		}*/

	}
	
	private Color getColor(char c) {
		Color color;
		if(c == 'C') {
			color = new Color(160,240,64);
		}
		else if(c == 'B') {
			color = new Color(91,155,213);
		}
		else if(c == 'V') {
			color = new Color(0,176,80);
		}
		else if(c == 'J') {
			color = new Color(201,187,103);
		}
		else if(c == 'S') {
			color = new Color(0,128,0);
		}
		else {
			color = Color.YELLOW;
		}
		return color;
	}
}
