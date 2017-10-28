
public class Main {
	public static void main(String[] args) {
		String nomFichier = "map.txt";
		if(args.length != 0) {
			nomFichier = args[0];
		}
		Jeu jeu = new Jeu(nomFichier);
	}
}
