package timur.Entity;

import java.io.Serializable;

public class Book implements Serializable {

    private int id;

    private String author;

    private String title;

    public Book() {

    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        else {
            Book book = (Book) obj;
            return this.getAuthor() == book.getAuthor() && this.getTitle() == book.getTitle();
        }
    }
}