import ecomm.Seller;
import ecomm.Platform;
import ecomm.Globals;
import ecomm.Product;
import java.util.ArrayList;

// Seller implemented by IMT2021086 Mayank that extends Seller
public class MayankSeller extends Seller {

    private String myID;
    private ArrayList<Product> ProductList; 
    // To store available products with seller

    // Public Constructor 
    public MayankSeller(String ID) {
        // Super for parent constructor
        super(ID);

        ProductList = new ArrayList<Product>();
        Book book1 = new Book("Mayank-Book1", "MayankID1", 399, 50);
        Book book2 = new Book("Mayank-Book2", "MayankID2", 499, 80);
        Mobile mobile1 = new Mobile("Mayank-Mobile1", "MayankID3", 8999, 9);
        Mobile mobile2 = new Mobile("Mayank-Mobile2", "MayankID4", 5999, 11);
        ProductList.add(book1);
        ProductList.add(book2);
        ProductList.add(mobile1);
        ProductList.add(mobile2);

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
    public String getID() {
        return myID;
    }

    // Platform it is being added to.
    public void addPlatform(Platform thePlatform) {
        // Adding the seller to platform passed
        thePlatform.addSeller(this);
    }

    // Seller to return listing of Products of specified Category
    public ArrayList<Product> findProducts(Globals.Category whichOne) {
        // Creating new arraylist of products
        ArrayList<Product> Products = new ArrayList<Product>();
        // Iterating through all products and checking category
        for (int i = 0; i < ProductList.size(); i++) {
            if (ProductList.get(i).getCategory().equals(whichOne)) {
                Products.add(ProductList.get(i));
            }
        }
        return Products;
    }

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
}
