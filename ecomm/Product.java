package ecomm;

public abstract class Product{

	// common queries to get category, unique name, price, and number available
	public abstract Globals.Category getCategory();
	public abstract String getName();
	public abstract String getProductID();
	public abstract float getPrice();
	public abstract int getQuantity();

	// Extra function to decrease qty after Buy Order
	// Can also use downcasting instead of creating this extra function
	// We can downcast the Product to Book / Mobile and then use their functions so that
	// we don't need to modify this
	// Or else we can create two vectors for each Seller- one for book and one for mobile
	// This has been done because it is easy to understand and to avoid compiler warnings on downcasting
	public abstract void decQuantity(int dec);
	
	// Protected data members.
	protected String name;
	protected String id;
	protected float price;
	protected int qty;
}


