package prak4client;
import java.util.HashMap;
import prak4gemklassen.*;
import java.util.Set;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import java.io.Serializable;


public class BenutzerVerwaltungAdmin implements BenutzerVerwaltung, Serializable {
	
	HashMap<String, Benutzer> user;
	
	
	public BenutzerVerwaltungAdmin() { user = new HashMap<>(); }	
	
	public void benutzerEintragen(Benutzer benutzer) throws UserAlreadyExistException, NoPasswordIsGiven, NoUserIdIsGiven, IOException, ClassNotFoundException
	{
		read();
		if( !(benutzerOk(benutzer)) ) {
				user.put(benutzer.userId, benutzer);
				write();
		}
		else {throw new UserAlreadyExistException("Benutzer gibts schon");}
		
		if(benutzer.passWort.length==0) {throw new NoPasswordIsGiven("Das Passwort fehlt!");}
		if(benutzer.userId.length()==0) {throw new NoUserIdIsGiven("Username fehlt!");}
	};
	
	public boolean benutzerOk(Benutzer benutzer)
	{
		read();
		return user.containsKey(benutzer.userId);
	}
	
	
	
	void benutzerLöschen(Benutzer benutzer) throws UserAlreadyExistException
	{
		this.read();
		if( benutzerOk(benutzer)) {
			user.remove(benutzer.userId, benutzer);
			write();
		}
		else {throw new UserAlreadyExistException("Dieser User existiert nicht");}
	}
	 
	
	
	public void read()
	{
		try {
			FileInputStream fs = new FileInputStream("fos.txt");
			ObjectInputStream is = new ObjectInputStream(fs);
			user = (HashMap<String, Benutzer>)is.readObject();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			dbInitialisieren();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	 
	public void write()
	{
		try {
			FileOutputStream fs = new FileOutputStream("fos.txt");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(user);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public void dbInitialisieren()
	{
		user = new HashMap();
		FileOutputStream fs;
		
		try {

			fs = new FileOutputStream("fos.txt");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(user);
			os.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public Set<String> ausgabe()
	{
		return user.keySet();
	}
	
	
}
