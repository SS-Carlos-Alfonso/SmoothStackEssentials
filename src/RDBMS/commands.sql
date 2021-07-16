

/* Question 1
SELECT NoOfCopies
FROM ( (tbl_book NATURAL JOIN tbl_book_copies ) NATURAL JOIN tbl_library_branch )
WHERE Title='The Lost Tribe' AND BranchName='Sharpstown'
*/


/*Question 2
SELECT BranchName, NoOfCopies
FROM ( (tbl_book NATURAL JOIN tbl_book_copies ) NATURAL JOIN tbl_library_branch )
WHERE Title='The Lost Tribe' 
*/

/*Question 3
SELECT Name FROM tbl_borrower WHERE cardNo NOT IN (SELECT cardNo FROM tbl_book_loans);
*/


/*Question 4 I placed the due date as the date i did the assignment 

SELECT B.title, R.Name, R.address
FROM tbl_book B, tbl_borrower R, tbl_book_loans BL, tbl_library_branch LB
WHERE LB.branchName='Sharpstown' AND LB.branchId=BL.branchId AND
BL.dueDate='2021-07-16 14:00:45' AND BL.cardNo=R.cardNo AND BL.bookId=B.bookId 

*/


/*Question 5
SELECT L.branchName, COUNT(*)
FROM tbl_book_loans B, tbl_library_branch L
WHERE B.branchId = L.branchId
GROUP BY L.branchName 
*/

/*Question 6
SELECT B.name, B.address, COUNT(*)
FROM tbl_borrower B, tbl_book_loans L
WHERE B.cardNo = L.cardNo
GROUP BY B.cardNo
HAVING COUNT(*) > 5 
*/

/*Question 7
SELECT title, noOfCopies
FROM ( ( (tbl_author NATURAL JOIN tbl_book)
NATURAL JOIN tbl_book_copies)
NATURAL JOIN tbl_library_branch)
WHERE authorName = 'Stephen King' and branchName = 'Central'
*/