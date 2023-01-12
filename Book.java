import ecomm.Product;
import ecomm.Globals;

// Book is derived class of Product
public class Book extends Product {
    
    // Public constructor 
    public Book(String NAME,String ID,float PRICE,int QTY){
        name=NAME;
        id=ID;
        price=PRICE;
        qty=QTY;
    }

	// Implementing the abstract functions of the Product Class
	public  Globals.Category getCategory(){
        return Globals.Category.Book;
    }
	public  String getName(){
        return name;
    }
    public  String getProductID(){
        return id;
    }
    public  float getPrice(){
        return price;
    }
    public  int getQuantity(){
        return qty;
    }
    public void decQuantity(int dec){
        qty=qty-dec;
    }
}