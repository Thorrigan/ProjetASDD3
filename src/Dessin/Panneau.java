package Dessin;
import main.Jeu;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Label;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Dimension;

import javax.swing.JTextField;

import Maths.Point;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
 
public class Panneau extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblTrouActuel;
	private JLabel lblScoreActuel;
	private JLabel lblScoreTotal;
	private JLabel lblParTotal;
	private JLabel lblDistanceAuProchain;
	private JLabel lblDegrParRapport;
	private JLabel lblParDuTrou;
	private Jeu jeu;
	public Panneau(final Jeu jeu) {
		this.setVisible(true);
		this.setPreferredSize(new Dimension(280,600));
		setLayout(null);
		this.jeu = jeu;
		lblTrouActuel = new JLabel("Trou actuel: " + jeu.trouActuel());
		lblTrouActuel.setBounds(134, 81, 136, 14);
		add(lblTrouActuel);
		
		lblScoreActuel = new JLabel("Score Actuel: " + jeu.scoreactuel());
		lblScoreActuel.setBounds(134, 49, 136, 14);
		add(lblScoreActuel);
		
		lblScoreTotal = new JLabel("Score Total: " + jeu.scoretotal());
		lblScoreTotal.setBounds(10, 49, 114, 14);
		add(lblScoreTotal);
		
		lblParTotal = new JLabel("Par Total: " + jeu.partotal());
		lblParTotal.setBounds(10, 81, 114, 14);
		add(lblParTotal);
		
		JLabel label_1 = new JLabel("X :");
		label_1.setBounds(25, 283, 27, 22);
		add(label_1);
		
		textField = new JTextField();
		textField.setBounds(59, 313, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("Y :");
		label_2.setBounds(25, 311, 28, 22);
		add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(58, 285, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAstuce = new JLabel("Astuces :");
		lblAstuce.setBounds(77, 414, 68, 14);
		add(lblAstuce);
		
		lblDistanceAuProchain = new JLabel("Distance au Trou:");
		lblDistanceAuProchain.setBounds(25, 439, 154, 14);
		add(lblDistanceAuProchain);
		
		lblDegrParRapport = new JLabel("Angle par rapport au trou :");
		lblDegrParRapport.setBounds(25, 464, 215, 14);
		add(lblDegrParRapport);
		
		JLabel lblAngle = new JLabel("Angle :");
		lblAngle.setBounds(10, 144, 46, 14);
		add(lblAngle);
		
		JLabel lblDistance = new JLabel("Distance :");
		lblDistance.setBounds(10, 169, 61, 14);
		add(lblDistance);
		
		textField_2 = new JTextField();
		textField_2.setBounds(72, 141, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(72, 166, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		// AVEC DISTANCE ET ANGLE
		JButton btnTirer = new JButton("Tirer !");
		btnTirer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!textField_2.getText().isEmpty() && !textField_3.getText().isEmpty()) {
					float angle = 0.0f;
					float distance = 0.0f;
					try {
						angle = Float.parseFloat(textField_2.getText());
						distance = Float.parseFloat(textField_3.getText());
						if(angle >= 0 && angle <= 360 && distance > 0) {
							//System.out.println("Vous avez tapé un angle de: " + angle + " et une distance " + distance);
							jeu.JouerCoup(angle, distance);
							textField_2.setText("");
							textField_3.setText("");
						}else {
							System.out.println("Vos valeurs ne sont pas cohérentes.");
						}
					}catch (NumberFormatException ex) {
						System.out.println("Vous n'avez pas entré deux réels."); 
				    } 
				}
			}
		});
		btnTirer.setBounds(59, 219, 89, 23);
		add(btnTirer);
		
		// AVEC COORDONNEES
		JButton btnTirer_1 = new JButton("Tirer !");
		btnTirer_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(!textField.getText().isEmpty() && !textField_1.getText().isEmpty()) {
					float x = 0.0f;
					float y = 0.0f;
					try {
					x = Float.parseFloat(textField_1.getText());
					y = Float.parseFloat(textField.getText());
					if(x >= 0.0f && x <= 10.0f && y >= 0.0f && y <= 10.0f) {
						Point dest = new Point(x,y);
						jeu.JouerCoup(dest);
						textField.setText("");
						textField_1.setText("");
					}else {
						System.out.println("Vos valeurs ne sont pas cohérentes.");
					}
					}catch (NumberFormatException ex) {
						System.out.println("Vous n'avez pas entré deux réels."); 
				    } 
				}
			}
		});
		btnTirer_1.setBounds(65, 365, 89, 23);
		add(btnTirer_1);
		
		lblParDuTrou = new JLabel("Par du trou actuel: " + jeu.parAct());
		lblParDuTrou.setBounds(59, 11, 116, 14);
		add(lblParDuTrou);
	} 	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);   
		lblTrouActuel.setText("Trou actuel: " + jeu.trouActuel());
		lblScoreActuel.setText("Score Actuel: " + jeu.scoreactuel());
		lblScoreTotal.setText("Score Total: " + jeu.scoretotal());
		lblParTotal.setText("Par Total: " + jeu.partotal());
		lblDistanceAuProchain.setText("Distance au Trou:");
		lblDegrParRapport.setText("Angle par rapport au trou :");
		lblParDuTrou.setText("Par du trou actuel: " + jeu.parAct());
	}
}