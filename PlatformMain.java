import demo.DemoPlatform;
import ecomm.Platform;
import ecomm.Seller;
import java.util.*;

public class PlatformMain {

	public static void main(String[] args) {

		
		Platform pf = new DemoPlatform();  // replace with appropriate derived class
		
		// create a number of sellers (of different sub-types of Seller) -- for Neil, Danesh, Mayank
		// Assign a name (sellerID) to each of them.
		
		// replace each of the XYZ below with the derived class name of one of the
		// team members.
		
		// Creating sellers for each team member and adding them to the Platform
		Seller s1 = new NeilSeller("Seller1"); 
		s1.addPlatform(pf);
		Seller s2 = new DaneshSeller("Seller2");
		s2.addPlatform(pf);
		Seller s3 = new MayankSeller("Seller3");
		s3.addPlatform(pf);


		/*
		// keep reading from System.in
		// If "Check" typed in
		// invoke 
			pf.processRequests();
		*/
		
		Scanner sc = new Scanner(System.in);
		while(true){
			// Scanner to take input from console
			String s;
			s = sc.nextLine();
			// If Check is typed in, we call processRequests function of Platform
			if(s.equals("Check")){
				pf.processRequests();
			}

			if(s.equals("End")){
				//Creating this if condition, so that the Scanner sc can be closed outside the while loop
				break;
			}
		}
		sc.close();
		
	}

}
