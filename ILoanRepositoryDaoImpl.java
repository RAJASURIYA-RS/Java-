package com.dao;

import com.model.Loan;
import com.util.DBPropertyUtil;
import com.exception.InvalidLoanException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ILoanRepositoryDaoImpl implements ILoanRepositoryDao {

	List<Loan> loans = new ArrayList<>();

	@Override
	public void applyLoan(int customerId, String name, String emailAddress, String phoneNumber, String address,
			int creditScore) throws SQLException {

		Connection conn = DBPropertyUtil.getDBConn();

		String sql = "insert into customer(customer_id, name, email_address, phone_number, address, credit_score) VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, customerId);
		pstmt.setString(2, name);
		pstmt.setString(3, emailAddress);
		pstmt.setString(4, phoneNumber);
		pstmt.setString(5, address);
		pstmt.setInt(6, creditScore);

		pstmt.executeUpdate();

		pstmt.close();

		DBPropertyUtil.dbClose();
	}



	@Override
	public double calculateInterest(int loanId) throws InvalidLoanException {
		Loan loan = getLoanById(loanId);
		double interest = (loan.getPrincipalAmount() * loan.getInterestRate() * loan.getLoanTerm()) / 12;
		return interest;
	}

	@Override
	public double calculateInterest(int principalAmount, double interestRate, int loanTerm)
			throws InvalidLoanException {
		double interest = (principalAmount * interestRate * loanTerm) / 12;
		return interest;
	}

	@Override
	public void loanStatus(int loanId) throws InvalidLoanException {
		Loan loan = getLoanById(loanId);
		if (loan.getCustomer().getCreditScore() > 650) {
			loan.setLoanStatus("Approved");
		} else {
			loan.setLoanStatus("Rejected");
		}
		System.out.println("Loan status updated: " + loan.getLoanStatus());
	}

	@Override
	public double calculateEMI(int loanId) throws InvalidLoanException {
		Loan loan = getLoanById(loanId);
		double monthlyInterestRate = loan.getInterestRate() / 12 / 100;
		int loanTermInMonths = loan.getLoanTerm();
		double principalAmount = loan.getPrincipalAmount();
		double emi = (principalAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTermInMonths))
				/ (Math.pow(1 + monthlyInterestRate, loanTermInMonths) - 1);
		return emi;
	}

	@Override
	public double calculateEMI(int principalAmount, double interestRate, int loanTerm) throws InvalidLoanException {
		double monthlyInterestRate = interestRate / 12 / 100;
		double emi = (principalAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTerm))
				/ (Math.pow(1 + monthlyInterestRate, loanTerm) - 1);
		return emi;
	}

	@Override
	public void loanRepayment(int loanId, double principalAmount) throws InvalidLoanException {
		Loan loan = getLoanById(loanId);
		double emi = calculateEMI(loanId);
		int numberOfEmis = (int) (principalAmount / emi);
		if (numberOfEmis >= 1) {
			System.out.println("Payment successful. Number of EMIs paid: " + numberOfEmis);
			
		} else {
			System.out.println("Payment rejected. Insufficient amount to cover at least one EMI.");
		}
	}

	@Override
	public List<Loan> getAllLoan() {
		return loans;
	}

	@Override
	public Loan getLoanById(int loanId) throws InvalidLoanException {
		for (Loan loan : loans) {
			if (loan.getLoanId() == loanId) {
				return loan;
			}
		}
		throw new InvalidLoanException("Loan not found with ID: " + loanId);
	}
}
