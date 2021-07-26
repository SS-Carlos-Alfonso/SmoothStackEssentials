package libraryManagementSystem;

import java.io.IOException;

import java.sql.*;
import java.util.*;

import oracle.sql.*;
import oracle.jdbc.driver.*;

public class LMSmain {

	public static int printMainMenu() {
		int result = 0;
		System.out.println("Welcome to the SS Library Management System. Which category of user are you?");
		System.out.println("\t1 :Librarian");
		System.out.println("\t2 :Administrator");
		System.out.println("\t3 :Borrower");
		System.out.println("\t4 :Exit Program");
		Scanner input = new Scanner(System.in);

		try {
			result = input.nextInt();
			input.nextLine();
		} catch (InputMismatchException e) {

		}
		return result;
	}
	public static int printLIB1() {
		int result = 0;
		System.out.println("1: Enter Branch you manage");
		System.out.println("2: Quit to previous");

		Scanner input = new Scanner(System.in);

		try {
			result = input.nextInt();
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Please enter a number 1-2");
		}

		if (result > 2) {
			result = 0;
		}
		return result;

	}
	public static int printLIB3() {
		int result = 0;
		System.out.println("1: Update the details of the library");
		System.out.println("2: Add copies if Book to Branch");
		System.out.println("3: Quit to previous");

		Scanner input = new Scanner(System.in);

		try {
			result = input.nextInt();
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Please enter a number 1-3");
		}

		if (result > 3) {
			result = 0;
		}
		return result;

	}

	public static void main(String[] args) throws SQLException, IOException {
		DriverManager.registerDriver((new oracle.jdbc.driver.OracleDriver()));
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newLMS", "Carlos", "Yankeekid108");
		Scanner in = new Scanner(System.in);

		int category = 0;
		int branchChoice = 999;
		boolean repeat = true;
		boolean repeat_lib2 = true;
		boolean repeat_lib3 = true;
		boolean repeat_lib3_option1 = true;
		boolean repeat_lib3_option2 = true;
		boolean repeat_borrow = true;
		boolean repeat_Borr1 = true;
		boolean repeat_admin = true;
		boolean repeat_crudbooks_1 = true;
		boolean repeat_adm_choose_author = true;
		boolean repeat_bookOrAuth = true;
		boolean repeat_crudGenre = true;

		Librarian lib = Librarian.getInstance();
		Administrator adm = Administrator.getInstance();
		Borrower bor = Borrower.getInstance();
		
		while (true) {

			category = printMainMenu();

			switch (category) {

			case 1:
				repeat = true;
				while (repeat) {
					int lib1_choice = 0;
					lib1_choice = printLIB1();
					if (lib1_choice == 1) {

						repeat_lib2 = true;
						while (repeat_lib2) {

							Statement stmt = conn.createStatement();
							ResultSet rset = stmt.executeQuery("select * from tbl_library_branch");

							ArrayList<Integer> branchId = new ArrayList<Integer>();
							ArrayList<String> branchName = new ArrayList<String>();
							ArrayList<String> location = new ArrayList<String>();

							int i = 1;
							while (rset.next()) {
								int id = rset.getInt(1);
								String name = rset.getString(2);
								String local = rset.getString(3);

								branchId.add(id);
								branchName.add(name);
								location.add(local);

								System.out.print(i + ": " + name + ", ");
								System.out.println(local);
								i++;
							}
							System.out.println(i + ": " + "Quit to previous");

							try {
								branchChoice = in.nextInt();
								in.nextLine();
							} catch (InputMismatchException e) {
								System.out.println("Please enter a number 1-" + i);
								in.next();
								branchChoice = i;
							}

							if (branchChoice < i) {
								// Save information on choice and open LIB3
								repeat_lib3 = true;
								while (repeat_lib3) {
								
									int lib3_choice = 0;
									lib3_choice = printLIB3();
									if (lib3_choice == 1) {
										repeat_lib3_option1 = true;
										while (repeat_lib3_option1) {
											String newBranchName;
											String newLocation;
											Scanner newIn = new Scanner(System.in);
											System.out.println("You have chosen to update the Branch with Branch id: "
													+ branchId.get(branchChoice - 1) + " and Branch Name: "
													+ branchName.get(branchChoice - 1));
											System.out.println("Enter quit at any prompt to cancel operation");

											System.out.println(
													"Please enter new branch name or enter N/A for no change:");

											try {

												newBranchName = newIn.nextLine();
												if (newBranchName.equals("quit") || newBranchName == "") {
													repeat_lib3_option1 = false;
													break;
												}
											} catch (Exception e) {
												System.out.println("Please enter a string");
												break;
											}

											System.out.println(
													"Please enter a new branch address or enter N/A for no change:");

											try {
												newLocation = newIn.nextLine();
												if (newLocation.equals("quit") || newLocation == "") {
													repeat_lib3_option1 = false;
													break;
												}
											} catch (Exception e) {
												System.out.println("Please enter a string");
												break;
											}

											if (newBranchName.isEmpty() && !newLocation.isEmpty()) {
												lib.updateBranchDetails(branchName.get(branchChoice - 1),
														location.get(branchChoice - 1),
														branchName.get(branchChoice - 1), newLocation, conn);
											} else if (!newLocation.isEmpty() && newBranchName.isEmpty()) {
												lib.updateBranchDetails(branchName.get(branchChoice - 1),
														location.get(branchChoice - 1), newBranchName,
														location.get(branchChoice - 1), conn);
											} else if (newLocation.isEmpty() && newBranchName.isEmpty()) {
												break;
											} else {
												lib.updateBranchDetails(branchName.get(branchChoice - 1),
														location.get(branchChoice - 1), newBranchName, newLocation,
														conn);
											}

											System.out.println("Successful Update");
											repeat_lib3_option1 = false;
											break;

										}

									} else if (lib3_choice == 2) {

										repeat_lib3_option2 = true;
										while (repeat_lib3_option2) {

											System.out
													.println("Pick the Book you want to add copies of to your branch:");

											int bookId = lib.displayBooks(conn);
										

											if (bookId != 0) {
												lib.getNumCopies(bookId, branchId.get(branchChoice-1), conn);

												System.out.println("Enter new number of copies: ");
												int newNumCopies = 0;

												try {
													newNumCopies = in.nextInt();
													in.nextLine();
												} catch (Exception e) {
													System.out.println("Please enter an integer");
												}

												lib.updateNumCopies(bookId, branchId.get(branchChoice-1), newNumCopies, conn);

												repeat_lib3_option2 = false;
												break;

											} else if (bookId == 0) {
												repeat_lib3_option2 = false;
												break;
											} else {
												System.out.println("Please enter an integer");
											}

											break;
										}

									} else if (lib3_choice == 3) {
										repeat_lib3 = false;
										break;
									} else if (lib3_choice == 0) {
										System.out.println("Please enter a number 1-3");

									} else {
										break;
									}
								}

							} else if (branchChoice == i) {
								repeat_lib2 = false;
								break;
							} else {
								System.out.println("Please enter a number 1-" + i);
								break;
							}
						}

					} else if (lib1_choice == 2) {
						repeat = false;
						break;
					} else if (lib1_choice == 0) {
						System.out.println("Please enter a number 1-2");
					}
				}

				break;

			case 2:
				repeat_admin = true;
				
				int admChoice = 0;
				while (repeat_admin) {

					admChoice = adm.displayChoices();

					if (admChoice == 0 || admChoice == 8) {
						repeat_admin = false;
						break;
					}

					switch (admChoice) {
					case 1:
						int crud_book_choice = 0;

						repeat_crudbooks_1 = true;
						while (repeat_crudbooks_1) {
							crud_book_choice = adm.CRUDbooks();

							if (crud_book_choice == 0 || crud_book_choice == 5) {
								repeat_crudbooks_1 = false;
								break;
							}

							switch (crud_book_choice) {
							case 1: // Add book
								repeat_bookOrAuth = true;
								while (repeat_bookOrAuth) {
									System.out.println("What would you like to add?");
									System.out.println("1: Book");
									System.out.println("2: Author");
									System.out.println("3: Quit to previous");

									int bookOrAuth = 0;
									try {
										bookOrAuth = in.nextInt();
										in.nextLine();
										if (bookOrAuth < 0 || bookOrAuth >= 3) {
											bookOrAuth = 0;
										}
									} catch (Exception e) {
										System.out.println("Please enter an integer 1-3");
										break;
									}

									if (bookOrAuth == 0) {
										repeat_bookOrAuth = false;
										break;
									}

									if (bookOrAuth == 1) {

										System.out.println("Please enter the title of the book you are adding");

										String title = in.nextLine();

										repeat_adm_choose_author = true;
										while (repeat_adm_choose_author) {
											int authorChoice = adm.chooseAuthor(conn);

											if (authorChoice == 0) {
												repeat_adm_choose_author = false;
												break;
											}
											System.out.println("Please select the genre of the book:");
											int genre = adm.chooseGenre(conn);

											if (genre == 0) {
												repeat_adm_choose_author = false;
												break;
											}

											int publishChoice = adm.choosePub(conn);

											if (publishChoice == 0) {
												repeat_adm_choose_author = false;
												break;
											}

											adm.addBook(title, authorChoice, genre, publishChoice, conn);
											repeat_adm_choose_author = false;
										}
									} else {
										System.out.println("Please enter the name of the author you wish to add");

										String name = in.nextLine();

										adm.addAuthor(name, conn);

									}

								}
								break;

							case 2: // Update a book

								System.out.println("Please select the book you would like to update");
								int size = adm.printOutAllBooks(conn);
								int updateSelection = 0;
								try {
									updateSelection = in.nextInt();
									in.nextLine();
									if (updateSelection == size + 1) {
										break;
									}
								} catch (Exception e) {
									System.out.println("Please enter an integer");
								}

								System.out.println("What would you like to update?:");
								System.out.println("1: Update title");
								System.out.println("2: Update author");
								System.out.println("3: Update genre");
								System.out.println("4: Update publisher");
								System.out.println("5: Quit to previous");

								int updateChoice = 0;
								try {
									updateChoice = in.nextInt();
									in.nextLine();
								} catch (Exception e) {
									System.out.println("Please enter an integer");
								}

								if (updateChoice == 1) {
									String newTitle = null;
									System.out.println("Please enter in a new title");

									newTitle = in.nextLine();

									adm.updateTitle(updateSelection, newTitle, conn);

									break;

								} else if (updateChoice == 2) {
									String newAuthor = null;
									int authId = adm.chooseAuthor(conn);

									adm.updateAuthor(authId, updateSelection, conn);
									break;
								} else if(updateChoice == 3){
									int newGenreId = 0;
									System.out.println("Please select a new genre");
									newGenreId = adm.chooseGenre(conn);
									adm.updateBookGenre(newGenreId, updateSelection, conn);
									
									break;
								}else if(updateChoice == 4) {
									int newPubId = 0;
									System.out.println("Please select a new publisher");
									newPubId = adm.choosePub(conn);
									adm.updateBookPub(newPubId, updateSelection, conn);
									
									break;
								}else {
									break;
								}
								
							case 3: // Delete a book/author

								System.out.println("What would you like to delete?");
								System.out.println("1: Book");
								System.out.println("2: Author");
								System.out.println("3: Quit to previous");

								int bookOrAuth = 0;
								try {
									bookOrAuth = in.nextInt();
									in.nextLine();

									if (bookOrAuth < 0 || bookOrAuth >= 3) {
										bookOrAuth = 0;
									}
								} catch (Exception e) {
									System.out.println("Please enter an integer 1-3");
									repeat_bookOrAuth = false;
									break;
								}

								if (bookOrAuth == 0) {
									repeat_bookOrAuth = false;
									continue;
								}

								if (bookOrAuth == 1) {
									System.out.println("Please select the book you would like to delete:");
									int totalSize = adm.printOutAllBooks(conn);

									int deletionChoice = 0;
									try {
										deletionChoice = in.nextInt();
										in.nextLine();
										if (deletionChoice < 0 || deletionChoice > totalSize) {
											deletionChoice = 0;
										}
									} catch (Exception e) {
										System.out.println("Please enter an integer ");
										break;
									}

									if (deletionChoice == 0) {
										repeat_bookOrAuth = false;
										break;
									}

									adm.deleteBook(deletionChoice, conn);
								} else if (bookOrAuth == 2) {
									System.out.println("Please select the author you would like to delete:");
									int totalSize = adm.printOutAllAuthors(conn);

									int deletionChoice = 0;
									try {
										deletionChoice = in.nextInt();
										in.nextLine();
										if (deletionChoice < 0 || deletionChoice > totalSize) {
											deletionChoice = 0;
										}
									} catch (Exception e) {
										System.out.println("Please enter an integer");
										break;
									}

									if (deletionChoice == 0) {
										repeat_bookOrAuth = false;
										break;
									}

									adm.deleteAuth(deletionChoice, conn);
									repeat_bookOrAuth = false;
									break;

								} else {
									break;
								}

								break;

							case 4: // Read book/authors

								repeat_bookOrAuth = true;
								while (repeat_bookOrAuth) {

									System.out.println("What would you like to read?");
									System.out.println("1: Books");
									System.out.println("2: Authors");
									System.out.println("3: Quit to Previous");

									bookOrAuth = 0;
									try {
										bookOrAuth = in.nextInt();
										in.nextLine();
										if (bookOrAuth < 0 || bookOrAuth >= 3) {
											bookOrAuth = 0;
										}
									} catch (Exception e) {
										System.out.println("Please enter an integer 1-3");
										break;
									}

									if (bookOrAuth == 0) {
										repeat_bookOrAuth = false;
										break;
									}

									if (bookOrAuth == 1) {
										int totalSize = adm.printOutAllBooks(conn);
										System.out.println("Please enter any number to go to previous selection");

										int bookRead = 0;
										try {

											bookRead = in.nextInt();
											in.nextLine();
											if (bookRead < 0 || bookRead >= totalSize) {
												bookRead = 0;
											}

										} catch (Exception e) {
											System.out.println("Please enter an integer");
											break;
										}

										if (bookRead == 0) {
											repeat_bookOrAuth = false;
											break;
										} else {
											continue;
										}

									} else if (bookOrAuth == 2) {
										int totalSize = adm.printOutAllAuthors(conn);
										System.out.println("Please enter any number to go to previous selection");

										int authRead = 0;
										try {

											authRead = in.nextInt();
											in.nextLine();
											if (authRead < 0 || authRead >= totalSize) {
												authRead = 0;
											}

										} catch (Exception e) {
											System.out.println("Please enter an integer");
											break;
										}

										if (authRead == 0) {
											repeat_bookOrAuth = false;
											break;
										} else {
											continue;
										}
									} else {
										break;
									}

								}
								break;
							}
						}

						break;
					case 2:
						int brud_genre_choice = 0;

						repeat_crudGenre = true;
						while (repeat_crudGenre) {
							brud_genre_choice = adm.CRUDGenre();

							if (brud_genre_choice == 0 || brud_genre_choice == 5) {
								repeat_crudbooks_1 = false;
								break;
							}

							switch (brud_genre_choice) {
							case 1: // Add genre
								System.out.println("What is your new genre called?");

								String newGenre = null;
								try {
									newGenre = in.nextLine();
								} catch (Exception e) {
									break;
								}

								adm.addGenre(newGenre, conn);

								break;

							case 2: // Update Genre i.e change the genre name, changing a specific books genre is
									// done in the CRUD books menu

								System.out.println("Please select the genre you would like to update");
								int userChoice = adm.chooseGenre(conn);

								if (userChoice == 0) {
									break;
								}

								System.out.println("What should the genre be called now?");

								newGenre = null;

								try {

									newGenre = in.nextLine();
								} catch (Exception e) {

									break;
								}

								adm.updateGenre(userChoice, newGenre, conn);
								break;

							case 3: // Delete Genre

								System.out.println("Please select the genre you would like to delete");
								userChoice = adm.chooseGenre(conn);

								if (userChoice == 0) {
									break;
								}

								adm.deleteGenre(userChoice, conn);
								break;

							case 4: // Read Genres

								System.out.println("Please press any key to return");
								adm.chooseGenre(conn);
								userChoice = 0;
								if (userChoice == 0) {
									break;
								}

								break;
							}
						}

						break;
					case 3:
						int crud_publis_choice = 0;

						repeat_crudGenre = true;
						while (repeat_crudGenre) {
							crud_publis_choice = adm.CRUDPublish();

							if (crud_publis_choice == 0 || crud_publis_choice == 5) {
								repeat_crudbooks_1 = false;
								break;
							}

							switch (crud_publis_choice) {
							case 1:// Add publisher

								System.out.println("Please enter the name of the publisher you are adding");

								String name = in.nextLine();

								repeat_adm_choose_author = true;
								while (repeat_adm_choose_author) {
									System.out.println("Please enter the publishers address");

									String address = in.nextLine();

									System.out.println("Please select the phonenumber of the publisher:");

									String phoneNumber = null;
									try {
										phoneNumber = in.nextLine();
									} catch (Exception e) {
										System.out.println("Please enter a valid phone number ");
										repeat_adm_choose_author = false;
										break;
									}

									adm.addPublisher(name, address, phoneNumber, conn);
									repeat_adm_choose_author = false;
									break;
								}
								break;
							case 2:// Update a publisher

								repeat_adm_choose_author = true;
								while (repeat_adm_choose_author) {

									int publishChoice = adm.choosePub(conn);

									if (publishChoice == 0) {
										repeat_adm_choose_author = false;
										break;
									}

									System.out.println("Please enter a new name for the publisher");
									String newName = null;
									try {
										newName = in.nextLine();
										if (newName.isEmpty()) {
											newName = "";
										}
									} catch (Exception e) {
										break;
									}

									System.out.println("Please enter a new address for the publisher");
									String newAddress = null;
									try {
										newAddress = in.nextLine();
										if (newAddress.isEmpty()) {
											newAddress = "";
										}
									} catch (Exception e) {
										break;
									}

									System.out.println("Please enter a new phonenumber for the publisher");
									String newPhoneNumber = null;
									try {
										newPhoneNumber = in.nextLine();
										if (newPhoneNumber.isEmpty()) {
											newPhoneNumber = "";
										}
									} catch (Exception e) {
										break;
									}

									adm.updatePub(publishChoice, newName, newAddress, newPhoneNumber, conn);
									repeat_adm_choose_author = false;

									break;
								}

								break;

							case 3: // Delete a publisher
								repeat_adm_choose_author = true;
								while (repeat_adm_choose_author) {

									int publishChoice = adm.choosePub(conn);

									if (publishChoice == 0) {
										repeat_adm_choose_author = false;
										break;
									}

									adm.deletePub(publishChoice, conn);
									repeat_adm_choose_author = false;

									break;
								}
								break;

							case 4:// Read publishers

								adm.choosePub(conn);
								// System.out.println("Please press any key to return");
								// String junk = in.nextLine();
								break;
							}

						}
						break;
					case 4:
						int crud_branch_choice = 0;

						repeat_crudbooks_1 = true;
						while (repeat_crudbooks_1) {
							crud_branch_choice = adm.CRUDBranches();

							if (crud_branch_choice == 0 || crud_branch_choice == 5) {
								repeat_crudbooks_1 = false;
								break;
							}

							switch (crud_branch_choice) {
							case 1: // Add branch;
								System.out.println("What is the name of this new branch:");
								String newBranchName = in.nextLine();

								System.out.println("What is the address of this new branch:");
								String newBranchAddress = in.nextLine();

								adm.addBranch(newBranchName, newBranchAddress, conn);

								break;

							case 2: // Update Branch
								System.out.println("Please choose the branch to update");
								Statement stmt = conn.createStatement();
								ResultSet rset = stmt.executeQuery("select * from tbl_library_branch");

								ArrayList<String> branchName = new ArrayList<String>();
								ArrayList<String> location = new ArrayList<String>();

								int i = 1;
								while (rset.next()) {

									String name = rset.getString(2);
									String local = rset.getString(3);

									branchName.add(name);
									location.add(local);

									System.out.print(i + ": " + name + ", ");
									System.out.println(local);
									i++;
								}
								System.out.println(i + ": " + "Quit to previous");

								int branchUpdateChoice = 0;
								try {
									branchUpdateChoice = in.nextInt();
									in.nextLine();
									if (branchUpdateChoice == i) {
										break;
									}
								} catch (Exception e) {
									System.out.println("Please enter an integer");
									break;
								}

								System.out.println("Enter new name:");

								newBranchName = in.nextLine();

								System.out.println("Enter new address:");
								newBranchAddress = in.nextLine();

								adm.updateBranch(newBranchName, newBranchAddress, branchUpdateChoice, conn);

								break;

							case 3: // Delete Branch
								System.out.println("Please choose the branch to delete");
								stmt = conn.createStatement();
								rset = stmt.executeQuery("select * from tbl_library_branch");

								branchName = new ArrayList<String>();
								location = new ArrayList<String>();

								i = 1;
								while (rset.next()) {

									String name = rset.getString(2);
									String local = rset.getString(3);

									branchName.add(name);
									location.add(local);

									System.out.print(i + ": " + name + ", ");
									System.out.println(local);
									i++;
								}
								System.out.println(i + ": " + "Quit to previous");

								int branchDeletionChoice = 0;
								try {
									branchDeletionChoice = in.nextInt();
									in.nextLine();
									if (branchDeletionChoice == i) {
										break;
									}
								} catch (Exception e) {
									System.out.println("Please enter an integer");
									break;
								}

								adm.deleteBranch(branchDeletionChoice, conn);

								break;

							case 4:// Read Branch
								System.out.println("Please enter any key to leave");
								stmt = conn.createStatement();
								rset = stmt.executeQuery("select * from tbl_library_branch");

								branchName = new ArrayList<String>();
								location = new ArrayList<String>();

								i = 1;
								while (rset.next()) {

									String name = rset.getString(2);
									String local = rset.getString(3);

									branchName.add(name);
									location.add(local);

									System.out.print(i + ": " + name + ", ");
									System.out.println(local);
									i++;
								}
								System.out.println(i + ": " + "Quit to previous");

								branchDeletionChoice = 0;
								try {
									branchDeletionChoice = in.nextInt();
									in.nextLine();
									break;
								} catch (Exception e) {
									System.out.println("Please enter an integer");
									break;
								}

							}

						}

						break;
					case 5:

						int crud_borrower_choice = 0;

						repeat_crudbooks_1 = true;
						while (repeat_crudbooks_1) {
							crud_borrower_choice = adm.CRUDBorrower();

							if (crud_borrower_choice == 0 || crud_borrower_choice == 5) {
								repeat_crudbooks_1 = false;
								break;
							}

							switch (crud_borrower_choice) {
							case 1: // Add borrower
								System.out.println("Please enter the name of the new borrower");
								String name = in.nextLine();

								System.out.println("Please enter the address of the new borrower");
								String address = in.nextLine();

								System.out.println("Please enter the phone number of the new borrower");
								String phoneNumber = in.nextLine();

								adm.addBorrower(name, address, phoneNumber, conn);

								break;

							case 2: // Update Borrower

								adm.updateBorrower(conn);

								break;

							case 3: // Delete borrower
								adm.deleteBorrower(conn);

								break;

							case 4:// Read borrower
								adm.readBorrower(conn);

								break;
							}

						}
						break;
					case 6: // Override Due Date

						adm.OverrideDueDate(conn);

						break;

					case 7: // Show all books in LMS

						adm.showAllBooksInfo(conn);

						break;
					}
					// break;
				}
				break;
			case 3:
				repeat_borrow = true;
				while (repeat_borrow) {
					int cardNo = 0;
					System.out.println("Enter your card Number - type 0 to exit");
					try {
						cardNo = in.nextInt();
						in.nextLine();
						if (cardNo == 0) {
							break;
						}
					} catch (Exception e) {
						System.out.println("Please enter an integer");
						break;
					}

					if (bor.checkCardNo(cardNo, conn)) {
						repeat_Borr1 = true;
						while (repeat_Borr1) {
							System.out.println("1:Check out a book");
							System.out.println("2:Return a book");
							System.out.println("3:Quit to previous");

							int boor1Choice = 0;
							try {
								boor1Choice = in.nextInt();
								in.nextLine();
								if (boor1Choice == 3) {
									break;
								}
							} catch (Exception e) {
								in.next();

							}

							if (boor1Choice == 1) {
								int option1_choice = bor.printBranches(conn);
								int totalBranches = bor.getNumBranches(conn);
								int bookChoice = 0;

								if (option1_choice == totalBranches + 1) {
									continue;
								}
								int totalTitles = bor.showBooksInBranch(option1_choice, conn);

								System.out.println("Pick the book you want to check out:");

								try {
									bookChoice = 0;
									bookChoice = in.nextInt();
									in.nextLine();

								} catch (Exception e) {
									System.out.println("Please enter an integer");
									break;
								}

								if (bookChoice == totalTitles) {

									continue;
								}

								bookChoice = bor.choiceToBookId(bookChoice, option1_choice, conn);

								if (bor.checkIfAlreadyOut(bookChoice, option1_choice, cardNo, conn)) {
									System.out.println(
											"You already have that book rented from this branch please return it before renting it again");
									continue;
								}

								bor.checkOutBook(bookChoice, option1_choice, cardNo, conn);
								System.out.println("Book successfully checked out");
								continue;

							} else if (boor1Choice == 2) {

								bor.returnBook(cardNo, conn);
								System.out.println("Book successfully returned");

							} else if (boor1Choice == 3) {
								repeat_Borr1 = false;
								break;

							} else {
								System.out.println("Please enter an integer 1-3");
							}

						}
					} else {
						System.out.println("Invalid card number please try again");
					}

				}

				break;

			case 4:

				conn.close();
				System.exit(0);
				break;

			default:
				System.out.println("Invalid input please enter in a number 1-4");
				break;

			}

		}

	}

}
