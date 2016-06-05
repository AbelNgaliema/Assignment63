package Factories;

import java.util.Map;

import Domain.Author;
import Domain.Book;
import Domain.Publisher;

/**
 * Created by AbelN on 15/04/2016.
 */
public class BookFactory {

    public static Book creaBook(long id, Map<String,String> value, int year, int qty, double price, Publisher publisher, Author author){
        return new Book.Builder().isbnNumber(value.get("isbn")).title(value.get("title")).year(year).quantity(qty).price(price).publisher(publisher).author(author).build();
    }
}
