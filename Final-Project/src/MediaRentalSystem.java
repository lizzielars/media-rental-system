import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Elizabeth Larson
 * Date: 
 * Description: This class implements in the methods in Manager and utilizes the Media objects.
 * It displays a menu and based on the user’s selection, it will load media objects from files, 
 * find a specific media object, rent a specific media object, or exit the program. If the user 
 * does not exit the program, the menu will loop and the user will be prompted to enter another menu option.
 * 
 * 
 */

public class MediaRentalSystem {
	
	//------------------------
	//		Attributes
	//------------------------
	
	private Manager media;
	private Scanner scan = new Scanner(System.in);;
	public String mediaFileLocation = null;
	
	// Method to display a welcome message to the screen
	public void welcomeMessage() {
		System.out.println("\t\tWelcome to the Media Rental System");
		System.out.println("\t\t**********************************");
		System.out.println();
	}
	
	// Method to display menu options for the user to select from
	public void displayMenuOptions() {
		System.out.println("1. Load Media Objects");
		System.out.println("2. Find Media Object");
		System.out.println("3. Rent Media Object");
		System.out.println("4. Exit Program");
	}
	
	// Method to process user's menu selection
	public void processMenuOptions(int menuSelection) {
		
		// Call switch method to implement menu choices
		switch(menuSelection) {
		
		case 1:
			try {
			// Display prompt to enter a location to load the media file from
			System.out.println("\nPlease enter the location of the Media objects: ");
			
			// Read in file location
			mediaFileLocation = scan.next();
			scan.nextLine();
			
			// Read in files
			media= new Manager(mediaFileLocation);

			}
			// If no file found, throw exception
			catch(FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
			System.out.println();
			break;
			
		case 2:
			// Display prompt to enter a title of the media the user would like to find
			System.out.println("\nPlease enter the title of the media you would like to find:");
			
			// Read in the title
			String mediaTitle = scan.next();
			scan.nextLine();
			
			// Call method to find the media object
			media.findMediaObject(mediaTitle);
			break;
			
		case 3:
			try {
				// Display prompt for user to enter the id of the media object they would like to rent
				System.out.println("\nPlease enter the ID of the media you would like to rent:");
				
				// Read in user's id choice
				int mediaID = scan.nextInt();
				
				// Call method to rent the media object
				media.rentMediaObject(mediaID);
				
				try {
					// Overwrite the media file if the user rents it
					media.overWriteMediaFiles(mediaFileLocation);
				}
				//Throw an exception is there is an error overwriting file
				catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			// Throw an exception if files were not loaded before renting media object
			catch(NullPointerException e) {
				System.out.println("\n*No directory loaded. Please load a directory before renting a Media object.*");
			}
			System.out.println();
			break;
			
		case 4:
			// Display good-bye message and exit program.
			System.out.println("\n\tThank you for using the Media Rental System. Goodbye!");
			System.out.println("\t*****************************************************");
			System.exit(0);
			
		default:
			// Display message to enter a valid menu option.
			System.out.println("\n*Please enter a valid menu option (Numbers 1-4)*\n");
		}	
	}

	public static void main(String[] args) {
		
		// Create a new instance of Manager
		MediaRentalSystem media= new MediaRentalSystem();  
		
		// Display welcome message
		media.welcomeMessage();		
		
		// Initialize menu choice to 0 
		int menuChoice = 0;

		// Loop the menu while the user does not exit the program.
		do {
			// Display menu options
			media.displayMenuOptions();
			
			// Display prompt for user to enter their menu choice
			System.out.println("\nPlease enter your menu choice (1-4):");
					
			// Read in the user's choice
			menuChoice = media.scan.nextInt();
			
			// Process the user's choice
			media.processMenuOptions(menuChoice);
			
				
		}while (menuChoice != 4);	
		
		// close the Scanner
		media.scan.close();

	}

}


