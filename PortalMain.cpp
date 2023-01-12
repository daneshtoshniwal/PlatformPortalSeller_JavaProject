#include <bits/stdc++.h>
#include<fstream>
#include "DemoPortal.h"
using namespace std;

int main(){
    
    // Initialising PortalID and RequestId
    string PortalID = "Portal2";
    int RequestID = 1;
    // string Portal2="Portal2"
    // Creating object of DemoPortal class
    DemoPortal myPortal = DemoPortal(PortalID,RequestID);

    // Taking input from user
    while(true){
        string command;
        getline(cin,command);
        // If check type in, call checkResponse
        if(command=="Check"){
            myPortal.checkResponse();
        }
        // Else call processUserCommand
        else{
            myPortal.processUserCommand(command);
        }
    }
}