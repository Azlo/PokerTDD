package models;

import java.util.*;
import javax.validation.*;
import play.data.validation.Constraints.*;

import models.PokerCarte;

public class PokerMain {
	@Required
	private ArrayList<PokerCarte> main = new ArrayList<PokerCarte>();
	public PokerMain() {
		main.add(new PokerCarte("8","carreau"));
		main.add(new PokerCarte("7","coeur"));
		main.add(new PokerCarte("dame","pique"));
		main.add(new PokerCarte("roi","coeur"));
		main.add(new PokerCarte("as","trefle"));
	}

	public int size() {
		return main.size();
	}
}