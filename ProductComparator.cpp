#include <bits/stdc++.h>
#include "ProductComparator.h"
#include "Product.h"
using namespace std;




    // Constructor that sets parameter
    ProductComparator::ProductComparator(string s){
        parameter = s;
    }
    // Operator function that sorts based on the parameter
    bool ProductComparator::operator()(Product o1, Product o2)const{
        if(parameter == "Name"){
            return(o1.getName()<o2.getName());
        }
        else if(parameter == "Price"){
            return(o1.getPrice()<o2.getPrice());
        }
        return(true);
    }
