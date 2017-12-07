package Dessin;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Jeu;

public class Fond extends JPanel{
	public Fond(Jeu jeu, Fenetre fen) {
		
	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    
	    JPanel panel = new JPanel();
	    this.add(panel);
	    
	    JLabel lblJeuDeGolf = new JLabel("Jeu de Golf");
	    lblJeuDeGolf.setFont(new Font("Arial Black", Font.BOLD, 16));
	    panel.add(lblJeuDeGolf);
	    
	    JPanel panel_1 = new JPanel();
	    this.add(panel_1);
	    JPanel dessin = new Dessin(jeu, fen);
	    panel_1.add(dessin);
	    dessin.setPreferredSize(new Dimension(800,800));
	    dessin.setLayout(null);
	    JPanel panneau = new Panneau(jeu, fen);
	    panel_1.add(panneau);
	    this.setSize(new Dimension(1100,900));
	    this.setVisible(true);
	}
}
