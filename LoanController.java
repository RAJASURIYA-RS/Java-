package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.model.Customer;
import com.model.Loan;
import com.service.LoanService;

public class LoanController {

	public static void main(String[] args) {

		LoanService ls = new LoanService();
		Scanner sc = new Scanner(System.in);
		List<Loan> loans = new ArrayList<>();
		while (true) {
			System.out.println("Loan Management System");
			System.out.println("1. Apply for a loan");
			System.out.println("2. Get all loans");
			System.out.println("3. Get loan by ID");
			System.out.println("4. Repay loan");

			System.out.print("Enter your choice: ");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting thank you");
				break;
			}

			switch (input) {
			case 1:

				System.out.println("Enter customer details:");
				System.out.print("Customer ID: ");
				int customerId = sc.nextInt();
				sc.nextLine();
				System.out.print("Name: ");
				String name = sc.nextLine();
				System.out.print("Email Address: ");
				String emailAddress = sc.nextLine();
				System.out.print("Phone Number: ");
				String phoneNumber = sc.nextLine();
				System.out.print("Address: ");
				String address = sc.nextLine();
				System.out.print("Credit Score: ");
				int creditScore = sc.nextInt();
				sc.nextLine(); 

				Customer customer = new Customer(customerId, name, emailAddress, phoneNumber, address, creditScore);
				System.out.print("Enter loan details:");
				System.out.print("Loan ID: ");
				int loanId = sc.nextInt();
				sc.nextLine(); 
				System.out.print("Principal Amount: ");
				double principalAmount = sc.nextDouble();
				System.out.print("Interest Rate: ");
				double interestRate = sc.nextDouble();
				System.out.print("Loan Term (months): ");
				int loanTerm = sc.nextInt();
				sc.nextLine();
				System.out.print("Loan Type: ");
				String loanType = sc.nextLine();

				Loan loan = new Loan(loanId, customer, principalAmount, interestRate, loanTerm, loanType, "Pending");

				loans.add(loan);
				System.out.println("Loan applied successfully.");
				try {
					ls.applyLoan(customerId, name, emailAddress, phoneNumber, address,creditScore);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 2:

				System.out.println("All Loans:");

				ls.getAllLoan();
				break;

			case 3:
				System.out.print("Enter Loan ID: ");
				int loanId1 = sc.nextInt();
				sc.nextLine();
				ls.getLoanById();
				System.out.println("");
				break;

			case 4:
				System.out.println("Enter Loan ID:");
				int loanId2 = sc.nextInt();
				System.out.println("Enter principal amount");
				double principalAmount1 = sc.nextDouble();
				ls.loanRepayment(loanId2, principalAmount1);
				break;
			}

		}
		sc.close();
	}
}
