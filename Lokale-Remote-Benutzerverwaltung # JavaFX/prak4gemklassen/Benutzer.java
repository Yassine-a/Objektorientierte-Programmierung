package prak4gemklassen;

import java.io.Serializable;

public class Benutzer implements Serializable {

	public String userId;
	public char[] passWort;
	
	
	Benutzer(){
		this.userId = ""; this.passWort = "".toCharArray();
		}
	
	public Benutzer(String userId, String passWort) {
		this.userId = userId;
		this.passWort = passWort.toCharArray();
	}
	
	
	public boolean equals(Object obj) throws NullPointerException{
		if(obj == null) return false;
		return (obj instanceof Benutzer &&	
								this.userId.equals(((Benutzer)obj).userId) &&
								String.copyValueOf(this.passWort).equals(String.copyValueOf(((Benutzer) obj).passWort)));
	}
	
	public String toString() {
	return ( "Username: "+ this.userId + " Passwort = " + String.copyValueOf(this.passWort));										
	}
}
