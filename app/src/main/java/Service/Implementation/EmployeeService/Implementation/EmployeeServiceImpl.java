package Service.Implementation.EmployeeService.Implementation;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import Config.GlobalContext;

import Domain.Employee;
import Repository.EmployeeRepository.EmployeeRepository;
import Repository.EmployeeRepository.Implementation.EmployeeRepositoryImpl;
import Service.Implementation.EmployeeService.EmployeeService;

/**
 * Created by AbelN on 07/05/2016.
 */
public class EmployeeServiceImpl extends Service implements EmployeeService {

    EmployeeRepository repo;
    private static EmployeeServiceImpl service = null;

    public EmployeeServiceImpl()
    {
         repo = new EmployeeRepositoryImpl(GlobalContext.getAppContext());


    }
    private final IBinder localBinder = new EmployeeServiceLocalBinder();

    public static EmployeeServiceImpl getInstance() {
        if (service == null)
            service = new EmployeeServiceImpl();
        return service;
    }




    @Override
    public Employee findById(Long id) {
       return repo.findById(id);
    }

    @Override
    public Employee save(Employee entity) {

       return repo.save(entity);


    }

    @Override
    public Employee update(Employee entity) {
        repo.update(entity);
        return entity;
    }

    @Override
    public Employee delete(Employee entity) {
        repo.delete(entity);
        return  entity;
    }

    @Override
    public Set<Employee> findAll() {

        Set <Employee> employees;
        employees =   repo.findAll();
        return employees;

    }

    @Override
    public int deleteAll() {
        int result = repo.deleteAll();
        return result;
    }

    @Override
    public boolean isManager(long id) {
        Employee resultObject = repo.findById(id);
        if (resultObject.getPosition().equalsIgnoreCase("Manager"))
              return  true;
        else
            return  false;
    }

    public boolean loggedIn(Employee entity)
    {
        Set <Employee> employees;
        employees =   repo.findAll();

        for (Employee employee : employees)

        {
            if (employee.getSystemName().equalsIgnoreCase(entity.getSystemName())
                && employee.getPassword().equalsIgnoreCase(entity.getPassword()))
                return true;

        }
        return false;
    }

    public boolean userNameExists(String username)
    {
        Set <Employee> employees;
        employees =   repo.findAll();

        for (Employee employee : employees)

        {
            if (employee.getSystemName().equalsIgnoreCase(username))
                return true;

        }
        return false;
    }


    public boolean passwordValidation(String password)
    {

        if (password.length() >=  5)

            return  true;
        else
            return false;
    }
//

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class EmployeeServiceLocalBinder extends Binder {
        public EmployeeServiceImpl getService() {
            return EmployeeServiceImpl.this;
        }
    }

    }
