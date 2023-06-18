package Main;

import Book.Book;
import Book.BookManager;
import Person.Student;
import Person.StudentManager;
import Transaction.BookTransaction;
import Transaction.BookTransactionManager;
import Exception.StudentNotFoundException;
import Exception.BookNotFoundException;

import java.awt.print.PrinterGraphics;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        BookManager bookManager = new BookManager();
        StudentManager studentManager = new StudentManager();
        BookTransactionManager bookTransactionManager = new BookTransactionManager();
        do{
            System.out.println("Enter 1 if student\nEnter 2 if librarian\nEnter 3 to quit: ");
            choice = scanner.nextInt();

            if(choice ==1){ //if choice is student
                System.out.println("Enter student roll number: ");
                int roll = scanner.nextInt();
                try{
                    Student student =  studentManager.getStudent(roll);
                    if( student == null)
                        throw new StudentNotFoundException();
                    int student_choice;
                    do{
                        System.out.println("Enter 1 to view all books\nEnter 2 to search book by ISBN\nEnter 3 to search book by Subject\nEnter 4 to issue a book\nEnter 5 to return a book\nEnter 99 to exit");
                        student_choice = scanner.nextInt();
                        switch (student_choice){
                            case 1://view all books
                                System.out.println("All Books");
                                bookManager.viewAllBooks();
                                break;
                            case 2://search books by ISBN
                                System.out.println("Enter isbn of book to search");
                                int isbnSearch = scanner.nextInt();
                                scanner.nextLine();
                                Book bookByISBN = bookManager.searchBookByISBN(isbnSearch);
                                if(bookByISBN == null){
                                    System.out.println("No book with the given isbn found.");
                                }else{
                                    System.out.println(bookByISBN);
                                }
                                break;
                            case 3://view books by subject
                                System.out.println("Enter books subjects to search");
                                scanner.nextLine();
                                String subjectSearch = scanner.nextLine();
                                bookManager.viewBooksBySubject(subjectSearch);
//                                if(bookBySubject == null){
//                                    System.out.println("No book with the given subject found.");
//                                }else{
//                                    System.out.println(bookBySubject);
//                                }
                                break;
                            case 4://issue book
                                System.out.println("Enter the isbn of book to issue:");
                                int issueISBN = scanner.nextInt();
                                scanner.nextLine();
                                Book issuebook = bookManager.searchBookByISBN(issueISBN);
                                try{
                                    if(issuebook == null){
                                        throw  new BookNotFoundException();
                                    }
                                    if(issuebook.getAvailable_quantity()>0){
                                        if(bookTransactionManager.issueBook(issueISBN, roll)){
                                            issuebook.setAvailable_quantity(issuebook.getAvailable_quantity() - 1);
                                            System.out.println("Book  has been issued");
                                        }
                                    }else{
                                        System.out.println("There are no available qty. Try again later.");
                                    }
                                }catch (BookNotFoundException bookNotFoundException){
                                    System.out.println(bookNotFoundException);
                                }
                                break;
                            case 5://return a book
                                System.out.println("Enter the isbn of book to return:");
                                int returnISBN = scanner.nextInt();
                                scanner.nextLine();
                                Book returnbook = bookManager.searchBookByISBN(returnISBN);
                                try{
                                    if(returnbook == null){
                                        throw  new BookNotFoundException();
                                    }
                                        if(bookTransactionManager.returnBook(returnISBN, roll)){
                                            returnbook.setAvailable_quantity(returnbook.getAvailable_quantity() + 1);
                                            System.out.println("Book  has been returned");
                                        }else{
                                            System.out.println("Book couldn't be returned");
                                        }
                                }catch (BookNotFoundException bookNotFoundException){
                                    System.out.println(bookNotFoundException);
                                }
                                break;
                            case 99:
                                System.out.println("Thank you...");
                                break;
                            default:
                                System.out.println("Invalid choice");
                        }
                    }while(student_choice != 99);
                }catch(StudentNotFoundException studentNotFoundException){
                    System.out.println(studentNotFoundException);
                }
            }
            else if(choice ==2){//choice is librarian
                int librarian_choice;
                do{
                    System.out.println("Enter 11 to view all students\nEnter 12 to search student by roll number\nEnter 13 to register student\nEnter 14 to update studnet\nEnter 15 to delete a student");
                    System.out.println("Enter 21 to view all books\nEnter 22 to search book by isbn\nEnter 23 to add new book\nEnter 24 to update book\nEnter 25 to delete the book");
                    System.out.println("Enter 31 to view all transactions");
                    System.out.println("Enter 99 to exit");
                    librarian_choice = scanner.nextInt();
                    switch (librarian_choice){
                        case 11: //view all students
                            System.out.println("Viewing all students");
                            studentManager.viewAllStudents();
                            break;
                        case 12: //search students by roll
                            System.out.println("Enter roll number to search student: ");
                            int rollSearch = scanner.nextInt();
                            Student student = studentManager.getStudent(rollSearch);
                            if (student == null) {
                                System.out.println("Roll number does not match any student.");
                            }else{
                                System.out.println(student);
                            }
                            break;
                        case 13://add(register) a student
                            String name;
                            String email;
                            String phone;
                            String address;
                            String dob;
                            int roll;
                            int semester;
                            System.out.println("Enter Student Details");
                            scanner.nextLine();//because we have nextInt above

                            System.out.println("Enter name");
                            name = scanner.nextLine();

                            System.out.println("Enter email");
                            email = scanner.nextLine();

                            System.out.println("Enter phone");
                            phone = scanner.nextLine();

                            System.out.println("Enter address");
                            address = scanner.nextLine();

                            System.out.println("Enter dob");
                            dob = scanner.nextLine();

                            System.out.println("Enter roll");
                            roll = scanner.nextInt();
                            scanner.nextLine();

                            System.out.println("Enter semester");
                            semester = scanner.nextInt();
                            scanner.nextLine();

                            Student newStudent = new Student(name,  email,  phone,  address,  dob,  roll,  semester);
                            studentManager.addStudent(newStudent);
                            System.out.println("New student added successfully.\n"+newStudent);
                            break;

                        case 14://update a student
                            System.out.println("Enter the roll number of student to update");
                            int updateRoll = scanner.nextInt();
                            Student updateStudent = studentManager.getStudent(updateRoll);
                            try {
                                if (updateStudent == null) {
                                    throw new StudentNotFoundException();
                                }
                                System.out.println(updateStudent);
                                System.out.println("Enter Updated Student Details");
                                scanner.nextLine();//because we have nextInt above

                                System.out.println("Enter name");
                                name = scanner.nextLine();

                                System.out.println("Enter email");
                                email = scanner.nextLine();

                                System.out.println("Enter phone");
                                phone = scanner.nextLine();

                                System.out.println("Enter address");
                                address = scanner.nextLine();

                                System.out.println("Enter dob");
                                dob = scanner.nextLine();

                                System.out.println("Enter semester");
                                semester = scanner.nextInt();
                                scanner.nextLine();

                                studentManager.updateStudent(updateRoll, name, email, phone, address, dob, semester);
                                System.out.println("Update successful");
                            }catch (StudentNotFoundException studentNotFoundException){
                                System.out.println(studentNotFoundException);
                            }
                            break;
                        case 15: //delete student record
                            int deleteRoll;
                            System.out.println("Enter the roll number of student to delete");
                            deleteRoll = scanner.nextInt();
                            scanner.nextLine();
                            if(studentManager.deleteStudent(deleteRoll)){
                                System.out.println("Student record was deleted sucessfully");
                            }else{
                                System.out.println("No student record with given roll number");
                            }
                            break;
                        case 21://view all books
                            bookManager.viewAllBooks();
                            break;
                        case 22: //search book by isbn
                            System.out.println("Enter isbn of book to search");
                            int isbnSearch = scanner.nextInt();
                            scanner.nextLine();
                            Book book = bookManager.searchBookByISBN(isbnSearch);
                            if(book == null){
                                System.out.println("No book with the given isbn found.");
                            }else{
                                System.out.println(book);
                            }
                            break;
                        case 23:// add new book
                            int isbn;
                            String title;
                            String author;
                            String publisher;
                            String subject;
                            int available_quantity;
                            System.out.println("Enter book details");

                            System.out.print("Enter isbn: ");
                            isbn = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter title: ");
                            title = scanner.nextLine();


                            System.out.print("Enter author: ");
                            author = scanner.nextLine();

                            System.out.print("Enter publisher: ");
                            publisher = scanner.nextLine();

                            System.out.print("Enter subject: ");
                            subject = scanner.nextLine();

                            System.out.print("Enter available quantity: ");
                            available_quantity = scanner.nextInt();
                            scanner.nextLine();

                            Book newBook = new Book(isbn,title,author,publisher,subject,available_quantity);
                            bookManager.addBook(newBook);
                            System.out.println("Successfully added new book\n"+newBook);
                            break;
                        case 24://update book details
                            System.out.println("Enter the isbn of book to update");
                            int updateISBN = scanner.nextInt();
                            scanner.nextLine();
                            Book updateBook= bookManager.searchBookByISBN(updateISBN);
                            try {
                                if (updateBook == null) {
                                    throw new BookNotFoundException();
                                }
                                System.out.println(updateBook);
                                System.out.println("Enter Updated Book Details");

                                System.out.print("Enter title: ");
                                title = scanner.nextLine();


                                System.out.print("Enter author: ");
                                author = scanner.nextLine();

                                System.out.print("Enter publisher: ");
                                publisher = scanner.nextLine();

                                System.out.print("Enter subject: ");
                                subject = scanner.nextLine();

                                System.out.print("Enter available quantity: ");
                                available_quantity = Integer.parseInt(scanner.nextLine());

                                bookManager.updateBook(updateISBN,title,author,publisher,subject,available_quantity);
                                System.out.println("Update successful");
                            }catch (BookNotFoundException bookNotFoundException){
                                System.out.println(bookNotFoundException);
                            }
                            break;
                        case 25://delete a book record
                            int deleteISBN;
                            System.out.println("Enter the ISBN of book to delete");
                            deleteISBN = scanner.nextInt();
                            scanner.nextLine();
                            if(bookManager.deleteBook(deleteISBN)){
                                System.out.println("Book record was deleted successfully");
                            }else{
                                System.out.println("No book record with given isbn");
                            }
                            break;

                        case 31://view books transaction
                            System.out.println("Book Transactions");
                            bookTransactionManager.viewAllBookTransaction();
                            break;
                        case 99:
                            System.out.println("Thank you");
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }
                }while (librarian_choice !=99);
            }
        }while(choice !=3);
        scanner.close();
        studentManager.writeToFile();
        bookManager.writeToFile();
        bookTransactionManager.writeToFile();
    }
}