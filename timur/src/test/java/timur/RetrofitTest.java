package timur;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import retrofit.Response;
import timur.Entity.Book;

public class RetrofitTest extends APITestCase {

    @Test
    public void test_verify_1() throws IOException {
        Response<List<Book>> response = getBooksList();
        assertTrue("Success", response.code() == 200 && response.body().isEmpty());
    }

    @Test
    public void test_verify_2_1() throws IOException {
        Book book = getBookTrue();
        Response<Book> response = putBook(book);
        assertTrue("Sucess", response.code() == 200 && response.body() != null);
    }

    @Test
    public void test_verify_2_2() throws IOException {
        Book book = getBookNonAuthor();
        Response<Book> response = putBook(book);
        assertTrue("Field cannot be null.",
                response.code() == 400 && response.errorBody().string().equals("Field 'author' is required"));
    }

    @Test
    public void test_verify_2_3() throws IOException {
        Book book = getBookNonTitle();
        Response<Book> response = putBook(book);
        assertTrue("Field cannot be null.",
                response.code() == 400 && response.errorBody().string().equals("Field 'title' is required"));
    }

    @Test
    public void test_verify_2_4() throws IOException {
        Book book = getBookNonAnyField();
        Response<Book> response = putBook(book);
        assertTrue("Field cannot be null.", response.code() == 400
                && response.errorBody().string().equals("Field 'author' and 'title' are required"));
    }

    @Test
    public void test_verify_3_1() throws IOException {
        Book book = getBookTrue();
        Response<Book> response = putBook(book);
        assertTrue("Success", response.code() == 200 && response.body() != null);
    }

    @Test
    public void test_verify_3_2() throws IOException {
        Book book = getBookEmptyAuthor();
        Response<Book> response = putBook(book);
        assertTrue("Field can not be empty.",
                response.code() == 400 && response.errorBody().string().equals("Field 'author' cannot be empty."));
    }

    @Test
    public void test_verify_3_3() throws IOException {
        Book book = getBookEmptyTitle();
        Response<Book> response = putBook(book);
        assertTrue("Field can not be empty.",
                response.code() == 400 && response.errorBody().string().equals("Field 'title' cannot be empty."));
    }

    @Test
    public void test_verify_3_4() throws IOException {
        Book book = getBookEmptyAllField();
        Response<Book> response = putBook(book);
        assertTrue("Field can not be empty.", response.code() == 400
                && response.errorBody().string().equals("Field 'author' and 'title' cannot be empty."));
    }

    @Test
    public void test_verify_4() throws IOException {
        Book book = getBookTrue();
        Response<Book> response = putBook(book);
        // Book Id 0 eşitlememin sebebi int tipinin default değeri 0 olmasından
        // dolayıdır.
        assertTrue("Success", response.code() == 200 && response.body() != null && book.getId() == 0);
    }

    @Test
    public void test_verify_5() throws IOException {
        Book book = getBookTrue();
        Response<Book> responsePut = putBook(book);

        if (responsePut.code() == 200 && responsePut.body() != null) {
            Response<Book> responseGet = getBook(responsePut.body().getId());
            if (responseGet.code() == 200 && responseGet.body() != null) {
                assertSame("Success", book, responseGet.body());
            }
        }
    }

    @Test
    public void test_verify_6() throws IOException{
        Book book = getBookTrue();
        Response<Book> responsePut = putBook(book);

        if(responsePut.code() == 400 && responsePut.errorBody().string().equals("Another book with similar title and author already exists.")){
            Response<List<Book>> responseGet = getBooksList();
            if(responseGet.code() == 200 && !responseGet.body().isEmpty()){
                assertTrue("Success", responseGet.body().contains(book));
            }
        }
    }
}