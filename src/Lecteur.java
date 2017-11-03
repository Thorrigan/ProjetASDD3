import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Lecteur {
	String nomFichier = "";
	public Lecteur(String nomFichier) {
		this.nomFichier = nomFichier;
	}
	public Map read(){
		Map m = null;
		File f = new File(nomFichier);
		//si existe
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(f));
			String ligne;
			int compteur = 0;
			int nbsurfaces = 0;
			ArrayList<Polygone> map = new ArrayList<Polygone>();
			while((ligne = br.readLine()) != null){
				System.out.println(ligne);				
				if(compteur == 0){
					nbsurfaces = Integer.parseInt(ligne);
				}
				else if(compteur <= nbsurfaces){
					ArrayList<Point> lp = new ArrayList<Point>();
					for(int i = 0; i < ligne.length()-1; i++){
						if(ligne.charAt(i) == '('){
							i++;
							float X = 0.0f;
							float Y = 0.0f;
							String s = "";
							while(ligne.charAt(i) != ','){
								s += ligne.charAt(i);
								i++;
							}
							X = Float.parseFloat(s);
							i++;
							
							s = "";
							while(ligne.charAt(i) != ')'){
								s += ligne.charAt(i);
								i++;
							}
							Y = Float.parseFloat(s);
							//System.out.println("X: " + X + " Y: " + Y);
							Point p = new Point(X, Y);
							lp.add(p);
						}		
					}
					char type = ligne.charAt(ligne.length()-1);
					//System.out.println("Type: " + type);
					Polygone s = new Polygone(lp);
					map.add(s);
				}
				compteur ++;
			}
			for(Polygone sf : map){
				System.out.println(sf.toString());
			}
			m = new Map(map, new Point(0,0), new Point(0,0));
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}
	

}
