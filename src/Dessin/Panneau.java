package Dessin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import Maths.Point;
import Maths.Polygone;
import Maths.Triangle;

import javax.swing.JPanel;
 
public class Panneau extends JPanel { 
	  private ArrayList<Polygone> lpoly;
	  private ArrayList<Triangle> lt;
	  
	  public Panneau(ArrayList<Polygone> lpoly) {
		  this.lpoly = lpoly;
	  }
	  
	  public Panneau(ArrayList<Triangle> lpo, int nb) {
		  this.lt = lpo;
	  }
	  
	  @Override
	  public void paintComponent(Graphics g) {
	        super.paintComponent(g);
		  Graphics2D g2 = (Graphics2D) g;
		  int scalaire = 79; //resize ++
		  g2.translate(0, getHeight());
		  g2.scale(1.0, -1.0);
		  
		  for(Triangle t : this.lt) {
			  Path2D path = new Path2D.Float();
			  double thickness = 2;
			  Stroke oldStroke = g2.getStroke();
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
			  else //
				  g2.setColor(new Color(253, 241, 184)); //sable
			  
			  //g2.setColor(new Color(253, 241, 184)); //sable
		  
			  g2.fill(path);
			  g2.setColor(Color.black);
			  g2.draw(path);
			  path.closePath();
		  }
		  
		  
		  /*for (int i = 0; i < this.lpoly.size(); i++) {
			  Path2D path = new Path2D.Float();
			  double thickness = 2;
			  Stroke oldStroke = g2.getStroke();
			  g2.setStroke(new BasicStroke(2.0f));
			  for (int j = 0; j < this.lpoly.get(i).getLstp().size(); j++) {
				  float[] x = new float[this.lpoly.get(i).getLstp().size()];
				  float[] y = new float[this.lpoly.get(i).getLstp().size()];
				  x[j] = ((this.lpoly.get(i).getLstp().get(j).getX())*80.0f);
				  y[j] = ((this.lpoly.get(i).getLstp().get(j).getY()) *80.0f);
				  if (j == 0)
					  path.moveTo(x[j], y[j]);
				  else
					  path.lineTo(x[j], y[j]);
			  }
			  if (this.lpoly.get(i).getType() == 'B')//eau
				  g2.setColor(new Color(115, 194, 251));
			  else if(this.lpoly.get(i).getType() == 'C') //vertclair
				  g2.setColor(new Color(159, 232, 85));
			  else if(this.lpoly.get(i).getType() == 'V') //vert
				  g2.setColor(new Color(1, 215, 88));
			  else if(this.lpoly.get(i).getType() == 'S') //vertsapin
				  g2.setColor(new Color(9, 106, 9));
			  else //
				  g2.setColor(new Color(253, 241, 184)); //sable
			  g2.fill(path);
			  g2.setColor(Color.black);
			  g2.draw(path);
			  path.closePath();
		  }*/
	  }            
}