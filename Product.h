#include<bits/stdc++.h>
using namespace std;

#ifndef PRODUCT_H
#define PRODUCT_H

class Product{

    public:
    // Public Constructor that sets the data members
    Product(string a,string b,string c,string d);

    string getName();
    string getProductID();
	float getPrice();
    int getQuantity();

    
   
    protected:
	string name;
	string id;
	float price;
	int qty;

};


#endif