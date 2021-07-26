package libraryManagementSystem;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import oracle.sql.*;
import oracle.jdbc.driver.*;

//Singleton Class representation of Librarian
public class Librarian {
	public static volatile Librarian lSoleInstance;

	private Librarian() {

		// Prevent form the reflection api.
		if (lSoleInstance != null) {
			throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
		}
	}

	public static Librarian getInstance() {
		if (lSoleInstance == null) { // if there is no instance available... create new one
			synchronized (Librarian.class) {
				if (lSoleInstance == null)
					lSoleInstance = new Librarian();
			}
		}

		return lSoleInstance;
	}

	protected Librarian readResolve() {
		return getInstance();
	}

	public  void updateBranchDetails(String branchName1, String location, String newName, String newLocation,
			Connection conn) throws SQLException {

		if (newName == "") {
			String query = "UPDATE tbl_library_branch SET branchAddress = ? WHERE branchName = ? AND branchAddress = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, newLocation);
			stmt.setString(2, branchName1);
			stmt.setString(3, location);

			stmt.executeUpdate();

		} else if (newLocation == "") {
			String query = "UPDATE tbl_library_branch SET branchName = ? WHERE branchName = ? AND branchAddress = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, newName);
			stmt.setString(2, branchName1);
			stmt.setString(3, location);

			stmt.executeUpdate();
		} else {
			String query = "UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchName = ? AND branchAddress = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, newName);
			stmt.setString(2, newLocation);
			stmt.setString(3, branchName1);
			stmt.setString(4, location);

			stmt.executeUpdate();
		}

	}

	public  int returnBookId(String searchTitle, Connection conn) throws SQLException {
		int bookId = 0;

		String query = "SELECT bookId FROM tbl_book WHERE title =?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, searchTitle);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			bookId = rs.getInt(1);
		}

		return bookId;
	}

	public  String findAuthorWithTitle(String title, Connection conn) throws SQLException {

		int bookIdHolder = returnBookId(title, conn);

		String query2 = "SELECT authorId FROM tbl_book_authors WHERE bookId = ?;";
		PreparedStatement stmt2 = conn.prepareStatement(query2);
		stmt2.setInt(1, bookIdHolder);

		ResultSet rset2 = stmt2.executeQuery();

		int authorIdHolder = 0;
		while (rset2.next()) {
			authorIdHolder = rset2.getInt(1);
		}

		String query3 = "SELECT authorName FROM tbl_author WHERE authorId = ?;";
		PreparedStatement stmt3 = conn.prepareStatement(query3);
		stmt3.setInt(1, authorIdHolder);

		ResultSet rset3 = stmt3.executeQuery();

		String authorName = "";
		while (rset3.next()) {
			authorName = rset3.getString(1);
		}

		return authorName;
	}

	public  int displayBooks(Connection conn) throws SQLException {

		
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT title FROM tbl_book");

		Statement stmt2 = conn.createStatement();
		ResultSet rset2 = stmt2.executeQuery("SELECT authorName FROM tbl_author, tbl_book WHERE authorID = bookId");

		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<String> authors = new ArrayList<String>();
		
		Scanner in = new Scanner(System.in);

		int i = 1;
		while (rset.next()) {
			String title = rset.getString(1);
			titles.add(title);
			i++;
		}
		while (rset2.next()) {
			String author = rset2.getString(1);
			authors.add(author);
		}

		int j = 0;
		for (int q = 0; q < titles.size(); q++) {
			System.out.print(q + 1 + ": " + titles.get(q) + ", by ");
			System.out.println(findAuthorWithTitle(titles.get(q), conn));
		}

		System.out.println(i + ": " + "Quit to previous");
		
		int copiesChoice = 0;
		try {
			copiesChoice = in.nextInt();
			in.nextLine();
			if(copiesChoice <= 0 || copiesChoice >= i) {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Please enter a integer");
		}
		
		String query = "SELECT bookId FROM tbl_book WHERE title = ?";
		PreparedStatement stmt3 = conn.prepareStatement(query);
		stmt3.setString(1, titles.get(copiesChoice-1));
		ResultSet rs = stmt3.executeQuery();

		int bookIdToReturn = 0;
		while (rs.next()) {
			bookIdToReturn = rs.getInt(1);
		
		}
		
		return bookIdToReturn;
	}

	public  int choiceToBookId(int choice, int branchId, Connection conn) throws SQLException {
		ArrayList<Integer> bookIds = new ArrayList<Integer>();

		String query = "SELECT bookId FROM tbl_book_copies WHERE branchId = ?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, branchId);
		ResultSet rset = stmt.executeQuery();

		while (rset.next()) {
			int bookId = rset.getInt(1);
			bookIds.add(bookId);

		}

		Statement stmt2 = conn.createStatement();
		ResultSet rset2 = stmt2.executeQuery("SELECT title FROM tbl_book");
		ArrayList<String> titles = new ArrayList<String>();

		while (rset2.next()) {
			String title = rset2.getString(1);
			titles.add(title);

		}

		int bookId = returnBookId(titles.get(choice - 1), conn);

		return bookId;
	}

	public  void getNumCopies(int bookId, int branchId, Connection conn) throws SQLException {

		String query = "SELECT noOfCopies FROM tbl_book_copies WHERE bookId = ? AND branchID = ?;";
		PreparedStatement stmt = conn.prepareStatement(query);

		// Need to change bookId to bookId not user selection
		stmt.setInt(1, bookId);
		stmt.setInt(2, branchId);

		ResultSet rs = stmt.executeQuery();
		int numCop = 0;
		while (rs.next()) {
			numCop = rs.getInt(1);
		}

		System.out.println("Existing number of copies: " + numCop);

	}

	public void updateNumCopies(int bookId, int branchId, int newNumOfCopies, Connection conn)
			throws SQLException {

		String query = "UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchID = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, newNumOfCopies);
		stmt.setInt(2, bookId);
		stmt.setInt(3, branchId);

		stmt.executeUpdate();

	}

}
