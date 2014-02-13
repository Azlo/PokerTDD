package models;

import java.util.*;
import javax.validation.*;
import play.data.validation.Constraints.*;

import models.PokerCarte;

public class PokerMain {
	@Required
	private ArrayList<PokerCarte> main = new ArrayList<PokerCarte>();
	private String[] denominations = {"2","3","4","5","6","7","8","9","10","valet","dame","roi","as"};
	private String[] couleurs = {"carreau", "coeur", "trefle", "pique"};
	private String[] rang = {"carteSuperieur", "paire", "deuxPaire", "brelan", "quinte", "couleur", "mainPleine", "carre", "quinteCouleur", "quinteRoyal"};
	private int poids = 0;

	public PokerMain() {}

	public PokerMain(boolean random) {
		generate();
	}

	private void generate() {

		int i = 0;		

		do{

			int indexDenomination = randInt(0,denominations.length-1);
			int indexCouleur = randInt(0,couleurs.length-1);

			PokerCarte carte = new PokerCarte(denominations[indexDenomination],couleurs[indexCouleur]);

			if (!main.contains(carte)) {
				main.add(carte);
				i++;
			}

		}while( i < 5 );
	}


	public PokerCarte getCarte(int numCarte) {
		return main.get(numCarte);
	}


	public ArrayList<PokerCarte> getMain() {
		return main;
	}


	public int size() {
		return main.size();
	}


	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}


	public void add(PokerCarte c1) {
		main.add(c1);
	}


	public int possedePaire() {

		ArrayList<PokerCarte> lesPaires = new ArrayList<PokerCarte>();

		for ( int i = 0 ; i < main.size() ; i++ ) {
			
			for ( int j = i+1 ; j < main.size() ; j++ ) {

				if( main.get(i).getDenomination() == main.get(j).getDenomination() ) {

					lesPaires.add( main.get(i) );

					lesPaires.add( main.get(j) );

				}
			}
		}
		return lesPaires.size()/2 ;
	}

	public ArrayList<PokerCarte> getPaire() {	

		if(this.possedePaire() > 0){

			ArrayList<PokerCarte> paire = new ArrayList<PokerCarte>();

			for(int i=0; i<main.size(); i++) {
				for(int j=i+1; j<main.size(); j++) {
					if(main.get(i).getDenomination()==main.get(j).getDenomination()) {
						paire.add(main.get(i));
						paire.add(main.get(j));
					}
				}
			}
			return paire;
		}
		else {
			System.out.println("Aucune Paire");
			return null;
		}		
	}

	public boolean possedeBrelan() {

		int carteIdentiques = 0;
		boolean brelan = false;

		for(int i=0; i<main.size() && brelan!=true; i++) {
			for(int j=i+1; j<main.size() && brelan!=true; j++) {
				if(main.get(i).getDenomination() == main.get(j).getDenomination()) {
					carteIdentiques++;
				}
				if (carteIdentiques == 3) {
					brelan = true;
				}
			}
		}
		return brelan;
	}

	public boolean possedeMainPleine() {
		
		ArrayList<PokerCarte> cartesRestantes = main;

		for(int i=0; i<main.size(); i++) {
			for(int j=i+1; j<main.size(); j++) {
				if(main.get(i).getDenomination()==main.get(j).getDenomination()) {

					cartesRestantes.remove(i);
					cartesRestantes.remove(j-1);

					int carteIdentiques = 0;
					boolean mainPleine = false;

					for(int k=0; k<cartesRestantes.size() && !mainPleine; k++) {
						for(int l=k+1; l<cartesRestantes.size() && !mainPleine; l++) {
							if(cartesRestantes.get(k).getDenomination() == cartesRestantes.get(l).getDenomination()) {
								carteIdentiques++;
							}
							if (carteIdentiques == 3) {
								mainPleine = true;
							}
						}
					}
					return mainPleine;
				}
			}
		}
		return false;
	}


	public boolean possedeCouleur() {
		boolean estUneCouleur = true;
		String couleur = main.get(0).getCouleur();
		for(int i=1; i<main.size() && estUneCouleur; i++) {
			if(!(main.get(i).getCouleur() == couleur)) {
				estUneCouleur = false;
			}
		}
		return estUneCouleur;
	}

	public PokerCarte carteSuperieur() {
		String firstDenomination = main.get(0).getDenomination();
		int higher = Arrays.asList(denominations).indexOf(firstDenomination);
		int returnCarte = 0;

		for(int i=1;i<main.size();i++){
			int index = Arrays.asList(denominations).indexOf(main.get(i).getDenomination());
			if(index > higher){
				higher = index;
				returnCarte = i;
			}
		}
		return main.get(returnCarte);
	}

	public int getRangCarte(PokerCarte carte) {
		return Arrays.asList(denominations).indexOf(carte.getDenomination());
	}

	public int poidsPaire() {

		if(this.getPaire() != null && this.getPaire().size() > 0) {

			PokerCarte premiereCarte = this.getPaire().get(0);

			int indexHigher = Arrays.asList(denominations).indexOf(premiereCarte.getDenomination());

			for ( int i = 0; i < this.getPaire().size() ; i++ ) {

				int indexRang = Arrays.asList(denominations).indexOf(this.getPaire().get(i).getDenomination());

				if( indexRang > indexHigher ) {

					indexHigher = indexRang;

				}

			}

			return indexHigher;

		}
		else{
			return -1;
		}

	}

	public int getPoids() {
		return this.poids;
	}

	public void evaluerMain() {
		if(this.possedePaire() == 1) {this.poids = Arrays.asList(rang).indexOf("paire");}
		else if(this.possedePaire() == 2) {this.poids = Arrays.asList(rang).indexOf("deuxPaire");}
		if (this.possedeBrelan()) {this.poids = Arrays.asList(rang).indexOf("brelan");}
		if (this.possedeCouleur()) {this.poids = Arrays.asList(rang).indexOf("couleur");}
		if (this.possedeMainPleine()) {this.poids = Arrays.asList(rang).indexOf("MainPleine");}
	}

}