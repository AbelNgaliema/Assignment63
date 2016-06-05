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
import java.util.Set;

import Config.GlobalContext;

import Domain.Employee;
import Factories.EmployeeFactory;
import Service.Implementation.EmployeeService.EmployeeService;
import Service.Implementation.EmployeeService.Implementation.EmployeeServiceImpl;

/**
 * Created by AbelN on 07/05/2016.
 */
public class TestEmployeeService  extends AndroidTestCase {
    private static final String TAG="EMPLOYEE TEST1";
    private Long id;
    private EmployeeServiceImpl employeeService;
    private boolean isBound;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            EmployeeServiceImpl.EmployeeServiceLocalBinder binder
                    = (EmployeeServiceImpl.EmployeeServiceLocalBinder) service;
            employeeService = binder.getService();
            isBound = true;
        }
//
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };
    @Override
    public void setUp() throws Exception {

        super.setUp();
        Intent intent = new Intent(this.getContext(), EmployeeServiceImpl.class);


        GlobalContext.context = this.getContext();
        employeeService = EmployeeServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);




    }



    @Test

    public void testCreateReadUpdateDelete() throws Exception {
        EmployeeService service = new EmployeeServiceImpl();
        Map<String,String> values = new HashMap<String,String>();

        values.put("name","Abel");
        values.put("surname", "Ngaliema");
        values.put("position", "Developer");
        values.put("systemName", "Abel");
        values.put("password", "Abel55");


        //Object

        Employee employee = EmployeeFactory.createEmployee(values,12000);



      Employee insertedEntity = service.save(employee);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
       Set<Employee> books = service.findAll();
        Assert.assertTrue(TAG+" READ ALL",books.size()>0);

        //READ ENTITY
        Employee entity = service.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
       Employee updateEntity = new Employee.Builder()
               .id(entity.getId())
               .copy(entity)
               .position("Manager")
             .build();
        service.update(updateEntity);
        Employee newEntity = service.findById(id);
       Assert.assertEquals(TAG+ " UPDATE ENTITY","Manager",newEntity.getPosition());

        //isManager
         assertEquals(true, service.isManager(newEntity.getId()));

        //loggedIN
        assertEquals(true,service.loggedIn(newEntity));

        //userAlreadyExists
        assertEquals(true,service.userNameExists(newEntity.getSystemName()));

        //passwordValidation

        assertEquals(true, service.passwordValidation(newEntity.getPassword()));


        // DELETE ENTITY
       // boolean manager  = service.isManager(updateEntity);

      //  Assert.assertEquals(true, manager);



    }






}
