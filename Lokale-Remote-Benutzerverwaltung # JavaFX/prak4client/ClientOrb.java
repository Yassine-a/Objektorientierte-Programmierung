package prak4client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream; // java.io (Streams)
import java.net.Socket; // .net (Socket)


import prak4gemklassen.*;

// Implementierung der Benutzerverwaltung für Remotefall
 
public class ClientOrb implements BenutzerVerwaltung {
	
	private String address = "localhost";
	private int port = 2019;
	
	/*
	 * sends the server the username and password and add them to our datenbank
	 * @see prak4client.BenutzerVerwaltung#benutzerEintragen(prak4gemklassen.Benutzer)
	 */
	public void benutzerEintragen(Benutzer benutzer) throws UserAlreadyExistException, NoPasswordIsGiven, NoUserIdIsGiven, IOException, ClassNotFoundException
	{
		Socket server = new Socket(address, port);
		ObjectOutputStream out =  new ObjectOutputStream(server.getOutputStream());
		ObjectInputStream in =  new ObjectInputStream(server.getInputStream());
		out.writeObject("benutzerEintragen");
		out.writeObject(benutzer);
		out.flush();
		
		String response = in.readObject().toString();
		server.close();
		if(response.equals("UserAlreadyExistException")) { throw new UserAlreadyExistException ("this user already exist");}
		else if(response.equals("Erfolgreich")) {System.out.println("registration was successful");}
		
	}
	
	// Schickt dem Server Benutzerdaten, damit der Server prüfen kann ob dieser Benutzer vorhanden ist
	/*
	 * sends the server the ben object to check if it in our datenbank
	 * OK: returns the server true
	 * NotOK: return the server false
	 * @see prak4client.BenutzerVerwaltung#benutzerOk(prak4gemklassen.Benutzer)
	 */
	public boolean benutzerOk(Benutzer ben) {
		
		try {
			Socket server = new Socket(address, port);
			ObjectOutputStream out =  new ObjectOutputStream(server.getOutputStream());
			ObjectInputStream in =  new ObjectInputStream(server.getInputStream());
			out.writeObject("benutzerOk");
			out.writeObject(ben);
			out.flush();
			boolean response = in.readBoolean();
			server.close();
			return response;
		} catch (IOException e) {
			System.out.println("There is a problem connecting to the server");
			e.printStackTrace();
		}
		return false;
		
	}
	
}
