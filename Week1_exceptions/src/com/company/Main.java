package com.company;

public class Main {

    public static void main(String[] args) {



//        BankAccount account =
//                new BankAccount("Mohammed", 1000);
//
//        System.out.println(account.getBalance());
//
//        account.deposit(500);
//
//        System.out.println(
//                "Balance: " + account.getBalance()
//        );
//
//        account.withdraw(200);
//
//        System.out.println(
//                "Balance: " + account.getBalance()
//        );
//
//        account.withdraw(2000);
//
//        System.out.println(
//                "Balance: " + account.getBalance()
//        );


        // ===== Bank Account with throw exception=====

//        BankAccount2 account =
//                new BankAccount2("Mohammed", 1000);
//
//        System.out.println(account.getBalance());
//
//        account.deposit(500);
//
//        System.out.println(
//                "Balance: " + account.getBalance()
//        );
//
//        account.withdraw(200);
//
//        System.out.println(
//                "Balance: " + account.getBalance()
//        );
//
//        account.withdraw(2000);
//
//        System.out.println(
//                "Balance: " + account.getBalance()
//        );


        // ===== Bank Account with try catch =====


//        BankAccount2 account = new BankAccount2("Mohammed", 1000);
//        try {
//            account.withdraw(2000);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("Program continues...");


        // ===== Bank Account with Custome ExCEPTION =====

//                BankAccount3 account =
//                new BankAccount3("Mohammed", 1000);
//
//        System.out.println(account.getBalance());
//
//        account.deposit(500);
//
//        System.out.println(
//                "Balance: " + account.getBalance()
//        );
//
//        try {
//            account.withdraw(200);
//        } catch (InsufficientBalanceException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(
//                "Balance: " + account.getBalance()
//        );
//
//        try {
//            account.withdraw(2000);
//        } catch (InsufficientBalanceException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(
//                "Balance: " + account.getBalance()
//        );

/*   ==========>       task   <============
    1- Create Custom exception  "InvalidAmountException"
    2- modify deposit method in BankAccount3 Class to throw InvalidAmountException when the amount less than or
        equal 0
    3- modify the main method to use deposit method and handel the error

     ==========>       task   <============
*/
    }

}