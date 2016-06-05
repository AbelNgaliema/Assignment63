package Repository;

import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Domain.Supplier;
import Factories.SupplierFactory;
import Repository.SupplierRepository.Implementation.SupplierRepositoryImpl;
import Repository.SupplierRepository.SupplierRepository;

/**
 * Created by AbelN on 07/05/2016.
 */
public class TestSupplierRepo extends AndroidTestCase {
    private static final String TAG="SUPPLIER TEST";
    private Long id;


    @Test

    public void testCreateReadUpdateDelete() throws Exception {
        SupplierRepository repo = new SupplierRepositoryImpl(this.getContext());
        Map<String,String> values = new HashMap<String,String>();


        values.put("regNumber","CDERIRIO");
        values.put("address", "Vic Road 58");
        values.put("name", "Ngaliema");


        //Object

        Supplier supplier = SupplierFactory.createSupplier(values,0213645214);
        // CREATE

        Supplier insertedEntity = repo.save(supplier);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Supplier> books = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",books.size()>0);

        //READ ENTITY
        Supplier entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Supplier updateEntity = new Supplier.Builder()
                .id(entity.getId())
                .copy(entity)
                .name("LOUISON")
                .build();
        repo.update(updateEntity);
        Supplier newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","LOUISON",newEntity.getName());


        // DELETE ENTITY
        repo.delete(updateEntity);
        Supplier deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);


    }






}
