/**
 * 
 * @author Elizabeth Larson
 * Date: 
 * Description: This class is the child class of Media and is utilized to hold specific information about
 * MusicCD that are read in from files in xml format by the Manager class. The method toString is used to
 * format the information in xml format. 
 * 
 */

import java.util.Calendar;

public class MusicCD extends Media{
	
	//------------------------
	//		Attributes
	//------------------------
	
	private int lengthInMinutes;
	
	//------------------------
	//		Constructors
	//------------------------
	
	public MusicCD(int id, String title, int yearPublished, boolean mediaRented, int lengthInMinutes) {
		
		super(id, title, yearPublished, mediaRented);
		this.lengthInMinutes = lengthInMinutes;
		
	}
	
	public MusicCD(String line) {
        super(line);
        lengthInMinutes = Integer.parseInt(line.substring(line.indexOf("<lengthinminutes>") + 17, line.indexOf("</lengthinminutes>")));
	}
	
	//------------------------
	//		Get Method
	//------------------------
	
	public int getLengthInMinutes() {
		
		return lengthInMinutes;
	}
	
	//------------------------
	//		Methods
	//------------------------
	
	// Method to print out test in xml format
	@Override
	public String toString() {
		return "<MusicCD><id>" +getId() +"</id><title>" +getTitle() + "</title><yearpublished>" + getYearPublished() +
				"</yearpublished><lengthinminutes>" + getLengthInMinutes() + "</lengthinminutes><availableforrent>" + getMediaRented() + "</availableforrent></MusicCD>";
	}
	// Method to calculate the rental fee by charging $0.02 for each minute in the cd. If 
	// the cd was released this year, an additional $1 is added to the rental fee.
	@Override
	public double calculateRentalFee() {
		
		// Calculate the rental fee of $0.02/minute of the cd
		double rentalFee = (lengthInMinutes * 0.02);
		
		// Gets the current year
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		
		// If the year published is the current year, add a dollar to the rental fee
		if(this.getYearPublished() == currYear) {
			rentalFee += 1;
		}
		
		return rentalFee;

	
	}
	

}