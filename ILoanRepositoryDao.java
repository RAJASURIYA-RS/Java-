package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exception.InvalidLoanException;
import com.model.Loan;

public interface ILoanRepositoryDao {

	void applyLoan(int customerId, String name, String emailAddress, String phoneNumber, String address,
			int creditScore) throws SQLException;

	double calculateInterest(int loanId) throws InvalidLoanException;

	double calculateInterest(int principalAmount, double interestRate, int loanTerm) throws InvalidLoanException;

	void loanStatus(int loanId) throws InvalidLoanException;

	double calculateEMI(int loanId) throws InvalidLoanException;

	double calculateEMI(int principalAmount, double interestRate, int loanTerm) throws InvalidLoanException;

	void loanRepayment(int loanId, double principalAmount) throws InvalidLoanException;

	List<Loan> getAllLoan();

	Loan getLoanById(int loanId) throws InvalidLoanException;

}
