package Repository;

import java.util.Set;

/**
 * Created by AbelN on 20/04/2016.
 */
public interface Repository<E, ID> {

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}
