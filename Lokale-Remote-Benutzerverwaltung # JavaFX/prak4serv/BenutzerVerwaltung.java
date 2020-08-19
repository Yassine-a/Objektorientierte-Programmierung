package prak4serv;
import java.io.IOException;

import prak4gemklassen.*;

public interface BenutzerVerwaltung {
	public void benutzerEintragen(Benutzer benutzer) throws UserAlreadyExistException, NoPasswordIsGiven, NoUserIdIsGiven, IOException, ClassNotFoundException;
	public boolean benutzerOk(Benutzer benutzer);
}
