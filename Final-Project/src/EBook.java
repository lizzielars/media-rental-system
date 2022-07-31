/**
 * 
 * @author Elizabeth Larson
 * Date: 
 * Description: This class is the child class of Media and is utilized to hold specific information about
 * EBooks that are read in from files in xml format by the Manager class. The method toString is used to
 * format the information in xml format. The calculateRentalFee overrides the parent class and adjusts
 * the price of rental based on the publication year. 
 * 
 */

import java.util.Calendar;

public class EBook extends Media{
	
	//------------------------
	//		Attribute
	//------------------------
	
	private int numChapters;
	
	//------------------------
	//		Constructor
	//------------------------
	
	public EBook(int id, String title, int yearPublished, boolean mediaRented, int numChapters) {
		
		super(id, title, yearPublished, mediaRented);
		this.numChapters = numChapters;
	}
	
	// This constructor is used to parse the line read in from files in the Manager class and takes out just the numChapters attribute.
	public EBook(String line) {
        super(line);
        numChapters = Integer.parseInt(line.substring(line.indexOf("<numberofchapters>") + 18, line.indexOf("</numberofchapters>")));
	}
	
	//------------------------
	//		Get Method
	//------------------------
	
	public int getNumChapters() {
		
		return numChapters;
	}
	
	//------------------------
	//		Methods
	//------------------------
	
	// Method to display EBook ID information
	@Override
	public String toString() {
		return "<EBook><id>" +getId() +"</id><title>" +getTitle() + "</title><yearpublished>" + getYearPublished() +
				"</yearpublished><numberofchapters>" + getNumChapters() + "</numberofchapters><availableforrent>" + getMediaRented() + "</availableforrent></EBook>"; 
	}
	
	
	// Method to override the parent class rental fee and charge $0.10 for each chapter
	// in the E-Book. If the E-Book was published in the current year, an additional $1 is added
	// to the fee.
	@Override
	public double calculateRentalFee() {
		
		// Set the rental fee to $0.10/chapter
		double rentalFee = numChapters * 0.10;
		
		// Get the current year
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		
		// If the year published is the current year, add an additional year to the rental fee
		if(this.getYearPublished() == currYear) {
			rentalFee += 1;
		}
		
		return rentalFee;
		
	}

}
