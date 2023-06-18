package Transaction;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookTransactionManager {
    ObjectOutputStream oos_bookTransactionManager = null;
    ObjectInputStream ois_bookTransactionManager = null;
    File bookTranascation_File;
    ArrayList<BookTransaction> bookTransactionArrayList = null;

    public BookTransactionManager(){
        bookTranascation_File = new File("BookTransactionsManager.dat");
        bookTransactionArrayList = new ArrayList<BookTransaction>();

        if(bookTranascation_File.exists()){
            try {
                ois_bookTransactionManager = new ObjectInputStream(new FileInputStream(bookTranascation_File));
                bookTransactionArrayList =  (ArrayList<BookTransaction>) ois_bookTransactionManager.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean issueBook(int isbn,int roll){
        int total_books_issued_Student =0;

        for(BookTransaction bookTransaction: bookTransactionArrayList){
            if(bookTransaction.getRoll() == roll && bookTransaction.getReturn_date() == null){
                total_books_issued_Student++;
            }
            if(total_books_issued_Student>=3){
                return false;
            }
        }
        String issueDate = new SimpleDateFormat("dd--mm-yyyy").format(new Date());
        BookTransaction bookTransaction = new BookTransaction(isbn, roll, issueDate, null);
        bookTransactionArrayList.add(bookTransaction);
        return true;
    }
    public boolean returnBook(int isbn,int roll){

        for(BookTransaction bookTransaction: bookTransactionArrayList){
            if(bookTransaction.getRoll() == roll && bookTransaction.getIsbn() == isbn && bookTransaction.getReturn_date() == null ){

                String returnDate = new SimpleDateFormat("dd--mm-yyyy").format(new Date());
                bookTransaction.setReturn_date(returnDate);
                return true;
            }
        }
        return false;
    }

    public void viewAllBookTransaction(){
        for(BookTransaction bookTransaction: bookTransactionArrayList){
            System.out.println(bookTransaction);
        }
    }

    public void writeToFile(){
        try {
            oos_bookTransactionManager = new ObjectOutputStream(new FileOutputStream(bookTranascation_File));
            oos_bookTransactionManager.writeObject(bookTransactionArrayList);
        }catch(IOException  ioException){
            ioException.printStackTrace();
        }
    }
}
