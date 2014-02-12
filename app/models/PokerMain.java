package models;

import java.util.*;
import javax.validation.*;
import play.data.validation.Constraints.*;

public class PokerMain {
	@Required
	private ArrayList<PokerCarte> main;
	public PokerMain() {
		main.add(New PokerCarte("8","carreau"));
		main.add(New PokerCarte("7","coeur"));
		main.add(New PokerCarte("dame","pique"));
		main.add(New PokerCarte("roi","coeur"));
		main.add(New PokerCarte("as","trefle"));
	}

	public size() {
		
	}
}