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

		int nbPaires = 0;

		for(int i=0; i<main.size(); i++) {
			for(int j=i+1; j<main.size(); j++) {
				if(main.get(i).getDenomination()==main.get(j).getDenomination()) {
					nbPaires++;
				}
			}
		}
		return nbPaires;
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



}