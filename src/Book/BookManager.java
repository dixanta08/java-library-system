package Book;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class BookManager {
//    ObjectOutputStream class is used to write objects to a stream. In this case, it will be used to write objects to a file.
    ObjectOutputStream oos_book = null;
//    ObjectInputStream class is used to read objects from a stream. In this case, it will be used to read objects from a file.
    ObjectInputStream ois_book = null;
    // File is used to create a representation of a file or directory on the file system.
    File book_file =null;

    ArrayList<Book> bookArrayList = null;

    public BookManager(){
        //create a represenation of books.dat
        book_file = new File("Books.dat");
        //initialize the studentArrayList
        bookArrayList = new ArrayList<Book>();

        if(book_file.exists()){
            try {
                // Create an ObjectInputStream to read objects from the file "Books.dat"
                ois_book = new ObjectInputStream(new FileInputStream(book_file));
                // Read and deserialize(convert bytestream into object) an ArrayList of Book objects from the ObjectInputStream
                bookArrayList =  (ArrayList<Book>) ois_book.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void addBook(Book book){
        bookArrayList.add(book);
    }
    public void viewAllBooks(){
        for(Book book:bookArrayList){
            System.out.println(book);
        }
    }

    public Book searchBookByISBN(int search_isbn){
        for(Book book:bookArrayList){
            if(book.getIsbn()==search_isbn){
                return book;
            }
        }
        return null;
    }

    public void viewBooksBySubject(String subject){
        for(Book book:bookArrayList){
            if(book.getSubject().equals(subject)){
                System.out.println(book);
            }
        }
    }

    public boolean updateBook(int updateISBN, String title, String author, String publisher, String subject, int available_quantity){
        ListIterator<Book> bookListIterator = (ListIterator<Book>) bookArrayList.listIterator();
        while(bookListIterator.hasNext()){
            Book book = bookListIterator.next();
            if(book.getIsbn()== updateISBN){
                book.setTitle(title);
               book.setAuthor(author);
               book.setPublisher(publisher);
               book.setSubject(subject);
               book.setAvailable_quantity(available_quantity);
                return true;
            }
        }
        return false;
    }


    public boolean deleteBook(int delete_isbn){
//        creating a listiterator for bookarraylist to traverse and typecasting itereator to  listiterator
        ListIterator<Book> bookListIterator = (ListIterator<Book>) bookArrayList.listIterator();
//        ListIterator<Book> bookListIterator = bookArrayList.listIterator();
        while(bookListIterator.hasNext()){
            Book book = bookListIterator.next();
            if(book.getIsbn()== delete_isbn){
                bookListIterator.remove();
                return true;
            }
        }
        return false;
    }
    public void writeToFile(){
        try {
            oos_book = new ObjectOutputStream(new FileOutputStream(book_file));
            oos_book.writeObject(bookArrayList);
        }catch(IOException  ioException){
            ioException.printStackTrace();
        }
    }
}
