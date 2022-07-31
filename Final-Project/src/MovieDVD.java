/**
 * 
 * @author Elizabeth Larson
 * Date: 
 * Description: This class is the child class of Media and is utilized to hold specific information about
 * MovieDVD that are read in from files in xml format by the Manager class. The method toString is used to
 * format the information in xml format. 
 * 
 */

public class MovieDVD extends Media {
	
	//------------------------
	//		Attribute
	//------------------------
	
	private double sizeInMegabytes;
	
	//------------------------
	//		Constructors
	//------------------------
	
	public MovieDVD(int id, String title, int yearPublished, boolean mediaRented, double sizeInMegabytes) {
		
		super(id, title, yearPublished, mediaRented);
		this.sizeInMegabytes = sizeInMegabytes;
	}
	
	// Constructor to parse read in line from Manager class and store the values with their variables
	public MovieDVD(String line) {
        super(line);
        sizeInMegabytes = Double.parseDouble(line.substring(line.indexOf("<sizeinmegabytes>") + 17, line.indexOf("</sizeinmegabytes>")));
	}
	
	//------------------------
	//		Get Method
	//------------------------
	
	public double getSizeInMegabytes() {
		
		return sizeInMegabytes;
	}
	
	//------------------------
	//		Method
	//------------------------
	
	// Method to print out test in xml format
	@Override
	public String toString() {
		return "<MovieDVD><id>" +getId() +"</id><title>" +getTitle() + "</title><yearpublished>" + getYearPublished() +
				"</yearpublished><sizeinmegabytes>" + getSizeInMegabytes() + "</sizeinmegabytes><availableforrent>" + getMediaRented() + "</availableforrent></MovieDVD>";
	}

}
