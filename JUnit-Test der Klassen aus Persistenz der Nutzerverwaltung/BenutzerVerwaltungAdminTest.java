package Praktikum3;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class BenutzerVerwaltungAdminTest  {

	
	private BenutzerVerwaltungAdmin e1;
	private BenutzerVerwaltungAdmin ba1;	
	private BenutzerVerwaltungAdmin ba2;
	private Benutzer b1;
	private Benutzer b2;
	private Benutzer b3;
	private Benutzer b4;
	private Benutzer b5;
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception,IOException {
		ba1 = new BenutzerVerwaltungAdmin();
		ba2 = new BenutzerVerwaltungAdmin();
		ba2 =ba1;
		b1 = new Benutzer("User1","123456");
		b2 = new Benutzer("User2","123");
		b3 = new Benutzer("User3","345");
		b4 = new Benutzer("User4","789");
		b5 = new Benutzer("User1","111");
	}

	@After
	public void tearDown() throws Exception {
	
	}

	@Test
	public void testBenutzerVerwaltungAdmin() {
		
		assertNotNull(ba1);
		assertNull(e1);
		assertNotSame(ba1,e1);
		assertSame(ba1,ba2);
		assertEquals(ba1,ba2);
	}

	@Test
	public void testBenutzerEintragen() {
		
		try {
			ba1.dbInitialisieren();
			ba1.benutzerEintragen(b1);
			ba1.benutzerEintragen(b2);
			ba1.benutzerEintragen(b3);

		} catch (UserAlreadyExistException | NoPasswordIsGiven
				| NoUserIdIsGiven e) {
			fail("BenutzerEintragen zeigt ein Fehler");
			e.printStackTrace();
		}
		
		assertTrue(ba1.benutzerOk(b1));
		assertTrue(ba1.benutzerOk(b2));
		assertTrue(ba1.benutzerOk(b3));
		assertFalse(ba1.benutzerOk(b4));
		
	}
	
	@Test
	public void testBenutzerEintragenduplikat() {
		
		
		try {
			ba1.dbInitialisieren();
			ba1.benutzerEintragen(b1); // ba1. user == b5.user;
		//	ba1.benutzerEintragen(b5);
			
		} catch (NoPasswordIsGiven| NoUserIdIsGiven e) {
			fail("Unerwartete Fehler aufgetreten");
			e.printStackTrace();
		} catch(UserAlreadyExistException e)	{
			fail("Benutzer doppelt eingetragen");
			e.printStackTrace();
		}
		
	
	}

	@Test
	public void testBenutzerOk() {
		 
		try {
			ba1.dbInitialisieren();
			ba1.benutzerEintragen(b1);
			ba1.benutzerEintragen(b2);
			ba1.benutzerEintragen(b3);
			
		} catch (UserAlreadyExistException | NoPasswordIsGiven
				| NoUserIdIsGiven e) {
			fail("Unerwartete Fehler aufgetreten");
			e.printStackTrace();
		}
		
		assertTrue(ba1.benutzerOk(b1));
		assertTrue(ba1.benutzerOk(b2));
		assertTrue(ba1.benutzerOk(b3));
		assertFalse(ba1.benutzerOk(b4));
		
		
	}

	@Test
	public void testBenutzerLöschen() {
		try {
			ba1.dbInitialisieren();
			ba1.benutzerEintragen(b1);
			ba1.benutzerEintragen(b2);
			ba1.benutzerEintragen(b3);
			
			
		} catch (UserAlreadyExistException | NoPasswordIsGiven
				| NoUserIdIsGiven e) {
			fail("Unerwartete Fehler aufgetreten");
			e.printStackTrace();
		}
		
		assertTrue(ba1.benutzerOk(b1));
		assertTrue(ba1.benutzerOk(b2));
		assertTrue(ba1.benutzerOk(b3));
		
		
		try {
			ba1.benutzerLöschen(b1);
			assertFalse(ba1.benutzerOk(b1));
			assertTrue(ba1.benutzerOk(b2));
			ba1.benutzerLöschen(b2);
			assertFalse(ba1.benutzerOk(b2));
			assertTrue(ba1.benutzerOk(b3));
			ba1.benutzerLöschen(b3);
			assertFalse(ba1.benutzerOk(b3));
			
			
		} catch (UserAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEquals()
	{
		assertFalse(ba1.equals(new BenutzerVerwaltungAdmin()));
	}
	
	@Test
	public void testRead() {
		try {
			ba1.dbInitialisieren();
			ba1.benutzerEintragen(b1);
			ba1.read();
			ba1.benutzerOk(b1);
			ba1.benutzerEintragen(b2);
			ba1.read();
			ba1.benutzerOk(b2);
			//System.out.println(ba1.ausgabe());
			
		} catch (UserAlreadyExistException | NoPasswordIsGiven
				| NoUserIdIsGiven e) {
			fail("ein Fehler bei Initialisierung aufgetreten");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testWrite() {
		try {
			ba1.dbInitialisieren();
			ba1.benutzerEintragen(b1);
			ba1.write();
			ba1.benutzerOk(b1);
			ba1.benutzerEintragen(b2);
			ba1.write();
			ba1.benutzerOk(b2);
			System.out.println(ba1.ausgabe());
			
		} catch (UserAlreadyExistException | NoPasswordIsGiven
				| NoUserIdIsGiven e) {
			fail("ein Fehler bei Initialisierung aufgetreten");
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void dbInitialisieren() {
		try {
			ba2.dbInitialisieren();
			ba2.benutzerEintragen(b2);
		} catch (UserAlreadyExistException| NoPasswordIsGiven| NoUserIdIsGiven e) {
			fail("ein Fehler bei Initialisierung aufgetreten");
			e.printStackTrace();
		}
		assertTrue(ba2.benutzerOk(b2));
		
		
		ba2.dbInitialisieren();
		assertFalse(ba2.benutzerOk(b2));
		
		
		
	}
	
	
	@Test
	public void dbOhneInitialisieren() {
		BenutzerVerwaltungAdmin bva1 = new BenutzerVerwaltungAdmin();
		Benutzer k1 = new Benutzer("UserX","123456");
		
		try {
			bva1.benutzerEintragen(k1);
		} catch (UserAlreadyExistException| NoPasswordIsGiven| NoUserIdIsGiven e) {
			fail("ein Fehler ist aufgetreten");
			e.printStackTrace();
		}
		assertTrue(bva1.benutzerOk(k1));
	}
	

}
