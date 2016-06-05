package com.example.abeln.myapplication.TestFactories;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import Domain.Author;
import Domain.Book;
import Domain.Publisher;
import Factories.AuthorFactory;
import Factories.BookFactory;
import Factories.PublisherFactory;

/**
 * Created by AbelN on 15/04/2016.
 */
public class TestBookFactory extends TestCase {



    @Test
    public void testCreate()
    {
        //author created
        Map<String,String> values = new HashMap<String,String>();
        values.put("name","Boniface");
        values.put("surname", "Kabaso");
        Author author = AuthorFactory.createAuthor(values);

        //Publisher created

        Map<String,String> values2 =  new HashMap<String,String>();
        values2.put("name", "SkyRock");
        values2.put("city","Cape Town");
        values2.put("registration","c123idfr");

        Publisher publisher = PublisherFactory.createPublisher(values2);


        //Book Object
        Map<String,String> values3 =  new HashMap<String,String>();
        values3.put("isbn", "88878-4445544");
        values3.put("title","Testing");;
        Book book = BookFactory.creaBook(1,values3,2014,23,120.00,publisher,author);


        assertEquals("88878-4445544", book.getIsbnNumber());
        assertEquals("Testing", book.getTitle());
        assertEquals(2014, book.getYear());
        assertEquals(23, book.getQuantity());
        assertEquals(120.00, book.getPrice());
        assertEquals("Boniface", book.getAuthor().getName());
        assertEquals("Kabaso", book.getAuthor().getSurname());
        assertEquals("SkyRock", book.getPublisher().getName());
        assertEquals("Cape Town", book.getPublisher().getCity());
        assertEquals("c123idfr", book.getPublisher().getRegistration());

    }

    @Test
    public void testUpdate()
    {
        //author created
        Map<String,String> values = new HashMap<String,String>();
        values.put("name","Boniface");
        values.put("surname", "Kabaso");
        Author author = AuthorFactory.createAuthor(values);

        //Publisher created

        Map<String,String> values2 =  new HashMap<String,String>();
        values2.put("name", "SkyRock");
        values2.put("city","Cape Town");
        values2.put("registration","c123idfr");

        Publisher publisher = PublisherFactory.createPublisher(values2);


        //Book Object
        Map<String,String> values3 =  new HashMap<String,String>();
        values3.put("isbn", "88878-4445544");
        values3.put("title","Testing");;
        Book  book = BookFactory.creaBook(1,values3,2014,23,120.00,publisher,author);

        //Updated

        Book newBook = new Book.Builder().copy(book).title("Testing edition2").build();

        System.out.print(newBook);
        assertEquals("88878-4445544", newBook.getIsbnNumber());
        assertEquals("Testing edition2", newBook.getTitle());
        assertEquals(2014, newBook.getYear());
        assertEquals(23, newBook.getQuantity());
        assertEquals(120.00, newBook.getPrice());
        assertEquals("Boniface", newBook.getAuthor().getName());
        assertEquals("Kabaso", newBook.getAuthor().getSurname());
        assertEquals("SkyRock", newBook.getPublisher().getName());
        assertEquals("Cape Town", newBook.getPublisher().getCity());
        assertEquals("c123idfr", newBook.getPublisher().getRegistration());


    }




}

