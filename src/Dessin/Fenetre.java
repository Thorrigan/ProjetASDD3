package Dessin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import Maths.Polygone;

public class Fenetre extends JFrame {
	ArrayList<Polygone> lpoly;
	
	public Fenetre(ArrayList<Polygone> lpoly){
	this.lpoly = lpoly;
    this.setTitle("Ma première fenêtre Java");
    this.setSize(810, 810);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
    this.setVisible(true);
    this.setResizable(false);
    
    
    JPanel pan = new JPanel();
    this.setContentPane(new Panneau(this.lpoly));
    
  }
}
