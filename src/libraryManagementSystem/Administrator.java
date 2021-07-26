package libraryManagementSystem;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class Administrator implements Serializable {
	public static volatile Administrator ASoleInstance;

	private Administrator() {

		// Prevent form the reflection api.
		if (ASoleInstance != null) {
			throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
		}
	}

	public static Administrator getInstance() {
		if (ASoleInstance == null) { // if there is no instance available... create new one
			synchronized (Administrator.class) {
				if (ASoleInstance == null)
					ASoleInstance = new Administrator();
			}
		}

		return ASoleInstance;
	}

	protected Administrator readResolve() {
		return getInstance();
	}
	
	//Functions to display CRUD menus
	public int CRUDbooks() {
		System.out.println("You have selected the Books table to perform operations");
		System.out.println("Which operation would you like to perform?");
		System.out.println("1 :Add book/author");
		System.out.println("2 :Update book/author");
		System.out.println("3 :Delete book/author");
		System.out.println("4 :Read book/author");
		System.out.println("5 :Quit to previous");

		Scanner in = new Scanner(System.in);
		int CRUD_Books_Choice = 0;

		try {
			CRUD_Books_Choice = in.nextInt();
			in.nextLine();
			if (CRUD_Books_Choice > 5 || CRUD_Books_Choice < 1) {
				System.out.println("Please enter an integer 1-5");
				CRUD_Books_Choice = 0;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer 1-5");
			CRUD_Books_Choice = 0;
		}

		return CRUD_Books_Choice;
	}
	public int CRUDGenre() {

		System.out.println("You have selected the Genre table to perform operations");
		System.out.println("Which operation would you like to perform?");
		System.out.println("1 :Add Genre");
		System.out.println("2 :Update Genre");
		System.out.println("3 :Delete Genre");
		System.out.println("4 :Read Genre");
		System.out.println("5 :Quit to previous");

		Scanner in = new Scanner(System.in);
		int CRUD_genre_Choice = 0;

		try {
			CRUD_genre_Choice = in.nextInt();
			in.nextLine();
			if (CRUD_genre_Choice > 5 || CRUD_genre_Choice < 1) {
				System.out.println("Please enter an integer 1-5");
				CRUD_genre_Choice = 0;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer 1-5");
			CRUD_genre_Choice = 0;
		}

		return CRUD_genre_Choice;

	}
	public int CRUDBranches() {

		System.out.println("You have selected the branch table to perform operations");
		System.out.println("Which operation would you like to perform?");
		System.out.println("1 :Add Branch");
		System.out.println("2 :Update Branch");
		System.out.println("3 :Delete Branch");
		System.out.println("4 :Read Branch");
		System.out.println("5 :Quit to previous");

		Scanner in = new Scanner(System.in);
		int CRUD_branch_Choice = 0;

		try {
			CRUD_branch_Choice = in.nextInt();
			in.nextLine();
			if (CRUD_branch_Choice > 5 || CRUD_branch_Choice < 1) {
				System.out.println("Please enter an integer 1-5");
				CRUD_branch_Choice = 0;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer 1-5");
			CRUD_branch_Choice = 0;
		}

		return CRUD_branch_Choice;

	}
	public int CRUDBorrower() {

		System.out.println("You have selected the borrower table to perform operations");
		System.out.println("Which operation would you like to perform?");
		System.out.println("1 :Add Borrower");
		System.out.println("2 :Update Borrower");
		System.out.println("3 :Delete Borrower");
		System.out.println("4 :Read Borrower");
		System.out.println("5 :Quit to previous");

		Scanner in = new Scanner(System.in);
		int CRUD_branch_Choice = 0;

		try {
			CRUD_branch_Choice = in.nextInt();
			in.nextLine();
			if (CRUD_branch_Choice > 5 || CRUD_branch_Choice < 1) {
				System.out.println("Please enter an integer 1-5");
				CRUD_branch_Choice = 0;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer 1-5");
			CRUD_branch_Choice = 0;
		}

		return CRUD_branch_Choice;

	}
	public int CRUDPublish() {

		System.out.println("You have selected the Publisher table to perform operations");
		System.out.println("Which operation would you like to perform?");
		System.out.println("1 :Add Publisher");
		System.out.println("2 :Update Publisher");
		System.out.println("3 :Delete Publisher");
		System.out.println("4 :Read Publishers");
		System.out.println("5 :Quit to previous");

		Scanner in = new Scanner(System.in);
		int CRUD_publish_Choice = 0;

		try {
			CRUD_publish_Choice = in.nextInt();
			in.nextLine();
			if (CRUD_publish_Choice > 5 || CRUD_publish_Choice < 1) {
				System.out.println("Please enter an integer 1-5");
				CRUD_publish_Choice = 0;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer 1-5");
			CRUD_publish_Choice = 0;
		}

		return CRUD_publish_Choice;

	}
	public int displayChoices() {
		System.out.println("Welcome Administrator: Please choose an option:");
		System.out.println("1: Add/Update/Delete/Read Books and Authors");
		System.out.println("2 :Add/Update/Delete/Read Genres");
		System.out.println("3 :Add/Update/Delete/Read Publishers");
		System.out.println("4 :Add/Update/Delete/Read Library Branches");
		System.out.println("5 :Add/Update/Delete/Read Borrowers");
		System.out.println("6 :Override Due Date");
		System.out.println("7 :Show all books in LMS");
		System.out.println("8 :Quit to previous");

		Scanner in = new Scanner(System.in);
		int adminChoice = 0;

		try {
			adminChoice = in.nextInt();
			in.nextLine();
			if (adminChoice > 8 || adminChoice < 1) {
				System.out.println("Please enter an integer 1-8");
				adminChoice = 0;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer 1-8");
			adminChoice = 0;
		}

		return adminChoice;
	}

	//Methods to create new entries
	public int addGenre(String newGenre, Connection conn) throws SQLException {
		String query = "INSERT INTO tbl_genre (genre_name) VALUES (?); ";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, newGenre);
		stmt.executeUpdate();
		System.out.println("New Genre successfully added to tbl_genre");

		return 0;
	}
	public static void addAuthor(String authorName, Connection conn) throws SQLException {
		String query5 = "INSERT INTO tbl_author (authorName) VALUES (?)";
		PreparedStatement stmt5 = conn.prepareStatement(query5);
		stmt5.setString(1, authorName);
		stmt5.executeUpdate();
		System.out.println("Sucessfully added author to table");
	}
	public int addPublisher(String name, String address, String phoneNumber, Connection conn) throws SQLException {
		String query = "INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?,?,?); ";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, name);
		stmt.setString(2, address);
		stmt.setString(3, phoneNumber);
		stmt.executeUpdate();
		System.out.println("New publisher successfully added to tbl_publisher");

		return 0;
	}
	public int addBranch(String name, String address, Connection conn) throws SQLException {

		String query = "INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (?,?); ";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, name);
		stmt.setString(2, address);
		stmt.executeUpdate();
		System.out.println("New branch successfully added to tbl_library_branch");

		return 0;
	}
	public void addBorrower(String name, String address, String phoneNumber, Connection conn) throws SQLException {

		String query = "INSERT INTO tbl_borrower (name, address, phone) VALUES (?,?,?); ";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, name);
		stmt.setString(2, address);
		stmt.setString(3, phoneNumber);
		stmt.executeUpdate();
		System.out.println("New borrower successfully added to tbl_library_branch");

	}
	public void addBook(String title, int authorId, int genreId, int publishId, Connection conn) throws SQLException {

		System.out.println("*******ADDING BOOK TO TABLE*************");

		String query = "INSERT INTO tbl_book (title,pubId) VALUES (?,?);";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, title);
		stmt.setInt(2, publishId);
		stmt.executeUpdate();
		System.out.println("Book succesfully added to tbl_book");

		String query2 = "SELECT bookId from tbl_book WHERE title = ?;";
		PreparedStatement stmt2 = conn.prepareStatement(query2);
		stmt2.setString(1, title);
		ResultSet rs = stmt2.executeQuery();

		int bookId = 0;
		while (rs.next()) {
			bookId = rs.getInt(1);
		}
		System.out.println("BookId generated and recieved");

		String query3 = "INSERT INTO tbl_book_authors (bookId,authorId) VALUES (?,?);";
		PreparedStatement stmt3 = conn.prepareStatement(query3);
		stmt3.setInt(1, bookId);
		stmt3.setInt(2, authorId);
		stmt3.executeUpdate();
		System.out.println("Book connected to author via tbl_book_authors");



		ArrayList<Integer> branchIds = new ArrayList<Integer>();
		
		query3 = "SELECT branchId FROM tbl_library_branch";
		stmt3 = conn.prepareStatement(query3);
		rs = stmt3.executeQuery();
		
		
		while(rs.next()) {
			branchIds.add(rs.getInt(1));
		}
		
		
		
		int i = 1;
		int numBranches = getNumBranches(conn);
		while (i < branchIds.size() + 1) {
			String query4 = "INSERT INTO tbl_book_copies (bookId,branchId,noOfCopies) VALUES (?,?,5);";
			PreparedStatement stmt4 = conn.prepareStatement(query4);
			stmt4.setInt(1, bookId);
			stmt4.setInt(2, branchIds.get(i-1));
			stmt4.executeUpdate();
			System.out.println("Automatically added 5 copies to branchId" + i);
			i++;
		}

		String query5 = "INSERT INTO tbl_book_genres (genre_id,bookId) VALUES (?,?);";
		PreparedStatement stmt5 = conn.prepareStatement(query5);
		stmt5.setInt(1, genreId);
		stmt5.setInt(2, bookId);
		stmt5.executeUpdate();
		System.out.println("Successfully hooked up book and its genre");
	}
	
	//Helper Methods for creating new entries
	public int chooseGenre(Connection conn) throws SQLException {

		String query = "SELECT genre_name FROM tbl_genre;";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rset = stmt.executeQuery();

		ArrayList<String> genreNames = new ArrayList<String>();
		
		String genreName = "";
		int i = 1;
		while (rset.next()) {
			genreName = rset.getString(1);
			genreNames.add(genreName);
			System.out.println(i + ": " + genreName);
			i++;
		}

		System.out.println(i + ": Exit to previous");
		Scanner in = new Scanner(System.in);

		int userInput = 0;
		try {
			userInput = in.nextInt();
			in.nextLine();
			if (userInput < 1 || userInput == i || userInput > i) {
				userInput = 0;
				return userInput;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer");
		}

		
		 query = "SELECT genre_id FROM tbl_genre WHERE genre_name = ?;";
		 stmt = conn.prepareStatement(query);
		 stmt.setString(1, genreNames.get(userInput-1));
		 rset = stmt.executeQuery();
		
		 int genId = 0;
		 while(rset.next()) {
			 genId = rset.getInt(1);
		 }
		
		return genId;

	}
	public int choosePub(Connection conn) throws SQLException {

		System.out.println("Please select the publisher:");

		String query = "SELECT publisherName FROM tbl_publisher;";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rset = stmt.executeQuery();

		ArrayList<String> publisherNames = new ArrayList<String>();
		
		
		String publishName = "";
		int i = 1;
		while (rset.next()) {
			publishName = rset.getString(1);
			publisherNames.add(publishName);
			System.out.println(i + ": " + publishName);
			i++;
		}

		System.out.println(i + ": Exit to previous");
		Scanner in = new Scanner(System.in);

		int userInput = 0;
		try {
			userInput = in.nextInt();
			in.nextLine();
			if (userInput < 1 || userInput == i || userInput > i) {
				userInput = 0;
				return userInput;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer");
		}

		
		 query = "SELECT publisherId FROM tbl_publisher WHERE publisherName = ?;";
		 stmt = conn.prepareStatement(query);
		 stmt.setString(1, publisherNames.get(userInput-1));
		 rset = stmt.executeQuery();
		
		 int pubId = 0;
		 while(rset.next()) {
			 pubId = rset.getInt(1);
		 }
		
		return pubId;
	}
	public int chooseAuthor(Connection conn) throws SQLException {

		System.out.println("Please select the author of the book:");

		String query = "SELECT authorName FROM tbl_author;";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rset = stmt.executeQuery();

		ArrayList<String> authors = new ArrayList<String>();

		String authorName = "";
		int i = 1;
		while (rset.next()) {
			authorName = rset.getString(1);
			authors.add(authorName);
			System.out.println(i + ": " + authorName);
			i++;
		}

		System.out.println(i + ": Exit to previous");
		Scanner in = new Scanner(System.in);

		int userInput = 0;
		try {
			userInput = in.nextInt();
			in.nextLine();
			if (userInput < 1 || userInput == i || userInput > i) {
				userInput = 0;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer");
		}

		String query2 = "SELECT authorId FROM tbl_author WHERE authorName = ?;";
		PreparedStatement stmt2 = conn.prepareStatement(query2);
		stmt2.setString(1, authors.get(userInput - 1));
		ResultSet rset2 = stmt2.executeQuery();

		int result = 0;
		while (rset2.next()) {
			result = rset2.getInt(1);
		}

		return result;

	}
	
	//Update Methods for entries
	public void updateGenre(int userChoice, String newGenre, Connection conn) throws SQLException {
		String query = "UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, newGenre);
		stmt.setInt(2, userChoice);
		stmt.executeUpdate();
		System.out.println("Genre Name Succesfully updated");
	}
	public void updateBranch(String newName, String newAddress, int choice, Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select * from tbl_library_branch");

		ArrayList<Integer> branchIds = new ArrayList<Integer>();
		ArrayList<String> branchName = new ArrayList<String>();
		ArrayList<String> location = new ArrayList<String>();

		while (rset.next()) {

			String name = rset.getString(2);
			String local = rset.getString(3);

			branchName.add(name);
			location.add(local);

		}

		String query = "SELECT branchId FROM tbl_library_branch WHERE branchName = ? AND branchAddress = ?;";
		PreparedStatement stmt2 = conn.prepareStatement(query);
		stmt2.setString(1, branchName.get(choice - 1));
		stmt2.setString(2, location.get(choice - 1));
		rset = stmt2.executeQuery();

		int branchId = 0;
		while (rset.next()) {
			branchId = rset.getInt(1);
		}

		query = "UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?";
		stmt2 = conn.prepareStatement(query);
		stmt2.setString(1, newName);
		stmt2.setString(2, newAddress);
		stmt2.setInt(3, branchId);
		stmt2.executeUpdate();

	}
	public void updateBorrower(Connection conn) throws SQLException {

		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> addresses = new ArrayList<String>();
		ArrayList<String> phones = new ArrayList<String>();

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT name, address, phone FROM tbl_borrower");

		String name = null;
		String address = null;
		String phone = null;

		Scanner in = new Scanner(System.in);

		System.out.println("Please choose a borrower to update:");

		int i = 0;
		while (rset.next()) {
			name = rset.getString(1);
			address = rset.getString(2);
			phone = rset.getString(3);
			names.add(name);
			addresses.add(address);
			phones.add(phone);

		}
		int k = 0;
		for (; k < names.size(); k++) {
			System.out.println(k + 1 + ": " + names.get(k) + "_" + addresses.get(k) + "_" + phones.get(k));
		}

		System.out.println(k + 1 + " : Quit to previous");

		int userChoice = 0;
		try {
			userChoice = in.nextInt();
			in.nextLine();
			if (userChoice < 1 || userChoice >= k + 1) {
				return;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer");
			return;
		}

		System.out.println("Please enter the new name of the borrower");
		String newName = in.nextLine();

		System.out.println("Please enter the address of the new borrower");
		String newAddress = in.nextLine();

		System.out.println("Please enter the phone number of the new borrower");
		String newPhoneNumber = in.nextLine();

		String query = "SELECT cardNo FROM tbl_borrower WHERE name = ? AND address = ? AND phone = ?";
		PreparedStatement stmt2 = conn.prepareStatement(query);
		stmt2.setString(1, names.get(userChoice - 1));
		stmt2.setString(2, addresses.get(userChoice - 1));
		stmt2.setString(3, phones.get(userChoice - 1));
		rset = stmt2.executeQuery();
		System.out.println("Borrower cardNo located");

		int cardNoToUpdate = 0;
		while (rset.next()) {
			cardNoToUpdate = rset.getInt(1);
		}

		query = "UPDATE tbl_borrower SET name = ? , address = ?, phone = ? WHERE cardNo = ?";
		stmt2 = conn.prepareStatement(query);
		stmt2.setString(1, newName);
		stmt2.setString(2, newAddress);
		stmt2.setString(3, newPhoneNumber);
		stmt2.setInt(4, cardNoToUpdate);
		stmt2.executeUpdate();

		System.out.println("Sucessfully updated borrower details");

	}
	public static void updateAuthor(int userChoice, int oldAuth, Connection conn) throws SQLException {

		
		int toReplaceAuthId = getAuthId(oldAuth,conn);
		int bookId = getBookId(oldAuth,conn);
		
		String query5 = "UPDATE tbl_book_authors SET authorId = ? WHERE authorId = ? AND bookId = ?";
		PreparedStatement stmt5 = conn.prepareStatement(query5);
		stmt5.setInt(1, userChoice);
		stmt5.setInt(2, toReplaceAuthId);
		stmt5.setInt(3, bookId);
		stmt5.executeUpdate();
		System.out.println("Sucessfully updated name of author");

	}
	public static void updatePub(int choice, String newName, String newAddress, String newPhoneNumber, Connection conn)
			throws SQLException {
		String query = "UPDATE tbl_publisher SET publisherName = ? , publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, newName);
		stmt.setString(2, newAddress);
		stmt.setString(3, newPhoneNumber);
		stmt.setInt(4, choice);
		stmt.executeUpdate();

	}
	public static void updateTitle(int userChoice, String newTitle, Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT title FROM tbl_book");

		Statement stmt2 = conn.createStatement();
		ResultSet rset2 = stmt2.executeQuery("SELECT authorName FROM tbl_author, tbl_book WHERE authorID = bookId");

		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<String> authors = new ArrayList<String>();

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
		
		
		 String query3 = "SELECT bookId FROM tbl_book WHERE title =?";
		 PreparedStatement stmt3 = conn.prepareStatement(query3);
		 stmt3.setString(1, titles.get(userChoice-1));
		 ResultSet rs = stmt3.executeQuery();
		 
		 int bookId = 0;
		 while(rs.next()) {
			 bookId = rs.getInt(1);
		 }
		

		String query = "UPDATE tbl_book SET title = ? WHERE bookId = ?";
		PreparedStatement stmt4 = conn.prepareStatement(query);
		stmt4.setString(1, newTitle);
		stmt4.setInt(2, bookId);
		stmt4.executeUpdate();
		System.out.println("Sucessfully updated name of book");
	
		

	}
	
	//Delete Methods for entries
	public void deleteGenre(int userChoice, Connection conn) throws SQLException {
		String query = "SELECT genre_name FROM tbl_genre;";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rset = stmt.executeQuery();


		ArrayList<Integer> bookIds = new ArrayList<Integer>();
		query = "SELECT bookId FROM tbl_book_genres WHERE genre_id = ?;";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, userChoice);
		rset = stmt.executeQuery();

		int book_id = 0;
		while (rset.next()) {
			book_id = rset.getInt(1);
			bookIds.add(book_id);
		}

		System.out.println("***********Preparing to delete books****************");
		System.out.println("***********Deleting all books which match genre****************");

		for (int i = 0; i < bookIds.size(); i++) {
			String query6 = "DELETE FROM tbl_book_genres WHERE bookId = ?";
			PreparedStatement stmt2 = conn.prepareStatement(query6);
			stmt2.setInt(1, bookIds.get(i));
			stmt2.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book_genres");

			String query2 = "DELETE FROM tbl_book_loans WHERE bookId = ?";
			PreparedStatement stmt3 = conn.prepareStatement(query2);
			stmt3.setInt(1, bookIds.get(i));
			stmt3.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book_loans");

			String query3 = "DELETE FROM tbl_book_copies WHERE bookId = ?";
			PreparedStatement stmt4 = conn.prepareStatement(query3);
			stmt4.setInt(1, bookIds.get(i));
			stmt4.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book_copies");

			String query4 = "DELETE FROM tbl_book_authors WHERE bookId = ?";
			PreparedStatement stmt5 = conn.prepareStatement(query4);
			stmt5.setInt(1, bookIds.get(i));
			stmt5.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book_authors");

			String query5 = "DELETE FROM tbl_book WHERE bookId = ?";
			PreparedStatement stmt6 = conn.prepareStatement(query5);
			stmt6.setInt(1, bookIds.get(i));
			stmt6.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book");
			System.out.println("Book sucessfully removed");
		}

		query = "DELETE FROM tbl_genre WHERE genre_id = ?";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, userChoice);

		stmt.executeUpdate();
		System.out.println("Genre succesfully removed drom tbl_genre");

		query = "DELETE FROM tbl_book_genres WHERE genre_id = ?";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, userChoice);

		stmt.executeUpdate();
		System.out.println("Genre succesfully removed drom tbl_book_genres");

	}
	public void deleteBranch(int userChoice, Connection conn) throws SQLException {
		ArrayList<String> branchNames = new ArrayList<String>();

		String query = "SELECT branchName FROM tbl_library_branch;";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rset = stmt.executeQuery();

		String branchName = "";

		while (rset.next()) {
			branchName = rset.getString(1);
			branchNames.add(branchName);

		}

		query = "SELECT branchId FROM tbl_library_branch WHERE branchName = ?";
		stmt = conn.prepareStatement(query);
		stmt.setString(1, branchNames.get(userChoice - 1));
		rset = stmt.executeQuery();

		int branchId = 0;
		while (rset.next()) {
			branchId = rset.getInt(1);
		}

		ArrayList<Integer> bookIds = new ArrayList<Integer>();

		query = "SELECT bookId FROM tbl_book_copies WHERE branchId = ?";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, branchId);
		rset = stmt.executeQuery();

		int bookId = 0;
		while (rset.next()) {
			bookId = rset.getInt(1);
			bookIds.add(bookId);
		}

		for (int i = 0; i < bookIds.size(); i++) {
			String query6 = "DELETE FROM tbl_book_genres WHERE bookId = ?";
			PreparedStatement stmt2 = conn.prepareStatement(query6);
			stmt2.setInt(1, bookIds.get(i));
			stmt2.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book_genres");

			String query2 = "DELETE FROM tbl_book_loans WHERE bookId = ?";
			PreparedStatement stmt3 = conn.prepareStatement(query2);
			stmt3.setInt(1, bookIds.get(i));
			stmt3.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book_loans");

			String query3 = "DELETE FROM tbl_book_copies WHERE bookId = ?";
			PreparedStatement stmt4 = conn.prepareStatement(query3);
			stmt4.setInt(1, bookIds.get(i));
			stmt4.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book_copies");

			String query4 = "DELETE FROM tbl_book_authors WHERE bookId = ?";
			PreparedStatement stmt5 = conn.prepareStatement(query4);
			stmt5.setInt(1, bookIds.get(i));
			stmt5.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book_authors");

			String query5 = "DELETE FROM tbl_book WHERE bookId = ?";
			PreparedStatement stmt6 = conn.prepareStatement(query5);
			stmt6.setInt(1, bookIds.get(i));
			stmt6.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book");
			System.out.println("Book sucessfully removed");
		}

		query = "DELETE FROM tbl_library_branch  WHERE branchId = ?";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, branchId);
		stmt.executeUpdate();
		System.out.println("Branch Successfully deleted");

	}
	public void deletePub(int publishId, Connection conn) throws SQLException {

	

		String query = "SELECT bookId FROM tbl_book WHERE pubId = ?";
		PreparedStatement stmt1 = conn.prepareStatement(query);
		stmt1.setInt(1, publishId);
		ResultSet rset = stmt1.executeQuery();

		ArrayList<Integer> bookIdsToBeRemoved = new ArrayList<Integer>();

		int bookId = 0;
		while (rset.next()) {
			bookId = rset.getInt(1);
			bookIdsToBeRemoved.add(bookId);
		}

		System.out.println("***********Preparing to delete book****************");
		for (int i = 0; i < bookIdsToBeRemoved.size(); i++) {
			String query2 = "DELETE FROM tbl_book_genres WHERE bookId = ?";
			PreparedStatement stmt2 = conn.prepareStatement(query2);
			stmt2.setInt(1, bookIdsToBeRemoved.get(i));
			stmt2.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book_genres");

			String query3 = "DELETE FROM tbl_book_loans WHERE bookId = ?";
			PreparedStatement stmt3 = conn.prepareStatement(query3);
			stmt3.setInt(1, bookIdsToBeRemoved.get(i));
			stmt3.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book_loans");

			String query4 = "DELETE FROM tbl_book_copies WHERE bookId = ?";
			PreparedStatement stmt4 = conn.prepareStatement(query4);
			stmt4.setInt(1, bookIdsToBeRemoved.get(i));
			stmt4.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book_copies");

			String query5 = "DELETE FROM tbl_book_authors WHERE bookId = ?";
			PreparedStatement stmt5 = conn.prepareStatement(query5);
			stmt5.setInt(1, bookIdsToBeRemoved.get(i));
			stmt5.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book_authors");

			String query6 = "DELETE FROM tbl_book WHERE bookId = ?";
			PreparedStatement stmt6 = conn.prepareStatement(query6);
			stmt6.setInt(1, bookIdsToBeRemoved.get(i));
			stmt6.executeUpdate();
			System.out.println("Book succesfully removed drom tbl_book");
			System.out.println("Book sucessfully removed");
		}

		query = "DELETE FROM tbl_publisher WHERE publisherId = ?";
		stmt1 = conn.prepareStatement(query);
		stmt1.setInt(1, publishId);
		stmt1.executeUpdate();
		System.out.println("Publisher succesfully removed drom tbl_publisher");

	}
	public static void deleteBook(int userChoice, Connection conn) throws SQLException {

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT title FROM tbl_book");

		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<Integer> bookIds = new ArrayList<Integer>();

		int i = 1;
		while (rset.next()) {
			String title = rset.getString(1);
			titles.add(title);
			i++;
		}

		for (int q = 0; q < titles.size(); q++) {
			bookIds.add(returnBookId(titles.get(q), conn));

		}
		System.out.println("***********Preparing to delete book****************");

		String query = "DELETE FROM tbl_book_genres WHERE bookId = ?";
		PreparedStatement stmt2 = conn.prepareStatement(query);
		stmt2.setInt(1, bookIds.get(userChoice - 1));
		stmt2.executeUpdate();
		System.out.println("Book succesfully removed drom tbl_book_genres");

		String query2 = "DELETE FROM tbl_book_loans WHERE bookId = ?";
		PreparedStatement stmt3 = conn.prepareStatement(query2);
		stmt3.setInt(1, bookIds.get(userChoice - 1));
		stmt3.executeUpdate();
		System.out.println("Book succesfully removed drom tbl_book_loans");

		String query3 = "DELETE FROM tbl_book_copies WHERE bookId = ?";
		PreparedStatement stmt4 = conn.prepareStatement(query3);
		stmt4.setInt(1, bookIds.get(userChoice - 1));
		stmt4.executeUpdate();
		System.out.println("Book succesfully removed drom tbl_book_copies");

		String query4 = "DELETE FROM tbl_book_authors WHERE bookId = ?";
		PreparedStatement stmt5 = conn.prepareStatement(query4);
		stmt5.setInt(1, bookIds.get(userChoice - 1));
		stmt5.executeUpdate();
		System.out.println("Book succesfully removed drom tbl_book_authors");

		String query5 = "DELETE FROM tbl_book WHERE bookId = ?";
		PreparedStatement stmt6 = conn.prepareStatement(query5);
		stmt6.setInt(1, bookIds.get(userChoice - 1));
		stmt6.executeUpdate();
		System.out.println("Book succesfully removed drom tbl_book");
		System.out.println("Book sucessfully removed");

	}
	public static void deleteAuth(int userChoice, Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT authorName FROM tbl_author;");

		ArrayList<String> authors = new ArrayList<String>();
		ArrayList<Integer> bookIds = new ArrayList<Integer>();

		while (rset.next()) {
			String author = rset.getString(1);
			authors.add(author);
		}

		ArrayList<String> deDupedList = removeDuplicates(authors);

		String authorName = deDupedList.get(userChoice - 1);

		String query = "SELECT authorId FROM tbl_author WHERE authorName = ?";
		PreparedStatement stmt1 = conn.prepareStatement(query);
		stmt1.setString(1, authorName);
		ResultSet rset2 = stmt1.executeQuery();

		int authorId = 0;
		while (rset2.next()) {
			authorId = rset2.getInt(1);
		}

		String query1 = "SELECT bookId FROM tbl_book_authors WHERE authorId = ?";
		PreparedStatement stmt2 = conn.prepareStatement(query1);
		stmt2.setInt(1, authorId);
		ResultSet rset1 = stmt2.executeQuery();

		int bookId = 0;
		while (rset1.next()) {
			bookId = rset1.getInt(1);
			bookIds.add(bookId);
		}
		if (!bookIds.isEmpty()) {
			// Deleting an author should delete all their books
			for (int i = 0; i < bookIds.size(); i++) {
				String query4 = "DELETE FROM tbl_book_genres WHERE bookId = ?";
				PreparedStatement stmt5 = conn.prepareStatement(query4);
				stmt5.setInt(1, bookIds.get(i));
				stmt5.executeUpdate();
				System.out.println("Author's book succesfully removed from tbl_book_genres");

				String query5 = "DELETE FROM tbl_book_loans WHERE bookId = ?";
				PreparedStatement stmt6 = conn.prepareStatement(query5);
				stmt6.setInt(1, bookIds.get(i));
				stmt6.executeUpdate();
				System.out.println("Author's book succesfully removed from tbl_book_loans");

				String query6 = "DELETE FROM tbl_book_copies WHERE bookId = ?";
				PreparedStatement stmt7 = conn.prepareStatement(query6);
				stmt7.setInt(1, bookIds.get(i));
				stmt7.executeUpdate();
				System.out.println("Author's book succesfully removed from tbl_book_copies");

				String query7 = "DELETE FROM tbl_book_authors WHERE bookId = ?";
				PreparedStatement stmt8 = conn.prepareStatement(query7);
				stmt8.setInt(1, bookIds.get(i));
				stmt8.executeUpdate();
				System.out.println("Author's book succesfully removed from tbl_book_authors");

				String query8 = "DELETE FROM tbl_book WHERE bookId = ?";
				PreparedStatement stmt9 = conn.prepareStatement(query8);
				stmt9.setInt(1, bookIds.get(i));
				stmt9.executeUpdate();
				System.out.println("Book succesfully removed from tbl_book");
				System.out.println("Book sucessfully removed");
			}
		}

		String query2 = "DELETE FROM tbl_book_authors WHERE authorId = ?";
		PreparedStatement stmt3 = conn.prepareStatement(query2);
		stmt3.setInt(1, authorId);
		stmt3.executeUpdate();
		System.out.println("Book succesfully removed from tbl_book_authors");

		String query3 = "DELETE FROM tbl_author WHERE authorId = ?";
		PreparedStatement stmt4 = conn.prepareStatement(query3);
		stmt4.setInt(1, authorId);
		stmt4.executeUpdate();
		System.out.println("Book succesfully removed from tbl_author");

	}
	public static void deleteBorrower(Connection conn) throws SQLException {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> addresses = new ArrayList<String>();
		ArrayList<String> phones = new ArrayList<String>();

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT name, address, phone FROM tbl_borrower");

		String name = null;
		String address = null;
		String phone = null;

		Scanner in = new Scanner(System.in);

		System.out.println("Please choose a borrower to delete:");

		int i = 0;
		while (rset.next()) {
			name = rset.getString(1);
			address = rset.getString(2);
			phone = rset.getString(3);
			names.add(name);
			addresses.add(address);
			phones.add(phone);

		}
		int k = 0;
		for (; k < names.size(); k++) {
			System.out.println(k + 1 + ": " + names.get(k) + "_" + addresses.get(k) + "_" + phones.get(k));
		}

		System.out.println(k + 1 + " : Quit to previous");

		int userChoice = 0;
		try {
			userChoice = in.nextInt();
			in.nextLine();
			if (userChoice < 1 || userChoice >= k + 1) {
				return;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer");
			return;
		}

		String query = "SELECT cardNo FROM tbl_borrower WHERE name = ? AND address = ? AND phone = ?";
		PreparedStatement stmt2 = conn.prepareStatement(query);
		stmt2.setString(1, names.get(userChoice - 1));
		stmt2.setString(2, addresses.get(userChoice - 1));
		stmt2.setString(3, phones.get(userChoice - 1));
		rset = stmt2.executeQuery();
		System.out.println("Borrower cardNo located");

		int cardNoToUpdate = 0;
		while (rset.next()) {
			cardNoToUpdate = rset.getInt(1);
		}

		query = "DELETE FROM tbl_book_loans WHERE cardNo = ?";
		stmt2 = conn.prepareStatement(query);
		stmt2.setInt(1, cardNoToUpdate);
		stmt2.executeUpdate();
		System.out.println("Borrower loans deleted from tbl_book_loans");

		query = "DELETE FROM tbl_borrower WHERE cardNo = ?";
		stmt2 = conn.prepareStatement(query);
		stmt2.setInt(1, cardNoToUpdate);
		stmt2.executeUpdate();
		System.out.println("Borrower  deleted from tbl_borrower");

	}

	


	//Methods for printing large amounts of data
	public static int printOutAllAuthors(Connection conn) throws SQLException {

		Statement stmt2 = conn.createStatement();
		ResultSet rset2 = stmt2.executeQuery("SELECT authorName FROM tbl_author;");

		ArrayList<String> authors = new ArrayList<String>();

		while (rset2.next()) {
			String author = rset2.getString(1);
			authors.add(author);
		}

		ArrayList<String> deDupedList = removeDuplicates(authors);

		int j = 0;
		for (int q = 0; q < authors.size(); q++) {

			System.out.println(q + 1 + ": " + deDupedList.get(q));
			j++;
		}

		System.out.println(j + 1 + ": " + "Quit to previous");
		return authors.size();
	}
	public static int printOutAllBooks(Connection conn) throws SQLException {

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT title FROM tbl_book");

		Statement stmt2 = conn.createStatement();
		ResultSet rset2 = stmt2.executeQuery("SELECT authorName FROM tbl_author, tbl_book WHERE authorID = bookId");

		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<String> authors = new ArrayList<String>();

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

		return titles.size();
	}
	public void showAllBooksInfo(Connection conn) throws SQLException {

		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<String> authors = new ArrayList<String>();
		ArrayList<String> genres = new ArrayList<String>();
		ArrayList<String> publishers = new ArrayList<String>();

		ArrayList<Integer> bookIds = new ArrayList<Integer>();
		ArrayList<Integer> pubIds = new ArrayList<Integer>();
		ArrayList<Integer> authorIds = new ArrayList<Integer>();
		ArrayList<Integer> genreIds = new ArrayList<Integer>();

		String query = "SELECT title FROM tbl_book";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();

		String name = null;
		while (rs.next()) {
			name = rs.getString(1);
			titles.add(name);
		}

		for (int i = 0; i < titles.size(); i++) {
			query = "SELECT bookId FROM tbl_book WHERE title = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, titles.get(i));
			rs = stmt.executeQuery();
			while (rs.next()) {
				bookIds.add(rs.getInt(1));
			}
		}

		for (int i = 0; i < titles.size(); i++) {
			query = "SELECT pubId FROM tbl_book WHERE bookId = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, bookIds.get(i));
			rs = stmt.executeQuery();
			while (rs.next()) {
				pubIds.add(rs.getInt(1));
			}
		}

		for (int i = 0; i < titles.size(); i++) {
			query = "SELECT authorId FROM tbl_book_authors WHERE bookId = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, bookIds.get(i));
			rs = stmt.executeQuery();
			while (rs.next()) {
				authorIds.add(rs.getInt(1));
			}
		}
		
		for (int i = 0; i < titles.size(); i++) {
			query = "SELECT genre_id FROM tbl_book_genres WHERE bookId = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, bookIds.get(i));
			rs = stmt.executeQuery();
			while (rs.next()) {
				genreIds.add(rs.getInt(1));
			}
		}
		
		for (int i = 0; i < authorIds.size(); i++) {
			query = "SELECT authorName FROM tbl_author WHERE authorId = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, authorIds.get(i));
			rs = stmt.executeQuery();
			while (rs.next()) {
				authors.add(rs.getString(1));
			}
		}
		
		for (int i = 0; i < genreIds.size(); i++) {
			query = "SELECT genre_name FROM tbl_genre WHERE genre_id = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, genreIds.get(i));
			rs = stmt.executeQuery();
			while (rs.next()) {
				genres.add(rs.getString(1));
			}
		}
		
		for (int i = 0; i < pubIds.size(); i++) {
			query = "SELECT publisherName FROM tbl_publisher WHERE publisherId = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, pubIds.get(i));
			rs = stmt.executeQuery();
			while (rs.next()) {
				publishers.add(rs.getString(1));
			}
		}
		
		int i = 0;
		for (i = 0; i < titles.size(); i++) {
			System.out.println(i+1 + " :" + titles.get(i) + ", by " + authors.get(i) + " Genre: " + genres.get(i) + " Published by: " + publishers.get(i) + ".");
		}
		
		System.out.println(i+1 + ": Exit to previous");
		
		
		Scanner in = new Scanner(System.in);
		int userInput = 0;
		try {
			userInput = in.nextInt();
			in.nextLine();
			if(userInput == i+1) {
				return;
			}
		}catch(Exception e) {
			System.out.println("Please enter an integer");
			return;
		}

		System.out.println("Book succesfully added to tbl_book");

	}
	
	
	//Useful helper method 
	public static int getAuthId(int userChoice, Connection conn) throws SQLException {

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT title FROM tbl_book");

		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<String> authorNames = new ArrayList<String>();

		int i = 1;
		while (rset.next()) {
			String title = rset.getString(1);
			titles.add(title);
			i++;
		}

		for (int q = 0; q < titles.size(); q++) {
			authorNames.add(findAuthorWithTitle(titles.get(q), conn));

		}

		String query = "SELECT authorId FROM tbl_author WHERE authorName = ?";
		PreparedStatement stmt2 = conn.prepareStatement(query);
		stmt2.setString(1, authorNames.get(userChoice - 1));
		ResultSet rs = stmt2.executeQuery();

		int authId = 0;
		while (rs.next()) {
			authId = rs.getInt(1);
		}

		return authId;
	}
	public static int returnBookId(String searchTitle, Connection conn) throws SQLException {
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
	public static String findAuthorWithTitle(String title, Connection conn) throws SQLException {

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
	public static int getNumBranches(Connection conn) throws SQLException {

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT COUNT(DISTINCT branchId) from tbl_library_branch");
		int numbBranches = 0;
		while (rset.next()) {
			numbBranches = rset.getInt(1);
		}

		return numbBranches;
	}
	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {

		ArrayList<T> newList = new ArrayList<T>();

		for (T element : list) {
			if (!newList.contains(element)) {

				newList.add(element);
			}
		}

		// return the new list
		return newList;
	}
	public static int getBookId(int bookChoice, Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT title FROM tbl_book");

		Statement stmt2 = conn.createStatement();
		ResultSet rset2 = stmt2.executeQuery("SELECT authorName FROM tbl_author, tbl_book WHERE authorID = bookId");

		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<String> authors = new ArrayList<String>();

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

		String query = "SELECT bookId FROM tbl_book WHERE title = ?";
		PreparedStatement stmt5 = conn.prepareStatement(query);
		stmt5.setString(1, titles.get(bookChoice-1));
		rset = stmt5.executeQuery();
		
		int bookId = 0;
		while(rset.next()) {
			bookId = rset.getInt(1);
		}
		

		return bookId;
		
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
	public void readBorrower(Connection conn) throws SQLException {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> addresses = new ArrayList<String>();
		ArrayList<String> phones = new ArrayList<String>();

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT name, address, phone FROM tbl_borrower");

		String name = null;
		String address = null;
		String phone = null;

		Scanner in = new Scanner(System.in);

		int i = 0;
		while (rset.next()) {
			name = rset.getString(1);
			address = rset.getString(2);
			phone = rset.getString(3);
			names.add(name);
			addresses.add(address);
			phones.add(phone);

		}
		int k = 0;
		for (; k < names.size(); k++) {
			System.out.println(k + 1 + ": " + names.get(k) + "_" + addresses.get(k) + "_" + phones.get(k));
		}

		System.out.println(k + 1 + " : Quit to previous");

		int userChoice = 0;
		try {
			userChoice = in.nextInt();
			in.nextLine();
			if (userChoice < 1 || userChoice >= k + 1) {
				return;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer");
			return;
		}
	}

	//Override due date method
	public void OverrideDueDate(Connection conn) throws SQLException {

		System.out.println("Please select the account to override");
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> addresses = new ArrayList<String>();
		ArrayList<String> phones = new ArrayList<String>();

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT name, address, phone FROM tbl_borrower");

		String name = null;
		String address = null;
		String phone = null;

		Scanner in = new Scanner(System.in);

		int i = 0;
		while (rset.next()) {
			name = rset.getString(1);
			address = rset.getString(2);
			phone = rset.getString(3);
			names.add(name);
			addresses.add(address);
			phones.add(phone);

		}
		int k = 0;
		for (; k < names.size(); k++) {
			System.out.println(k + 1 + ": " + names.get(k) + "_" + addresses.get(k) + "_" + phones.get(k));
		}

		System.out.println(k + 1 + " : Quit to previous");

		int userChoice = 0;
		try {
			userChoice = in.nextInt();
			in.nextLine();
			if (userChoice < 1 || userChoice >= k + 1) {
				return;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer");
			return;
		}

		String query = "SELECT cardNo FROM tbl_borrower WHERE name = ? AND address = ? AND phone = ?";
		PreparedStatement stmt2 = conn.prepareStatement(query);
		stmt2.setString(1, names.get(userChoice - 1));
		stmt2.setString(2, addresses.get(userChoice - 1));
		stmt2.setString(3, phones.get(userChoice - 1));
		rset = stmt2.executeQuery();
		System.out.println("Borrower cardNo located");

		int cardNoToUpdate = 0;
		while (rset.next()) {
			cardNoToUpdate = rset.getInt(1);
		}

		query = "SELECT * FROM tbl_book_loans WHERE cardNo = ? AND dateIn IS NULL";
		PreparedStatement stmt3 = conn.prepareStatement(query);
		stmt3.setInt(1, cardNoToUpdate);
		rset = stmt3.executeQuery();

		ArrayList<Integer> bookIds = new ArrayList<Integer>();
		ArrayList<Integer> branchIds = new ArrayList<Integer>();

		int j = 1;
		System.out.println("Please select the rental to extend:");
		while (rset.next()) {
			int bookId = rset.getInt(1);
			bookIds.add(bookId);

			int branchId = rset.getInt(2);
			branchIds.add(branchId);

			System.out.println(
					j + ": " + bookIdtoString(bookId, conn) + ", rented out by: " + branchIdtoString(branchId, conn));

			j++;

		}

		System.out.println(j + ": Quit to previous");

		int returnSelection = 0;
		try {
			returnSelection = in.nextInt();
			in.nextLine();
			if (returnSelection == j || returnSelection > j || returnSelection <= 0) {
				return;
			}
		} catch (Exception e) {
			System.out.println("Please enter an integer");
			return;
		}

		query = "SELECT dueDate FROM tbl_book_loans WHERE cardNo = ? AND bookId = ? AND branchId = ?";
		PreparedStatement stmt4 = conn.prepareStatement(query);
		stmt4.setInt(1, cardNoToUpdate);
		stmt4.setInt(2, bookIds.get(returnSelection - 1));
		stmt4.setInt(3, branchIds.get(returnSelection - 1));
		rset = stmt4.executeQuery();

		java.util.Date date = new Date();
		java.sql.Timestamp param = null;

		while (rset.next()) {
			param = rset.getTimestamp(1);
		}

		query = "UPDATE tbl_book_loans SET dueDate = ? WHERE cardNo = ? AND bookId = ? AND branchId = ?";
		PreparedStatement stmt5 = conn.prepareStatement(query);
		stmt5.setTimestamp(1, java.sql.Timestamp.valueOf(param.toLocalDateTime().plusDays(7)));
		stmt5.setInt(2, cardNoToUpdate);
		stmt5.setInt(3, bookIds.get(returnSelection - 1));
		stmt5.setInt(4, branchIds.get(returnSelection - 1));
		stmt5.executeUpdate();

	}

	
}
