package threads;

import java.time.LocalDate;
import java.util.ArrayList;

public class BankApp2 {
  public static void main(String[] args) throws InterruptedException {
    System.out.println("Velkommen til BankApp2 ");
    Account a1=new Account("Joe Pass", 1.5);
    Customer Tom=new Customer(a1);
    Customer Jerry=new Customer(a1);
    Tom.start();
    Jerry.start();
    Tom.join();
    Jerry.join();
    a1.printTransactions();
  }
} // class BankApp2

class Customer extends Thread {
  Account account;
  Customer(Account account){
    this.account=account;
  }

  public void run(){
    account.deposit(1000);
    account.withdraw(50);
    account.deposit(1000);
  }
}

class Account {
  protected int accountNo;
  protected String owner;
  protected double balance;
  protected double interestRate; // rente i %
  protected ArrayList<Transaction> transactions=new ArrayList<>();
  static private int noOfAccounts=0;

  public Account(String ow, double ir){
    noOfAccounts++;
    accountNo=noOfAccounts;
    owner=ow;
    balance=0;
    interestRate=ir;
  }

  public static int getNoOfAccounts() { return noOfAccounts; }

  public void deposit(double amount){
    System.out.println(this);
    synchronized (this){
      balance=balance+amount;
      Transaction t=new Transaction("Indsat", amount, balance);
      transactions.add(t);
    }
  }

  synchronized public void withdraw(double amount){
    balance=balance-amount;
    transactions.add(new Transaction("Hævet", amount, balance));
  }

  public void anualInterest(){
    double interest=(balance*interestRate)/100;
    balance=balance+interest;
    transactions.add(new Transaction("Renter", interest, balance));
  }

  public void printTransactions(){
    System.out.println(toString());
    System.out.println("Tekst\tDato\t\tBeløb\tSaldo");
    for (Transaction t:transactions){
      System.out.println(t);
    }
    System.out.println();
  }

  public String toString(){
    return "Konto: "+accountNo+"\t"+owner+"\t"+balance;
  }
} // class Account

class Transaction {
  String text;
  LocalDate date;
  double amount;
  double newBalance;

  Transaction(String text, double amount, double newBalance){
    this.text=text;
    date=LocalDate.now();
    this.amount=amount;
    this.newBalance=newBalance;
  }

  public String toString(){
    return text+"\t"+date+"\t"+amount+"\t"+newBalance;
  }
} // class Transaction
