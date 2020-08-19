package prak4serv;
import prak4gemklassen.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerOrb {

public ServerOrb(BenutzerVerwaltungAdmin bva) throws IOException {
		
		
		try {
			ServerSocket server = new ServerSocket(2019);

			System.out.println(">> Server started on Port 2019 ");
	
			initdb(bva);
			
			// It will wait untill the client contact it
			while (true) {
			
				Socket client = server.accept(); 
				
				// Verbindung annehmen und korresp. Streams erzeugen:
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			
				// Lesen der Methodenkodierung:
				String methode = (String) in.readObject();
				// Lesen des Parameters:
				Benutzer ben = (Benutzer) in.readObject();
				
				// Methodenimplementierungen durch Delegation an
				// Dienstanbieterobjekt:
				if (methode.equals("benutzerOk")) { System.out.println("Calling BenutzerOK Fkt");
					out.writeBoolean(bva.benutzerOk(ben));
				// Sicherstellen, dass Ergebnis transportiert wird:
					out.flush();
				}
				
				if (methode.equals("benutzerEintragen")) { System.out.println("Calling benutzerEintragen Fkt");
					try {
						bva.benutzerEintragen(ben);
						out.writeObject("Erfolgreich");
					} catch (UserAlreadyExistException | NoPasswordIsGiven | NoUserIdIsGiven e) {
						out.writeObject("UserAlreadyExistException");
					}
					// Sicherstellen, dass Ergebnis transportiert wird:
					out.flush();
				}
				client.close();
			
			}
			
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	}

	private void initdb(BenutzerVerwaltungAdmin bva) {

		// asking the user if he want to init the serverdatenbak
		System.out.println("Initialisierung ServerDatenBank? 1 : ja oder 0 : nein");
		BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
		try {
			int dbInitialisieren = Integer.parseInt(din.readLine());
			if(dbInitialisieren == 1) {
				bva.dbInitialisieren();
				System.out.println("Initialisierung der ServerDatenbank ...");
			} 
		} catch (NumberFormatException | IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
