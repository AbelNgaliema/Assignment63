package ServicesTest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import Config.GlobalContext;

import Domain.Author;
import Domain.Book;
import Domain.Publisher;
import Factories.AuthorFactory;
import Factories.BookFactory;
import Factories.PublisherFactory;
import Repository.BookRepository.BookRepository;
import Repository.BookRepository.Implementation.BookRepositoryImpl;
import Service.Implementation.BookService.Implementation.BookServiceImpl;

/**
 * Created by AbelN on 09/05/2016.
 */
public class TestBookService  extends AndroidTestCase {
    private static final String TAG="EMPLOYEE TEST1";
    private Long id;
    private BookServiceImpl bookService;
    private boolean isBound;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BookServiceImpl.BookServiceLocalBinder binder
                    = (BookServiceImpl.BookServiceLocalBinder) service;
            bookService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };
    @Override
    public void setUp() throws Exception {

        super.setUp();
        Intent intent = new Intent(this.getContext(), BookServiceImpl.class);
        GlobalContext.context = this.getContext();
        bookService = BookServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }



    @Test

    public void testCrossValidation() throws Exception {

        BookRepository repo = new BookRepositoryImpl(this.getContext());
        Map<String,String> values = new HashMap<String,String>();
        values.put("name","Boniface");
        values.put("surname", "Kabaso");
        Author author = AuthorFactory.createAuthor(values);
        Long id;

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
        Book book = BookFactory.creaBook(2,values3,2014,23,120.00,publisher,author);
        // CREATE

        Book insertedEntity = repo.save(book);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);
        //DUPLICATED


        Map<String,String> valuesDuplicated = new HashMap<String,String>();
        valuesDuplicated.put("name","steve");
        valuesDuplicated.put("surname", "stetue");
        Author authorDuplicated = AuthorFactory.createAuthor(valuesDuplicated);
        Long idDuplicateDuplicated;

        //Publisher created

        Map<String,String> values2Duplicated =  new HashMap<String,String>();
        values2Duplicated.put("name", "BangArtist");
        values2Duplicated.put("city","Cape Town");
        values2Duplicated.put("registration","c123idfr");

        Publisher publisherDuplicated = PublisherFactory.createPublisher(values2Duplicated);


        //Book Object
        Map<String,String> values3Duplicated =  new HashMap<String,String>();
        values3Duplicated.put("isbn", "88878-4445544");
        values3Duplicated.put("title","Testing");;
        Book book1 = BookFactory.creaBook(2,values3Duplicated,2014,23,120.00,publisherDuplicated,authorDuplicated);
        // CREATE

        Book insertedEntityDuplicated = repo.save(book1);
        id=insertedEntityDuplicated.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

       assertEquals(true,bookService.duplicateCheck(book1.getTitle()));



    }

}

