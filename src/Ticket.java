/** A class to determine the amount that a driver will be fined for speeding.
 * Also generates a form letter with the ticket information.
 * Designed to be used either with a GUI or with a console using the toString() method.
 * 
 * @author Michael Meyers
 * @version 1.0
 */


import java.util.Random;
import java.text.DecimalFormat;

public class Ticket {
	
	//Declare variables
	private String name, ticketType = "PAYABLE";
	private int speed, speedLimit, ticketNum;
	private boolean schoolZone = false, workZone = false;
	private int courtDate;
	private final String PROGRAMMER_NAME = "Michael Meyers";
	

/** Constructor to create Ticket with School and Work Zone information
 * 
 * @param n User specified name
 * @param s User specified driver speed
 * @param limit User specified speed limit
 * @param sZone User specified school zone
 * @param wZone User specified work zone
 */

public Ticket(String n, int s, int limit, boolean sZone, boolean wZone) {
	name = n;
	speed = s;
	speedLimit = limit;
	schoolZone = sZone;
	workZone = wZone;
}

/** Constructor class to create Ticket without School and Work Zone information
 * 
 * @param n User specified name
 * @param s User specified driver speed
 * @param limit User specified speed limit
 */

public Ticket(String n, int s, int limit) {
	name = n;
	speed = s;
	speedLimit = limit;
}

/** Set driver's name
 * 
 * @param n User specified name (First and Last)
 */

public void setName(String n) {
	name = n;
}

/** Set ticketed driver's driving speed
 * 
 * @param s User specified speed
 */

public void setSpeed(int s) {
	speed = s;
}

/** Set posted speed limit
 * 
 * @param limit User specified speed limit
 */

public void setSpeedLimit(int limit) {
	speedLimit = limit;
}

/** Indicate whether infraction occurred in a marked school zone
 * 
 * @param sZone Set True if infraction was in school zone 
 */

public void setSchoolZone(boolean sZone) {
	schoolZone = sZone;
}

/** Indicate whether infraction occurred in a marked work zone
 * 
 * @param wZone Set True if infraction was in work zone
 */

public void setWorkZone(boolean wZone) {
	workZone = wZone;
}

/** Method to calculate the amount of the fine.
 * Fines are calculated based on how far above the speed limit the driver was going,
 * and have additional penalties if the violation was in a school or a work zone.
 * 
 * Excessive speed (defined as driver speed being more than 30mph above the limit)
 * changes the ticket type from "Payable" to "Must Appear"
 * 
 * @return Returns the amount of the fine
 */

public double calculateFine() {
	double fine = 0.0;

	//Validate that inputs are within parameters
	//Since this method must return a double to the GUI, the error message appears when trying
	//to call printNotice()
	if(!inputValidate()) { 
		return 0.000000000; 
	}
	
	else {
	
		//Calculate low speed fine amounts
		if(speed - speedLimit <= 10) {
			fine = 140.00;
			if(schoolZone) fine += 60.00;
			if(workZone) fine += 110.00;
			ticketType = "PAYABLE";
		}
		
		//Calculate high speed fine amounts
		else if(speed - speedLimit > 10 && speed - speedLimit <= 30) {
			fine = 195.00;
			if(speed - speedLimit >= 21) fine += 300.00;
			if(schoolZone) fine += 115.00;
			if(workZone) fine += 165.00;
			ticketType = "PAYABLE";
		}
		
		//Calculate excessive speed fine amounts
		else if(speed - speedLimit > 30) {
			if (speed - speedLimit < 40) fine = 450.00;
			else fine = 675.00;
			ticketType ="MUST APPEAR";
		}
		
		else {
			fine = 9999999999999.87;
			System.out.print("Error in method generateFine(), you shouldn't be seeing this");
		}
	
	}
	//fineAmount = fine; //removed because the method won't update the class variable for some reason

	return fine;
	
}

/** Method to generate the notice printout in the form of a (very long) String.
 * The String is primarily composed of string literals concatenated with variables,
 * but also calls the calculateFine() and getTicketType() methods to return their information 
 * in the string.
 * 
 * @return returns printout String
 */

public String printNotice() {
	DecimalFormat df = new DecimalFormat("0.00"); //Used to convert the returned fine to a decimal

	if(calculateFine() == 0.00) {
		String errorString = "There is an error in your entry data. "
						+ "Please check your data and try again.\n"
						+ "Driver speed and speed limit must both be positive integers.\n"
						+ "The speed limit must not exceed 80 miles per hour. \n"
						+ "The speed must be greater than the speed limit.";
		
		return errorString;
	}
	
	else {
		String notice = ("Department of Motor Vehicles\n"
				+ "Automatic Traffic Enforcement \n\n\n"
				+ "Dear " + name + ",\n\n"
				+ "Please pay the following speeding fine of $" + df.format(calculateFine()) + " to the DMV "
				+ "within 10 days of \nreceiving this notice to avoid a driver's license "
				+ "suspension.\nYou are being fined for going " + speed + " MPH in a " 
				+ speedLimit + " MPH"+ (workZone?" work":"") + (workZone && schoolZone?" and":"")
				+ (schoolZone?" school":"") + " zone." //inserts work and/or school zone if present
				+ "\n\n"
				+ "Ticket Type: " + getTicketType() + "\n"
				+ ticketTypeTest() + "\n" //the test returns an additional string depending on ticket type
				+ "Ticket Number: " + generateTicketNum() + "\n\n"
				+ "Returned checks are subject to a returned check fee of $35.00.\n\n"
				+ "Sincerely,\n" + PROGRAMMER_NAME);
		return notice;
	}
}

/** Getter method returns ticket number
 * 
 * @return returns ticket number
 */

public int getTicketNum() {
	return ticketNum;
}

/** Getter method returns the type of ticket as a String
 * 
 * @return returns ticket type
 */

public String getTicketType() {
	return ticketType;
}

/** Getter method returns the name of the driver as a String
 * 
 * @return returns name
 */

public String getName() {
	return name;
}

/** Getter method returns the speed of the driver
 * 
 * @return returns speed
 */

public int getSpeed() {
	return speed;
}

/** Getter method returns the posted speed limit
 * 
 * @return returns the speed limit
 */

public int getSpeedLimit() {
	return speedLimit;
}

/** Getter method returns whether infraction was in a school zone
 * 
 * @return returns true if violation was in school zone
 */

public boolean isSchoolZone() {
	return schoolZone;
}

/** Getter method returns whether infraction was in a work zone
 * 
 * @return returns true if violation was in work zone
 */

public boolean isWorkZone() {
	return workZone;
}

/** Private method to randomly generate a ticket number
 * 
 * @return returns ticket number as an integer
 */

private int generateTicketNum() {
	Random rand = new Random();
	
	int num = rand.nextInt(899999) + 100000;
	ticketNum = num;
	return num;
}

/** Private method to generate the court date as a random number between 1 and 31
 * Will be added to the generateNotice() as a date in October 
 * 
 * @return returns Court Date
 */

private int generateCourtDate() {
	Random rand = new Random();
	courtDate = rand.nextInt(30) + 1;
	return courtDate;
}

/** Private method to make a string. User can use console to print a string instead
 * of using a GUI or other interface
 * @return 
 * 
 * @return returns String of the ticket notice
 */

public String toString() {
	String string = printNotice();
	return string;
}

/** Method tests the ticket type. Generates a court appearance date if mandated by a MUST APPEAR ticket.
 * otherwise returns an empty string. Used to clean up the printNotice method a bit.
 * @return
 */

private String ticketTypeTest() {
	if(ticketType.equals("")) return ("ERROR null ticket type\n");
	if(ticketType.equals("MUST APPEAR")){ return "You must appear at the County Court House on 10/" 
											+ generateCourtDate() + "/2018\n";
	}
	else return "";

}

/** Method to validate user input. Conditions:
 * 1. Speed and speed limit must be greater than zero
 * 2. Speed must be greater than the speed limit
 * 3. Speed limit must be less than or equal to 80mph
 * 
 * @return returns True if input is valid
 */

private boolean inputValidate() {
	if (speed <= 0 || speedLimit <= 0) return false;
	else if (speed <= speedLimit) return false;
	else if (speedLimit > 80) return false;
	else return true;
}

}
