package Dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import Maths.Point;
import Maths.Polygone;


import javax.swing.JPanel;
 
public class Panneau extends JPanel { 
	  private ArrayList<Polygone> lpoly;
	  
	  public Panneau(ArrayList<Polygone> lpoly) {
		  this.lpoly = lpoly;
	  }
	  
	  @Override
	  public void paintComponent(Graphics g) {
	        super.paintComponent(g);
		  Graphics2D g2 = (Graphics2D) g;
		  int scalaire = 80; //resize ++

		  
		  for (int i = 0; i < this.lpoly.size(); i++) {
			  Path2D path = new Path2D.Float();

			  for (int j = 0; j < this.lpoly.get(i).getLstp().size(); j++) {
				  float[] x = new float[this.lpoly.get(i).getLstp().size()];
				  float[] y = new float[this.lpoly.get(i).getLstp().size()];
				  x[j] = ((this.lpoly.get(i).getLstp().get(j).getX())*40.0f);
				  y[j] = ((this.lpoly.get(i).getLstp().get(j).getY()) *40.0f);
				  if (j == 0)
					  path.moveTo(x[j], y[j]);
				  else
					  path.lineTo(x[j], y[j]);
			  }
			  if (this.lpoly.get(i).getType() == 'B')//eau
				  g2.setColor(Color.blue);
			  else if(this.lpoly.get(i).getType() == 'C') //vertclair
				  g2.setColor(Color.GRAY);
			  else if(this.lpoly.get(i).getType() == 'V') //vert
				  g2.setColor(Color.green);
			  else if(this.lpoly.get(i).getType() == 'S') //vertsapin
				  g2.setColor(Color.magenta);
			  else //
				  g2.setColor(Color.yellow); //sable
			  g2.fill(path);
			  path.closePath();
		  }
	  }            
}