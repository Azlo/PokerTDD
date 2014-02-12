package models;

import java.util.*;
import javax.validation.*;
import play.data.validation.Constraints.*;

public class PokerCarte {
	@Required
	private String denomination;
	private String couleur;
	public PokerCarte() {}
	public PokerCarte(String v, String c) {
		this.denomination = v;
		this.couleur = c;
	}

	public String getDenomination(){
		return this.denomination;
	}

	public String getCouleur(){
		return this.couleur;
	}

	public Boolean compareCouleur(PokerCarte pc){
		if(this.couleur.equalsIgnoreCase(pc.getCouleur()))
			return true;
		else
			return false;
	}

	public Boolean compareDenomination(PokerCarte pc){
		if(this.denomination.equalsIgnoreCase(pc.getDenomination()))
			return true;
		else
			return false;
	}


// NE PAS TOUCHER (GROS BORDEL DANS LA SOUPE SINON)
	@Override
	public boolean equals(Object autreCarte) {
		if (autreCarte == null) return false;
		if (autreCarte == this) return true;
		PokerCarte c1 = (PokerCarte) autreCarte;
		if (this.getDenomination() == c1.getDenomination() && this.getCouleur() == c1.getCouleur()) {
			return true;
		}
		else {
			return false;
		}
	}
}