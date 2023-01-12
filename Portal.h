// .h has all the defintions of the constructor and methods used 
#include <bits/stdc++.h>
using namespace std;

#ifndef PORTAL_H
#define PORTAL_H
class Portal {

    // NOTE: Here processUserCommand and checkResponse are pure virtual functions
    // used to implement abstract class

	// Invoked by main or driver class
	// sends command to Platform (by writing to PortalToPlatform)
	// Each command line in the file should have a PortalID as the first token 
	// and a RequestID as second token. 
	// PortalID is unique to each instance of Portal
	// Each request from a portal should have a unique ID
	virtual void processUserCommand(string command)=0;
	
	// checks for pending responses (in PortalToPlatform)
	// Displays response
	virtual void checkResponse()=0;


};
#endif