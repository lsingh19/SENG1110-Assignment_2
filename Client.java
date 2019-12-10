import javax.swing.*;

public class Client
{
    // Declaring the instance variables for the Client class. 
    private String name;
    private int age;
    private double income; 
    // the loan is final so the size of it can't be changed by any method in this java file.
    final private Account loan [] = new Account[2];
    
    /**
     * Constructor for objects of class Client
     */
    public Client()
    {
        // initialising instance variables
        name = ""; 
        age = 0; 
        income = 0;
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   
  // This method is used to setup the client object.  
    public Client(String userName, int userAge, double userIncome)
    {
        // initialising instance variables
        name = userName; 
        age = userAge; 
        income = userIncome; 
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   
    // Return the string 'name' which contains the client name when the method is called   
    public String getName()
    {
        return name; 
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   
    // Returns the interger value of 'age' which contains the client age when the method is called
    public int getAge()
    {
        return age; 
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   
    // returns the double value of 'income' which contains the client income when the method is called. 
    public double getIncome()
    {
        return income; 
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   
  // The following method will return a string that includes information about the client the user wishs to view. The string includes the name, age and income 
  // of the clients aswell as the number of account that the client has. 
    public String getClient()
    {
        // The following string is used to store the information about the client
        String clientInfo;
        // the following variable will track the number of accounts the client has. 
        int accounts=0;
        clientInfo = "Client Name: " + name + "\n";
        clientInfo += "Client Age: " + age + "\n";
        clientInfo += "Client Income: " + income + "\n"; 
        // The following is for-loop will check both elements of the array to check whether it is null or it contains an account. If the element is not null 
        // then a variable accounts is incremented by a value of 1. 
        for(int i = 0; i < 2; i++)
        {
            if (loan[i] != null)
            accounts++; 
        }
        // The following code will store the number of accounts that exist within the client. 
        clientInfo += "This client has " + accounts + " accounts";
        // returning the client information. 
        return clientInfo; 
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   // The following method will check to see if theres a account space avaiable within the client    
    public int checkAccountSpace()
    {
        // The following integer variable will be used to check if there is an account free in this client. 
        int x=2;
        // The following for-loop will check if the see any of the accounts are free. 
        for(int i=0; i<2; i++)
        {
            if (loan[i] == null)
            {
                // if there is account space free then the the value of the variable 'x' is changed to the number in which the account is free. 
                x=i;
                // The for-loop is ended if a free account space is found within the account. 
                i = 2; 
            }
        }
        // the value of the variable 'x' is returned when the method is called. 
        // if no account is found then the predefined value of 'x' is returned. 
        return x;
    }
        
    //----------------------------------------------------------------------------------------------------------------------------
   // This method is used to set up the account object within the loan array within exists within the client. 
    public void addAccount(int x,double userAmount, int userMonths, String userAccount) 
    {
        // Creating a new object within the loan array.  
        loan[x] = new Account();
        // the amount, months and account type is sent to newly created account object using the 'setAccount' method. 
        loan[x].setAccount(userAmount, userMonths, userAccount);
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   // This method is used to delete an account that the user wishes to delete.
    public void deleteAccount(int accountNumber)
    {
        // if the account number selected is not empty then the if-statement is excuted
        if (loan[accountNumber] != null) 
        {
            // The following code will delete/set the account to null. 
            loan[accountNumber] = null; 
            // The following code will be update the account array if the first account array is deleted. 
            // all this if statment does is move the now 2 element of the array to the 1 element of the array, if and only if the first account is deleted.
                if (accountNumber == 0)
                {
                    loan[accountNumber] = loan[accountNumber+1];
                    loan[accountNumber+1] = null; 
                }
        }
        else        
            // If the account number is already empty then the user will be alerted to the fact that there exists no account in the one
            // they haves selected to delete. 
            JOptionPane.showMessageDialog(null, "Sorry, there does not exist an account in the account number you have choosen");
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   // This method is used to show the details of an account that the user wishes to view. 
    public String showAccount(int accountNumber) 
    {
        // The following String variable will be used to store the details of the account if the account actually exists. 
        String result; 
        if(loan[accountNumber] == null) 
        {
            // the account doesn't exists then the following String is returned.
            return result = "Sorry, there does not exist an account in the account number you have provided";
        }
        else 
        {
            // If the account actually exists then the following lines of code are excuted. 
            // The interest is reterieve by the method 'getInterestRate' within account.java and isconverted from decimal to percentage.  
            double interest = loan[accountNumber].getInterestRate();
            interest *= 100;
            // the following line codes reterieve the details of account and store them within the String 'result'. 
            result = "The interest rate for this account is " + interest + "\n"; 
            result += "Number of Months: " + loan[accountNumber].getNumberOfMonths() + "\n";
            result += "The amount borrowed: " + loan[accountNumber].getAmount() + "\n";
            result += "Account Type is: " + loan[accountNumber].getAccountType() + "\n"; 
            result += loan[accountNumber].setAmortizationTable();
            // return the data when the method is called. 
            return result;
        }
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   // This method is used to check if the account is empty or not. 
    public boolean checkAccount(int number) 
    {
        boolean check = true; 
        if (loan[number] == null) 
        {
            check = false; 
        }
        return check; 
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   // Returns interest rate when called 
    public double getInterestRate(int number) 
    {
        return loan[number].getInterestRate(); 
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   // Returns the number of months when the method is called
    public int getNumberOfMonths(int number) 
    {
        return loan[number].getNumberOfMonths(); 
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   // Returns the amount of money borrowed when the method is called
    public double getAmount(int number) 
    {
        return loan[number].getAmount(); 
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   // Returns the account type when the method is called
    public String getAccountType (int number) 
    {
        return loan[number].getAccountType(); 
    }
    
    //----------------------------------------------------------------------------------------------------------------------------
   // Returns the amortization table when the method called
    public String getAmortizationTable(int number)
    {
        return loan[number].setAmortizationTable(); 
    }
}
