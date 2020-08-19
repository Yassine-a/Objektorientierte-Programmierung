package Praktikum3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BenutzerTest {
	
	private Benutzer e1;
	private Benutzer e2;
	private Benutzer e3;
	private Benutzer e4;
	private Benutzer e5;
	private Benutzer e6;
	private Benutzer e7;
	private Benutzer e11;
	
	private Benutzer tmp1;
	private Benutzer tmp2;
	private Benutzer tmp3;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("SetUp Klasse wird aufgerufen");
	}
	
	@Before
	public void setUp() throws Exception {
		e1 = new Benutzer("Yassine","passwort123");
		e11 = new Benutzer("Yassine","passwort123");
		e2 = new Benutzer("Jack","pass123");
		e3 = new Benutzer("He","passwort22");
		e4 = new Benutzer("Jens","passwort");
		e5 = new Benutzer("Hello","123");
		e6 = new Benutzer("Magic","See");
		e7 = new Benutzer();
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tearDown Klasse wird aufgerufen");
	}

	

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBenutzer() {
		tmp1 = new Benutzer();
		tmp3 = new Benutzer();
		
		assertEquals(tmp1,tmp3); // Achtung für Objekte ruft equals auf
	}

	@Test
	public void testBenutzerStringString() {
		
		e7 = e6;
		assertNotSame(e1,e2);
		
		assertEquals(e1,e11);
		assertEquals("Yassine",e1.userId);
		assertEquals("passwort123",String.valueOf(e1.passWort));
	}

	@Test
	public void testEqualsObject() {
	
		
		assertNotSame(e1,e11);
		assertTrue(e1.equals(e11));
		//assertTrue(e7.equals(e6));
		
		//assertFalse
		assertFalse(e1.equals(e2));
		assertFalse(e2.equals(e3));
		assertFalse(e6.equals(e5));
		assertFalse(e5.equals(e1));
		
		assertTrue(e1.equals(e11));
		
		Benutzer i1=null;
		assertFalse(e1.equals(i1));
		Object obj1 = new Object();
		
		assertFalse(e1.equals(obj1));
		
		
	}

	

}
