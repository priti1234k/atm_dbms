# atm_dbms
DBMS mini project for Atm backend development application
    Scope of the Project(What we will be doing in this Project):
    A user can login as admin/customer
    
    Admin:
      An admin can view the list of banks that already exists in the system
      An admin can create a bank 
      System has to take care of the duplicate entry for a bank based on IFSC code
      An admin can create a ATM within a Bank
      System has to take care of the duplicate entry of an ATM within a bank based on ATMCode.
      Admin can update the balance of an ATM
      Only Increase the balance and Admin cannot decrease the balance
      Admin can remove the ATM from a bank
      Admin can remove a bank
    Customer:
      Registration:
        Customer can register as a new user
        Details of Customer: Name, AAdhar, email, mobile
        System has to take care of the duplicate aadhar/email/mobile for a user
        Customer can register their card details
        Details for registering card no : cust_id, card_no, pin, IFSC Code, balance
      Login:
        Login using card_no, pin
        Withdrawal
        Deposit
        MiniTransactionsReport
        Money transfer
        Pin Update
        Balance check




