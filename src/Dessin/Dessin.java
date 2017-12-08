package Dessin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import Maths.Point;
import Maths.Polygone;
import Maths.Triangle;
import main.Jeu;
import main.Trace;

public class Dessin extends JPanel {
	  private int scalaire = 80; //resize ++
	  private Jeu jeu;
	  private Fenetre fen;
	  
	  public Dessin(final Jeu jeu, Fenetre fen) {
		  this.fen = fen;
		  this.jeu = jeu;
		  this.addMouseListener(new MouseAdapter() {
		    	public void mousePressed(MouseEvent e) {
		    		float x = (float) e.getX()/scalaire;
		    		float y = 10.0f - ((float) e.getY()/scalaire);
		    		x = Math.round(x*100.0f)/100.0f;
		    		y = Math.round(y*100.0f)/100.0f;
		    		if(e.getButton() == MouseEvent.BUTTON3) {
		    			Point dest = new Point(x,y);
						jeu.JouerCoup(dest);
						
		    		}else {
		    			System.out.println("Pas le bon bouton.");
		    		}
		    	}
		    });
	  }
	  
	  public void paintComponent(Graphics g) {
		  super.paintComponent(g);   	  
		  Graphics2D g2 = (Graphics2D) g;
		  g2.translate(0, getHeight());
		  g2.scale(1.0, -1.0);
		  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		  g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		  if(this.fen.getaffichagetriangulation() == true) {
			  dessinTriangle(g2);
		  }else {
			  dessinPolygone(g2);  
		  }
		  
		  if(this.fen.getaffichageTrace() == true) {
			  dessinTrace(g2);
		  }
		  dessinBalle(g2);
		  dessinArrivee(g2);
	  }
	  
	  private void dessinTrace(Graphics2D g2) {
		  Trace t = jeu.getactTrace();
		  ArrayList<Integer> surfacesID = t.getSurfaces();
		  System.out.println(surfacesID);
		  for (int i = 0; i < this.jeu.getPolygones().size(); i++) {
			  if(surfacesID.contains(i)) {
				  System.out.println(this.jeu.getPolygones().get(i));
				  Path2D path = new Path2D.Float();
				  g2.setStroke(new BasicStroke(3.0f));
				  for (int j = 0; j < this.jeu.getPolygones().get(i).getLstp().size(); j++) {
					  float[] x = new float[this.jeu.getPolygones().get(i).getLstp().size()];
					  float[] y = new float[this.jeu.getPolygones().get(i).getLstp().size()];
					  x[j] = ((this.jeu.getPolygones().get(i).getLstp().get(j).getX())*scalaire);
					  y[j] = ((this.jeu.getPolygones().get(i).getLstp().get(j).getY()) *scalaire);
					  if (j == 0)
						  path.moveTo(x[j], y[j]);
					  else
						  path.lineTo(x[j], y[j]);
				  }
				  if (this.jeu.getPolygones().get(i).getType() == 'B')//eau
					  g2.setColor(new Color(115, 194, 251));
				  else if(this.jeu.getPolygones().get(i).getType() == 'C') //vertclair
					  g2.setColor(new Color(159, 232, 85));
				  else if(this.jeu.getPolygones().get(i).getType() == 'V') //vert
					  g2.setColor(new Color(1, 215, 88));
				  else if(this.jeu.getPolygones().get(i).getType() == 'S') //vertsapin
					  g2.setColor(new Color(9, 106, 9));
				  else {
					  g2.setColor(new Color(253, 241, 184)); //sable  
				  }	
				  g2.fill(path);
				  g2.setColor(new Color(255, 100, 100));
				  g2.draw(path);
				  path.closePath(); 
			  }	
		  }
	  }
	  
	  private void dessinBalle(Graphics2D g2) {
		  g2.setColor(new Color(255, 255, 150));
		  Ellipse2D.Double shape = new Ellipse2D.Double(jeu.ballePosition().getX()*this.scalaire, jeu.ballePosition().getY() * this.scalaire, 15, 15);
		  g2.fill(shape);
	  }
	  
	  private void dessinArrivee(Graphics2D g2) {
		  g2.setColor(new Color(255, 100, 100));
		  Ellipse2D.Double shape = new Ellipse2D.Double(jeu.ptArrive().getX()*this.scalaire, jeu.ptArrive().getY() * this.scalaire, 20, 20);
		  g2.fill(shape);
	  }
	  
	  private void dessinTriangle(Graphics2D g2) {		  
		  for(Triangle t : this.jeu.getTriangles()) {
			  Path2D path = new Path2D.Float();
			  g2.setStroke(new BasicStroke(2.0f));
			  path.moveTo(t.getP1().getX()*scalaire,t.getP1().getY()*scalaire);
			  path.lineTo(t.getP2().getX()*scalaire,t.getP2().getY()*scalaire);
			  path.lineTo(t.getP3().getX()*scalaire,t.getP3().getY()*scalaire);
			  if (t.getType() == 'B')//eau
				  g2.setColor(new Color(115, 194, 251));
			  else if(t.getType() == 'C') //vertclair
				  g2.setColor(new Color(159, 232, 85));
			  else if(t.getType() == 'V') //vert
				  g2.setColor(new Color(1, 215, 88));
			  else if(t.getType() == 'S') //vertsapin
				  g2.setColor(new Color(9, 106, 9));
			  else {
				  g2.setColor(new Color(253, 241, 184)); //sable  
			  }
			  
			  //g2.setColor(new Color(253, 241, 184)); //sable
		  
			  g2.fill(path);
			  g2.setColor(Color.black);
			  g2.draw(path);
			  path.closePath();
		  }
	  }

	  private void dessinPolygone(Graphics2D g2) {
		  for (int i = 0; i < this.jeu.getPolygones().size(); i++) {
			  Path2D path = new Path2D.Float();
			  double thickness = 2;
			  Stroke oldStroke = g2.getStroke();
			  g2.setStroke(new BasicStroke(2.0f));
			  for (int j = 0; j < this.jeu.getPolygones().get(i).getLstp().size(); j++) {
				  float[] x = new float[this.jeu.getPolygones().get(i).getLstp().size()];
				  float[] y = new float[this.jeu.getPolygones().get(i).getLstp().size()];
				  x[j] = ((this.jeu.getPolygones().get(i).getLstp().get(j).getX())*scalaire);
				  y[j] = ((this.jeu.getPolygones().get(i).getLstp().get(j).getY()) *scalaire);
				  if (j == 0)
					  path.moveTo(x[j], y[j]);
				  else
					  path.lineTo(x[j], y[j]);
			  }
			  if (this.jeu.getPolygones().get(i).getType() == 'B')//eau
				  g2.setColor(new Color(115, 194, 251));
			  else if(this.jeu.getPolygones().get(i).getType() == 'C') //vertclair
				  g2.setColor(new Color(159, 232, 85));
			  else if(this.jeu.getPolygones().get(i).getType() == 'V') //vert
				  g2.setColor(new Color(1, 215, 88));
			  else if(this.jeu.getPolygones().get(i).getType() == 'S') //vertsapin
				  g2.setColor(new Color(9, 106, 9));
			  else {
				  g2.setColor(new Color(253, 241, 184)); //sable  
			  }	
			  g2.fill(path);
			  g2.setColor(Color.black);
			  g2.draw(path);
			  path.closePath();
	  }
	}
}
