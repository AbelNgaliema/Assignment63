package Repository;

import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Domain.Author;
import Domain.Book;
import Domain.Publisher;
import Factories.AuthorFactory;
import Factories.BookFactory;
import Factories.PublisherFactory;
import Repository.BookRepository.BookRepository;
import Repository.BookRepository.Implementation.BookRepositoryImpl;

/**
 * Created by AbelN on 21/04/2016.
 */
public class TestBookRepo extends AndroidTestCase {
    private static final String TAG="BOOK TEST";
    private Long id;


    @Test

    public void testCreateReadUpdateDelete() throws Exception {
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

        //READ ALL
        Set<Book> books = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",books.size()>0);

        //READ ENTITY
        Book entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Book updateEntity = new Book.Builder()
                .id(entity.getId())
                .copy(entity)
                .title("ABEL")
                .build();
        repo.update(updateEntity);
        Book newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","ABEL",newEntity.getTitle());

        // DELETE ENTITY
     //   repo.delete(updateEntity);
      //  Book deletedEntity = repo.findById(id);
     //  Assert.assertNull(TAG+" DELETE",deletedEntity);

    }






}
