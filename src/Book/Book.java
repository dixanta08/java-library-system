package Book;

import java.io.Serializable;

public class Book implements Serializable {
    private int isbn;
    private String title;
    private String author;
    private String publisher;
    private String subject;
    private int available_quantity;

    public Book(int isbn, String title, String author, String publisher, String subject, int available_quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.subject = subject;
        this.available_quantity = available_quantity;
    }

    public Book() {
        super();
    }

    //Getters And Setters
    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public int getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", subject='" + subject + '\'' +
                ", available_quantity=" + available_quantity +
                '}';
    }
}
