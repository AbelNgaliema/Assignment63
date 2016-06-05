package Service.Implementation.EmployeeService;

import java.util.Set;

/**
 * Created by AbelN on 07/05/2016.
 */
public interface Service<E, ID> {

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();

    boolean isManager(long id);

    boolean loggedIn(E entity);
    boolean passwordValidation(String password);
    boolean userNameExists (String username);
    //

}
