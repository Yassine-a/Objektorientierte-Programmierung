package Praktikum3;

public interface BenutzerVerwaltung {
	public void benutzerEintragen(Benutzer benutzer) throws UserAlreadyExistException, NoPasswordIsGiven, NoUserIdIsGiven;
	public boolean benutzerOk(Benutzer benutzer);
}
