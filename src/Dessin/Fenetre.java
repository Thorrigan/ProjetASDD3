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
    
    JPanel pan = new JPanel();
    pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
    
    JPanel panel = new JPanel();
    pan.add(panel);
    
    JLabel lblJeuDeGolf = new JLabel("Jeu de Golf");
    lblJeuDeGolf.setFont(new Font("Arial Black", Font.BOLD, 16));
    panel.add(lblJeuDeGolf);
    
    JPanel panel_1 = new JPanel();
    pan.add(panel_1);
    JPanel dessin = new Dessin(jeu);
    panel_1.add(dessin);
    dessin.setPreferredSize(new Dimension(800,800));
    dessin.setLayout(null);
    JPanel panneau = new Panneau(jeu);
    panel_1.add(panneau);
    pan.setSize(new Dimension(1100,900));
    pan.setVisible(true);
    this.setContentPane(pan);
  }
}
