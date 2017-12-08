package Dessin;
import main.Jeu;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JTextField;

import Maths.Point;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
 
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
	private JCheckBox chckbxAfficherLaTriangulation;
	private Jeu jeu;
	private JCheckBox chckbxAfficherLeTrace;
	private JLabel lblAa;
	public Panneau(final Jeu jeu, final Fenetre fen) {
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
		label_1.setBounds(10, 244, 27, 22);
		add(label_1);

		textField = new JTextField();
		textField.setBounds(89, 276, 86, 20);
		add(textField);
		textField.setColumns(10);

		JLabel label_2 = new JLabel("Y :");
		label_2.setBounds(10, 277, 28, 22);
		add(label_2);

		textField_1 = new JTextField();
		textField_1.setBounds(89, 245, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblAstuce = new JLabel("Astuces :");
		lblAstuce.setBounds(10, 353, 68, 14);
		add(lblAstuce);

		lblDistanceAuProchain = new JLabel("Distance au Trou : " + jeu.ballePosition().distance(jeu.getactTrace().getArrivee()));
		lblDistanceAuProchain.setBounds(10, 439, 215, 14);
		add(lblDistanceAuProchain);

		lblDegrParRapport = new JLabel("Angle par rapport au trou : " + jeu.ballePosition().angle(jeu.getactTrace().getArrivee()));
		lblDegrParRapport.setBounds(10, 460, 215, 14);
		add(lblDegrParRapport);

		JLabel lblAngle = new JLabel("Angle :");
		lblAngle.setBounds(10, 130, 46, 14);
		add(lblAngle);

		JLabel lblDistance = new JLabel("Distance :");
		lblDistance.setBounds(10, 155, 61, 14);
		add(lblDistance);

		textField_2 = new JTextField();
		textField_2.setBounds(89, 122, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(89, 152, 86, 20);
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
		btnTirer.setBounds(89, 183, 86, 22);
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
		btnTirer_1.setBounds(89, 307, 86, 23);
		add(btnTirer_1);

		lblParDuTrou = new JLabel("Par du trou actuel: " + jeu.parAct());
		lblParDuTrou.setBounds(59, 11, 116, 14);
		add(lblParDuTrou);

		chckbxAfficherLaTriangulation = new JCheckBox("Afficher la triangulation");
		chckbxAfficherLaTriangulation.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(chckbxAfficherLaTriangulation.isSelected()) {
					fen.affichageTriangulation(true);
					fen.repaint();
				}else {
					fen.affichageTriangulation(false);
					fen.repaint();
				}
			}
		});
		chckbxAfficherLaTriangulation.setBounds(6, 533, 248, 23);
		add(chckbxAfficherLaTriangulation);
		
		chckbxAfficherLeTrace = new JCheckBox("Afficher le Trace actuel");
		chckbxAfficherLeTrace.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(chckbxAfficherLeTrace.isSelected()) {
					fen.affichageTrace(true);
					fen.repaint();
				}else {
					fen.affichageTrace(false);
					fen.repaint();
				}
			}
		});
		chckbxAfficherLeTrace.setBounds(6, 559, 244, 23);
		add(chckbxAfficherLeTrace);
		
		JLabel lblPositionDuTrou = new JLabel("Position du trou : " + jeu.getactTrace().getArrivee());
		lblPositionDuTrou.setBounds(10, 485, 245, 14);
		add(lblPositionDuTrou);
		
		JTextArea txtrArriverAMoins = new JTextArea();
		txtrArriverAMoins.setBackground(UIManager.getColor("Label.background"));
		txtrArriverAMoins.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrArriverAMoins.setLineWrap(true);
		txtrArriverAMoins.setText("Arriver a moins d'un metre du trou vous garantis de  \r\nr\u00E9ussir votre prochain coup si vous visez le trou.");
		txtrArriverAMoins.setBounds(10, 374, 260, 30);
		add(txtrArriverAMoins);
		
		lblAa = new JLabel("");
		lblAa.setBounds(10, 406, 244, 30);
		add(lblAa);
	} 	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);   
		lblTrouActuel.setText("Trou actuel: " + (jeu.trouActuel() +1));
		lblScoreActuel.setText("Score Actuel: " + jeu.scoreactuel());
		lblScoreTotal.setText("Score Total: " + jeu.scoretotal());
		lblParTotal.setText("Par Total: " + jeu.partotal());
		lblDistanceAuProchain.setText("Distance au Trou:");
		lblDegrParRapport.setText("Angle par rapport au trou :");
		lblParDuTrou.setText("Par du trou actuel: " + jeu.parAct());
		lblDistanceAuProchain.setText("Distance au Trou : " + jeu.ballePosition().distance(jeu.getactTrace().getArrivee()));
		lblDegrParRapport.setText("Angle par rapport au trou : " + jeu.ballePosition().angle(jeu.getactTrace().getArrivee()));
		if(jeu.getState() == 0) {
			lblAa.setText("");
		}else if(jeu.getState() == 1) {
			lblAa.setForeground(Color.GREEN);
			lblAa.setText("Visez le trou !");
		}else if(jeu.getState() == 2) {
			lblAa.setForeground(Color.RED);
			lblAa.setText("Sable : Frappez fort !");
		}else if(jeu.getState() == 3) {
			lblAa.setForeground(Color.BLUE);
			lblAa.setText("Attention au Plouf");
		}else {
			System.out.println("ERREUR !");
		}
	}
}