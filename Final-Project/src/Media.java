/**
 * 
 * @author Elizabeth Larson
 * Date: 05/11/21
 * Description: This class is the parent class of EBook, MovieDVD, and MusicCD and is utilized to hold general information about
 * medias that are read in from files in xml format by the Manager class. 
 * 
 */

public abstract class Media {
	
	//------------------------
	//		Attributes
	//------------------------
	
	private int id;
	private String title;
	private int yearPublished;
	private Boolean mediaRented;
	
	//------------------------
	//		Constructors
	//------------------------
	
	public Media(int id, String title, int yearPublished, Boolean mediaRented) {
		
		this.id = id;
		this.title = title;
		this.yearPublished = yearPublished;
		this.mediaRented = mediaRented;
	}
	
	// Constructor used to parse the line read in from files in the Manager class for its values
    public Media(String line) {
    	this.id = Integer.parseInt(line.substring(line.indexOf("<id>") + 4, line.indexOf("</id>")));
        this.title = line.substring(line.indexOf("<title>") + 7, line.indexOf("</title>"));
        this.yearPublished = Integer.parseInt(line.substring(line.indexOf("<yearpublished>") + 15, line.indexOf("</yearpublished>")));
        this.mediaRented = Boolean.parseBoolean(line.substring(line.indexOf("<availableforrent>") + 18, line.indexOf("</availableforrent>")));
        
    }
	
	//------------------------
	//		Get Methods
	//------------------------
	
	public int getId() {
		
		return id;
	}
	
	public String getTitle() {
		
		return title;
	}
	
	public int getYearPublished() {
		
		return yearPublished;
	}
	
	public boolean getMediaRented() {
		
		return mediaRented;
	}
	
	//------------------------
	//		Set Method
	//------------------------
		
	public void setMediaRented(boolean mediaRented) {
		
		this.mediaRented = mediaRented;
	}
	
	//------------------------
	//		Methods
	//------------------------
	
	// Method to calculate rental fee; Flat fee of $3.50
	public double calculateRentalFee() {
		
		return 3.50;
	}
	
}