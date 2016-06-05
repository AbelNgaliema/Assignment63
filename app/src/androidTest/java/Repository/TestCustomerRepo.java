package Repository;

import android.test.AndroidTestCase;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Domain.Customer;
import Domain.CustomerAddress;
import Domain.PersonalInformation;
import Factories.CustomerAddressFactory;
import Factories.CustomerFactory;
import Factories.PersonalInformationFactory;
import Repository.CustomerRepository.CustomerRepository;
import Repository.CustomerRepository.Implementation.CustomerRepositoryImpl;

/**
 * Created by AbelN on 23/04/2016.
 */
public class TestCustomerRepo  extends AndroidTestCase {

    private static final String TAG="CUSTOMER TEST";
    private Long id;
    public void testCreateReadUpdateDelete() throws Exception {
        CustomerRepository repo = new CustomerRepositoryImpl(this.getContext());
        Map<String, String> values = new HashMap<String,String>();
        values.put("address", "58 Victoria Rd. Southfield");
        values.put("city", "Cape Town");

        CustomerAddress customerAddress = CustomerAddressFactory.createCustomerAddress(values, 7800);

        //PersonalInfromation Object Created
        Map<String, String> values1 = new HashMap<String,String>();
        values1.put("name","Abel");
        values1.put("surname","Ngaliema");
        values1.put("idNumber","12223944");
        values1.put("email", "abeln@dipar.co.za");

        PersonalInformation personalInformation = PersonalInformationFactory.createPersonalInformation(values1, 02100000, 0210000000);


        Customer customer = CustomerFactory.createCustomer(customerAddress,personalInformation);

        Customer insertedEntity = repo.save(customer);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Customer> customers = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",customers.size()>0);

        //READ ENTITY
        Customer entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Customer updateEntity = new Customer.Builder()
                .id(entity.getId())
                .copy(entity)
                .build();
        repo.update(updateEntity);
        Customer newEntity = repo.findById(id);
      //  console.();
        Assert.assertEquals(TAG+ " UPDATE ENTITY",updateEntity.getId(),newEntity.getId());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Customer deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
