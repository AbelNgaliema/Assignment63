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
import Repository.CustomerRepository.CustomerRepository;
import Repository.CustomerRepository.Implementation.CustomerRepositoryImpl;
import Service.Implementation.CustomerService.Implementation.CustomerServiceImpl;

/**
 * Created by AbelN on 09/05/2016.
 */
public class TestCustomerService extends AndroidTestCase {
    private static final String TAG="CUSTOMER TEST1";
    private Long id;
    private CustomerServiceImpl customerService;
    private boolean isBound;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CustomerServiceImpl.CustomerServiceLocalBinder binder
                    = (CustomerServiceImpl.CustomerServiceLocalBinder) service;
            customerService = binder.getService();
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
        Intent intent = new Intent(this.getContext(), CustomerServiceImpl.class);


        GlobalContext.context = this.getContext();
        customerService = CustomerServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }//



    @Test

    public void testCrossValidation() throws Exception {

        BuyRepository repo = new BuyRepositoryImpl(this.getContext());
        CustomerRepository repoCustomer = new CustomerRepositoryImpl(this.getContext());

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

        Customer insertedEntity2 = repoCustomer.save(customer);
        long id2=insertedEntity2.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity2);

        Map<String, String> values5 = new HashMap<String,String>();
        values5.put("mode","Cash");
        values5.put("cashier","Bingo");

        //Buy object
        Buy buy = BuyFactory.createBuy(1,values5,book.getTitle(),customer.getPersonalInformation().getName());
        Buy insertedEntity = repo.save(buy);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

       Assert.assertEquals(true,customerService.crossValidation(customer.getPersonalInformation().getName()));


    }

    }
