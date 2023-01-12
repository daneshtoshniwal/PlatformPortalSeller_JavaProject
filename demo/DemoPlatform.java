package demo;
import ecomm.Globals;
import ecomm.Platform;
import ecomm.Seller;
import java.util.*;
import java.io.*;
import ecomm.Product;

// DemoPlatform is derived from Platform that was given in ecomm Package
public class DemoPlatform extends Platform {


	// Public Constructor that initialises the SellerList
	public DemoPlatform(){
		SellerList = new ArrayList<Seller>();		
	}
	
	// GIVEN COMMENTS BY SIR:
	// checks the incoming messages (in file PortalToPlatform.txt) 
	// for unprocessed requests
	// Invokes methods on Sellers as needed and writes out responses 
	// into file PlatformToPortal.txt)
	// This could be invoked by main when processing command line or in a timer
	
	
	@Override
	// Used by Seller to attach to Platform		
	public boolean addSeller(Seller aSeller) {
		// Adding the seller to the SellerList
		SellerList.add(aSeller);
		return false;
	}

	@Override
	// Method to process requests- called when User types Check in console
	public void processRequests() {
		
		// Try Catch Block for error handling
		try{

			// Creating Scanner to read the file "PortalToPlatform.txt"
			File input = new File("PortalToPlatform.txt");
			Scanner myReader = new Scanner(input);
			
			// Going through every line in the file where each line is a request from Portal
			while(myReader.hasNextLine()){

				// Converting the line into an ArrayList of Strings for easy access
				String data = myReader.nextLine();
				String[] datas = data.split(" ");
				ArrayList <String> Requests = new ArrayList <String>(Arrays.asList(datas));
				
				// GIVEN COMMENTS BY SIR:
				// Receives requests from the portal (see below), and passes those on to the registered sellers. Gets
				// their responses, and sends the consolidated response to the portal
				// Should route requests to appropriate seller based on PortalID in the incoming message
			
				// Case 1 - Request is of type Start
				if(Requests.get(2).equals("Start")){
					
					// Making string s according to FileFormat.txt 
					// Response is of type PortalID + RequestID + category 1 + category 2 .....
					// On adding new Category in Globals, it will also be printed here
					String s = Requests.get(0)+" "+Requests.get(1);
					for(Globals.Category x:Globals.Category.values()){
						s = s+" "+x;
					}

					// Writing to the PlatformToPortal File in append mode 
					FileWriter myWriter = new FileWriter("PlatformToPortal.txt",true);
					myWriter.write(s);
					myWriter.write("\n");
					// myWriter.write("\n");
					myWriter.close();
				}
				// Case 2 - Request if of type List
				else if(Requests.get(2).equals("List")){
					// If List Book
					if(Requests.get(3).equals("Book")){

						// Writer to write to PlatformToPortal file
						FileWriter myWriter = new FileWriter("PlatformToPortal.txt",true);

						// Going through all the Sellers and getting products of each seller of "BOOK" category
						for(int i=0;i<SellerList.size();i++){
							ArrayList <Product> ProductList = SellerList.get(i).findProducts(Globals.Category.Book);
							for(Product p:ProductList){
								// Response is of type PortalID + RequestID + Name + ID + Price + Qty
								String s = Requests.get(0)+" "+Requests.get(1)+" "+p.getName()+" "+p.getProductID()+" "+p.getPrice()+" "+p.getQuantity();
								myWriter.write(s);
								myWriter.write("\n");
							}
						}
						// myWriter.write("\n");
						myWriter.close();
					}
					// If List Mobile
					else if(Requests.get(3).equals("Mobile")){

						// Writer to write to PlatformToPortal file
						FileWriter myWriter = new FileWriter("PlatformToPortal.txt",true);

						// Going through all the Sellers and getting products of each seller of "MOBILE" category
						for(int i=0;i<SellerList.size();i++){
							ArrayList <Product> ProductList = SellerList.get(i).findProducts(Globals.Category.Mobile);
							for(Product p:ProductList){
								// Response is of type PortalID + RequestID + Name + ID + Price + Qty
								String s = Requests.get(0)+" "+Requests.get(1)+" "+p.getName()+" "+p.getProductID()+" "+p.getPrice()+" "+p.getQuantity();
								myWriter.write(s);
								myWriter.write("\n");
							}
						}
						// myWriter.write("\n");
						myWriter.close();
					}
				}
				// Case 3 - Request if of type Buy
				else if(Requests.get(2).equals("Buy")){

					// Flag to check if given ProductID exists or not
					int flag=0;

					// Getting ProductID and qty from the Request Line
					// Boolean variable to check if success or failure
					String ProductID = Requests.get(3);
					int quantity=Integer.parseInt(Requests.get(4));
					boolean ans = false;

					// Going through all Sellers because any seller could have product
					for(Seller s:SellerList){
						// Getting All Books and Mobiles of each Seller
						ArrayList <Product> Products = s.findProducts(Globals.Category.Book);
						ArrayList <Product> Mobiles = s.findProducts(Globals.Category.Mobile);
						Products.addAll(Mobiles);
						// Products has all books and mobiles since we appended Mobiles to Book using addAll
						for(Product p:Products){
							// If given ProductID exists, we call seller buy function as mentioned in question
							// and set flag to 1 and break out of the loop
							if(p.getProductID().equals(ProductID)){ans = s.buyProduct(ProductID,quantity);flag=1;break;}
						}
						if(flag==1){break;}
					}

					// Now we have our result in boolean variable ans and we will write response in 
					// PlatformToPortal file
					FileWriter myWriter = new FileWriter("PlatformToPortal.txt",true);
					
					
					if(ans==false){

						// If ans is false, there can be two cases, product does not exist or quantity is 
						// limited. In both cases response is PortalID + RequestID + Failure 
						
						if(flag==0){
							// myWriter.write("Sorry but the given product does not exist\n");
							myWriter.write(Requests.get(0)+" "+Requests.get(1)+" Failure\n");
							// myWriter.write("\n");
							myWriter.close();
						}
						else{
							// myWriter.write("Sorry but the product has limited quantity\n");
							myWriter.write(Requests.get(0)+" "+Requests.get(1)+" Failure\n");
							// myWriter.write("\n");
							myWriter.close();
						}
					}
					else{
						
						// If ans is true, the response is  PortalID + RequestID + Success
						// myWriter.write("Congrats! Your order has been placed successfully :)\n");
						myWriter.write(Requests.get(0)+" "+Requests.get(1)+" Success\n");
						// myWriter.write("\n");
						myWriter.close();
					}
				}
			}
			// Closing the Scanner after reading file
			myReader.close();
		}
		catch(IOException e){
			// Catch block for error handling
			System.out.println("Error occured");
			e.printStackTrace();
		}
		
		// Try catch block for I/O error handling
		try{
			// Opening and closing file to clear the contents
			PrintWriter writer = new PrintWriter("PortalToPlatform.txt");
			writer.print("");
			writer.close();
		}
		catch(IOException e){
			System.out.println("Error occured");
			e.printStackTrace();
		}
		
	}
	// Private data member that maintains list of sellers.
	private ArrayList<Seller> SellerList;

}		