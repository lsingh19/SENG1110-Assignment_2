import javax.swing.*;
import java.io.*;
import java.io.PrintWriter;
import java.io.FileNotFoundException; 

public class LoanCalculator
{
    // The following variables are instance variables that only exist within the LoanCalculator.java file and cannot be change outside the file.
    // The clients array is static meaning that it can be accessed by any static method in this file. The array is set to final so that the size of the
    // cannot be changed anywhere inside the file. 
    final private static Client [] clients = new Client [5];
    static private int noOfClients = 0; 
    public static void main (String[] args) throws IOException
    { 
        int x;
        do {
        run();
        x = Integer.parseInt(JOptionPane.showInputDialog("Would you like to rerun the program?\nYes(1) or No(0)"));
        } 
        while (x==1); 
    }
    
    //-------------------------------------------------------------------------------------------------------------------------
    
    private static void run() throws IOException
    {
        int choice;
        do 
        {
                choice = options();  
                switch(choice) {
                case 1: inputClient();  
                        break; 
                case 2: deleteClient();
                        break; 
                case 3: showClient();
                        break; 
                case 4: addAccount();
                        break; 
                case 5: deleteAccountNumber();
                        break; 
                case 6: showAccount();
                        break; 
                case 7: sortClients();
                        break; 
                case 8: saveFile(); 
                        break; 
            }
        }
        while (choice != 9); 
    }
    
    //-------------------------------------------------------------------------------------------------------------------------
    
    private static int options()
    {
      String options = new String(); 
      int select; 
      options += "-----------Menu Bar---------\n"; 
      options += "Input Client: 1\n"; 
      options += "Delete Client: 2\n"; 
      options += "Show a Client: 3\n";
      options += "Input an Account: 4\n"; 
      options += "Delete an Account: 5\n"; 
      options += "Show an Account: 6\n";
      options += "Sort Clients: 7\n";
      options += "Save to File: 8\n";
      options += "To exit program: 9\n";
      select = Integer.parseInt(JOptionPane.showInputDialog(options));
      return select;
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
    
    private static void inputClient()
    {
        // Checking to see the max number of clients isnt reached. If the max number is reached then the user will be given the message that the no more Clients can be
        // stored within the program. On the otherhand if the max number of clients isnt reached then the user will be asked for the name, age and income of the new Client. 
        // Note that the client name is stored in lower case as it makes it easier to later on in the program to find clients. 
        if (noOfClients >= 5) 
            JOptionPane.showMessageDialog(null,"Max number of Clients reached");
            else 
            {
              String userName = JOptionPane.showInputDialog("Please enter the name of the client you wish to input:");
              userName = userName.toLowerCase();
              // The following integer variable be used to check if two client names are not the same. 
              int check=0;
              // The following for-loop will check to see if the client name the user has entered, does not exist within the system already.
              for (int j=0; j<noOfClients; j++)
              {
                // If the user input for client already exists within the system then the value of 'check' is increased by one.
                if(userName.equals(clients[j].getName()) == true)
                {
                   check++;     
                }
              }
              
              // If the user input for name matches another name within the system then the user is alerted to this and the user is direct back to the user interface
              // If however the user input for name does not match another client name within the system then the user is asked for the remaining input required to fill 
              // up the client. 
              if (check!=0)
              {
                  JOptionPane.showMessageDialog(null,"Sorry but this Client name already exists within the system.");
              }
                 else 
                    {
                            int userAge = Integer.parseInt(JOptionPane.showInputDialog("Please enter the age of the client"));
                            // Checking to see if the input isnt a negative input 
                            userAge = checkInteger(userAge);
                            double userIncome = Double.parseDouble(JOptionPane.showInputDialog("Please enter the income of the client"));
                            // Checking to see if the input isnt a negative input 
                            userIncome = checkDouble(userIncome); 
                            // The inputs are used to create and setup a client object within the system/program. 
                            clients[noOfClients] = new Client (userName, userAge, userIncome);
                            // The number of Clients is increased by 1. 
                            noOfClients++;
                    }
            } 
    }
    
    //---------------------------------------------------------------------------------------------------------------------------- 
    
    private static void deleteClient()
    {
        // Asking the user for name of the client they wish to delete. The input is transformed to lower case since all client names are stored as lower case. 
        // the input will be stored in the String variable 'userName'
        String userName = JOptionPane.showInputDialog("Please enter the name of the client you wish to delete");
        userName = userName.toLowerCase(); 
        // the integer variable is used to check if the client exists within the system. 
        int x=0; 
        // The following for loop will go through every client name to check if the name matches the input name provided by the user. 
        for (int i=0; i < noOfClients; i++) 
        {
            if (userName.equals(clients[i].getName()) == true)
            {
                // If the client name and user input matchs then the client object is set to null essentially its deleted/lost from the system. 
                clients[i] = null; 
                // If the 'x' variable is increment by 1 which is later checked to see if a client has been deleted
                x++;
                // updating the client array
                for (int j=0; j < noOfClients-1; j++) 
                {
                    if (clients[j] == null)
                    {
                        for (int k=j; k <noOfClients-1; k++)
                        {
                         clients[k] = clients[k+1];
                        }
                    }
                }
                // The last object in the array is set to null since it has been moved up the array
                clients[noOfClients-1] = null; 
                // The total number of clients is decresed by one. 
                noOfClients--; 
            }
        }
        // If the client doesn't exist and no client has been deleted then the following code will be exectued to inform the user that no client has been found
        // that matches the client name they have provided. 
        if (x == 0)
        {
            JOptionPane.showMessageDialog(null,"Sorry but this Client doesn't exist within the system\nPlease ensure that the name is correct and there is no space after the input");
        }
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
    
    private static void showClient() 
    {
        // Asking the user to input the client name they wish to view. 
        // the input will be stored in the String variable 'userName'
        String userName = JOptionPane.showInputDialog("Please enter the name of the client you wish to view");
        //converting the input to lower case
        userName = userName.toLowerCase();
        // the following variable is used to check if the client has been found or even it exists within the system. 
        int x=0;
        // The following for look will check if the input match any of the client name's. 
        for (int i=0; i < noOfClients; i++) 
        {
            if (userName.equals(clients[i].getName()) == true)
            {
                // if the name matches any of the client names then the method 'getClient' is called to retierve the information about the client. 
                // the method return a string which will be displayed to the user. 
                JOptionPane.showMessageDialog(null, clients[i].getClient());
                // the variable 'x' is incremented by a value of 1. 
                x++;
            }
        }
        // if the value of 'x' is 0 which means that no client was found with the name the user had provided, then the user is alerted that the client 
        // does not exits within the systme/program.
        if (x == 0)
            JOptionPane.showMessageDialog(null, "Invalid Client Name\nThis client does not exist with the system");
    }
   
    //----------------------------------------------------------------------------------------------------------------------------
    
    private static void addAccount() 
    {
        // Asking the user for the name of the client in which they wish to add the account in.
        // the input will be stored in the String variable 'userName'
        String userName = JOptionPane.showInputDialog("Please enter the name of the client you wish to add an account in:");
        // Converting the input to lower case. 
        userName = userName.toLowerCase();
        // the following integer variable is used to check if the client was found within the system/program. 
        int x=0;
        // The following for-loop will check through all the client names to check if they match the name provided by the user. 
        for (int i=0; i < noOfClients; i++) 
        {
            if (userName.equals(clients[i].getName()) == true)
            {
                // So in the following line an integer will store the output from the method 'checkAccountSpace' 
                int j = clients[i].checkAccountSpace();
                // if the value returned from the method is the predefined value then that means that there is no account space value for the
                // client to input an account. 
                if (j<2) 
                {
                    // asking the user for the amount, months and account type to setup the account in the returned 'j' value from the 
                    // method 'checkAccountSpace' 
                    double userAmount = Double.parseDouble(JOptionPane.showInputDialog("Please enter number the amount of money the user would like to borrow"));
                    // Checking to see if the input is not a negative value
                    userAmount = checkDouble(userAmount); 
                    
                    int userMonths = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of months"));
                    // Checking to see if the input is not a negative value
                    userMonths = checkInteger(userMonths);
                    
                    String userAccount = JOptionPane.showInputDialog("Please select a the type of account \n'no fees' or 'fees'");    
                    // Sets up the account is setup usinfg the method 'addAccount' which exists within client.java 
                    clients[i].addAccount(j, userAmount, userMonths, userAccount); 
                }
                else 
                    {
                        // if from the method 'checkAccountSpace' the predefined value is returned. then the user is alerted to that 
                        // the fact that there is no free account spaces within the client. 
                        JOptionPane.showMessageDialog(null, "Number of accounts full for this client");
                    }
                // the value of x is incremented by 1 since a client matching the input name by the user was found.  
                x++;
            }
        }
        
        // if the client was not found then the value of 'x' will remain the same, if the account wasn't found then the following if function will be 
        // exectuted        
        if (x == 0)
        {
            JOptionPane.showMessageDialog(null, "Invalid Client Name\nThis client does not exist with the system");
        }
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   
    private static void deleteAccountNumber() 
    {
        // Asking the user for the name of the client in which the account they wish to delete exists. 
        // the input will be stored in the String variable 'userName'
        String userName = JOptionPane.showInputDialog("Please enter the name of the client you wish to delete");
        // Converting the input to lower case. 
        userName = userName.toLowerCase();
        // the following variable will be used to check if a client was found with the same name as the input from the user. 
        int x=0;
        for (int i=0; i < noOfClients; i++) 
        {
            if (userName.equals(clients[i].getName()) == true)
            {
               // If the client name and input matches then the user is asked for the account they wish to delete. 
               int accountNumber = Integer.parseInt(JOptionPane.showInputDialog("Please enter the account you would like to delete\nEnter 1 for Account 1 and 2 for Account 2"));
               // The account number is decreased as the an array start with 0 and the user is asked to input either 1 or 2 as the 
               // account number they wish to delete. 
               accountNumber--; 
               // the account number is sent to the method 'deleteAccount' which will deleted/ set the account to null. 
               clients[i].deleteAccount(accountNumber);
               // the value of 'x' is incremented by a value. 
               x++; 
            }
        }
        
        // If the client is not found with the name provided by the user then the user alerted to this. 
            // If a client with name that the user has provided is found then the value of 'x' would be '0' then this if-statement won't be executed. 
        if (x==0) 
        {
            JOptionPane.showMessageDialog(null, "Invalid Client Name\nThis client does not exist with the system");
        }
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
    
    private static void showAccount()
    {
        // Asking the user for the name of client in which exists the account they wish to view exists. 
        // the input will be stored in the String variable 'userName' 
        String userName = JOptionPane.showInputDialog("Please enter the name of the client you wish to delete");
        // Converting the input to lower case. 
        userName = userName.toLowerCase();
        // the following variable is used to check if the client exists within the system. 
        int x=0;
        // the following for-loop will go through every client name to check if it matches that of the user input. 
        for (int i=0; i < noOfClients; i++) 
        {
            if (userName.equals(clients[i].getName()) == true)
            {
               // if the name was found then the value of 'x' is incremented by a value of 1. 
               x++; 
               // Asking the user which account they wish to view. 
               int accountNumber = Integer.parseInt(JOptionPane.showInputDialog("Please enter the account you would like to delete\nEnter 1 for Account 1 and 2 for Account 2"));
               // The account number is decreased as the an array start with 0 and the user is asked to input either 1 or 2 as the 
               // account number they wish to view. 
               accountNumber--; 
               // the input for account number is sent to the method 'showAccount' to retierve the information about the account.
               // the method will return a string with the information about the client or a string with a message thats states that the account 
               // does not exists. This is displayed to the user
               JOptionPane.showMessageDialog(null, clients[i].showAccount(accountNumber));      
            }
        }
        
        // If the client is not found with the name provided by the user then the user alerted to this. 
        // If a client with name that the user has provided is found then the value of 'x' would be '0' then this if-statement won't be executed. 
        if (x==0) 
        {
            JOptionPane.showMessageDialog(null, "Sorry, this client does not exist within the system.");
        }
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
    
    private static void sortClients()
    {
        // a temporary array of size 1 is created which is needed to exchange to client object within the clients array. 
        Client [] temp = new Client[1];
        temp [0]= new Client();
        // Boolean is used to see if the ordering of the client name is done. 
        boolean flag = true; 
        // as long as the there is 2 client objects that are exchange then this while statment will keep running
        while (flag == true)
        {
         // The boolean is set to false and is changed if two client objects are exchanged
         flag = false;    
         // For-loop is used to check the names of the clients 
         for (int j=0; j<noOfClients-1;j++)
         {
             // if the current client name is greater the next client name then the two objects are swapped
             if (clients[j].getName().compareToIgnoreCase(clients[j+1].getName())>0)
             {
                 // the temp array is assigned the current client object
                 temp[0] = clients[j];
                 // the current client object is assigned the value of following client object
                 clients[j] = clients[j+1];
                 // the following client object is assignment the value of the temp client object
                 clients[j+1] = temp[0];
                 // glag is set to true as there was 2 client object that needed switching
                 flag = true; 
             }
         }
        }
       // The following String variable 'order' will stored the new order of client will be shown to the user. 
        String order = "New order of Clients:\n"; 
        // the for-loop will stored the name of the newly sorted 'clients' array 
        for (int z=0; z<noOfClients; z++)
        {
            order += clients[z].getName() + "\n";
        }
        // the newly order of the 'clients' array is return to the user so they know what the new order is. 
        JOptionPane.showMessageDialog(null, order);
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
    
    private static void saveFile() throws IOException 
    {
        // NOTE: PLEASE OPEN THE OUTPUT FILE IN NOTEPAD++ as regular notepad doesn't seem to pickup '\n' to start a new line. Thanks =) 
        // Also, the spacing amortization table of the output file is different to the one displayed in the program. 
        // Although the spacing is enough to distinguish between columns of the amortizartion table. 
        
        //Asking the user for file name they wish to store the data into. 
        String fileName = JOptionPane.showInputDialog("Please enter the name of the file\nPlease include '.txt' after the name");
        PrintWriter outFile = new PrintWriter(fileName);
        // The for-loop will go through every client and save the details of that client. 
        for (int i=0; i<noOfClients; i++)
          {
              outFile.println("------------------------------------------");
              outFile.println ("Client Number: " + (i+1));
              outFile.println("------------------------------------------");
              outFile.println("Client: " + clients[i].getName());
              outFile.println("Client age: " + clients[i].getAge());
              outFile.println("Client Income: " + clients[i].getIncome());
              for (int j=0; j<2; j++)
              {
                  // The method 'checkAccount' will check to see if the account within the client is not empty
                  if(clients[i].checkAccount(j) == true)
                  {
                      // If the account is not empty then the following lines of codes will executed, in which the details of the
                      // as outputed to the file. 
                      outFile.println("");
                      outFile.println("Account " + (j+1));
                      outFile.println("*********");
                      outFile.println("Account Type: " + clients[i].getAccountType(j));
                      outFile.println("Amount Borrowed: $" + clients[i].getAmount(j));
                      // Within the next line of code the interest rate is converted from a decimal to percentage form
                      outFile.println("Number of Months to repay amount borrowed: " + clients[i].getNumberOfMonths(j));
                      outFile.println("Interest Rate for this account: " + (clients[i].getInterestRate(j)*100) + "%"); 
                      outFile.println("Amortization Table: " + clients[i].getAmortizationTable(j));
                  }
              }
              // Spacing between the various clients. purely done for presentation and organisation of data output. 
              outFile.println();
        }
        // closing the file. 
          outFile.close();
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
    
    // This Helper Method will check to see if the input is a positive integer number. 
    private static int checkInteger(int value)
    {
        {
            // As long the user provides a negative integer number the while-loop will keep repeating until a valid positive interger number is provided.
            while (value < 0)
            {
                // Asking the user to input a postive integer number
                value = Integer.parseInt(JOptionPane.showInputDialog("Please enter a valid integer number that is not a negative number"));  
            }
            // Once a postive integer number is provided then this number is returned. From which it will be stored into whatever the variable was put within
            // the brackets of the method call.
            return value; 
        }
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
    
    // This Helper Method will check to see if the input is a positive double number.
    private static double checkDouble(double number)
    {
        {
         // As long the user provides a negative DOUBLE number the while-loop will keep repeating until a valid double interger number is provided.
            while (number < 0)
            {
                // Asking the user to input a postive double number
                number = Double.parseDouble(JOptionPane.showInputDialog("Please enter a valid double value that is not a negative number"));     
            }
            // Once a postive double number is provided then this number is returned. From which it will be stored into whatever the variable was put within
            // the brackets of the method call.
            return number; 
        }
    }   
}
