package Dessin;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import Maths.Triangle;
import main.Jeu;

public class Fenetre extends JFrame {
	ArrayList<Triangle> lpoly;
	
	public Fenetre(ArrayList<Triangle> lpoly){
	this.lpoly = lpoly;
    this.setTitle("Ma première fenêtre Java");
    this.setSize(1100, 900);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
    this.setVisible(true);
    this.setResizable(false);
    Jeu jeu = null;
    
    JPanel pan = new JPanel();
    this.setContentPane(pan);
    JPanel dessin = new Dessin(lpoly, 0);
    JPanel panneau = new Panneau(null);
    pan.add(dessin);
    pan.add(panneau);
    pan.setSize(new Dimension(300,300));
    pan.setVisible(true);
  }
}
