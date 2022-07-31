/**
 * 
 * @author Elizabeth Larson
 * Date: 
 * Description: This class is utilized to read in media files and store them 
 * in an ArrayList. It then stores the media in their correct objects based on 
 * the name of each file, which contains the class name and the media id number.
 * The files must be in xml format on one line each and must not be empty. This class
 * contains methods that display all read in data in xml format, finds a media object,
 * rents a media object, and overwrites files if the media object is rented.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
	
	//------------------------
	//		Attribute
	//------------------------
	
	private ArrayList<Media> medias;

	//------------------------
	//	Default Constructor
	//------------------------

	public Manager() {
			
		// Initialize empty medias list
		medias = new ArrayList<Media>();
	}
	
	//------------------------
	//		Methods
	//------------------------
	
	
	// Method to load all media files from directory; assumes file name convention 
	//starts with media type EBook, MovieDVD, or MusicCD followed by id.
	//If the directory is not found, it will throw an exception.
	public Manager(String directory) throws FileNotFoundException {
	       
        // Initialize empty medias list
        medias = new ArrayList<Media>();
       
        // Create a File object for the directory
        File directoryPath = new File(directory);
       
        // Get list of all files and directories
        File fileslist[] = directoryPath.listFiles();
       
        // If the filelist is empty, throw an exception.
        if (fileslist == null)
            throw new FileNotFoundException("\n*No directory found. Please enter a valid directory with files containing"
            		+ " EBook, MusicCD, or MovieDVD*\n");
       
        // Declares local variables
        Media media = null;
        String line;
        Scanner scan;
       
        // Process each media file
        for (File file : fileslist) {
           
            // Parse files whose filename starts with "EBook", "MusicCD", or "MovieDVD"
            if (file.getName().contains("EBook") || file.getName().contains("MusicCD") || file.getName().contains("MovieDVD")) {

                // Open and read line (assumes whole object is stored on single line)
                scan = new Scanner(file);
                line = scan.nextLine();
               
                // If MusicCD object then call MusicCD constructor
                if (file.getName().contains("MusicCD")) {
                    media = new MusicCD(line);
                    	
                }
                // If MovieDVD object then call MovieDVD constructor
                if (file.getName().contains("MovieDVD")) {
                    media = new MovieDVD(line); 
                	
            	}
                // If EBook object then call EBook constructor
            	if (file.getName().contains("EBook")) {
            		media = new EBook(line);
            	}
            	
            	// Add each media to media arraylist
                medias.add(media);
            }     
        }
        // Print out confirmation that files were loaded.
        System.out.println("\nFiles loaded.\n");
        
        // Call method to display all medias
        displayAllMedias();
        
    }
	
	// Display all stored medias on console
  	public void displayAllMedias() {
  		
  		//for all Media objects display the xml tag data
  		for(Media media: medias) {
  			System.out.println(media.toString());
  		}
  	}
  	
    // Method to find all media objects for a specific title and returns that list based on
  	// user input title
    public void findMediaObject(String title) {
    	
    	// Initialize a boolean to keep try if title is found
    	boolean titleFound = false;
    	
    	// Search through all media in medias ArrayList
    	for(Media media : medias) {
    		
    		// If the user input title is found in the arraylist, print out its information
    		if(media.getTitle().contains(title)) {
    			
    			System.out.println();
    			System.out.println(media.toString());
    			System.out.println();
    			// Sets titleFound to true so an error message does not display.
    			titleFound = true;
    		}    	  
    	}
    	// If no title found, display error message
    	if(!titleFound ) {
    		System.out.println("\nNo titles found.\n");
    	}
    }
    
    // Method to rent a media if it is not already rented based on user input id
    public void rentMediaObject(int id) throws NullPointerException{
   
    	// Initialize a boolean to keep try if id is found
    	boolean idFound = false;
    	
    	// Search through all media in medias ArrayList
    	for(Media media : medias ) {
    		
    		// Throw an exception is no directory was loaded before renting a media.
    		if(media == null) {
        		throw new NullPointerException("\n*No directory loaded. Please load a directory before renting a Media object.*\n");
        	}
    		// If the user input id is found and the media is not already rented
    		if(media.getId()==id && media.getMediaRented()){
    			// Set the rental availability of object to false
    			media.setMediaRented(false);
    			// Dosplay confirmation and rental fee due.
    			System.out.println("\nID: " + media.getId() + " has been rented.");
    			System.out.printf("\nAmount Due:$ %.02f", media.calculateRentalFee());
    			System.out.println();
    			
    			// Set idFound to true so error message does not display.
    			idFound = true;
    		}
    		// If the object is already rented, display a message saying so
    		else if(media.getId()==id && !media.getMediaRented()){
    			System.out.println("\nID: " + media.getId() + " is already rented out.");
    			// Set idFound to true so error message does not display.
    			idFound = true;
    		}
    	}
    	// If no id found, display error message
    	if(!idFound) {
    		System.out.println("ID not found.");
    	}
    	
    }
    
    // Method to create (or overwrites) a file for each media object in medias attribute in the given directory
 	public void overWriteMediaFiles(String directory) throws IOException{
 		
 		PrintWriter out = null;
 		
 		// For all Media objects create files using media type and id value as filename
 		for(int i = 0; i < medias.size(); i++) {
 			// Creates filename
 			String filename = directory + "/" + medias.get(i).getClass().getSimpleName() + "-" +
 					medias.get(i).getId() + ".txt";
 			// Create/overwrite file
 			out = new PrintWriter(new FileWriter(filename)); 
 			// Writes the media data in xml to file
 			out.println(medias.get(i).toString()); 
 			// Flush and close files
 			out.flush();
 			out.close();
 		}
 	}
}
