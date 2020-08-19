package prak4client;
import prak4gemklassen.*;

import javafx.event.Event;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AnmeldungsController {

	public Client mainApplication;
	
	/**
	 * Setter for MainApplication
	 */
	public void setMain(Client client){
		mainApplication = client;
	}
	
	@FXML
	TextField tF;
	
	
	@FXML
	PasswordField pF;
	
	
	@FXML
	PasswordField pF2;
	
	@FXML
	private Label lb;
	
	@FXML
	public void enter(Event e)
	{
		if(pF.getText().equals(pF2.getText()))
		{
			Benutzer ben1 = new Benutzer(tF.getText(),pF.getText());
			System.out.println(ben1.toString());
			
			
			//checking if the user already exist 
			mainApplication.neuerBenutzer(ben1);
		}
		
		else
		{
			//lb.setText("“Passwort != Wiederholung”");
			tF.clear();
			tF.replaceSelection("Passwort != Wiederholung");
			pF.clear();
			pF2.clear();
		}
		
	}
	

	

}
