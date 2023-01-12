#include <bits/stdc++.h>
#include "Portal.h"
#include "Product.h"
using namespace std;


#ifndef DEMOPORTAL_H
#define DEMOPORTAL_H


class DemoPortal:public Portal{

    public:
    DemoPortal(string a,int b);
    void processUserCommand(string command);
    void checkResponse();

    private:
    string PortalID;
    int RequestID;
    map<string,string> sorter; //RequestID to Name/Price
    vector<Product> products;
};

#endif