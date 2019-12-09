# SENG1110-Assignment_2
Object-Oriented Programming Course - Assignment 2

**_Mark_**: 100 / 100

# Comments by Marker 
- -2 in loan calculator for all static methods on loan calculator 
- -1 for account method not having a setInterest rate function.  
- +15 for implementing sort. 
- -1 for incorrect output in show account details, showing message asking to delete instead of viewing, but does the correct functionality. 
- -2 for incorrect table output, ends too quickly when testing fees, (try amount 1234, 19 months fees, you will only see 17 months)   
95 + 15 = 100 marks !! 

# Specification 
 
The program will allow up to 5 clients and each client will be able to have 2 accounts. The program will start showing the following options: 
- **Add a client**    
If the number of clients is already 5, the program will say that it is not possible to add an extra client. If the number of clients is less than 5, then the program will ask the following inputs: 
    - Client name 
    - Client age (need to be a positive number. If not, the program will ask the input again) 
    - Client income per year (need to be a positive number. If not, the program will ask the input again) 
 
- **Delete a client**      
The program will ask the name of the client to be deleted. If the name does not exist, show a message. If the name exists, then the client should be deleted. More information about how to delete will be available in the Q&A document in Blackboard. 
 
- **Show a client**        
The program will ask the name of the client. If the name does not exist, show a message. If the name exists, then the program will display name, age, income and number of accounts of this client. 
 
- **Add an account**         
The program will ask the name of the client. If the name does not exist, show a message. If the name exists, then the program will check if the number of accounts that the client is 2. If it is 2, the program will show a message. If it is less than 2, then the program will ask 
  - amount of money the client would like to borrow (need to be a positive number. If not, the program will ask the input again) 
  - number of months the client would like to pay (need to be a positive number. If not, the program will ask the input again) 
  - type of account. 
  There are two types of accounts the client can choose:  
      1. **No fees**. If the client chooses this option, the interest rate will be  
        - 6.5%  if n < 50  
        - 7.5%  if 50 <= n < = 100   
        - 8.5%  if n > 100, where n is number of months   
      2. **Fees**. The interest rate will be 6%, but the client needs to pay an additional fee of $10 every month. 

- **Delete an account**      
The program will ask the name of the client. If the name does not exist, show a message. If the name exists, then the program will ask which account to delete (1 or 2). If the account does not exist, show a message. If the account exists, the account will be deleted. 
 
- **Show account details**      
The program will ask the name of the client. If the name does not exist, show a message. If the name exists, then the program will ask which account the client would like to see. If the account does not exist, the program will show a message. If it exists, the program will display all the details of the account, which will be: 
  - Amount 
  - Number of months 
  - Type of account 
  - Total payment 
  - Total interest paid 
  - Amortisation table, which will have the following columns: 
    - month 
    - initial balance 
    - payment 
    - interest paid 
    - principal paid 
    - final balance 
  Exactly the same way described in assignment 1. 
 
- **Save in a file**  
The program will save in a file (txt format) the information of each client (the same information described in “show client” above) and each account (the same information described in “show account details” above). If there are no clients, the files should have “no client”. For each client, if the client does not have an account, the file should have “no account”. 
 
- **Finish the program**   
Exit the program. 

## Program Requirements 
There must be three classes: Client, Account and LoanCalculator. 
 
1. **The Account class** (the file needs to be Account.java) 
 
	It will hold the required instance data for an account and it will have suitable methods to access and modify the data for an account.       
	The instance variables will be 
	- interestRate – double 
	- numberOfMonths – int 
	- amount – double 
	- accountType – String

	You need to implement at least one constructor, which will initialize the instance variables with values from parameters. The class needs to have methods to change and access all instance variables. It will also have the following methods (at least): 
	- setInterestRate, which will set the value in the interestRate which will depend on the type of the account (as explained above). 
	- calculateMontlyPayment, which will calculate the formula (1) 
	- setAmortizationTable - it will output a String with the table information. 

2. **The Client Class** (the file needs to be Client.java)  

	It will hold the required instance data for a client and it will have suitable methods to access and modify the data for a client.   
	The instance variables will be: 
	- name – String 
	- age – int 
	- income – double 
	- loan [ ]  – array of Account – size 2  
	You need to implement at least one constructor that will have the parameters name, age and income. The class needs to have methods to change and access all instance variables.  
 
3. **The LoanCalculator Class** (the file needs to be LoanCalculator.java)   

	It will receive inputs and show outputs. It will have an array of Client of size 5. This is the only class that should have a main method. The class LoanCalculator will also be the only one that will receive inputs and show outputs. 

## Extra Work for SENG6110 students  
 
The program will have an extra option: sort clients, where the clients in the arrays will be sorted alphabetically. You can implement any sorting algorithm for this task. In the lecture and computer lab of week 9 you will find resources to complete this task.    
**SENG1110 students that implement this task will receive extra 15 points (the maximum mark of your assignment 2 is 100)** 
 
