package Dessin;
import main.Jeu;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Label;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Button;
import javax.swing.JTextField;
 
public class Panneau extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	public Panneau(Jeu jeu) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		Panel panel_6 = new Panel();
		add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		
		JLabel lblTrouActuel = new JLabel("Trou actuel :");
		panel_8.add(lblTrouActuel);
		
		JLabel lblScoreActuel = new JLabel("Score Actuel :");
		panel_8.add(lblScoreActuel);
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));
		
		JLabel lblScoreTotal = new JLabel("Score Total :");
		panel_9.add(lblScoreTotal);
		
		JLabel lblParTotal = new JLabel("Par Total :");
		panel_9.add(lblParTotal);
		
		Panel panel_3 = new Panel();
		panel_6.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		Panel panel_4 = new Panel();
		panel_3.add(panel_4);
		
		Label label_1 = new Label("X :");
		panel_4.add(label_1);
		
		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);
		
		Panel panel_5 = new Panel();
		panel_3.add(panel_5);
		
		Label label_2 = new Label("Y :");
		panel_5.add(label_2);
		
		textField_1 = new JTextField();
		panel_5.add(textField_1);
		textField_1.setColumns(10);
		
		Button button = new Button("Tirer !");
		panel_3.add(button);
		
		JLabel lblAstuce = new JLabel("Astuces :");
		panel_6.add(lblAstuce);
		
		JLabel lblDistanceAuProchain = new JLabel("Distance au prochain Trou:");
		panel_6.add(lblDistanceAuProchain);
		
		JLabel lblDegrParRapport = new JLabel("Degr\u00E9 par rapport au prochain trou :");
		panel_6.add(lblDegrParRapport);
	} 		          
}