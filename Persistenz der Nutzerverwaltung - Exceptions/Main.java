package Praktikum3;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException, UserAlreadyExistException, NoPasswordIsGiven, NoUserIdIsGiven 
	{
		BenutzerVerwaltungAdmin a1 = new BenutzerVerwaltungAdmin();
		a1.dbInitialisieren();
		Benutzer b1 = new Benutzer("Yassine","123");
		Benutzer e1 = new Benutzer("Yassine","passwort123");
		Benutzer	e11 = new Benutzer("Yassine","passwort123");
		Benutzer	e2 = new Benutzer("Jack","pass123");
		Benutzer	e3 = new Benutzer("He","passwort22");
		Benutzer	e4 = new Benutzer("Jens","passwort");
		Benutzer	e5 = new Benutzer("Hello","123");
		Benutzer	e6 = new Benutzer("Magic","See");
		a1.benutzerEintragen(b1);
		a1.benutzerEintragen(e2);
		a1.benutzerEintragen(e3);
		a1.benutzerEintragen(e4);
		a1.benutzerEintragen(e5);
		a1.benutzerEintragen(e6);
		System.out.println(a1.ausgabe());
		a1.benutzerLöschen(b1);
		System.out.println(a1.ausgabe());
		
	}
}
