package Transaction;

import java.io.Serializable;

public class BookTransaction implements Serializable {
    private int isbn;
    private int roll;
    private String issue_date;
    private String return_date;

    public BookTransaction() {
        super();
    }

    public BookTransaction(int isbn, int roll, String issue_date, String return_date) {
        this.isbn = isbn;
        this.roll = roll;
        this.issue_date = issue_date;
        this.return_date = return_date;
    }


    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "BookTransaction{" +
                "isbn=" + isbn +
                ", roll=" + roll +
                ", issue_date='" + issue_date + '\'' +
                ", return_date='" + return_date + '\'' +
                '}';
    }
}
