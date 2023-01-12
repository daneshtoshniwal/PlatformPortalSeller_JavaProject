#include "Product.h"
#include<bits/stdc++.h>
using namespace std;

Product::Product(string a,string b,string c,string d){

    // Using stoi and stof for conversion to integer and float
    
    name=a;
    id=b;
    price=stof(c);
    qty=stoi(d);
}

string Product::getName(){
    return(name);
}
string Product::getProductID(){
    return(id);
}
float Product::getPrice(){
    return(price);
}
int Product::getQuantity(){
    return(qty);
}