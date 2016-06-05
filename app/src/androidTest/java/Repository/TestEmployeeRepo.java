package Repository;

import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Domain.Employee;
import Factories.EmployeeFactory;
import Repository.EmployeeRepository.EmployeeRepository;
import Repository.EmployeeRepository.Implementation.EmployeeRepositoryImpl;

/**
 * Created by AbelN on 07/05/2016.
 */
public class TestEmployeeRepo extends AndroidTestCase {
    private static final String TAG="EMPLOYEE TEST";
    private Long id;


    @Test

    public void testCreateReadUpdateDelete() throws Exception {
        EmployeeRepository repo = new EmployeeRepositoryImpl(this.getContext());
        Map<String,String> values = new HashMap<String,String>();


        values.put("name","Abel");
        values.put("surname", "Ngaliema");
        values.put("position", "Developer");
        values.put("password", "12345");
        values.put("systemName", "abeln");


        //Object

        Employee employee = EmployeeFactory.createEmployee(values,12000);

        assertNotNull(employee);
        // CREATE

       Employee insertedEntity = repo.save(employee);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Employee> books = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",books.size()>0);

        //READ ENTITY
        Employee entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Employee updateEntity = new Employee.Builder()
                .id(entity.getId())
                .copy(entity)
                .position("Manager")
                .build();
        repo.update(updateEntity);
        Employee newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Manager",newEntity.getPosition());


        // DELETE ENTITY
        repo.delete(updateEntity);
        Employee deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);


    }






}