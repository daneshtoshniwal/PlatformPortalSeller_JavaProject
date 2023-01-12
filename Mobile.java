import ecomm.Product;
import ecomm.Globals;

// Mobile is derived class of product
public class Mobile extends Product {

    // Public Constructor
    public Mobile(String NAME, String ID, float PRICE, int QTY) {
        name = NAME;
        id = ID;
        price = PRICE;
        qty = QTY;
    }

    // Implementing the abstract functions of the Product Class
    public Globals.Category getCategory(){
        return Globals.Category.Mobile;
    }
    public String getName(){
        return name;
    }
    public String getProductID(){
        return id;
    }
    public float getPrice(){
        return price;
    }
    public int getQuantity(){
        return qty;
    }
    public void decQuantity(int dec){
        qty = qty - dec;
    }
}
