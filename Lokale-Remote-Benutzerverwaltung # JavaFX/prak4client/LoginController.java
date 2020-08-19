package prak4client;
import prak4gemklassen.*;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	
	public Client mainApplication;
	
	/**
	 * Setter for MainApplication
	 */
	public void setMain(Client client){
		mainApplication = client;
	}
	
	@FXML
	boolean neuAnmeldung = false;
	
	@FXML
	protected TextField tF;
	
	
	@FXML
	protected PasswordField pF;
	
	@FXML
	private boolean lokal = false;
	
	
	public void lokal()
	{
		this.lokal = true;
		System.out.println("Lokal ist ausgewählt");
	}
	
	// kann man sich sparen
	public void remote()
	{
		this.lokal = false;
		System.out.println("remote ist ausgewählt");
	}
	
	
	public void neuan()
	{
		neuAnmeldung = !neuAnmeldung;
		//System.out.println("neuAnmeldung=" + neuAnmeldung);
	}
	
	
	@FXML
	public void enter(Event e)
	{
		
		if(lokal)
		{
			if(neuAnmeldung){
				//if the check box is 1 then go to the methode neuAnmeldung in mainApplication 
				mainApplication.neuAnmeldungLokal();
			}
			else{
				// if the checkbox not crossed then check the information and prosedue
				Benutzer ben1 = new Benutzer(tF.getText(),pF.getText());
				System.out.println(ben1.toString());
				mainApplication.benutzerLoginLokal(ben1);
			}
		}
		else if(!lokal) {
			if(neuAnmeldung){
				mainApplication.neuAnmeldungRemote();
			}
			else{
				Benutzer ben1 = new Benutzer(tF.getText(),pF.getText());
				System.out.println(ben1.toString());
				mainApplication.benutzerLoginRemote(ben1);
			}
		}
		
	}
	

	

}
