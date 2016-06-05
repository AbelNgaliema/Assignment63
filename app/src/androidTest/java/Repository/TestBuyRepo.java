package Repository;

import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Domain.Author;
import Domain.Book;
import Domain.Buy;
import Domain.Customer;
import Domain.CustomerAddress;
import Domain.PersonalInformation;
import Domain.Publisher;
import Factories.AuthorFactory;
import Factories.BookFactory;
import Factories.BuyFactory;
import Factories.CustomerAddressFactory;
import Factories.CustomerFactory;
import Factories.PersonalInformationFactory;
import Factories.PublisherFactory;
import Repository.BuyRepository.BuyRepository;
import Repository.BuyRepository.Implementation.BuyRepositoryImpl;

/**
 * Created by AbelN on 24/04/2016.
 */
public class TestBuyRepo extends AndroidTestCase {
    private static final String TAG="BUY TEST";
    private Long id;


    @Test

    public void testCreateReadUpdateDelete() throws Exception {
        BuyRepository repo = new BuyRepositoryImpl(this.getContext());
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
        Book book = BookFactory.creaBook(1,values3, 2014, 23, 120.00, publisher, author);

        //Customer Address object created
        Map<String, String> values4 = new HashMap<String,String>();
        values4.put("address", "58 Victoria Rd. Southfield");
        values4.put("city", "Cape Town");

        CustomerAddress customerAddress = CustomerAddressFactory.createCustomerAddress(values4, 7800);

        //PersonalInfromation Object Created
        Map<String, String> values1 = new HashMap<String,String>();
        values1.put("name","Abel");
        values1.put("surname","Ngaliema");
        values1.put("idNumber","12223944");
        values1.put("email", "abeln@dipar.co.za");

        PersonalInformation personalInformation = PersonalInformationFactory.createPersonalInformation(values1, 02100000, 0210000000);


        Customer customer = CustomerFactory.createCustomer(customerAddress, personalInformation);
        Map<String, String> values5 = new HashMap<String,String>();
        values5.put("mode","Cash");
        values5.put("cashier","Bingo");

        //Buy object
        Buy buy = BuyFactory.createBuy(1,values5,book.getTitle(),customer.getPersonalInformation().getName());
        // CREATE

        Buy insertedEntity = repo.save(buy);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Buy> buys = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",buys.size()>0);

        //READ ENTITY
        Buy entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Buy updateEntity = new Buy.Builder()
                .id(entity.getId())
                .copy(entity)
                .mode("Cash")
                .build();
        repo.update(updateEntity);
        Buy newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Cash",newEntity.getMode());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Buy deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }






}

