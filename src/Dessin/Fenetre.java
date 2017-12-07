package Dessin;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import Maths.Triangle;
import main.Jeu;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;

import java.awt.Font;

public class Fenetre extends JFrame {	
	private boolean afficherTriangulation;
	private boolean affichertrace;
	public Fenetre(Jeu jeu){
		this.afficherTriangulation = false;
		this.affichertrace = false;
		this.setTitle("Jeu de Golf");
		this.setSize(1200, 900);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		this.setVisible(true);
		this.setResizable(false);
		Fond pan = new Fond(jeu, this);
		this.setContentPane(pan);
  }
	
	public void affichageTriangulation(boolean b) {
		this.afficherTriangulation = b;
	}
	public boolean getaffichagetriangulation() {
		return this.afficherTriangulation;
	}
	
	public void affichageTrace(boolean b) {
		this.affichertrace = b;
	}
	public boolean getaffichageTrace() {
		return this.affichertrace;
	}
}
