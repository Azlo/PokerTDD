import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static org.junit.Assert.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import models.PokerCarte;
import models.PokerMain;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

	@Test
	public void testDenominationCarte() {
		PokerCarte c = new PokerCarte("as","carreau");
		assertEquals(c.getDenomination(), "as");
	}
	
	@Test
	public void testCouleurCarte() {
		PokerCarte c = new PokerCarte("as","carreau");
		assertEquals(c.getCouleur(),"carreau");
	}

	@Test
	public void compareCouleurCarte() {
		PokerCarte c1 = new PokerCarte("as","carreau");
		PokerCarte c2 = new PokerCarte("reine","carreau");
		assertTrue(c1.compareCouleur(c2));
		PokerCarte c3 = new PokerCarte("9", "coeur");
		assertFalse(c2.compareCouleur(c3));
	}

	@Test 
	public void compareDenominationCarte() {
		PokerCarte c1 = new PokerCarte("as","carreau");
		PokerCarte c2 = new PokerCarte("as","coeur");
		assertTrue(c1.compareDenomination(c2));
		PokerCarte c3 = new PokerCarte("9", "coeur");
		assertFalse(c2.compareDenomination(c3));
	}

	@Test 
	public void testNouvelleMainRandom() {
		PokerMain pm1 = new PokerMain(true);
		assertThat(pm1.size()).isEqualTo(5);
	}
	
	@Test 
	public void testUnePaire() {
		PokerMain main = new PokerMain();
		main.add( new PokerCarte("as","carreau"));
		main.add( new PokerCarte("as","pique"));
		main.add( new PokerCarte("8","trefle"));
		main.add( new PokerCarte("dame","coeur"));
		main.add( new PokerCarte("9", "coeur"));
		assertThat(main.possedePaire()).isEqualTo(1);
	}

	@Test 
	public void testDeuxPaire() {
		PokerMain main = new PokerMain();
		main.add( new PokerCarte("as","carreau"));
		main.add( new PokerCarte("as","pique"));
		main.add( new PokerCarte("8","trefle"));
		main.add( new PokerCarte("8","coeur"));
		main.add( new PokerCarte("9", "coeur"));
		assertThat(main.possedePaire()).isEqualTo(2);
	}

	@Test 
	public void testUnePaireDesordre() {
		PokerMain main = new PokerMain();
		main.add( new PokerCarte("8","carreau"));
		main.add( new PokerCarte("7","pique"));
		main.add( new PokerCarte("6","trefle"));
		main.add( new PokerCarte("8","coeur"));
		main.add( new PokerCarte("9", "coeur"));
		assertThat(main.possedePaire()).isEqualTo(1);
	}

	@Test 
	public void testAucunePaire() {
		PokerMain main = new PokerMain();
		main.add( new PokerCarte("8","carreau"));
		main.add( new PokerCarte("7","pique"));
		main.add( new PokerCarte("6","trefle"));
		main.add( new PokerCarte("3","coeur"));
		main.add( new PokerCarte("9", "coeur"));
		assertThat(main.possedePaire()).isEqualTo(0);
	}

	@Test 
	public void testUnBrelan() {
		PokerMain main = new PokerMain();
		main.add( new PokerCarte("8","carreau"));
		main.add( new PokerCarte("8","pique"));
		main.add( new PokerCarte("8","trefle"));
		main.add( new PokerCarte("3","coeur"));
		main.add( new PokerCarte("9", "coeur"));
		assertTrue(main.possedeBrelan());
	}

	@Test 
	public void testAucunBrelan() {
		PokerMain main = new PokerMain();
		main.add( new PokerCarte("8","carreau"));
		main.add( new PokerCarte("8","pique"));
		main.add( new PokerCarte("7","trefle"));
		main.add( new PokerCarte("3","coeur"));
		main.add( new PokerCarte("9", "coeur"));
		assertFalse(main.possedeBrelan());
	}

	@Test
	public void testMainPleine() {
		PokerMain main = new PokerMain();
		main.add( new PokerCarte("8","carreau"));
		main.add( new PokerCarte("8","pique"));
		main.add( new PokerCarte("dame","trefle"));
		main.add( new PokerCarte("dame","coeur"));
		main.add( new PokerCarte("dame","pique"));
		assertTrue(main.possedeMainPleine());
	}

	@Test
	public void testAucuneMainPleine() {
		PokerMain main = new PokerMain();
		main.add( new PokerCarte("8","carreau"));
		main.add( new PokerCarte("8","pique"));
		main.add( new PokerCarte("as","trefle"));
		main.add( new PokerCarte("dame","coeur"));
		main.add( new PokerCarte("dame","pique"));
		assertFalse(main.possedeMainPleine());
	}

	@Test
	public void testCouleur() {
		PokerMain main = new PokerMain();
		main.add( new PokerCarte("8","carreau"));
		main.add( new PokerCarte("7","carreau"));
		main.add( new PokerCarte("as","carreau"));
		main.add( new PokerCarte("roi","carreau"));
		main.add( new PokerCarte("dame","carreau"));
		assertTrue(main.possedeCouleur());
	}

	@Test
	public void testAucuneCouleur() {
		PokerMain main = new PokerMain();
		main.add( new PokerCarte("8","carreau"));
		main.add( new PokerCarte("7","pique"));
		main.add( new PokerCarte("as","carreau"));
		main.add( new PokerCarte("roi","carreau"));
		main.add( new PokerCarte("dame","carreau"));
		assertFalse(main.possedeCouleur());
	}

	@Test
	public void testPoidsCarteSuperieur() {
		PokerMain main = new PokerMain();
		main.add( new PokerCarte("8","carreau"));
		main.add( new PokerCarte("7","pique"));
		main.add( new PokerCarte("as","trefle"));
		main.add( new PokerCarte("roi","carreau"));
		main.add( new PokerCarte("dame","coeur"));
		assertEquals(main.carteSuperieur().getDenomination(),"as");
		assertThat(main.carteSuperieur().getDenomination()).isNotEqualTo("dame");
	}

	@Test
	public void testMainSuperieurCarteSuperieur() {
		PokerMain main1 = new PokerMain();
		main1.add( new PokerCarte("10","pique"));
		main1.add( new PokerCarte("2","carreau"));
		main1.add( new PokerCarte("3","trefle"));
		main1.add( new PokerCarte("valet","carreau"));
		main1.add( new PokerCarte("dame","coeur"));

		PokerMain main2 = new PokerMain();
		main2.add( new PokerCarte("8","carreau"));
		main2.add( new PokerCarte("7","pique"));
		main2.add( new PokerCarte("9","trefle"));
		main2.add( new PokerCarte("roi","carreau"));
		main2.add( new PokerCarte("5","coeur"));
		
		PokerCarte cartePlusHauteMain1 = main1.carteSuperieur();
		PokerCarte cartePlusHauteMain2 = main2.carteSuperieur();

		assertTrue(main1.getRangCarte(cartePlusHauteMain1) < main2.getRangCarte(cartePlusHauteMain2));
	}

	@Test
	public void testMainSuperieurPaire() {
		PokerMain main1 = new PokerMain();
		main1.add( new PokerCarte("3","pique"));
		main1.add( new PokerCarte("2","carreau"));
		main1.add( new PokerCarte("3","trefle"));
		main1.add( new PokerCarte("valet","carreau"));
		main1.add( new PokerCarte("dame","coeur"));

		PokerMain main2 = new PokerMain();
		main2.add( new PokerCarte("8","carreau"));
		main2.add( new PokerCarte("7","pique"));
		main2.add( new PokerCarte("5","trefle"));
		main2.add( new PokerCarte("roi","carreau"));
		main2.add( new PokerCarte("5","coeur"));
		
		assertTrue(main1.poidsPaire() < main2.poidsPaire());
	}

	@Test
	public void testMainSuperieurDeuxPaire() {
		PokerMain main1 = new PokerMain();
		main1.add( new PokerCarte("3","pique"));
		main1.add( new PokerCarte("2","carreau"));
		main1.add( new PokerCarte("3","trefle"));
		main1.add( new PokerCarte("2","trefle"));
		main1.add( new PokerCarte("dame","coeur"));

		PokerMain main2 = new PokerMain();
		main2.add( new PokerCarte("8","carreau"));
		main2.add( new PokerCarte("7","pique"));
		main2.add( new PokerCarte("5","trefle"));
		main2.add( new PokerCarte("roi","carreau"));
		main2.add( new PokerCarte("5","coeur"));
		
		assertTrue(main1.poidsPaire() < main2.poidsPaire());
	}

	@Test
	public void testEvaluerMainsCouleurBrelan() {

		PokerMain mainCouleur = new PokerMain();
		mainCouleur.add( new PokerCarte("8","carreau"));
		mainCouleur.add( new PokerCarte("7","carreau"));
		mainCouleur.add( new PokerCarte("as","carreau"));
		mainCouleur.add( new PokerCarte("roi","carreau"));
		mainCouleur.add( new PokerCarte("dame","carreau"));


		PokerMain mainBrelan = new PokerMain();
		mainBrelan.add( new PokerCarte("8","carreau"));
		mainBrelan.add( new PokerCarte("8","pique"));
		mainBrelan.add( new PokerCarte("8","trefle"));
		mainBrelan.add( new PokerCarte("3","coeur"));
		mainBrelan.add( new PokerCarte("9", "coeur"));

		mainCouleur.evaluerMain();
		mainBrelan.evaluerMain();

		assertThat(mainCouleur.getPoids()).isEqualTo(5);
		assertThat(mainBrelan.getPoids()).isEqualTo(3);

		assertTrue(mainCouleur.getPoids() > mainBrelan.getPoids());

	}

}