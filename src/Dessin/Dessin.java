package Dessin;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
+import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class Dessin(Map map) {
	
	public void paint(Graphics g) {
		super.paint(g);
		int scalaire = 80;
		Graphics2D g2 = (Graphics2D) g;
		//g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		//g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		ArrayList<Surface> surfaces = m.getSurfaces();
		for(Surface s : surfaces) {
			g2.setColor(getColor(s.getType()));
		float scalaire = 80.0f;
		/*Graphics2D g2 = (Graphics2D) g;
		AffineTransform olg = g2.getTransform();
		g2.translate(0, getHeight() -1);
		g2.scale(1, -1);
		ArrayList<Polygone> surfaces = m.getSurfaces();
		for(Polygone s : surfaces) {
			Path2D path = new Path2D.Float();
			ArrayList<Point> lstp = s.getListp();
			ArrayList<Point> lstp = s.getPoints();
			path.moveTo(lstp.get(0).getX()*scalaire, lstp.get(0).getY()*scalaire);
			for(int i = 1; i < lstp.size(); i++) {
				path.lineTo(lstp.get(i).getX()*scalaire, lstp.get(i).getY()*scalaire);
			}
			path.closePath();
			g2.setColor(getColor(s.getType()));
			g2.fill(path);
		}
		}*/

	}
	}
}