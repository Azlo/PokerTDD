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

}