package Factories;

import java.util.Map;

import Domain.Author;

/**
 * Created by AbelN on 15/04/2016.
 */
public class AuthorFactory {

    public static Author createAuthor(Map<String,String> value){
        return new Author.Builder().surname(value.get("surname")).name(value.get("name")).build();
    }
}
