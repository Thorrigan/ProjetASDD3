package main;

import java.util.ArrayList;

import Maths.Point;
import Maths.Polygone;

public class Trace {
	private Point depart;
	private Point arrivee;
	private int par;
	private ArrayList<Integer> surfaces;
	
	public Trace(Point depart, Point arrivee, int par, ArrayList<Integer> surfaces) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.par = par;
		this.surfaces = surfaces;
	}
	
	public String toString() {
		return "Trace [depart=" + depart + ", arrivee=" + arrivee + ", par=" + par + ", surfaces=" + surfaces + "]";
	}
	
	// O(n)
	public static Trace lireTrace(String s) {
		Point dep = null;
		Point arr = null;
		int parrr = 0;
		ArrayList<Integer> surfs = new ArrayList<Integer>();
		
		int compteur = 0;
		int i = 0;
		while(i < s.length()) {
			if(s.charAt(i) == ',') {
				i++;
			}else if(s.charAt(i) == '(') {
				i++;
				float X = 0.0f;
				float Y = 0.0f;
				String ss = "";
				while(s.charAt(i) != ','){
					ss += s.charAt(i);
					i++;
				}
				X = Float.parseFloat(ss);
				i++;
				
				ss = "";
				while(s.charAt(i) != ')'){
					ss += s.charAt(i);
					i++;
				}
				Y = Float.parseFloat(ss);
				if(dep == null) {
					dep = new Point(X, Y);
				}else {
					arr = new Point(X, Y);
				}
				i++;
			}else {
				String indicepoly = "";
				while(i < s.length() && s.charAt(i) != ',') {
					indicepoly += s.charAt(i);
					i++;
				}
				surfs.add(Integer.parseInt(indicepoly)-2);
			}
		}
		parrr = surfs.get(surfs.size()-1);
		surfs.remove(surfs.size()-1);
		return new Trace(dep, arr, parrr+2, surfs);
	}
	
	public Point getDepart() {
		return depart;
	}
	public Point getArrivee() {
		return arrivee;
	}
	public int getPar() {
		return par;
	}
	public ArrayList<Integer> getSurfaces() {
		return surfaces;
	}
}