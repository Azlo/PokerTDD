package models;

import java.util.*;
import javax.validation.*;
import play.data.validation.Constraints.*;

public class PokerCarte {
	@Required
	public String denomination;
	public String couleur;
	public PokerCarte() {}
	public PokerCarte(String v, String c) {
		this.denomination = v;
		this.couleur = c;
	}

}