package game;

import static org.junit.Assert.*;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.Test;
import org.junit.runner.JUnitCore;

public class LandingPageTest {

	@Test
	public final void testLandingPage() {
		org.junit.runner.Result result = JUnitCore.runClasses(LandingPageTest.class);
		
		System.out.println("Landing Page Loading......"); // TODO
	}

	@Test
	public final void testAGPage() {
		org.junit.runner.Result result = JUnitCore.runClasses(AGPage.class);
		System.out.println("Loading AGPage......"); // TODO
	}

	@Test
	public final void testGetScene() {
		System.out.println("Getting Scene......"); // TODO
	}

	@Test
	public final void testUpdateSize() {
		System.out.println("Updating Size......");// TODO
	}

}
