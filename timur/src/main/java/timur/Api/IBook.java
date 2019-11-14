package timur.Api;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import timur.Entity.Book;

public interface IBook {
    
    @GET("/api/books/{book_id}")
    Call<Book> GetBook(@Path("book_id") int bookId);

    @GET("/api/books")
    Call<List<Book>> GetBooks();

    @PUT("/api/books")
    Call<Book> PutBooks(@Body Book book);
}