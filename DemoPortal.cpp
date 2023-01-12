#include <bits/stdc++.h>
#include "DemoPortal.h"
#include "Portal.h"
#include "ProductComparator.h"
using namespace std;

DemoPortal::DemoPortal(string a, int b)
{
    PortalID = a;
    RequestID = b;
}

void DemoPortal::processUserCommand(string command)
{

    // If command is list
    if (command.substr(0, 4) == "List")
    {
        // If Book category
        // if(command.substr(5,9)=="Book")
        if (command[5] == 'B')
        {
            // Here we are mapping requestID with the sorting Parameter which will be
            // used in checkResponse later
            sorter[to_string(RequestID)] = command.substr(10);
            // Removing the parameter according to FileFormat.txt
            command = command.substr(0, 10);
        }
        // If Mobile category
        else
        {
            // Here we are mapping requestID with the sorting Parameter which will be
            // used in checkResponse later
            sorter[to_string(RequestID)] = command.substr(12);
            // Removing the parameter according to FileFormat.txt
            command = command.substr(0, 12);
        }
    }
    // Writing to the file in append mode
    // Request is of type PortalID + RequestID + Command (Start/List/Buy)
    ofstream write;
    write.open("PortalToPlatform.txt", fstream::app);
    string s = PortalID + " " + to_string(RequestID) + " " + command;
    write << s << endl;

    // Incrementing requestID by 1 for next request
    RequestID++;
}

void DemoPortal::checkResponse()
{

    // Reading each line of file in C++ where reader contains the data of line
    string reader;
    ifstream in("PlatformToPortal.txt");

    // Returning if the file is empty
    if(in.peek()==EOF){
        return ;
    }

    string s;
    vector<string> v;
    // Vector to store request IDs that will be used for printing later
    vector<string> requestids;
    
    // Reading till we reach end of file
    while (!in.eof())
    {
        getline(in, reader);
        string check;
        // for case where we had spaces bw lines
        if (reader.size() == 0) {continue;}
    
        stringstream ss(reader);
        while (getline(ss, s, ' '))
        {
            v.push_back(s);
        }
        // Getting RequestID
        check = v[1];
        // Checking if List command - Since we stored requestID of "List"
        // command in map, checking if it is List command
        if (sorter[check] != "")
        {
            // If it is list command, we split the command and make a vector of strings
            // Making product and adding to list as mentioned above
            // Mapping the Name of the product to its requestID - used later for printing
            // getRequestID[v[2]] = v[1];
            Product p(v[2], v[3], v[4], v[5]);
            products.push_back(p);
            requestids.push_back(v[1]);
        }
        // Clearing vector for next requests
        v.clear();
    }
    
    // SORTING THE RESPONSES FROM THE GIVEN FILE

    // In this, each vector corresponds to a List command
    vector<vector<Product>> Output;
    // Storing requestID of the first product and creating temp vector of Products
    int start = stoi(requestids[0]);
    vector<Product> temp;
    int it=0;
    for (Product p : products)
    {
        // Continuing if we get Response with same ID
        if (stoi(requestids[it]) == start)
        {
            temp.push_back(p);
        }
        else
        {
            // If we get newID we push the temp vector in Output and then create new
            Output.push_back(temp);
            temp.clear();
            temp.push_back(p);
            // Getting new RequestID
            start = stoi(requestids[it]);
        }
        it++;
    }
    // Pushing the last vector corresponding to the last request
    if (temp.size() != 0)
    {
        Output.push_back(temp);
    }

    it=0;
    // Going through all the vectors and sorting them according to the parameter
    for (int i = 0; i < Output.size(); i++)
    {
        vector<Product> temp = Output[i];
        // Since sorting parameter is stored in SORTER MAP
        sort(temp.begin(), temp.end(), ProductComparator(sorter[requestids[it]]));
        // Replacing the previous vector with the new sorted temp vector
        Output[i] = temp;
        // Doing it = it + size so that we sort according to next vector in the vector of vectors
        it=it+temp.size();
    }
    int i = 0;
    
    // // READING FILE AGAIN FOR OUTPUT ON CONSOLE
    ifstream in2("PlatformToPortal.txt");
   

    // Reading till end of file
    it=0;
    while (!in2.eof())
    {
        getline(in2, reader);
        string check;
        // case where we have space b/w lines 
        if (reader.size() == 0) {continue;}
      

        stringstream ss(reader);
        while (getline(ss, s, ' '))
        {
            v.push_back(s);
        }
        // Getting RequestID and clearing vector for next requests
        check=v[1];
        v.clear();

        // Checking if list command and doing same as mentioned above
        if (sorter[check] != "")
        {
            // If list command, then we output the sorted Products from Output
            vector<Product> temp = Output[i];
            for (Product x : temp)
            {
                cout << PortalID << " " << requestids[it] << " " << x.getName() << " " << x.getProductID() << " " << x.getPrice() << " " << x.getQuantity() << endl;
            }
            // cout << endl;
            // Incrementing i and skipping rest of the products to avoid repetition
            i++;
            // Doing it = it + size so that we sort according to next vector in the vector of vectors
            it=it+temp.size();
            for (int j = 0; j < temp.size()-1; j++)
            {
                getline(in2, reader);
            }
        }
        // Printing if Buy/Start command
        else
        {
            cout << reader << endl;
            // Below lines are if we are printing Sorry/Congrats message along with success/ failure
            // if(reader[0]=='S' or reader[0]=='C'){}
            // else{cout<<endl;}     
        }
    }
    

    // Opening and closing file to clear the content of the file
    ofstream closer;
    closer.open("PlatformToPortal.txt");
    closer.close();
    // Clearing products for next Check command
    products.clear();
    return;

}
