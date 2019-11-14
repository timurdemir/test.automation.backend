package timur;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Response;
import timur.Entity.Book;
import timur.Api.IBook;
import timur.Api.RetrofitClient;

public class APITestCase {

    public Book getBookTrue() {
        Book book = new Book();
        book.setAuthor("timur");
        book.setTitle("test");
        return book;
    }

    public Book getBookNonAuthor() {
        Book book = new Book();
        book.setTitle("test");
        return book;
    }

    public Book getBookNonTitle() {
        Book book = new Book();
        book.setAuthor("timur");
        return book;
    }

    public Book getBookNonAnyField() {
        Book book = new Book();
        return book;
    }

    public Book getBookEmptyAuthor() {
        Book book = new Book();
        book.setAuthor("");
        book.setTitle("test");
        return book;
    }

    public Book getBookEmptyTitle() {
        Book book = new Book();
        book.setAuthor("timur");
        book.setTitle("");
        return book;
    }

    public Book getBookEmptyAllField() {
        Book book = new Book();
        book.setAuthor("");
        book.setTitle("");
        return book;
    }

    public Response<List<Book>> getBooksList() throws IOException {
        Call<List<Book>> call = RetrofitClient.getInstance().create(IBook.class).GetBooks();
        return call.execute();
    }

    public Response<Book> putBook(Book book) throws IOException {
        Call<Book> call = RetrofitClient.getInstance().create(IBook.class).PutBooks(book);
        return call.execute();
    }

    public Response<Book> getBook(int bookId) throws IOException {
        Call<Book> call = RetrofitClient.getInstance().create(IBook.class).GetBook(bookId);
        return call.execute();
    }
}