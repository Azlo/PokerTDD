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
	}
	
}
