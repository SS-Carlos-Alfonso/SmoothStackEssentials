package libraryManagementSystem;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Timestamp;

import oracle.sql.*;
import oracle.jdbc.driver.*;
import java.util.*;

public class Borrower implements Serializable {
	public static volatile Borrower BSoleInstance;

	private Borrower() {

		// Prevent form the reflection api.
		if (BSoleInstance != null) {
			throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
		}
	}

	public static Borrower getInstance() {
		if (BSoleInstance == null) { // if there is no instance available... create new one
			synchronized (Borrower.class) {
				if (BSoleInstance == null)
					BSoleInstance = new Borrower();
			}
		}

		return BSoleInstance;
	}

	protected Borrower readResolve() {
		return getInstance();
	}

	public static boolean checkCardNo(int cardNo, Connection conn) throws SQLException {

		String query = "SELECT COUNT(*) FROM tbl_borrower WHERE cardNo =?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, cardNo);

		ResultSet rs = stmt.executeQuery();
		int checkId = 0;
		while (rs.next()) {
			checkId = rs.getInt(1);
		}

		if (checkId == 1) {
			return true;
		} else {
			return false;
		}

	}

	public static int getNumBranches(Connection conn) throws SQLException {

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT COUNT(DISTINCT branchId) from tbl_library_branch");
		int numbBranches = 0;
		while (rset.next()) {
			numbBranches = rset.getInt(1);
		}

		return numbBranches;
	}

	public static int printBranches(Connection conn) throws SQLException {

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select * from tbl_library_branch");

		Scanner in = new Scanner(System.in);

		int branchChoice = 0;

		System.out.println("Pick the branch you want to check out from:");

		int i = 1;
		while (rset.next()) {
			int id = rset.getInt(1);
			String name = rset.getString(2);
			String local = rset.getString(3);

			System.out.print(i + ": " + name + ", ");
			System.out.println(local);
			i++;
		}
		System.out.println(i + ": " + "Quit to previous");

		try {
			branchChoice = in.nextInt();
			in.nextLine();
			if (branchChoice > i) {
				branchChoice = i;
			}
		} catch (InputMismatchException e) {
			System.out.println("Please enter a number 1-" + i);
			in.next();
			branchChoice = i;
		}

		return branchChoice;
	}

	public static int showBooksInBranch(int userOption, Connection conn) throws SQLException {

		ArrayList<Integer> bookIds = new ArrayList<Integer>();
		ArrayList<Integer> authorIds = new ArrayList<Integer>();
		ArrayList<Integer> branchIds = new ArrayList<Integer>();

		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<String> authors = new ArrayList<String>();

		String query = "SELECT  branchId FROM tbl_library_branch;";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rset = stmt.executeQuery();
		int branchId = 0;
		while (rset.next()) {
			branchId = rset.getInt(1);
			branchIds.add(branchId);

		}

		query = "SELECT bookId FROM tbl_book_copies WHERE branchId = ? AND noOfCopies != 0;";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, branchIds.get(userOption - 1));
		rset = stmt.executeQuery();
		int i = 1;
		while (rset.next()) {
			int bookId = rset.getInt(1);
			bookIds.add(bookId);
			i++;
		}

		for (int k = 0; k < bookIds.size(); k++) {
			String query2 = "SELECT authorId FROM tbl_book_authors WHERE bookId = ?;";
			PreparedStatement stmt2 = conn.prepareStatement(query2);
			stmt2.setInt(1, bookIds.get(k));
			ResultSet rset2 = stmt2.executeQuery();
			while (rset2.next()) {
				int authorID = rset2.getInt(1);
				authorIds.add(authorID);
			}

		}

		for (int k = 0; k < bookIds.size(); k++) {
			String query3 = "SELECT title FROM tbl_book WHERE bookId = ?;";
			PreparedStatement stmt3 = conn.prepareStatement(query3);
			stmt3.setInt(1, bookIds.get(k));
			ResultSet rset3 = stmt3.executeQuery();
			while (rset3.next()) {
				String title = rset3.getString(1);
				titles.add(title);
			}
		}

		for (int k = 0; k < bookIds.size(); k++) {
			String query4 = "SELECT authorName FROM tbl_author WHERE authorId = ?;";
			PreparedStatement stmt4 = conn.prepareStatement(query4);
			stmt4.setInt(1, authorIds.get(k));
			ResultSet rset4 = stmt4.executeQuery();
			while (rset4.next()) {
				String author = rset4.getString(1);
				authors.add(author);
			}
		}

		for (int q = 0; q < bookIds.size(); q++) {
			System.out.println(q + 1 + ": " + titles.get(q) + ", by " + authors.get(q));
		}

		System.out.println(i + ": " + "Quit to previous");
		return i;
	}

	public int choiceToBookId(int choice, int branchChoice, Connection conn) throws SQLException {
		ArrayList<Integer> bookIds = new ArrayList<Integer>();
		ArrayList<Integer> branchIds = new ArrayList<Integer>();

		String query = "SELECT branchId FROM tbl_library_branch;";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rset = stmt.executeQuery();
		int branchId = 0;
		while (rset.next()) {
			branchId = rset.getInt(1);
			branchIds.add(branchId);

		}

		query = "SELECT bookId FROM tbl_book_copies WHERE branchId = ? AND noOfCopies != 0;";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, branchIds.get(branchChoice - 1));
		rset = stmt.executeQuery();
		int bookId = 0;
		while (rset.next()) {
			bookId = rset.getInt(1);
			bookIds.add(bookId);

		}

		int result = bookIds.get(choice - 1);

		return result;
	}

	public static String bookIdtoString(int bookId, Connection conn) throws SQLException {

		String query = "SELECT title FROM tbl_book WHERE bookId = ?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, bookId);
		ResultSet rs = stmt.executeQuery();
		String title = null;
		while (rs.next()) {
			title = rs.getString(1);
		}

		return title;
	}

	public static String branchIdtoString(int branchId, Connection conn) throws SQLException {

		String query = "SELECT branchName FROM tbl_library_branch WHERE branchId = ?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, branchId);
		ResultSet rs = stmt.executeQuery();
		String branch = null;
		while (rs.next()) {
			branch = rs.getString(1);
		}

		return branch;
	}

	public static void checkOutBook(int bookChoice, int branch, int cardNo, Connection conn) throws SQLException {
		ArrayList<Integer> bookIds = new ArrayList<Integer>();
		ArrayList<Integer> branchIds = new ArrayList<Integer>();

		String query = "SELECT branchId FROM tbl_library_branch;";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rset = stmt.executeQuery();
		int branchId = 0;
		while (rset.next()) {
			branchId = rset.getInt(1);
			branchIds.add(branchId);

		}

		query = "SELECT bookId FROM tbl_book_copies WHERE branchId = ? AND noOfCopies != 0;";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, branchIds.get(branch - 1));
		rset = stmt.executeQuery();
		int bookId = 0;
		while (rset.next()) {
			bookId = rset.getInt(1);
			bookIds.add(bookId);

		}

		query = "UPDATE tbl_book_copies SET noOfCopies = noOfCopies -1 WHERE bookId = ? AND branchId = ? AND noOfCopies > 0";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, bookChoice);
		stmt.setInt(2, branchIds.get(branch - 1));
		stmt.executeUpdate();

		String query2 = "INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) VALUES (?,?,?,?,?);";
		PreparedStatement stmt2 = conn.prepareStatement(query2);
		stmt2.setInt(1, bookChoice);
		stmt2.setInt(2, branchIds.get(branch - 1));
		stmt2.setInt(3, cardNo);
		stmt2.setTimestamp(4, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
		stmt2.setTimestamp(5, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now().plusDays(7)));
		stmt2.executeUpdate();

	}

	public static boolean checkIfAlreadyOut(int bookChoice, int branch, int cardNo, Connection conn)
			throws SQLException {

		ArrayList<Integer> branchIds = new ArrayList<Integer>();

		String query = "SELECT branchId FROM tbl_library_branch;";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rset = stmt.executeQuery();
		int branchId = 0;
		while (rset.next()) {
			branchId = rset.getInt(1);
			branchIds.add(branchId);

		}
		
		
		 query = "SELECT COUNT(*) FROM tbl_book_loans WHERE cardNo = ? AND bookId = ? AND branchID = ? AND dateIn IS NULL";
		 stmt = conn.prepareStatement(query);
		stmt.setInt(1, cardNo);
		stmt.setInt(2, bookChoice);
		stmt.setInt(3, branchIds.get(branch-1));
		 rset = stmt.executeQuery();

		int output = 0;
		while (rset.next()) {
			output = rset.getInt(1);
		}

		if (output == 0) {
			return false;
		} else {
			return true;
		}

	}

	public static void returnBook(int cardNo, Connection conn) throws SQLException {

		Scanner in = new Scanner(System.in);
		String query = "SELECT * FROM tbl_book_loans WHERE cardNo = ? AND dateIn IS NULL";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, cardNo);
		ResultSet rset = stmt.executeQuery();

		ArrayList<Integer> bookIds = new ArrayList<Integer>();
		ArrayList<Integer> branchIds = new ArrayList<Integer>();

		int i = 1;
		System.out.println("Please select a book to return");
		while (rset.next()) {
			int bookId = rset.getInt(1);
			bookIds.add(bookId);

			int branchId = rset.getInt(2);
			branchIds.add(branchId);

			System.out.println(
					i + ": " + bookIdtoString(bookId, conn) + ", rented out by: " + branchIdtoString(branchId, conn));

			i++;

		}

		System.out.println(i + ": Quit to previous");

		int returnSelection = 0;
		try {
			returnSelection = in.nextInt();
			in.nextLine();
			if (returnSelection == i || returnSelection > i) {
				return;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer");
			return;
		}

		String query2 = "UPDATE tbl_book_copies SET noOfCopies = noOfCopies +1 WHERE bookId = ? AND branchId = ? ";
		PreparedStatement stmt2 = conn.prepareStatement(query2);
		stmt2.setInt(1, bookIds.get(returnSelection - 1));
		stmt2.setInt(2, branchIds.get(returnSelection - 1));
		stmt2.executeUpdate();

		String query3 = "DELETE FROM tbl_book_loans WHERE branchId = ? AND bookId = ? AND cardNo = ?;";
		PreparedStatement stmt3 = conn.prepareStatement(query3);
		stmt3.setInt(1, branchIds.get(returnSelection - 1));
		stmt3.setInt(2, bookIds.get(returnSelection - 1));
		stmt3.setInt(3, cardNo);
		stmt3.executeUpdate();

	}

}
