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
import javax.swing.BoxLayout;
import java.awt.Font;

public class Fenetre extends JFrame {	
	public Fenetre(Jeu jeu){
		this.setTitle("Jeu de Golf");
		this.setSize(1200, 900);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		this.setVisible(true);
		this.setResizable(false);
    
		Fond pan = new Fond(jeu, this);
		this.setContentPane(pan);
  }
}
