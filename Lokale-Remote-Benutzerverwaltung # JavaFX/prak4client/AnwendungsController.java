package prak4client;

public class AnwendungsController {
	
	public Client mainApplication;
	
	/**
	 * Setter for MainApplication
	 */
	public void setMain(Client mainApp){
		mainApplication = mainApp;
	}
	
	public void abbrechen()
	{
		
		System.out.println("Abbrechen");
		System.exit(0);
	}

}
