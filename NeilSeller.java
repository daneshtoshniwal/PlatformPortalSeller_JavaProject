import ecomm.Seller;
import ecomm.Platform;
import ecomm.Globals;
import ecomm.Product;

import java.util.ArrayList;

// Seller implemented by IMT2021037 Neil that extends Seller
public class NeilSeller extends Seller {

	// Public Constructor
	// id is passed in by the class that instantiates sub-type of seller
	public NeilSeller(String id) {
		super(id);
		// 2 Books and 2 Mobiles as of now
		// name,id,price,qty
		// Hardcoding books and mobiles and adding to ProductList of the seller
		Book book1 = new Book("Neil-Book1","NeilID1",350.5f,50);
		Mobile mob1 = new Mobile("Neil-Mobile1","NeilID2",49999,5);
		Book book2 = new Book("NeilBook2","NeilID3",560,80);
		Mobile mob2 = new Mobile("Neil-Mobile2","NeilID4",89999,4);
		ProductList = new ArrayList<Product> ();
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
	
	// ID of seller. 
	public String getID() { return myID;}
	
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
		// Iterating through all products and checking category
        for(Product p : ProductList){
            if(p.getCategory().equals(whichOne)){
                products.add(p);
            }
        }
		return products;
    }
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
		// Returning False
		return false;
	}
	
	// Private Data Members
	// Each seller has own ID and ProductList
	private String myID;
	private ArrayList<Product> ProductList;
	
}
