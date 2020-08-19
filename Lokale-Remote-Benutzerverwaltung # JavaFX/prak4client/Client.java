package prak4client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prak4gemklassen.Benutzer;
import prak4gemklassen.NoPasswordIsGiven;
import prak4gemklassen.NoUserIdIsGiven;
import prak4gemklassen.UserAlreadyExistException;

public class Client extends Application {

	BenutzerVerwaltungAdmin bva = new BenutzerVerwaltungAdmin();
	ClientOrb clientOrb;
	private boolean lokal;

	
	// welche Controller werden zwischen sich kommunizieren
	Stage primaryStage;
	LoginController loginController;
	AnwendungsController anwendungsController;
	AnmeldungsController anmeldungsController;

	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		
		bva = new BenutzerVerwaltungAdmin();
		this.primaryStage = primaryStage;
		
		System.out.println("Wollen Sie die Lokalerdatenbank initialisieren? ja:1 oder nein:0");
		try {
			BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
			int dbInitialisieren = Integer.parseInt(din.readLine());
			if(dbInitialisieren == 1)
			{
				bva.dbInitialisieren();
				System.out.println("Initialisierung der LokalerDatenbank ...");
			}
			
			showLogin();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// Erzeugung einer LoginScene
	public void showLogin() {
	  		FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
	  		try {
		    Parent root = loader.load(); //Loads an object hierarchy from a FXML document.
		    primaryStage.setTitle("Login");
		    
		    loginController = loader.getController(); // Returns the controller associated with the root object.
		    loginController.setMain(this); // Übergabe der eigenen Referenz an deren Controller.
		     
		    Scene scene = new Scene(root);
		    primaryStage.setScene(scene);
		    primaryStage.show();
		  } 
		  catch (IOException e) {
		    e.printStackTrace();
		  }
	}
	
	/**
	 * Callback-Methode to pop up AnmeldungScene window
	 * new variable lokal, will be set = true if this method was called
	 */
	public void neuAnmeldungLokal() {
		this.lokal = true;
		
	  try {
	  		FXMLLoader loader = new FXMLLoader(getClass().getResource("anmeldung.fxml"));
		    Parent root = loader.load();
		    primaryStage.setTitle("Anmeldung");
		    
		    anmeldungsController = loader.getController();
		    anmeldungsController.setMain(this); //Übergabe der eigenen Referenz an deren Controller.
		    
		    Scene scene = new Scene(root);
		    primaryStage.setScene(scene);
		    primaryStage.show();
		  } 
		  catch (IOException e) {
		    e.printStackTrace();
		  }
	}
	
	
	/**
	 * Callback-Methode to pop up AnmeldungScene window
	 * new variable lokal, will be set = false if this method was called, to indicated whether it's a remote call
	 */
	public void neuAnmeldungRemote() {
		this.lokal = false;
		this.clientOrb = new ClientOrb();
		
	  try {
	  		FXMLLoader loader = new FXMLLoader(Client.class.getResource("Anmeldung.fxml"));
		    Parent root = loader.load();
		    primaryStage.setTitle("Anmeldung");
		    
		    anmeldungsController = loader.getController();
		    anmeldungsController.setMain(this); //Übergabe der eigenen Referenz an deren Controller.
		    
		    Scene scene = new Scene(root);
		    primaryStage.setScene(scene);
		    primaryStage.show();
		  } 
		  catch (IOException e) {
		    e.printStackTrace();
		  }
	}
	
	/**
	 * Callback-Methode to pop up AnmeldungScene window
	 * new variable lokal, will be set = true if this method was called
	 * add a user to our database
	 */
	void neuerBenutzer(Benutzer benutzer)
	{
		
		try {
			if(lokal) {
				bva.benutzerEintragen(benutzer);
				showLogin();
		    }
		    else if(!lokal) {
				clientOrb.benutzerEintragen(benutzer);
				showLogin();
			}
			
		} catch (UserAlreadyExistException | NoPasswordIsGiven | NoUserIdIsGiven |  ClassNotFoundException |  IOException e) {
	    
	    anmeldungsController.tF.clear();
	    anmeldungsController.tF.replaceSelection("Username already exist");;
	    anmeldungsController.pF.clear();
	    anmeldungsController.pF2.clear();
	    
	    
		} 
		
		
	}

	
	/*
	 * BenutzerLogin checks the username
	 * OK: switch to AnwendungsScene
	 * NotOK: display in the username field an error
	 */
	
	 void benutzerLoginLokal(Benutzer benutzer)
	 {
		 if(bva.benutzerOk(benutzer))
		 {
			 try{
				 		FXMLLoader loader = new FXMLLoader(Client.class.getResource("anwendung.fxml"));
				    Parent root = loader.load();
				    primaryStage.setTitle("Benutzerverwaltung");
				    
				    anwendungsController = loader.getController();
				    anwendungsController.setMain(this); //Übergabe der eigenen Referenz an deren Controller.
				        
				    Scene scene = new Scene(root);
				    primaryStage.setScene(scene);
				    primaryStage.show();
				  } 
				  catch (IOException e) {
				    e.printStackTrace();
				  }
			}
			else{
				// if the username is wrong or unknown dann Fehlermeldung
			    loginController.tF.clear();
			    loginController.tF.replaceSelection("Username or Password is incorrect");;
			    loginController.pF.clear();
			}
	 }
	 
	 
	 void benutzerLoginRemote(Benutzer benutzer)
	 {
		 this.clientOrb=new ClientOrb();
		 
		 if(clientOrb.benutzerOk(benutzer))
		 {
			 try{
				 		FXMLLoader loader = new FXMLLoader(Client.class.getResource("anwendung.fxml"));
				    Parent root = loader.load();
				    primaryStage.setTitle("Benutzerverwaltung");
				    
				    anwendungsController = loader.getController();
				    anwendungsController.setMain(this); // Übergabe der eigenen Referenz an deren Controller.
				        
				    Scene scene = new Scene(root);
				    primaryStage.setScene(scene);
				    primaryStage.show();
				  } 
				  catch (IOException e) {
				    e.printStackTrace();
				  }
			}
			else{
				// if the user is wrong or unknown dann Fehlermeldung
			    loginController.tF.clear();
			    loginController.tF.replaceSelection("Username or Password is incorrect");
			    loginController.pF.clear();
			}
	 }
		
	

}
