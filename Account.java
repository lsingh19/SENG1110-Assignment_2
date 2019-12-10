import java.lang.Math;
import javax.swing.*;

public class Account
{
    // instance variables - replace the example below with your own
    private double interestRate, amount;
    private int numberOfMonths; 
    private String accountType;

    /**
     * Constructor for objects of class Account
     */
    public Account()
    {
        // initialising instance variables
        interestRate = 0; 
        numberOfMonths = 0; 
        amount = 0; 
        accountType = ""; 
    }
    
    public void setAccount(double userAmounts, int userMonths, String userAccount)
    {
        // Inputting the value of variable 'userAmounts' into the instance variable 'amount' 
        amount = userAmounts; 
        
        // Inputting the value of variable 'userMonths' into the instance variable 'numberOfMonths'
        numberOfMonths = userMonths; 
        
        // changing the case of the 'userAccount' (the account type the user wants) to lower case in case the user enters it in CAPS. 
        userAccount = userAccount.toLowerCase();
        
        // the do-while statement will set the value of the instance variable 'accountType' depending of the type of account the user wants and the number 
        // of months if the 'no fees' option is choosen. 
        do 
        {
            // the String 'userAccount' is checked to see if it equals 'fees'. If yes then the interestRate is set to 0.06 (6%)
            // and the instance String variable 'accountType' is assigned the string value of 'userAccount' 
            if (userAccount.equals("fees"))
            {
                interestRate = 0.06;
                accountType = userAccount; 
            }
                // if the first condition is not true then the next if condition is checked which checks to see if 'userAccount' equals 'no fees'. 
                // if this is true then the instance String variable 'accountType' is assigned the string value of 'userAccount'  
                // and the interest rate is set depending on the number of months the user would like to pay.
                else if (userAccount.equals("no fees"))
                    {   
                        accountType = userAccount; 
                        // if the number of months is less than 50 then the interest rate will be set to 0.065 (6.5%) 
                        if (numberOfMonths < 50)
                        {
                            interestRate = 0.065;
                        }
                            // if the number of months is greater than or equal 50 and less than or equal to 100 then the interest rate is set to 0.075 (7.5%)
                            // if the LHS and RHS are false then this statement is skipped. 
                            else if (numberOfMonths >= 50 && numberOfMonths <= 100)
                            {
                                interestRate = 0.075;
                            }
                                // if the first two conditions are not met then the number of months is obviously greater than 100. So the interestRate is set to 0.085 (8.5%) 
                                else
                                {
                                    interestRate = 0.085; 
                                }
                    }
                        // although this is a requirement for the assignment I decided to implement it anyway as in real life a customer might forget get to 
                        // input in a space between 'no' and 'fees' when selecting the 'no fees' option. Or they might not evern type in one of the options for
                        // account type. 
                        // So this code will ask the user for a valid account type and it'll be transformed to lower case. Since the interest rate is not set the do-while loop will
                        // be executed again to which we check if the new 'userAccount' String value is equal to any of the options for account type. If not then 
                        // this else statement will be rerun. This process will be repeated until a valid account type is given. 
                        else 
                        {
                            userAccount = JOptionPane.showInputDialog("Invalid Account Type \nPlease choice either: no fees or fees \nNote: Ensure that there is a space between no and fees when selecting the 'no fees' option");  
                            userAccount = userAccount.toLowerCase(); 
                        } 
        }
        while (interestRate == 0); 
    }
                
   // The following code is a Helper Method which calculates the Monthly Payment using the instance variables. The formula is provided in the assignment pdf file. 
   // the 'import java.lang.Math;' library is used so the one variable can be the power of another variable. 
   // this helper method will be return the calculated double value from the monthly payment formula
   private double calculateMonthlyPayment()
    {
        double p,a;
        // calculating the value for a using the provided formula
        a = 1 + (interestRate/12); 
        // the monthly payment is calcualted using the provided formula and the value for a calculated above
        // note: the interest rate is already in decimal form so no conversion from percentage to decimal is needed. 
        p = (amount * interestRate * Math.pow(a, numberOfMonths))/(12 * (Math.pow(a,numberOfMonths) - 1));
        return p; 
    }
    
   // Outputting and Setting Amortization Table
   // This method will return a string when called. 
   public String setAmortizationTable()
   {
       // the variable 'monthlyPayment' is assigned the calculated value from the helper method called 'calculateMonthlyPayment' 
       double monthlyPayment = calculateMonthlyPayment();    
       // Initialsing the following varibles for the while loop, the variable 'amounts' is assigned the value of the instance variable 'amount' 
       double interestPaid = 0, principalPaid = 0, finalBalance = 0, totalPayment = 0, totalInterestPaid = 0 , initBalance = amount; 
       
       String table = "", data = "";
       
       // if the accountType is equal to 'fees' then $10 will be added to the variable 'monthlyPayment' which contains the calculated value for monthly payment 
       // from the Helper method called 'calculateMonthlyPayment'
       if (accountType.equals("fees"))
       {
           monthlyPayment += 10;
        }
        
       // The while loop will run until the counter is greater than the instance variable 'numberOfMonths' 
       // Counter is used to count the number of months starting at 1. 
       for (int counter = 1; counter <= numberOfMonths; counter++)
       {
           // The following codes are just using formula's provided in the assignment pdf file that show how to calculate the interest paid, principal paid and final balance. 
           interestPaid = initBalance*(interestRate/12);
           principalPaid = monthlyPayment - interestPaid; 
           finalBalance = initBalance - monthlyPayment + interestPaid; 
           // If the final balance becomes negative then that means that the monthly payment has been payed off 
           // So the following if statment sets the final balance to zero if it below zero. 
           if (finalBalance < 0) 
           {
               finalBalance = 0; 
            }
           // the results from the while-loop are stored in the String variable 'data' which is later addded on to the final output string 
           data += counter;
           data += String.format("%1$23s", "$" + Double.toString(Math.round(initBalance*100.0)/100.0)); 
           data += String.format("%1$23s", "$" + Double.toString(Math.round(monthlyPayment*100.0)/100.0));
           data += String.format("%1$24s", "$" + Double.toString(Math.round(interestPaid*100.0)/100.0)); 
           data += String.format("%1$26s", "$" + Double.toString(Math.round(principalPaid*100.0)/100.0));
           data += String.format("%1$24s", "$" + Double.toString(Math.round(finalBalance*100.0)/100.0));
           data += "\n";
           // The Math.round is used to round off the answer to 2 decimal place
           // Double.toString(amount) converts the double value into an string and the dollar sign is placed infront of it coversion. 
           // String format allows us to dicate how many spaces the string can take up
           
           //Setting the initial Balance to the Final Balance
           initBalance = finalBalance; 
           // if the final balance is 0 then there is no need for anymore monthly payment as the amount borrowed has already been paid off.
           // so the if statement below will set the counter to the number of months which breaks while-loop. 
           if (finalBalance == 0)
           {
               counter = numberOfMonths+1; 
            }
           // The monthly payment is added to the total payment for each loop 
           totalPayment += monthlyPayment; 
           // The interest paid is added to the total interest paid for each loop 
           totalInterestPaid += interestPaid; 
        }
        //Outputing the monthly payment, total payment and total interest paid. Adding these to the final string 'table' 
        table += "The monthly payment will be p = $" + String.format("%.2f\n", monthlyPayment); 
        table += "The total payment is $" + String.format("%.2f\n", totalPayment); 
        table += "Total interest paid is $" +  String.format("%.2f\n",totalInterestPaid);
        // Setting out the table which will include the 'data' string from the while-loop
        table += "The amortization table is: \n\n"; 
        table += "Month    InitBalance       MonthlyPayment      InterestPaid        PrincipalPaid       FinalBalance\n";
        table += data;
        // Once the string is made the string 'table' is returned when the method is called. 
        return table; 
    }    
    
    // returns the double value of interestRate when called
    public double getInterestRate()
    {
        return interestRate; 
    }
    
    // returns the integer value of numberOfMonths when called
    public int getNumberOfMonths()
    {
        return numberOfMonths;
    }
    
    // returns the double value of amount when called
    public double getAmount()
    {
        return amount;
    }
    
    // returns the string accountType when called 
    public String getAccountType()
    {
        return accountType; 
    }
    }
    
    