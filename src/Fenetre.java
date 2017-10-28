import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
	JPanel panel;
	public Fenetre(String nomFenetre, int taille_x, int taille_y, Dessin dessin) {
		dessin.setBackground(Color.WHITE);
		dessin.setPreferredSize(new Dimension(800,800));
		panel = new JPanel();
		panel.add(dessin);
		getContentPane().add(panel);
		setTitle(nomFenetre);
		setSize(taille_x, taille_y);
		setVisible(true);
	}
}
