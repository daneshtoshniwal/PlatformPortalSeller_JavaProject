#include <bits/stdc++.h>
#include "Product.h"
using namespace std;


// Comparator for Product Class
class ProductComparator{
    // Private data member for parameter - Name or Price
    private:
    string parameter;

    public:
    // Constructor that sets parameter
    ProductComparator(string s);
    // Operator function that sorts based on the parameter
    bool operator()(Product o1, Product o2)const;
};