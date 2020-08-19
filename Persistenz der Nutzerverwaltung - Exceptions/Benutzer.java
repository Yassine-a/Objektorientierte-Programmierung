package Praktikum3;

import java.io.Serializable;

public class Benutzer implements Serializable {

	String userId;
	char[] passWort;
	
	
	Benutzer(){
		this.userId = ""; this.passWort = "".toCharArray();
		}
	
	Benutzer(String userId, String passWort) {
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
