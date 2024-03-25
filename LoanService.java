package com.service;

import java.sql.SQLException;
import java.util.List;
import com.dao.*;
import com.model.Loan;

public class LoanService {
	ILoanRepositoryDao loan = new ILoanRepositoryDaoImpl();

	public void applyLoan(int customerId, String name, String emailAddress, String phoneNumber, String address,
			int creditScore)throws SQLException {
		// TODO Auto-generated method stub
		return;

	}

	public List<Loan> getAllLoan() {
		System.out.println("All Loans:");
		return loan.getAllLoan();
	}

	public void getLoanById() {

		// TODO Auto-generated method stub

	}

	public void loanRepayment(int loanId, double principalAmount) {
		// TODO Auto-generated method stub

	}

}
