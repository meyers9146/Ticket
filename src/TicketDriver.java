import java.util.Scanner; //Scanner object to take user input

public class TicketDriver {
	public static void main(String[] args) {
		java.util.Scanner input = new Scanner(System.in);
		
		//Print header
		System.out.println("Ticket Manager\n\n");
		
		//Create variables to store user input
		String name;
		char yesNo;
		int speed;
		int speedLimit;
		boolean isSchoolZone, isWorkZone;
		
		char createTicket = 'Y'; //Sentinel value for ticket generation loop
				
	while (!(createTicket == 'N' || createTicket == 'n')){
		
		//Obtain input from user; store in Ticket
		//First get violator name
		System.out.print("Enter the name of the violator: ");
			name = input.nextLine();
			
		//Get violator speed
		System.out.print("Enter the speed of the violator (>0): ");
			speed = input.nextInt();
			//Validate
			while(speed <= 0) {
				System.out.print("Speed must be greater than zero. "
						+ "Enter the speed of the violator (>0): ");
				speed = input.nextInt();
			}
			
		//Get speed limit	
		System.out.print("Enter the speed limit (>0,<=80): ");
			speedLimit = input.nextInt();
			//Validate
			while(speedLimit <= 0 || speedLimit >= 80) {
				System.out.print("Invalid input. Speed limit must be between 0 and 80. " 
							+ " Enter the speed limit (>0, <= 80): ");
				speedLimit = input.nextInt();
			}
			
			
		//Clear buffer to enter characters for work zone and school zone
			input.nextLine();
			
		//Get school zone	
		System.out.print("Was this in a school zone? Y/N ");
			yesNo = input.nextLine().charAt(0);
			//Validate
			while (!(yesNo == 'Y' || yesNo == 'y' || yesNo == 'N' || yesNo == 'n')) {
				System.out.print("Invalid entry. Please enter Y/N: ");
				yesNo = input.next().charAt(0);
			}
			if(yesNo == 'Y' || yesNo == 'y') {
				isSchoolZone = true;
			}
			else {
				isSchoolZone = false;
			}
		
		//Get work zone
		System.out.print("Was this in a work zone? Y/N ");
			yesNo = input.nextLine().charAt(0);
			//Validate
			while (!(yesNo == 'Y' || yesNo == 'y' || yesNo == 'N' || yesNo == 'n')) {
				System.out.print("Invalid entry. Please enter Y/N: ");
				yesNo = input.next().charAt(0);
			}
			if(yesNo == 'Y' || yesNo == 'y') {
				isWorkZone = true;
			}
			else {
				isWorkZone = false;
			}
			
		//Create ticket object and generate notice
			Ticket ticket1 = new Ticket(name, speed, speedLimit, isSchoolZone, isWorkZone);
		
			System.out.println("\n\n" + ticket1.toString());
			
		//Prompt to enter another ticket. Validate to ensure Y/y/N/n entry
			System.out.print("\nDo you want to enter another ticket? Y/N ");
			//Validate single character entered
			String tempString = input.nextLine();
				while (tempString.length() > 1) {
					System.out.print("Correct values are Y,y,N,n. Do you want to enter another ticket? Y/N ");
						tempString = input.nextLine();
				}
			createTicket = tempString.charAt(0);
			//Validate correct character entered (Y/y/N/N)
			while (!(createTicket == 'Y' || createTicket == 'y' || createTicket == 'N' 
					|| createTicket == 'n')){
				System.out.println("Invalid input. Do you want to enter another ticket? Y/N ");
				tempString = input.nextLine();
				createTicket = tempString.charAt(0);
			}
		}
	
	//When user enters N or n, close Scanner and print closing message
	input.close();
	System.out.println("\nClosing Ticket Manager");
	
	}
}