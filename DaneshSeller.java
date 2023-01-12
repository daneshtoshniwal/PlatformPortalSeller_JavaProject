import ecomm.Seller;
import ecomm.Platform;
import ecomm.Globals;
import ecomm.Product;
import java.util.ArrayList;

// Seller implemented by IMT2021094 Danesh that extends Seller
public class DaneshSeller extends Seller {
	
	// Public Constructor
	// Id is passed in by the class that instantiates sub-type of seller
	public DaneshSeller(String id) {
		// Calling constructor of parent class
		super(id);
		
		// Hardcoding books and mobiles and adding to ProductList of the seller
		Book book1 = new Book("Danesh-Book1","DaneshID1",400,100);
		Mobile mob1 = new Mobile("Danesh-Mobile1","DaneshID2",14000,10);
		Book book2 = new Book("Danesh-Book2","DaneshID3",200,120);
		Mobile mob2 = new Mobile("Danesh-Mobile2","DaneshID4",16000,8);
		ProductList = new ArrayList<Product>();
		ProductList.add(book1);
		ProductList.add(book2);
		ProductList.add(mob1);
		ProductList.add(mob2);
	}
	// Extra function -not needed but implemented
	// Sellers can use this function to add products to their store 
	public void addProduct(String name,String id,float price, int qty,Globals.Category c){
		if(c.equals(Globals.Category.Book)){
			Book b = new Book(name,id,price,qty);
			ProductList.add(b);
		}
		else if(c.equals(Globals.Category.Mobile)){
			Mobile m = new Mobile(name,id,price,qty);
			ProductList.add(m);
		}
	}
	// ID of seller
	public String getID() {return myID;}
	
	// Platform it is being added to.
	// Should in turn add itself to the Platform (with addSeller)
	public void addPlatform(Platform thePlatform){
		// Adding the seller to platform passed
        thePlatform.addSeller(this);
    }
	
	// Seller to return listing of Products of specified Category
	public ArrayList<Product> findProducts(Globals.Category whichOne){
		// Creating new arraylist of products
		ArrayList<Product> products = new ArrayList<Product>();
        for(Product p : ProductList){
			// Iterating through all products and checking category
            if(p.getCategory().equals(whichOne)){
                products.add(p);
            }
        }
		return products;
    }
	
	// GIVEN COMMENTS BY SIR:
	// User wants to buy specified quantity of productID
	// Return true if transaction succeeds, false otherwise. 
	// Transaction fails if incorrect productID or quantity exceeds available inventory

	// Buy function that is called by the Platform 
	public boolean buyProduct(String productID, int quantity){

		// Iterating through all products
		for(Product p:ProductList){
			// Checking if qty is more than required qty if the ID matches
			if(p.getProductID().equals(productID) && p.getQuantity()>=quantity){
				// Decreasing qty of the the product accordingly and returning true
				p.decQuantity(quantity);
				return true;
			}
		}		
		// Failure if qty is less
		return false;
	}
	
	// Private Data Members
	// Each seller has own ID and ProductList
	private String myID;
	private ArrayList<Product> ProductList;	
}
